package ru.floerka.max.sdk.bots.impl;

import ru.floerka.max.core.models.messages.update.Update;
import ru.floerka.max.sdk.bots.DefaultMaxBot;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

public abstract class WebHookMaxBot extends DefaultMaxBot {

    private String url = null;
    private final String secret;

    public WebHookMaxBot(String token) {
        super(token);
        this.secret = setupSecret();
    }

    @Override
    protected void setup() {

    }

    public abstract void update(Update update);
    public abstract void update(List<Update> updates);

    private String setupSecret() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
