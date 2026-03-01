package ru.floerka.max.core.models.messages.body.markup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class MarkupElement extends MaxObject {

    private final @Param String type;
    private final @Param int from;
    private final @Param int length;


    public Type getTypeAsEnum() {
        return Type.valueOf(type);
    }

    public enum Type {
        STRONG, EMPHASIZED, MONOSPACED, LINK, STRIKETHROUGH, UNDERLINE, USER_MENTION
    }
}
