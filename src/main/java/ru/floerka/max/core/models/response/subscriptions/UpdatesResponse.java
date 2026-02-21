package ru.floerka.max.core.models.response.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.models.messages.update.Update;

@AllArgsConstructor
@Getter
public class UpdatesResponse {

    private final Update[] updates;
    private final @Nullable Long marker;
}
