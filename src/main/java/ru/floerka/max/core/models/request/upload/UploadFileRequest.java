package ru.floerka.max.core.models.request.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.upload.UploadType;
import ru.floerka.max.core.models.upload.file.InputFile;

@AllArgsConstructor
@Getter
public class UploadFileRequest extends MaxObject {

    private final @Param UploadType type;
    private final @Param InputFile file;

}
