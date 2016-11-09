package com.hermes.gps;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by imanbahmani on 9/24/16 AD.
 */
public class G {
    public static  Context context;
    public static ContentResolver contentResolver;
    public static Context getContext() {
        return context;
    }
    public static ContentResolver getContentResolver() {
        return contentResolver;
    }
    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {
        try {
            final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

            final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, customFontTypeface);
        } catch (Exception e) {
            Log.e("TypefaceUtil", "Can not set custom font " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride);
        }
    }
    public static String get_unique_id(Context context){
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)+"321";
        return deviceId;
    }
    public static void saveInt(Context context, String userKey, String userValue,String passKey,String passValue) {
//        SharedPreferences.Editor editor    = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//        editor.putString(userKey, userValue);
//        editor.putString(passKey, passValue);
//        editor.commit();
    }
}
