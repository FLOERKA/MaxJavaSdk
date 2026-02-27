package ru.floerka.max.sdk.bots;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.http.impl.OkHttpMaxClient;
import ru.floerka.max.core.models.bot.BotInfo;
import ru.floerka.max.core.models.messages.update.Update;
import ru.floerka.max.core.models.request.bot.BotInfoRequest;

import java.util.List;

@Slf4j
public abstract class DefaultMaxBot {

    protected final OkHttpMaxClient client;
    @Getter
    private final String token;

    public DefaultMaxBot(String token) {
        client = new OkHttpMaxClient();
        this.token = token;
        if(validate())
            setup();
    }

    private boolean validate() {
        BotInfoRequest request = new BotInfoRequest();
        BotInfo botInfo = execute(request);
        if(botInfo != null && botInfo.isBot()) return true;
        log.error("Error to validate bot with token '{}'", token);
        return false;
    }

    protected abstract void setup();
    public abstract void update( Update update);
    public abstract void update( List<Update> updates);


    public <A extends MaxObject, B extends MaxObject> B execute(A first) {
        return client.execute(token, first);
    }





}
