package com.example.airloca.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Functions {


    public static final String LabelPermanentPersonne = "PermanentPersonne";

    public static void SaveSharedPreferences(Context context,String key, String value){

        SharedPreferences sharedPref = context.getSharedPreferences("AirLoca",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String LoadSharedPreferences(Context context, String key){
        SharedPreferences sharedPref = context.getSharedPreferences("AirLoca",MODE_PRIVATE);
        return sharedPref.getString(key,"");
    }

    }
