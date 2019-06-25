package com.example.fujimiya.clientpakarpsikologi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

public class SharedVariable {
    public static Locale locale;
    public static String activeLang = "no";

    public static void changeLang(Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        Configuration config = context.getApplicationContext().getResources().getConfiguration();

        if (settings.getString("locale","").equals("en")) {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            config.locale = locale;
            context.getResources().updateConfiguration(config,
                    context.getResources().getDisplayMetrics());
            settings.edit().putString("locale", "en").commit();
        }else if (settings.getString("locale","").equals("in")){
            Locale locale = new Locale("in");
            Locale.setDefault(locale);
            config.locale = locale;
            context.getResources().updateConfiguration(config,
                    context.getResources().getDisplayMetrics());
            settings.edit().putString("locale", "in").commit();
        }
    }
}
