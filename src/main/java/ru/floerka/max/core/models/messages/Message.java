package ru.floerka.max.core.models.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.user.User;
import ru.floerka.max.core.models.messages.body.MessageBody;
import ru.floerka.max.core.models.messages.linked.LinkedMessage;
import ru.floerka.max.core.models.messages.stat.MessageStat;

@AllArgsConstructor
@Getter
public class Message {

    private final @Param User sender;
    private final @Param Recipient recipient;
    private final @Param long timestamp;

    private final @Param(require = false) LinkedMessage link;
    private final @Param MessageBody body;
    private final @Param(require = false) MessageStat messageStat;
    private final @Param(require = false) String url;

}
