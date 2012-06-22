package com.hackkrk.guessgame;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.hackkrk.guessgame.adapters.LaderboardAdapter;
import com.hackkrk.guessgame.adapters.RiddlesAdapter;
import com.hackkrk.guessgame.api.GuessGameApi;
import com.hackkrk.guessgame.model.LaderboardUser;
import com.hackkrk.guessgame.model.Riddle;

public class LaderboardActivity extends ListActivity {

  private GuessGameApi api;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.riddles);
    api = new GuessGameApi(this);
    List<LaderboardUser> users = api.getLaderboard();
    LaderboardAdapter adapter = new LaderboardAdapter(users, this);
    setListAdapter(adapter);

  }

}
