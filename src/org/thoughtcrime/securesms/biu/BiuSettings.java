package org.thoughtcrime.securesms.biu;

import android.content.Context;
import android.content.SharedPreferences;

import org.thoughtcrime.securesms.R;

public class BiuSettings {

    public static String get(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    public static void set(Context context, String key, String value){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
