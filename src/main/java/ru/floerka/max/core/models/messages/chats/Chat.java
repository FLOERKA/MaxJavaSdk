package ru.floerka.max.core.models.messages.chats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.image.Image;
import ru.floerka.max.core.models.messages.Message;
import ru.floerka.max.core.models.messages.enums.ChatStatus;
import ru.floerka.max.core.models.messages.enums.ChatType;
import ru.floerka.max.core.models.user.UserWithPhoto;

@AllArgsConstructor
@Getter
public class Chat extends MaxObject {

    private final @Param long chatId;
    private final @Param ChatType chatType;
    private final @Param ChatStatus status;
    private final @Param(require = false) String title;
    private final @Param(require = false) Image icon;
    private final @Param long lastEventTime;
    private final @Param int participantsCount;
    private final @Param long ownerId;
    private final @Param String[] participants;
    private final @Param boolean isPublic;
    private final @Param(require = false) String link;
    private final @Param(require = false) String description;
    private final @Param(require = false) UserWithPhoto dialogWithUser;
    private final @Param(require = false) String chatMessageId;
    private final @Param(require = false) Message pinnedMessage;

}
