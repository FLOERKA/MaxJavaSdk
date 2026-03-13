package ru.floerka.max.core.models.messages.request.attachment.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class PhotoPayload extends AttachmentPayload {

    private final @Param(require = false) String url;
    private final @Param(require = false) String token;
    private final @Param(require = false) Photos photos;

    private PhotoPayload(Builder builder) {
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

        public PhotoPayload build() {
            return new PhotoPayload(this);
        }
    }
}
