package ru.floerka.max.core.json;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static boolean isJson(String check) {
        try {
            new JSONObject(check);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
