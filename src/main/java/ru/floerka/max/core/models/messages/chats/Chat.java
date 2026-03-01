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

    private Chat(Builder builder) {
        chatId = builder.chatId;
        chatType = builder.chatType;
        status = builder.status;
        title = builder.title;
        icon = builder.icon;
        lastEventTime = builder.lastEventTime;
        participantsCount = builder.participantsCount;
        ownerId = builder.ownerId;
        participants = builder.participants;
        isPublic = builder.isPublic;
        link = builder.link;
        description = builder.description;
        dialogWithUser = builder.dialogWithUser;
        chatMessageId = builder.chatMessageId;
        pinnedMessage = builder.pinnedMessage;
    }

    public static final class Builder {
        private long chatId;
        private ChatType chatType;
        private ChatStatus status;
        private String title;
        private Image icon;
        private long lastEventTime;
        private int participantsCount;
        private long ownerId;
        private String[] participants;
        private boolean isPublic;
        private String link;
        private String description;
        private UserWithPhoto dialogWithUser;
        private String chatMessageId;
        private Message pinnedMessage;

        public Builder() {
        }

        public Builder chatId(long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder chatType(ChatType chatType) {
            this.chatType = chatType;
            return this;
        }

        public Builder status(ChatStatus status) {
            this.status = status;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder icon(Image icon) {
            this.icon = icon;
            return this;
        }

        public Builder lastEventTime(long lastEventTime) {
            this.lastEventTime = lastEventTime;
            return this;
        }

        public Builder participantsCount(int participantsCount) {
            this.participantsCount = participantsCount;
            return this;
        }

        public Builder ownerId(long ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder participants(String... participants) {
            this.participants = participants;
            return this;
        }

        public Builder isPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder dialogWithUser(UserWithPhoto dialogWithUser) {
            this.dialogWithUser = dialogWithUser;
            return this;
        }

        public Builder chatMessageId(String chatMessageId) {
            this.chatMessageId = chatMessageId;
            return this;
        }

        public Builder pinnedMessage(Message pinnedMessage) {
            this.pinnedMessage = pinnedMessage;
            return this;
        }

        public Chat build() {
            return new Chat(this);
        }
    }
}
