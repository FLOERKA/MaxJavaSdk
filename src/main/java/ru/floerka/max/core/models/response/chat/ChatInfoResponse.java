package ru.floerka.max.core.models.response.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.models.image.Image;
import ru.floerka.max.core.models.messages.Message;
import ru.floerka.max.core.models.messages.enums.ChatStatus;
import ru.floerka.max.core.models.messages.enums.ChatType;
import ru.floerka.max.core.models.user.UserWithPhoto;

@AllArgsConstructor
@Getter
@Deprecated
public class ChatInfoResponse extends MaxObject {

    private final long chatId;
    private final ChatType type;
    private final ChatStatus status;
    private final @Nullable String title;
    private final @Nullable Image icon;
    private final Long lastEventTime;
    private final int participantsCount;
    private final long ownerId;
    private final String[] participants;
    private final boolean isPublic;
    private final @Nullable String link;
    private final @Nullable String description;
    private final @Nullable UserWithPhoto dialogWithUser;
    private final @Nullable String chatMessageId;
    private final @Nullable Message pinnedMessage;

}
