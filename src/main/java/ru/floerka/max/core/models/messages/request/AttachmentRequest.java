package ru.floerka.max.core.models.messages.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AttachmentRequest {


    public enum Type {
        IMAGE,VIDEO,AUDIO,FILE,STICKER,CONTACT,INLINE_KEYBOARD,SHARE,LOCATION
    }
}
