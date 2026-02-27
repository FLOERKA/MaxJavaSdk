package ru.floerka.max.core.models.request.bot;

import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.models.bot.BotInfo;

@ApiEndpoint(path = "bots", method = HttpMethod.GET, response = BotInfo.class)
public class BotInfoRequest extends MaxRequest {
}
