package ru.floerka.max.core.models.videos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class VideoUrls {

    private final @Param(require = false) String mp4_1080;
    private final @Param(require = false) String mp4_720;
    private final @Param(require = false) String mp4_480;
    private final @Param(require = false) String mp4_360;
    private final @Param(require = false) String mp4_240;
    private final @Param(require = false) String mp4_144;
    private final @Param(require = false) String hls;
}
