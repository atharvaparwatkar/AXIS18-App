package com.developer.kartikraut.axis;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.developer.kartikraut.axis.Events.CategoryClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    static Constants _instance;
    public static final String GOING_TO_EVENTS = "REGISTERED_EVENTS";

    Context context;
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;


    public static Constants instance(Context context) {
        if (_instance == null) {
            _instance = new Constants();
            _instance.configSessionUtils(context);
        }
        return _instance;
    }

    public static Constants instance() {
        return _instance;
    }

    public void configSessionUtils(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences("AppPreferences", Activity.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
    }

    public void storeValueString(String key, String value) {
        sharedPrefEditor.putString(key, value);
        sharedPrefEditor.commit();
    }

    public String fetchValueString(String key) {
        return sharedPref.getString(key, null);
    }

    public void storeValueBool(String key, Boolean value) {
        sharedPrefEditor.putBoolean(key,value);
        sharedPrefEditor.commit();
    }

    public boolean fetchValueBool(String key){
        return  sharedPref.getBoolean(key,false);
    }

    public void storeValueInt(String key, int value) {
        sharedPrefEditor.putInt(key,value);
        sharedPrefEditor.commit();
    }

    public int fetchValueInt(String key){
        return  sharedPref.getInt(key,0);
    }

}
