package ru.floerka.max.sdk.bots.impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.floerka.max.core.json.JsonConverter;
import ru.floerka.max.core.json.JsonUtils;
import ru.floerka.max.core.models.messages.update.Update;
import ru.floerka.max.core.models.request.subscriptions.AddSubscriptionsRequest;
import ru.floerka.max.core.models.response.subscriptions.AddSubscriptionsResponse;
import ru.floerka.max.sdk.bots.DefaultMaxBot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Slf4j
public class WebHookMaxBot extends DefaultMaxBot {

    @Setter
    private String url = null;
    @Setter
    private int port = 8080;
    private final String secret;
    private final String[] updateTypes;

    private Listener webListener = null;

    public WebHookMaxBot(String token, String url, int port, Update.Type... types) {
        super(token);
        this.secret = setupSecret();
        this.url = url;
        this.updateTypes = reGenerateTypes(types);
    }

    public WebHookMaxBot(String token, String url, int port) {
        super(token);
        this.secret = setupSecret();
        this.url = url;
        this.port = port;
        this.updateTypes = reGenerateTypes(Update.Type.values());
    }

    @Override
    protected void setup() {
        AddSubscriptionsRequest request = new AddSubscriptionsRequest(url, updateTypes, secret);
        AddSubscriptionsResponse response = execute(request);
        if(response.isSuccess()) {
            webListener = new Listener(url, port);
            try {
                webListener.create(body -> {
                    Update update = updateFromJson(body);
                    if(update != null) {
                        this.update(update);
                    }
                });
                log.info("Webhook successful registered and started.");
                return;
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }
        log.error("Webhook register failed. Check url or send a request to the github issues");
    }

    public @Nullable Update updateFromJson(String text) {
        if(JsonUtils.isJson(text)) {
            JSONObject object = new JSONObject(text);
            return JsonConverter.fromObject(object, Update.class);
        }
        return null;
    }

    private String setupSecret() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
    private String[] reGenerateTypes(Update.Type[] types) {
        String[] strings = new String[types.length];
        for(int i = 0; i < types.length; i++) {
            strings[i] = types[i].toString().toLowerCase();
        }
        return strings;
    }



    @AllArgsConstructor
    public static class Listener {

        private final String url;
        private final int port;

        protected void create(Mapper mapper) throws IOException {
            HttpServer server = HttpServer.create(new InetSocketAddress(url, port), 0);

            server.createContext("", exchange -> {
                if(exchange.getRequestMethod().equals("POST")) {
                    byte[] requestBytes = exchange.getRequestBody().readAllBytes();
                    String body = new String(requestBytes, StandardCharsets.UTF_8);
                    mapper.map(body);
                }
            });
        }

        public interface Mapper {
            void map(String response);
        }


    }

}
