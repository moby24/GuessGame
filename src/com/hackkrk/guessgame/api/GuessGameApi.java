package com.hackkrk.guessgame.api;

import com.hackkrk.guessgame.model.ConvertibleToJson;
import com.hackkrk.guessgame.model.Riddle;
import com.hackkrk.guessgame.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class GuessGameApi {

  private static String API_ROOT_HTTP = "http://hackkrk-guess.herokuapp.com";

  HttpClient getHttpClient() {
    HttpClient client = new DefaultHttpClient();
    return client;
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
    try {
      return Riddle.fromJson(new JSONObject(post));
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }

  public User registerUser(String login, String password) {
    return null;
  }

  public User loginUser(String login, String password) {
    return null;
  }

  String get(String path) {
    try {
      HttpClient httpClient = getHttpClient();
      HttpGet get = new HttpGet(API_ROOT_HTTP + path);

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

    try {
      post.setEntity(new ByteArrayEntity(json.toJson().toString().getBytes("UTF8")));
      HttpResponse execute = httpClient.execute(post);
      return EntityUtils.toString(execute.getEntity());

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
