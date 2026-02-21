package ru.floerka.max.core.api.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.StringJoiner;

@AllArgsConstructor
@Getter
public abstract class MaxObject {


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

    public static <T extends MaxObject> T create(Class<T> clazz, Object... args) {
        try {
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            Constructor<?> targetConstructor = constructors[0];

            Object[] finalArgs = new Object[args.length];
            Class<?>[] parameterTypes = targetConstructor.getParameterTypes();
            Type[] genericTypes = targetConstructor.getGenericParameterTypes();

            for (Type type : genericTypes) {
                if (type instanceof ParameterizedType pType) {

                    Type rawType = pType.getRawType();

                    Type[] typeArgs = pType.getActualTypeArguments();

                    for (Type arg : typeArgs) {
                        System.out.println("Параметр ждет тип: " + arg.getTypeName());
                    }
                }
            }
        } catch (Exception e) {
            //
        }
    }

    @AllArgsConstructor
    @Getter
    public static class ValidateMessage {
        private final boolean success;
        private final String message;
    }

}
