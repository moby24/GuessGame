package com.hackkrk.guessgame.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements ConvertibleToJson {

  public String username;
  public String token;
  public String password;
  
  public User() {
    // TODO Auto-generated constructor stub
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public JSONObject toJson() {
    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put("username", username);
      jsonObject.put("password", password);
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return jsonObject;
  }

  public static User fromJson(JSONObject jsonObject) {
    User user = new User();
    try {
      user.username = jsonObject.getString("username");
      user.token = jsonObject.getString("token");
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return user;
  }

}
