package com.hackkrk.guessgame.adapters;

import java.util.List;

import com.hackkrk.guessgame.model.Riddle;
import com.hackkrk.guessgame.utils.ImageDownloader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class RiddlesAdapter extends BaseAdapter {
  Context context;
  private List<Riddle> list;

  public RiddlesAdapter(List<Riddle> riddles, Context context) {
    this.context = context;
    this.list=riddles;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Riddle getItem(int arg0) {
    return list.get(arg0);
  }

  @Override
  public long getItemId(int arg0) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView image;
    if(convertView==null){
      image = new ImageView(context);
      image.setLayoutParams(new LayoutParams(100,100 ));
    }else {
      image = (ImageView) convertView;
    }
    ImageDownloader.getInstance().download(getItem(position).photo_url, image);
    return image;
  }

}
