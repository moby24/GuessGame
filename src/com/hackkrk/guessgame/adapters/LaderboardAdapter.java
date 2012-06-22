package com.hackkrk.guessgame.adapters;

import com.hackkrk.guessgame.R;
import com.hackkrk.guessgame.model.LaderboardUser;
import com.hackkrk.guessgame.model.Riddle;
import com.hackkrk.guessgame.utils.ImageDownloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LaderboardAdapter extends BaseAdapter {
  Context context;
  private List<LaderboardUser> list;
  private LayoutInflater mInflater;

  public LaderboardAdapter(List<LaderboardUser> riddles, Context context) {
    this.context = context;
    this.list = riddles;
    mInflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount() {
    if (list == null) return 0;
    return list.size();
  }

  @Override
  public LaderboardUser getItem(int arg0) {
    return list.get(arg0);
  }

  @Override
  public long getItemId(int arg0) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view;
    if (convertView == null) {
      view = mInflater.inflate(R.layout.item_user, parent, false);
    } else {
      view = convertView;
    }
    LaderboardUser user = getItem(position);
    TextView username = (TextView) view.findViewById(R.id.user);
    username.setText(user.username);
    TextView score = (TextView) view.findViewById(R.id.score);
    score.setText(user.score);
    return view;
  }

}
