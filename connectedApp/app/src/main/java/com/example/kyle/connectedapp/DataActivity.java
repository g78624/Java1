package com.example.kyle.connectedapp;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;


public class DataActivity extends Activity {

    final String TAG = "Warcraft Character App";
    private TextView mServerView;
    private TextView mCharacterView;
    ProgressBar mDataProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        mServerView = (TextView) findViewById(R.id.serverInput);
        mCharacterView = (TextView) findViewById(R.id.toonInput);
        mDataProgress = (ProgressBar) findViewById(R.id.dataProgress);
        mDataProgress.setVisibility(View.INVISIBLE);

        Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (networkConnection()) {

                    InputMethodManager hideKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    hideKeyboard.hideSoftInputFromWindow(mCharacterView.getWindowToken(), 0);

                    String serverName = mServerView.getText().toString().replace(" ", "-");
                    String characterName = mCharacterView.getText().toString();

                    try {

                        String starterURL = "http://us.battle.net/api/wow/character/";
                        URL getInfoURL = new URL(starterURL + serverName + "/" + characterName);
                        new GetCharacterInfo().execute(getInfoURL);

                    } catch (Exception e) {

                        Log.e(TAG, "Invalid Information: " + serverName + " or " + characterName);

                    }

                } else {

                    InputMethodManager hideKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    hideKeyboard.hideSoftInputFromWindow(mCharacterView.getWindowToken(), 0);

                    Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    protected boolean networkConnection() {

        ConnectivityManager myConnection = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myNetwork = myConnection.getActiveNetworkInfo();

        return myNetwork != null && myNetwork.isConnectedOrConnecting();
    }

    private class GetCharacterInfo extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "World of Warcraft Data Fetch";

        @Override
        protected void onPreExecute(){

            mDataProgress.setVisibility(View.VISIBLE);

        }

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonInfo = "";

            for (URL getInfoURL : urls) {

                //publishProgress();

                try {

                    URLConnection connect = getInfoURL.openConnection();
                    jsonInfo = IOUtils.toString(connect.getInputStream());
                    break;

                } catch (Exception e) {

                    Log.e(TAG, "Could not establish a connection to " + getInfoURL.toString());

                }

            }

            Log.i(TAG, "Received Data: " + jsonInfo);

            JSONObject characterData;

            try {

                characterData = new JSONObject(jsonInfo);

            } catch (Exception e) {

                Log.e(TAG, "Cannot convert response");
                characterData = null;

            }

            return characterData;

        }

        @Override
        protected void onPostExecute(JSONObject characterData) {

            Character newCharacter = new Character(characterData);
            mDataProgress.setVisibility(View.INVISIBLE);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data, menu);
        return true;
    }

}
