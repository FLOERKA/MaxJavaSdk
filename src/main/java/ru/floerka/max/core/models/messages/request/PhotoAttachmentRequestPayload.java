package ru.floerka.max.core.models.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
@Builder
public class PhotoAttachmentRequestPayload extends MaxObject {

    private final @Param(require = false) String url;
    private final @Param(require = false) String token;
    private final @Param(require = false) Photos photos;


    @AllArgsConstructor
    @Getter
    @Builder
    public static class Photos extends MaxObject{
        private final @Param String token;
    }

}
