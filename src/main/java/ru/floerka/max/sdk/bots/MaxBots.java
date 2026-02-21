package ru.floerka.max.sdk.bots;

import ru.floerka.max.core.models.bot.BotInfo;
import ru.floerka.max.sdk.exceptions.CannotRegisterException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MaxBots {

    private static final List<DefaultMaxBot> registeredBots = new CopyOnWriteArrayList<>();

    public static void register(DefaultMaxBot bot) {
       BotInfo botInfo = bot.client.botInfo(bot.getToken());
       if(botInfo == null) {
           throw new CannotRegisterException("");
       }
       registeredBots.add(bot);
    }
}
