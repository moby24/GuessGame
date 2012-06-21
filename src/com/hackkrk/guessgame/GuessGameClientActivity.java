package com.hackkrk.guessgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hackkrk.guessgame.api.GuessGameApi;
import com.hackkrk.guessgame.model.User;

public class GuessGameClientActivity extends Activity {
  private static final String USER_LOGIN = null;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dashboard);
    Button riddlesButton = (Button) findViewById(R.id.riddlesButton);
    Button createRiddleButton = (Button) findViewById(R.id.crearteRiddleButton);
    Button leaderboardButton = (Button) findViewById(R.id.leaderboardButton);
    riddlesButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View arg0) {

        fireRiddlesIntent();
      }
    });

    leaderboardButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        fireLeaderboardIntent();

      }
    });

    createRiddleButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        fireCreateRiddleIntent();
      }
    });

  }

  private User getUser() {

    SharedPreferences preferences = getPreferences(MODE_PRIVATE);
    User user = new User();
    user.username = preferences.getString(USER_LOGIN, null);

    return user;

  }

  protected void fireCreateRiddleIntent() {
    // TODO Auto-generated method stub

  }

  protected void fireLeaderboardIntent() {
    // TODO Auto-generated method stub

  }

  protected void fireRiddlesIntent() {
    // TODO Auto-generated method stub

  }

  private void createLoginDialog() {
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    Context mContext = getApplicationContext();
    LayoutInflater inflater = (LayoutInflater) mContext
        .getSystemService(LAYOUT_INFLATER_SERVICE);
    View layout = inflater.inflate(R.layout.login_dialog,
        (ViewGroup) findViewById(R.id.dialogRoot));

    final EditText userNameEdiText = (EditText) layout
        .findViewById(R.id.userName);
    final EditText userPassEditText = (EditText) layout
        .findViewById(R.id.userPass);

    builder = new AlertDialog.Builder(mContext);
    builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        GuessGameApi guessApi = new GuessGameApi();
        guessApi.loginUser(userNameEdiText.getText().toString(),
            userPassEditText.getText().toString());
      }

    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        finish();
      }
    });
    builder.setView(layout);
    alertDialog = builder.create();

  }

}