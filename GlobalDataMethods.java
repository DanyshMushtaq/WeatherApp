package com.example.administrator.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;


public class Global_data_methods {
    public static final String DEF_VALUE = "";
    public static final String CITY = "_nocity";
    public static final String USER_NAME = "_user_name";
    public static final String PASSWORD = "_password";
    public static final String LAT = "_lat";
    public static final String LON = "_lon";

    public static void setStringValue(Context context, String key, String value){
        SharedPreferences.Editor ed = PreferenceManager.getDefaultSharedPreferences(context).edit();
        ed.putString(key,value);
        ed.commit();
    }
    public static String getStringValue(Context context, String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key,DEF_VALUE);
    }

    public static String getStringValueLat(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(LAT,"12.952659"); //,
    }

    public static String getStringValueLon(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(LON,"77.606564"); //,
    }

   /* public static void setIntValue(Context context, String key, int value){
        SharedPreferences.Editor ed = PreferenceManager.getDefaultSharedPreferences(context).edit();
        ed.putInt(key,value);
        ed.commit();
    }
    public static int getIntValue(Context context, String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(key, DEF_VALUE_INT);
    }*/
    public static void displayToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

   }
