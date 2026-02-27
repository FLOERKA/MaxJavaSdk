package ru.floerka.max.core.models.request.video;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.api.queries.ParamType;
import ru.floerka.max.core.models.response.video.VideoResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "videos", method = HttpMethod.GET, response = VideoResponse.class)
public class GetVideoInfoRequest extends MaxRequest {

    private final @Param(type = ParamType.QUERY, name = "videoToken") String videoToken;

    private GetVideoInfoRequest(Builder builder) {
        videoToken = builder.videoToken;
    }

    public static final class Builder {
        private String videoToken;

        public Builder() {
        }

        public Builder videoToken(String videoToken) {
            this.videoToken = videoToken;
            return this;
        }

        public GetVideoInfoRequest build() {
            return new GetVideoInfoRequest(this);
        }
    }
}
