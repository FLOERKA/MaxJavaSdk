package ru.floerka.max.core.api.queries;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Param {

    String name() default "";
    boolean require() default true;
    ParamType type() default ParamType.BODY;

}
