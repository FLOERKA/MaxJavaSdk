package ru.floerka.max.core.models.request.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.response.subscriptions.UpdatesResponse;
import ru.floerka.max.core.models.upload.UploadFileResponse;
import ru.floerka.max.core.models.upload.UploadType;
import ru.floerka.max.core.models.upload.file.InputFile;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "uploads", method = HttpMethod.POST, response = UploadFileResponse.class)
public class UploadFileRequest extends MaxRequest {

    private final @Param UploadType type;
    private final @Param InputFile file;

    private UploadFileRequest(Builder builder) {
        type = builder.type;
        file = builder.file;
    }

    public static final class Builder {
        private UploadType type;
        private InputFile file;

        public Builder() {
        }

        public Builder type(UploadType type) {
            this.type = type;
            return this;
        }

        public Builder file(InputFile file) {
            this.file = file;
            return this;
        }

        public UploadFileRequest build() {
            return new UploadFileRequest(this);
        }
    }
}
