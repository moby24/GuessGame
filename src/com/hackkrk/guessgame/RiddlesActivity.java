package com.hackkrk.guessgame;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;

import com.hackkrk.guessgame.adapters.RiddlesAdapter;
import com.hackkrk.guessgame.api.GuessGameApi;
import com.hackkrk.guessgame.model.Riddle;


public class RiddlesActivity extends ListActivity {

  private GuessGameApi api;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.riddles);
     api = new GuessGameApi(this);
     List<Riddle> riddles = api.getRiddles();
     RiddlesAdapter adapter = new RiddlesAdapter(riddles, this);
     setListAdapter(adapter);
     
    
  }
  

}
