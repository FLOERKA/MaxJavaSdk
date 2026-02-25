package ru.floerka.max.sdk.bots;

import lombok.Getter;
import ru.floerka.max.core.http.impl.OkHttpMaxClient;
import ru.floerka.max.core.models.request.subscriptions.AddSubscriptionsRequest;
import ru.floerka.max.core.models.request.subscriptions.UpdatesRequest;

public class DefaultMaxBot {

    protected final OkHttpMaxClient client;
    @Getter
    private final String token;

    public DefaultMaxBot(String token) {
        client = new OkHttpMaxClient();
        this.token = token;
    }


    public void execute() {
    }


}
