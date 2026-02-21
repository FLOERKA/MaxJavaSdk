package ru.floerka.max.core.models.messages.body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class MessageBody {

    private final @Param String mid;
    private final @Param long seq;
    private final @Param String text;
    private final @Param(require = false) Attachment[] attachments;


}
