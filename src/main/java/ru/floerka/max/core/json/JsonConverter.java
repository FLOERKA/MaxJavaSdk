package ru.floerka.max.core.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.floerka.max.core.models.request.callback.CallbackAnswerRequest;

import java.lang.reflect.Field;

public class JsonConverter {

    public static <T> JSONObject getObject(T object) {

        if (object == null) return null;

        try {
            JSONObject main = new JSONObject();
            Class<?> objectClass = object.getClass();

            for (Field field : objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                Object result = field.get(object);

                if (result == null) continue;
                String name = convertName(field.getName());

                if (field.getType().isArray()) {
                    main.put(name, convertArrayToJson(result));
                } else if (isPrimitiveType(result.getClass())) {
                    main.put(name, result);
                } else {
                    main.put(name, getObject(result));
                }
            }
            return main;
        } catch (Exception e) {
            throw new RuntimeException("Object convert error: " + e.getMessage(), e);
        }
    }

    public static <T> T fromObject(JSONObject object, Class<T> objectClass) {
        try {
            T main = objectClass.getConstructor().newInstance();

            for (Field field : objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                String name = convertName(field.getName());
                Class<?> fieldType = field.getType();

                if (object.has(name) && !object.isNull(name)) {
                    Object value = object.get(name);

                    if (fieldType.isArray()) {
                        if (value instanceof JSONArray) {
                            field.set(main, fromJsonArray((JSONArray) value, fieldType.getComponentType()));
                        }
                    } else if (isPrimitiveType(value.getClass()) || value instanceof String) {
                        if (fieldType == long.class || fieldType == Long.class) {
                            field.set(main, ((Number) value).longValue());
                        } else if (fieldType == double.class || fieldType == Double.class) {
                            field.set(main, ((Number) value).doubleValue());
                        } else if (fieldType == float.class || fieldType == Float.class) {
                            field.set(main, ((Number) value).floatValue());
                        } else {
                            field.set(main, value);
                        }
                    } else if (value instanceof JSONObject) {
                        field.set(main, fromObject((JSONObject) value, fieldType));
                    }
                } else {
                    if (!fieldType.isPrimitive()) {
                        field.set(main, null);
                    }
                }
            }
            return main;
        } catch (Exception e) {
            throw new RuntimeException("Object convert error: ", e);
        }
    }

    private static String convertName(String field) {
        StringBuilder builder = new StringBuilder();
        for(char character : field.toCharArray()) {
            if(Character.isUpperCase(character))
                builder.append("_");
            builder.append(character);
        }
        return builder.toString().toLowerCase();
    }

    private static boolean isPrimitiveType(Class<?> check) {
        return check.equals(String.class) || check.equals(Boolean.class)
                || check.equals(boolean.class)
                || check.equals(Integer.class)
                || check.equals(int.class)
                || check.equals(Long.class)
                || check.equals(long.class)
                || check.equals(Float.class)
                || check.equals(float.class)
                || check.equals(Double.class)
                || check.equals(double.class);
    }

    private static JSONArray convertArrayToJson(Object array) {
        JSONArray jsonArray = new JSONArray();
        int length = java.lang.reflect.Array.getLength(array);

        for (int i = 0; i < length; i++) {
            Object element = java.lang.reflect.Array.get(array, i);

            if (element == null) {
                jsonArray.put(JSONObject.NULL);
            } else if (isPrimitiveType(element.getClass())) {
                jsonArray.put(element);
            } else {
                jsonArray.put(getObject(element));
            }
        }
        return jsonArray;
    }
    private static Object fromJsonArray(JSONArray jsonArray, Class<?> componentType) throws Exception {
        int length = jsonArray.length();
        Object array = java.lang.reflect.Array.newInstance(componentType, length);

        for (int i = 0; i < length; i++) {
            Object element = jsonArray.get(i);

            if (element instanceof JSONObject) {
                java.lang.reflect.Array.set(array, i, fromObject((JSONObject) element, componentType));
            } else if (element instanceof JSONArray) {
                java.lang.reflect.Array.set(array, i, fromJsonArray((JSONArray) element, componentType.getComponentType()));
            } else {
                java.lang.reflect.Array.set(array, i, element);
            }
        }
        return array;
    }
}
