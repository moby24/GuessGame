package com.hackkrk.guessgame.adapters;

import com.hackkrk.guessgame.R;
import com.hackkrk.guessgame.model.Riddle;
import com.hackkrk.guessgame.utils.ImageDownloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class RiddlesAdapter extends BaseAdapter {
  Context context;
  private List<Riddle> list;
  private LayoutInflater mInflater;

  public RiddlesAdapter(List<Riddle> riddles, Context context) {
    this.context = context;
    this.list = riddles;
    mInflater = LayoutInflater.from(context);
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
    View image;
    if (convertView == null) {
      image = mInflater.inflate(R.layout.item_riddle, parent, false);
    } else {
      image = convertView;
    }
    ImageDownloader.getInstance().download(getItem(position).photo_url,
        (ImageView) image.findViewById(R.id.riddle));
    return image;
  }

}
