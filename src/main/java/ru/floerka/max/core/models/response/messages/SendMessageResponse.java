package ru.floerka.max.core.models.response.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.models.messages.Message;

@AllArgsConstructor
@Getter
public class SendMessageResponse {

    private final Message message;
}
