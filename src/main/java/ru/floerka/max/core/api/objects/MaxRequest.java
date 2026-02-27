package ru.floerka.max.core.api.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public abstract class MaxRequest extends MaxObject{

    public ValidateMessage validate() {
        boolean success = true;
        StringJoiner message = new StringJoiner(", ");

        try {
            Class<?> clazz = this.getClass();
            Field[] fields = clazz.getFields();
            for(Field field : fields) {
                if(field.isAnnotationPresent(Param.class)) {
                    Param param = field.getAnnotation(Param.class);
                    if(param.require()) {
                        Object value = field.get(this);
                        if(value == null) {
                            success = false;
                            message.add(field.getName());
                        }
                    }
                }
            }
            String msg;
            if(!success)
                msg = "Parameters must necessarily be configured for the object " + clazz.getName() + ": " + message;
            else msg = "";
            return new ValidateMessage(success, msg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AllArgsConstructor
    @Getter
    public static class ValidateMessage {
        private final boolean success;
        private final String message;
    }
}
