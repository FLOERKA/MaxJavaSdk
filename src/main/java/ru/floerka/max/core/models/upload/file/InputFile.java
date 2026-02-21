package ru.floerka.max.core.models.upload.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;

import java.io.*;

@AllArgsConstructor
@Getter
public class InputFile {

    private final InputStream inputStream;
    private final String fileName;
    private final long contentLength;

    public static InputFile of(File file) throws FileNotFoundException {
        return new InputFile(new FileInputStream(file), file.getName(), file.length());
    }

    public RequestBody toRequestBody(MediaType contentType) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return contentLength;
            }

            @Override
            public void writeTo(@NotNull BufferedSink sink) throws IOException {
                try (Source source = Okio.source(inputStream)) {
                    sink.writeAll(source);
                }
            }
        };
    }
}