package com.example.kyle.advancedviews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class detailView extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        String title = getIntent().getStringExtra(advancedViewMaster.TITLE);
        String platform = getIntent().getStringExtra(advancedViewMaster.PLATFORM);
        String genre = getIntent().getStringExtra(advancedViewMaster.GENRE);
        String score = getIntent().getStringExtra(advancedViewMaster.SCORE);

        TextView titleLabel = (TextView) findViewById(R.id.gameTitle);
        TextView platformLabel = (TextView) findViewById(R.id.platformView);
        TextView genreLabel = (TextView) findViewById(R.id.genreView);
        TextView scoreLabel = (TextView) findViewById(R.id.scoreView);

        titleLabel.setText(title);
        platformLabel.setText(platform);
        genreLabel.setText(genre);
        scoreLabel.setText(score);

    }

}
