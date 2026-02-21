package ru.floerka.max.core.models.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.TextFormat;

@AllArgsConstructor
@Getter
@Builder
public class NewMessageBody extends MaxObject {

    private final @Param(require = false) String text;
    private final @Param(require = false) AttachmentRequest[] attachments;
    private final @Param(require = false) NewMessageLink link;
    private final @Param boolean notify;
    private final @Param TextFormat format;



}
