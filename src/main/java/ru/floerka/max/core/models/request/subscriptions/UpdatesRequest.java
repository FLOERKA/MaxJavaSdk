package ru.floerka.max.core.models.request.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class UpdatesRequest extends MaxObject {

    private final @Param(require = false) Integer limit;
    private final @Param(require = false) Integer timeout;
    private final @Param(require = false) Long marker;
    private final @Param(require = false) String[] types;

}
