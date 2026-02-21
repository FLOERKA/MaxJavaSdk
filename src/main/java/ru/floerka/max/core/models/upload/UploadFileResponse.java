package ru.floerka.max.core.models.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class UploadFileResponse {

    private final @Param(require = false) String token;
}
