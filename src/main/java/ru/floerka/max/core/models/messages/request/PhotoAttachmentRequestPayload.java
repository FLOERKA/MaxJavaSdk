package ru.floerka.max.core.models.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class PhotoAttachmentRequestPayload extends MaxObject {

    private final @Param(require = false) String url;
    private final @Param(require = false) String token;
    private final @Param(require = false) Photos photos;

    private PhotoAttachmentRequestPayload(Builder builder) {
        url = builder.url;
        token = builder.token;
        photos = builder.photos;
    }


    @AllArgsConstructor
    @Getter
    public static class Photos extends MaxObject{
        private final @Param String token;
    }

    public static final class Builder {
        private String url;
        private String token;
        private Photos photos;

        public Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder photos(Photos photos) {
            this.photos = photos;
            return this;
        }

        public PhotoAttachmentRequestPayload build() {
            return new PhotoAttachmentRequestPayload(this);
        }
    }
}
