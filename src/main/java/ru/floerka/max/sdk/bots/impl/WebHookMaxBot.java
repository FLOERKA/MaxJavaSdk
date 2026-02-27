package ru.floerka.max.sdk.bots.impl;

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

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Slf4j
public abstract class WebHookMaxBot extends DefaultMaxBot {

    @Setter
    private String url = null;
    private final String secret;
    private final String[] updateTypes;

    public WebHookMaxBot(String token, String url, Update.Type... types) {
        super(token);
        this.secret = setupSecret();
        this.url = url;
        this.updateTypes = reGenerateTypes(types);
    }

    public WebHookMaxBot(String token, String url) {
        super(token);
        this.secret = setupSecret();
        this.url = url;
        this.updateTypes = reGenerateTypes(Update.Type.values());
    }

    @Override
    protected void setup() {
        AddSubscriptionsRequest request = new AddSubscriptionsRequest(url, updateTypes, secret);
        AddSubscriptionsResponse response = execute(request);
        if(response.isSuccess()) {
            log.info("Webhook successful registered and started.");
        } else {
            log.error("Webhook register failed. Check url or send a request to the github issues");
        }
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
    private String[] reGenerateTypes(Update.Type[] from) {
        Update.Type[] types = Update.Type.values();
        String[] strings = new String[types.length];
        for(int i = 0; i < types.length; i++) {
            strings[i] = types[i].toString().toLowerCase();
        }
        return strings;
    }

    public void update(Update update) {

    }
    public void update(List<Update> updates) {

    }

}
