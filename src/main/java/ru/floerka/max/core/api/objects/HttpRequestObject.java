package ru.floerka.max.core.api.objects;

import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.floerka.max.core.api.exceptions.RequireArgMissException;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.json.JsonConverter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HttpRequestObject {


    private final Class<? extends MaxObject> responseClazz;
    private String url;
    private final HttpMethod method;
    private final @Nullable RequestBody requestBody;

    public HttpRequestObject(String url, HttpMethod method, Class<? extends MaxObject> responseClazz) {
        this.url = url;
        this.method = method;
        this.requestBody = null;
        this.responseClazz = responseClazz;
    }

    public HttpRequestObject(String url, HttpMethod method , RequestBody body, Class<? extends MaxObject> responseClazz) {
        this.url=url;
        this.method = method;
        this.requestBody = body;
        this.responseClazz = responseClazz;
    }

    public <T extends MaxObject> HttpRequestObject(T object) {


        ApiEndpoint annotation = object.getClass().getAnnotation(ApiEndpoint.class);

        this.method = annotation.method();
        this.responseClazz = annotation.response();

        try {
            MHttpUrl mHttpUrl = new MHttpUrl(annotation.path());
            HttpUrl.Builder urlBuilder = mHttpUrl.toOkHttpBuilder();

            JSONObject body = new JSONObject();
            Field[] fields = object.getClass().getDeclaredFields();

            List<String> patched = new ArrayList<>();
            for(Field field : fields) {
                if(field.isAnnotationPresent(Param.class)) {

                    Param paramAnnotation = field.getAnnotation(Param.class);

                    String name;
                    if(paramAnnotation.name().isEmpty())
                        name = convertFieldName(field.getName());
                    else name = paramAnnotation.name();

                    Object paramObject = field.get(object);

                    if(paramObject == null) {
                        if(paramAnnotation.require()) {
                            throw new RequireArgMissException(field.getName());
                        }
                        continue;
                    }

                    switch (paramAnnotation.type()) {
                        case DEFAULT,QUERY -> urlBuilder.addQueryParameter(name, String.valueOf(paramObject));
                        case BODY -> body.put(name, JsonConverter.getObject(paramObject));
                        case URL -> {
                            if(!isPatchedUrl(annotation.path()))
                                urlBuilder.addPathSegment(String.valueOf(paramObject));
                            else patched.add(String.valueOf(paramObject));
                        }
                    }
                }
            }

            this.url = urlBuilder.build().toString();

            if(isPatchedUrl(annotation.path())) {
                for(String patch : patched) {
                    url = url.replaceFirst("\\{\\?}", patch);
                }
            }

            if(!body.isEmpty())
                this.requestBody = createBody(body);
            else this.requestBody = null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isPatchedUrl(String check) {
        return check.contains("{?}");
    }

    private String convertFieldName(String name) {
        StringBuilder builder = new StringBuilder();
        for(char c : name.toCharArray()) {
            if(Character.isUpperCase(c)) {
                builder.append("_").append(c);
            }
        }
        return builder.toString().toLowerCase();
    }
    private RequestBody createBody(JSONObject json) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(json.toString(), mediaType);
    }
}
