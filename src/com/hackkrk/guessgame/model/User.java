package com.hackkrk.guessgame.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements ConvertibleToJson {

  public String username;
  public String token;
  public String password;

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

}
