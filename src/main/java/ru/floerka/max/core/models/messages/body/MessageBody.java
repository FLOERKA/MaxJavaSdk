package ru.floerka.max.core.models.messages.body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class MessageBody extends MaxObject {

    private final @Param String mid;
    private final @Param long seq;
    private final @Param String text;
    private final @Param(require = false) Attachment[] attachments;

    private MessageBody(Builder builder) {
        mid = builder.mid;
        seq = builder.seq;
        text = builder.text;
        attachments = builder.attachments;
    }


    public static final class Builder {
        private String mid;
        private long seq;
        private String text;
        private Attachment[] attachments;

        public Builder() {
        }

        public Builder mid(String mid) {
            this.mid = mid;
            return this;
        }

        public Builder seq(long seq) {
            this.seq = seq;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder attachments(Attachment[] attachments) {
            this.attachments = attachments;
            return this;
        }

        public MessageBody build() {
            return new MessageBody(this);
        }
    }
}
