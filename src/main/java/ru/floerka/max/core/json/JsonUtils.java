package ru.floerka.max.core.json;

import lombok.experimental.UtilityClass;
import org.json.JSONException;
import org.json.JSONObject;

@UtilityClass
public class JsonUtils {

    public boolean isJson(String check) {
        try {
            new JSONObject(check);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
