package com.example.kyle.advancedviews;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class advancedViewMaster extends Activity {

    private ArrayList<VideoGames> mVideoGames = new ArrayList<VideoGames>();
    public final static String TITLE="title";
    public final static String PLATFORM="platform";
    public final static String GENRE="genre";
    public final static String SCORE="score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_view_master);

        mVideoGames = new ArrayList<VideoGames>();
        mVideoGames.add(new VideoGames("The Last of Us", "Playstation 4", "Action/Adventure", "10"));
        mVideoGames.add(new VideoGames("Halo: Master Chief Collections", "Xbox One", "First Person Shooter", "9"));
        mVideoGames.add(new VideoGames("Madden 15", "Xbox One, Playstation 4", "Sports", "8"));
        mVideoGames.add(new VideoGames("Dragon Age: Inquisition", "Playstation 4, Xbox One", "Roleplaying", "9"));
        mVideoGames.add(new VideoGames("Evolve", "Xbox One, Playstation 4", "Shooter", "10"));
        mVideoGames.add(new VideoGames("Super Mario", "Nintendo Wii U", "Platformer", "9"));
        mVideoGames.add(new VideoGames("MLB 14: The Show", "Playstation 4", "Sports", "7"));
        mVideoGames.add(new VideoGames("World of Warcraft", "PC", "Massively Multiplayer Online RPG", "9"));

        getScreenOrientation();

    }

    public void getScreenOrientation(){

        Display getScreen = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int currentOrientation = getScreen.getRotation();

        if (currentOrientation == Surface.ROTATION_0 || currentOrientation == Surface.ROTATION_180) {

            Spinner  mGamesSpinner;
            setContentView(R.layout.activity_advanced_view_master);
            mGamesSpinner = (Spinner) findViewById(R.id.gamesSpinner);
            mGamesSpinner.setAdapter(new spinnerAdapter(this, mVideoGames));

            mGamesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Log.i("Project 3: ", mVideoGames.get(position).getTitle());

                    createNewView(position);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } else if (currentOrientation == Surface.ROTATION_90 || currentOrientation == Surface.ROTATION_270){

            ListView mGamesList;
            setContentView(R.layout.activity_advanced_view_master);
            mGamesList = (ListView) findViewById(R.id.landListView);
            mGamesList.setAdapter(new newAdapter(this, mVideoGames));

            mGamesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.i("Project 3: ", mVideoGames.get(position).getTitle());

                    createNewView(position);

                }
            });

        }

    }

    private void createNewView(int position){

        Intent detailViewIntent = new Intent(getApplicationContext(), detailView.class);

        detailViewIntent.putExtra(TITLE, mVideoGames.get(position).getTitle());
        detailViewIntent.putExtra(PLATFORM, mVideoGames.get(position).getPlatform());
        detailViewIntent.putExtra(GENRE, mVideoGames.get(position).getGenre());
        detailViewIntent.putExtra(SCORE, mVideoGames.get(position).getScore());

        startActivity(detailViewIntent);

    }

    @Override
    public void onConfigurationChanged(Configuration myConfig){

        super.onConfigurationChanged(myConfig);

        if (myConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            getScreenOrientation();

        } else if (myConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            getScreenOrientation();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.advanced_view_master, menu);
        return true;
    }

}