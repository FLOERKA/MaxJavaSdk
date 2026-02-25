package ru.floerka.max.core.api.objects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiEndpoint {

    String path();
    HttpMethod method() default HttpMethod.GET;
    Class<? extends MaxObject> response();

}