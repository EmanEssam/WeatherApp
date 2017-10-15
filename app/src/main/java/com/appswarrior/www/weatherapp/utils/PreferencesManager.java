package com.appswarrior.www.weatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Eman Essam on 15/10/2017.
 */

public class PreferencesManager {
    private static final String PREF_NAME = "weather_info";
    private static final String PLACE_KEY_VALUE = "place_name";
    private static final String TEMP_KEY_VALUE = "temperature";
    private static final String DESC_KEY_VALUE = "description";
    private static PreferencesManager sInstance;
    private SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setPlaceName(String placeName) {
        mPref.edit()
                .putString(PLACE_KEY_VALUE, placeName)
                .apply();
    }

    public void setTemp(String temp) {
        mPref.edit()
                .putString(TEMP_KEY_VALUE, temp)
                .apply();
    }

    public void setDesc(String desc) {
        mPref.edit()
                .putString(DESC_KEY_VALUE, desc)
                .apply();
    }

    public String getPlaceName() {
        return mPref.getString(PLACE_KEY_VALUE, "");
    }

    public String getTemp() {
        return mPref.getString(TEMP_KEY_VALUE, "");
    }

    public String getDesc() {
        return mPref.getString(DESC_KEY_VALUE, "");
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .apply();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}
