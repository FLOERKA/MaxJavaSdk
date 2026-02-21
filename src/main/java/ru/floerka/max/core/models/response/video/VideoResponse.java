package ru.floerka.max.core.models.response.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.models.messages.body.PhotoAttachmentPayload;
import ru.floerka.max.core.models.videos.VideoUrls;

@AllArgsConstructor
@Getter
public class VideoResponse {

    private final String token;
    private final @Nullable VideoUrls urls;
    private final @Nullable PhotoAttachmentPayload thumbnail;
    private final int width;
    private final int height;
    private final int duration;

}
