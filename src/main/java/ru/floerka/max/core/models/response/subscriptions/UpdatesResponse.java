package ru.floerka.max.core.models.response.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.models.messages.update.Update;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public class UpdatesResponse extends MaxObject {

    private final Update[] updates;
    private final @Nullable Long marker;


    public List<Update> getUpdatesAsList() {
        return Arrays.asList(updates);
    }
}
