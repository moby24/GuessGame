package com.hackkrk.guessgame;

import com.hackkrk.guessgame.api.GuessGameApi;
import com.hackkrk.guessgame.model.Answer;
import com.hackkrk.guessgame.model.Riddle;
import com.hackkrk.guessgame.utils.ImageDownloader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RiddleActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.riddle);

    final Riddle riddle = (Riddle) getIntent().getSerializableExtra("riddle");

    ImageView image = (ImageView) findViewById(R.id.imageView1);
    Button submit = (Button) findViewById(R.id.bn_submit);
    final EditText answerEditText = (EditText) findViewById(R.id.et_answer);

    ImageDownloader.getInstance().download(riddle.photo_url, image);

    final GuessGameApi api = new GuessGameApi(this);

    submit.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Answer answer = new Answer();
        answer.answer = answerEditText.getText().toString().trim().toLowerCase();
        boolean result = api.sendAnswer(answer, riddle.id);
        String message = result ? "correct!" : "wrong!";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        if (result)
          finish();
      }
    });

  }

}
