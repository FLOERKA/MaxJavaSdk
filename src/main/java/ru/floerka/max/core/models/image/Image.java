package ru.floerka.max.core.models.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class Image {

    private final @Param String url;
}
