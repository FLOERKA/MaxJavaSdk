package ru.floerka.max.core.models.messages.request.attachment.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class ContactPayload extends AttachmentPayload {

    private final @Param(require = false) String name;
    private final @Param(require = false) Long contactId;
    private final @Param(require = false) String vcfInfo;
    private final @Param(require = false) String vcfPhone;

    private ContactPayload(Builder builder) {
        name = builder.name;
        contactId = builder.contactId;
        vcfInfo = builder.vcfInfo;
        vcfPhone = builder.vcfPhone;
    }


    public static final class Builder {
        private String name;
        private Long contactId;
        private String vcfInfo;
        private String vcfPhone;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder contactId(Long contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder vcfInfo(String vcfInfo) {
            this.vcfInfo = vcfInfo;
            return this;
        }

        public Builder vcfPhone(String vcfPhone) {
            this.vcfPhone = vcfPhone;
            return this;
        }

        public ContactPayload build() {
            return new ContactPayload(this);
        }
    }
}
