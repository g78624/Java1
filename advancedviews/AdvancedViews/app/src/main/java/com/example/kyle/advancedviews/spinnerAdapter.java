package com.example.kyle.advancedviews;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class spinnerAdapter extends BaseAdapter {

    private static final long CONSTANT_GAMEID = 0x010101010L;
    private Context mContext;
    private ArrayList<VideoGames> mVideoGames;

    public spinnerAdapter(Context _context, ArrayList<VideoGames> _videoGames){

        mContext = _context;
        mVideoGames = _videoGames;

    }


    @Override
    public int getCount() {

       return mVideoGames.size();

    }

    @Override
    public Object getItem(int _position) {

        return mVideoGames.get(_position);

    }

    @Override
    public long getItemId(int _position) {

        return CONSTANT_GAMEID + _position;

    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {

        if (_convertView == null){

            _convertView = LayoutInflater.from(mContext).inflate(R.layout.games_list, _parent, false);

        }

        VideoGames videoGames = (VideoGames) getItem(_position);

        //Text Views for my List Layout in Landscape
        TextView spinnerTitle = (TextView) _convertView.findViewById(R.id.gameSpinnerTitle);
        spinnerTitle.setText(videoGames.getTitle());

        TextView spinnerPlatform = (TextView) _convertView.findViewById(R.id.gameSpinnerPlatform);
        spinnerPlatform.setText(videoGames.getPlatform());

        return _convertView;

    }
}
