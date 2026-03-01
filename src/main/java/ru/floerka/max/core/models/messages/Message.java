package ru.floerka.max.core.models.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.user.User;
import ru.floerka.max.core.models.messages.body.MessageBody;
import ru.floerka.max.core.models.messages.linked.LinkedMessage;
import ru.floerka.max.core.models.messages.stat.MessageStat;

@AllArgsConstructor
@Getter
public class Message extends MaxObject {

    private final @Param User sender;
    private final @Param Recipient recipient;
    private final @Param long timestamp;

    private final @Param(require = false) LinkedMessage link;
    private final @Param MessageBody body;
    private final @Param(require = false) MessageStat messageStat;
    private final @Param(require = false) String url;

    private Message(Builder builder) {
        sender = builder.sender;
        recipient = builder.recipient;
        timestamp = builder.timestamp;
        link = builder.link;
        body = builder.body;
        messageStat = builder.messageStat;
        url = builder.url;
    }

    public static final class Builder {
        private User sender;
        private Recipient recipient;
        private long timestamp;
        private LinkedMessage link;
        private MessageBody body;
        private MessageStat messageStat;
        private String url;

        public Builder() {
        }

        public Builder sender(User sender) {
            this.sender = sender;
            return this;
        }

        public Builder recipient(Recipient recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder link(LinkedMessage link) {
            this.link = link;
            return this;
        }

        public Builder body(MessageBody body) {
            this.body = body;
            return this;
        }

        public Builder messageStat(MessageStat messageStat) {
            this.messageStat = messageStat;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
