package com.hackkrk.guessgame.api;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class GuessGameApi {

  private static String API_ROOT_HTTP = "http://hackkrk-guess.herokuapp.com";

  HttpClient getHttpClient() {
    HttpClient client = new DefaultHttpClient();
    return client;
  }
  
  

}
