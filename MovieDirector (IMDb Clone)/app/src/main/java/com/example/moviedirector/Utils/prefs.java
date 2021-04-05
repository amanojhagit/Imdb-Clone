package com.example.moviedirector.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class prefs {
    SharedPreferences sharedprefs;

    public prefs(Activity activity) {
        sharedprefs=activity.getPreferences(Context.MODE_PRIVATE);
    }
    public void setSearch(String search){
        sharedprefs.edit().putString("Search",search).commit();
    }
    public String getSearch(){
        return sharedprefs.getString("Search","american pie");
    }
}
