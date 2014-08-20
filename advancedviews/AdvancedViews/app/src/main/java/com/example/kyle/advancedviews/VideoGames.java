package com.example.kyle.advancedviews;

public class VideoGames {

    private String mTitle;
    private String mPlatform;
    private String mGenre;
    private String mScore;

    public VideoGames(){

        mTitle = "";
        mPlatform = "";
        mGenre = "";
        mScore = "";

    }

    public VideoGames (String _title, String _platform, String _genre, String _score){

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

    public String getScore(){

        return mScore;

    }

    public void setScore (String _score){

        mScore = _score;

    }

}
