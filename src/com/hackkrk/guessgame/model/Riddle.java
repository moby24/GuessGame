package com.hackkrk.guessgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

//"id": 1,
//"question": "What is it?",
//"photo_url": "http://f.cl.ly/items/1a3m2x1P3A0m1x3J031f/kitten.jpeg",
//"author": "sample_username",
//"created_at": "2012-06-21T18:20:12Z"
//"attempted_by": 7, // Number of all attempts to solve the riddle
//"solved_by": 4, // Number of users who solved the riddle
//"points": 2, // Points user earns for solving the riddle
//"solved": false

public class Riddle implements ConvertibleToJson, Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 2384148779905440288L;
  public String id;
  public String question;
  public String photo_url;
  public String author;
  public String created_at;
  public String attempter_by;
  public String solved_by;
  public String points;
  public Boolean solved;

  public String answer;
  public Bitmap photo;

  @Override
  public JSONObject toJson() {
    JSONObject jsonRepresentation = new JSONObject();
    try {
      jsonRepresentation.put("question", question);
      jsonRepresentation.put("answer", answer);
      
      ByteArrayOutputStream stream = new ByteArrayOutputStream();
      photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
      byte[] byteArray = stream.toByteArray();

      jsonRepresentation.put("photo", Base64.encodeToString(byteArray, Base64.DEFAULT));
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Log.d("xxx", jsonRepresentation.toString());
    return jsonRepresentation;
  }

  public static Riddle fromJson(JSONObject jsonObject) {
    Log.d("xxx", jsonObject.toString());
    Riddle riddle = new Riddle();
    try {
      riddle.photo_url = jsonObject.getString("photo_url");
      riddle.id = jsonObject.getString("id");
      riddle.question = jsonObject.getString("question");
      riddle.author = jsonObject.getString("author");
      riddle.solved = jsonObject.getBoolean("solved");
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return riddle;
  }
}
