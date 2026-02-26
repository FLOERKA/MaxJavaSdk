package ru.floerka.max.sdk.bots;

import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.http.impl.OkHttpMaxClient;
import ru.floerka.max.core.models.messages.update.Update;

public abstract class DefaultMaxBot {

    protected final OkHttpMaxClient client;
    @Getter
    private final String token;

    public DefaultMaxBot(String token) {
        client = new OkHttpMaxClient();
        this.token = token;
        setup();
    }

    protected abstract void setup();


    public <A extends MaxObject, B extends MaxObject> B execute(A first) {
        return client.execute(token, first);
    }



}
