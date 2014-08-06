package com.example.kyle.javaoneprojectone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;


public class textCollection extends Activity {

    final String TAG = "Java One Project One";
    private TextView mUserInput;
    private TextView mDataCount;
    private HashSet<String> collectedText = new HashSet<String>();
    private int currentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_collection);

        mUserInput = (TextView) findViewById(R.id.enteredText);
        mDataCount = (TextView) findViewById(R.id.dataEntries);

        //Creates my Save Alert Toast
        final Context saveContext = getApplicationContext();
        final CharSequence display = "Successfully Saved Entry";
        final int duration = Toast.LENGTH_SHORT;
        final Toast saveAlert = Toast.makeText(saveContext, display, duration);

        //Creates my Duplicate Alert Toast
        Context duplicateContext = getApplicationContext();
        CharSequence duplicateDisplay = "Duplicate Item Found, Entry Not Saved";
        int duplicateDuration = Toast.LENGTH_SHORT;
        final Toast duplicateAlert = Toast.makeText(duplicateContext,duplicateDisplay, duplicateDuration);

        Button submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This will hide the keyboard from the user when they click the submit button
                InputMethodManager keyboardHide = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboardHide.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                Log.i(TAG, "Button Clicked");
                String currentText = mUserInput.getText().toString();
                collectedText.add(currentText);

                int standardCount = currentCount;
                currentCount = collectedText.size();
                String currentCountString = Integer.toString(currentCount);
                mDataCount.setText("Current Number of Entries Is: " + currentCountString);

                if (standardCount == currentCount){

                    duplicateAlert.show();

                } else {

                    saveAlert.show();

                }

                mUserInput.setText("");

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.text_collection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
