package ru.floerka.max.core.models.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.update.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@Getter
public class Subscription {

    private final @Param String url;
    private final @Param long time;
    private final @Param(require = false) String[] updateTypes;

    public List<Update.Type> getTypesAsList() {
        List<String> names = Arrays.asList(updateTypes);
        List<Update.Type> types = new ArrayList<>();
        names.forEach(n -> types.add(Update.Type.valueOf(n.toUpperCase(Locale.ROOT))));
        return types;
    }

}
