package org.thoughtcrime.securesms.biu;

import android.content.Context;
import android.content.SharedPreferences;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.util.JsonUtils;

import java.io.IOException;

public class BiuSettings {

    enum AppMode {Original, Anonymous}

    public static <T> T get(Context context, String key, Class<T> obj, T defaultValue) throws IOException {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String v = sharedPref.getString(key, "");
        if (v == "")
            return defaultValue;

        return JsonUtils.fromJson(v, obj);
    }

    public static void set(Context context, String key, Object value) throws IOException {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, JsonUtils.toJson(value));
        editor.commit();
    }

    public static AppMode getMode(Context context) throws IOException {
        return get(context, "mode", AppMode.class, AppMode.Original);
    }
}
