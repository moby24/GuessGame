package com.hackkrk.guessgame.model;

import org.json.JSONException;
import org.json.JSONObject;

public class LaderboardUser {
  public String username;
  public String score;

  public static LaderboardUser fromJson(JSONObject json) {
    LaderboardUser user = new LaderboardUser();
    try {
      user.score = json.getString("score");
      user.username = json.getString("username");
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return user;
  }

}
