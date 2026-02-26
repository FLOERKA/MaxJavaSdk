package ru.floerka.max.core.http;

import okhttp3.*;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.floerka.max.core.http.models.ClientResponse;

import java.io.IOException;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OkHttpService {

    private final OkHttpClient httpClient;
    private final ExecutorService executorService;

    public OkHttpService(int threads, int connectTimeout, int readTimeout, int callTimeout, @Nullable Proxy proxy, @Nullable Authenticator proxyAuth) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .callTimeout(callTimeout, TimeUnit.SECONDS);
        if(proxy != null)
            builder.proxy(proxy);
        else if(proxyAuth != null)
            builder.proxyAuthenticator(proxyAuth);

        httpClient = builder.build();

        executorService = Executors.newFixedThreadPool(threads);
    }

    public OkHttpService(int threads) {
        this(threads, 10, 10, 10, null, null);
    }

    public OkHttpService() {
        this(5);
    }

    public Request.Builder buildRequest(String url) {
        return new Request.Builder().url(url);
    }

    public RequestBody createBody(Map<String, String> objects) {
        FormBody.Builder builder = new FormBody.Builder();
        for(var entry : objects.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    public RequestBody createBody(String... text) {
        FormBody.Builder builder = new FormBody.Builder();
        for(int i =0; i < text.length-1; i+=2) {
            builder.add(text[i], text[i+1]);
        }
        return builder.build();
    }

    public RequestBody createBody(JSONObject json) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(json.toString(), mediaType);
    }

    @Nullable
    public ClientResponse getResponse(Request request) {
        Call call = httpClient.newCall(request);
        try(Response response = call.execute()) {
            int code = response.code();
            boolean successful = response.isSuccessful();
            String text = null;
            if(successful) {
                ResponseBody body = response.body();
                if(body != null) {
                    text = body.string();
                }
            }
            return new ClientResponse(successful,code,text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public CompletableFuture<ClientResponse> getResponseAsync(Request request) {
        CompletableFuture<ClientResponse> future = new CompletableFuture<>();
        executorService.execute(() -> {
            future.complete(getResponse(request));
        });
        return future;
    }

    public void cleanExecute(Request request) {
        try {
            httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void cleanExecuteAsync(Request request) {
        executorService.execute(() -> cleanExecute(request));
    }
}
