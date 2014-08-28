//Kyle Kauck

package com.example.kyle.connectedapp;

import android.util.Log;

import org.json.JSONObject;

public class Character {

    final String TAG = "Character Class";

    private String mName;
    private Integer mLevel;
    private String mServer;
    private String mBattlegroup;
    private Integer mAchievements;
    private Integer mHonorableKills;

    public Character(){}

    public Character(String name, Integer level, String server, String battlegroup, Integer achievements, Integer honorableKills){

        mName = name;
        mLevel = level;
        mServer = server;
        mBattlegroup = battlegroup;
        mAchievements = achievements;
        mHonorableKills = honorableKills;

    }

    public Character(JSONObject characterData){

        try{

            mName = characterData.getString("name");
            mLevel = characterData.getInt("level");
            mServer = characterData.getString("realm");
            mBattlegroup = characterData.getString("battlegroup");
            mAchievements = characterData.getInt("achievementPoints");
            mHonorableKills = characterData.getInt("totalHonorableKills");

        } catch (Exception e){

            Log.e(TAG, "This really didn't work so well!");

        }

    }

    public String getName(){

        return mName;

    }

    public void setName(String mName){

        this.mName = mName;

    }

    public Integer getLevel(){

        return mLevel;

    }

    public void setLevel(Integer mLevel){

        this.mLevel = mLevel;

    }

    public String getServer(){

        return mServer;

    }

    public void setServer(String mServer){

        this.mServer = mServer;

    }

    public String getBattlegroup(){

        return mBattlegroup;

    }

    public void setBattlegroup(String mBattlegroup){

        this.mBattlegroup = mBattlegroup;

    }

    public Integer getAchievements(){

        return mAchievements;

    }

    public void setAchievements(Integer mAchievements){

        this.mAchievements = mAchievements;

    }

    public Integer getHonorableKills(){

        return mHonorableKills;

    }

    public void setHonorableKills(Integer mHonorableKills){

        this.mHonorableKills = mHonorableKills;

    }
}
