package com.example.kyle.advancedviews;

public class VideoGames {

    private String mTitle;
    private String mPlatform;
    private String mGenre;
    private int    mScore;

    public VideoGames(){

        mTitle = "";
        mPlatform = "";
        mGenre = "";
        mScore = 0;

    }

    public VideoGames (String _title, String _platform, String _genre, int _score){

        mTitle = _title;
        mPlatform = _platform;
        mGenre = _genre;
        mScore = _score;

    }

    public String getTitle(){

        return mTitle;

    }

    public void setTitle(String _title){

        mTitle = _title;

    }

    public String getPlatform (){

        return mPlatform;

    }

    public void setPlatform (String _platform){

        mPlatform = _platform;

    }

    public String getGenre(){

        return mGenre;

    }

    public void setGenre (String _genre){

        mGenre = _genre;

    }

    public int getScore(){

        return mScore;

    }

    public void setScore (int _score){

        mScore = _score;

    }

}
