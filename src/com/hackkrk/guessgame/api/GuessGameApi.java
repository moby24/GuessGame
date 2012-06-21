package com.hackkrk.guessgame.api;

import com.hackkrk.guessgame.model.ConvertibleToJson;
import com.hackkrk.guessgame.model.Riddle;
import com.hackkrk.guessgame.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class GuessGameApi {

  private static String API_ROOT_HTTP = "http://hackkrk-guess.herokuapp.com";

  HttpClient getHttpClient() {
    HttpClient client = new DefaultHttpClient();
    return client;
  }

  public List<Riddle> getRiddles() {
    return null;
  }

  public User registerUser(String login, String password) {
    return null;
  }

  public User loginUser(String login, String password) {
    return null;
  }

  int post(ConvertibleToJson json, String path) {
    HttpClient httpClient = getHttpClient();

    HttpPost post = new HttpPost(API_ROOT_HTTP + path);

    try {
      post.setEntity(new ByteArrayEntity(json.toJson().toString().getBytes("UTF8")));
      HttpResponse execute = httpClient.execute(post);
      return execute.getStatusLine().getStatusCode();

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
    return -1;
  }

}
