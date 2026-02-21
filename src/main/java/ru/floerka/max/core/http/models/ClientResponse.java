package ru.floerka.max.core.http.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@AllArgsConstructor
@Getter
public class ClientResponse implements AutoCloseable {

    private final boolean successful;
    private final int code;
    private final @Nullable String text;

    @Override
    public void close() throws Exception {

    }
}
