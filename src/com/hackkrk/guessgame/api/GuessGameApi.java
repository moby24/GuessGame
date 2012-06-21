package com.hackkrk.guessgame.api;

import com.hackkrk.guessgame.model.Riddle;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

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

}
