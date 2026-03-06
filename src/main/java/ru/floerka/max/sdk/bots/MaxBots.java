package ru.floerka.max.sdk.bots;

import ru.floerka.max.core.models.messages.update.Update;
import ru.floerka.max.sdk.bots.impl.LongPollingMaxBot;
import ru.floerka.max.sdk.bots.impl.WebHookMaxBot;

import java.util.function.Consumer;

public class MaxBots {

    public static void fastBot(String token, long millisPeriod, Consumer<Update> updateConsumer) {
        LongPollingMaxBot maxBot = new LongPollingMaxBot(token, millisPeriod);
        maxBot.setOnUpdateHandler(updateConsumer);
    }
    public static void fastBot(String token, Consumer<Update> updateConsumer) {
        LongPollingMaxBot maxBot = new LongPollingMaxBot(token);
        maxBot.setOnUpdateHandler(updateConsumer);
    }
    public static void fastBot(String token, String url, Consumer<Update> updateConsumer) {
        WebHookMaxBot maxBot = new WebHookMaxBot(token, url, 8080);
        maxBot.setOnUpdateHandler(updateConsumer);
    }
}
