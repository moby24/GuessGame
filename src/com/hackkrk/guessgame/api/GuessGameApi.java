package com.hackkrk.guessgame.api;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class GuessGameApi {

  HttpClient getHttpClient() {
    HttpClient client = new DefaultHttpClient();
    return client;
  }

}
