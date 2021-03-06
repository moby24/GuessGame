package com.hackkrk.guessgame.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Answer implements ConvertibleToJson {
  public String answer;
  public Boolean correct;

  @Override
  public JSONObject toJson() {
    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put("answer", answer);
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return jsonObject;
  }

  public static Answer fromJson(JSONObject jsonObject) {
    Answer answer = new Answer();
    try {
      answer.correct = jsonObject.getBoolean("correct");
      return answer;
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return answer;
  }
}
