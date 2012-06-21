package com.hackkrk.guessgame;

import com.hackkrk.guessgame.api.GuessGameApi;
import com.hackkrk.guessgame.model.Riddle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewRiddleActivity extends Activity {

  private ImageView mImage;
  private Bitmap mImageBitmap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.riddle_new);

    mImage = (ImageView) findViewById(R.id.imageView1);
    Button submit = (Button) findViewById(R.id.bn_submit);
    final EditText questionEditText = (EditText) findViewById(R.id.et_question);
    final EditText answerEditText = (EditText) findViewById(R.id.et_answer);

    mImage.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        getPhoto();
      }
    });

    final GuessGameApi api = new GuessGameApi(this);

    submit.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Riddle riddle = new Riddle();
        riddle.photo = mImageBitmap;
        riddle.question = questionEditText.getText().toString();
        riddle.answer = answerEditText.getText().toString();
        Riddle result = api.sendRiddle(riddle);
        String message = result != null ? "correct!" : "wrong!";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        finish();
      }
    });

  }

  private void getPhoto() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(takePictureIntent, 44);
  }

  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    switch (requestCode) {
    case 44:
      Bundle extras = intent.getExtras();
      mImageBitmap = (Bitmap) extras.get("data");
      mImage.setImageBitmap(mImageBitmap);
    }

  }
}
