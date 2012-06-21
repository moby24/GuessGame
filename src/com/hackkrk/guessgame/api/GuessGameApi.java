package com.hackkrk.guessgame.api;

import com.hackkrk.guessgame.model.Answer;
import com.hackkrk.guessgame.model.ConvertibleToJson;
import com.hackkrk.guessgame.model.Riddle;
import com.hackkrk.guessgame.model.User;
import com.hackkrk.guessgame.utils.CurrentUser;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class GuessGameApi {

  private static String API_ROOT_HTTP = "http://hackkrk-guess.herokuapp.com";
  private Context mContext;

  HttpClient getHttpClient() {
    HttpClient client = new DefaultHttpClient();
    return client;
  }

  public GuessGameApi(Context context) {
    mContext = context;
  }

  //Request

  //{
  // "page": 2,
  // "per_page": 10
  //}
  //
  ////Response
  //{
  // "total": 47
  // "page": 2,
  // "page_count": 5,
  // "riddles": […] // Array of riddles presented as in `POST /riddles` response.
  //}

  public List<Riddle> getRiddles() {
    String string = get("/riddles");
    try {
      JSONObject jsonObject = new JSONObject(string);
      JSONArray jsonArray = jsonObject.getJSONArray("riddles");
      ArrayList<Riddle> listOfRiddles = new ArrayList<Riddle>();
      for (int i = 0; i < jsonArray.length(); i++) {
        listOfRiddles.add(Riddle.fromJson(jsonArray.getJSONObject(i)));
      }
      return listOfRiddles;

    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public Riddle sendRiddle(Riddle riddle) {
    String post = post(riddle, "/riddles");
    Toast.makeText(mContext, post, Toast.LENGTH_LONG).show();
    try {
      return Riddle.fromJson(new JSONObject(post));
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public boolean sendAnswer(Answer answer, String riddleId) {
    String post = post(answer, "/riddles/" + riddleId + "/answer");
    Log.d("xxx", post);
    try {
      Answer fromJson = Answer.fromJson(new JSONObject(post));
      return fromJson.correct;
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return false;
  }

  public User registerUser(String login, String password) {
    User user = new User(login, password);
    String post = post(user, "/users");
    try {
      return User.fromJson(new JSONObject(post));
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }

  public User loginUser(String login, String password) {
    String string = get("/user?username=" + login + "&password=" + password);
    Toast.makeText(mContext, string, Toast.LENGTH_LONG).show();
    try {
      return User.fromJson(new JSONObject(string));
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  String get(String path) {
    try {
      HttpClient httpClient = getHttpClient();
      HttpGet get = new HttpGet(API_ROOT_HTTP + path);

      String userToken = CurrentUser.getUserToken(mContext);
      get.addHeader("X-Auth-Token", userToken);

      HttpResponse execute = httpClient.execute(get);
      return EntityUtils.toString(execute.getEntity());

    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "";

  }

  String post(ConvertibleToJson json, String path) {
    HttpClient httpClient = getHttpClient();

    HttpPost post = new HttpPost(API_ROOT_HTTP + path);
    String userToken = CurrentUser.getUserToken(mContext);
    post.addHeader("X-Auth-Token", userToken);
    post.addHeader("Content-type", "application/json");

    try {
      String stringRepresentation = json.toJson().toString();
      StringEntity entity = new StringEntity(stringRepresentation);
      post.setEntity(entity);
      HttpResponse execute = httpClient.execute(post);
      String string = EntityUtils.toString(execute.getEntity());
      Toast.makeText(mContext, string, Toast.LENGTH_LONG).show();
      return string;

    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClientProtocolException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "";
  }

}
