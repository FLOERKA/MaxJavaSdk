package ru.floerka.max.core.models.request.callback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
@Builder
@ApiEndpoint(path = "callback/answer", method = HttpMethod.POST)
public class CallbackAnswerRequest extends MaxObject {
    private final @Param String callbackId;
    private final @Param String body;

}
