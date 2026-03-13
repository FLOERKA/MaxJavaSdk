package ru.floerka.max.core.models.messages.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.TextFormat;
import ru.floerka.max.core.models.messages.request.attachment.AttachmentRequest;

@AllArgsConstructor
@Getter
public class NewMessageBody extends MaxObject {

    private final @Param(require = false) String text;
    private final @Param(require = false) AttachmentRequest[] attachments;
    private final @Param(require = false) NewMessageLink link;
    private final @Param boolean notify;
    private final @Param TextFormat format;

    private NewMessageBody(Builder builder) {
        text = builder.text;
        attachments = builder.attachments;
        link = builder.link;
        notify = builder.notify;
        format = builder.format;
    }


    public static final class Builder {
        private String text;
        private AttachmentRequest[] attachments;
        private NewMessageLink link;
        private boolean notify;
        private TextFormat format;

        public Builder() {
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder attachments(AttachmentRequest... attachments) {
            this.attachments = attachments;
            return this;
        }

        public Builder link(NewMessageLink link) {
            this.link = link;
            return this;
        }

        public Builder notify(boolean notify) {
            this.notify = notify;
            return this;
        }

        public Builder format(TextFormat format) {
            this.format = format;
            return this;
        }

        public NewMessageBody build() {
            return new NewMessageBody(this);
        }
    }
}
