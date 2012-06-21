package com.hackkrk.guessgame.utils;

import com.hackkrk.guessgame.model.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CurrentUser {

  public static String NAME;
  public static String TOKEN;

  public static void setCurrenUser(Context context, User user) {
    SharedPreferences defaultSharedPreferences = PreferenceManager
        .getDefaultSharedPreferences(context);
    defaultSharedPreferences.edit().putString(TOKEN, user.token).commit();
  }
  
  public static String getUserToken(Context context){
    
  SharedPreferences defaultSharedPreferences = PreferenceManager
      .getDefaultSharedPreferences(context);
  return defaultSharedPreferences.getString(TOKEN, null);
  }

}
