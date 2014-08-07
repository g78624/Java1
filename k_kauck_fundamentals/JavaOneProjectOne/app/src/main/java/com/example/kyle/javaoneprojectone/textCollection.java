package com.example.kyle.javaoneprojectone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;


public class textCollection extends Activity {

    final String TAG = "Java One Project One";
    private TextView mUserInput;
    private TextView mDataCount;
    private TextView mFindIndex;
    private LinkedHashSet<String> collectedText = new LinkedHashSet<String>();
    private int currentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_collection);

        mUserInput = (TextView) findViewById(R.id.enteredText);
        mDataCount = (TextView) findViewById(R.id.dataEntries);
        mFindIndex = (TextView) findViewById(R.id.findIndex);

        Button submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This will hide the keyboard from the user when they click the submit button
                InputMethodManager keyboardHide = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboardHide.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                //Creates my Save Alert Toast
                Context saveContext = getApplicationContext();
                CharSequence display = "Successfully Saved Entry";
                int duration = Toast.LENGTH_SHORT;
                Toast saveAlert = Toast.makeText(saveContext, display, duration);

                //Creates my Duplicate Alert Toast
                Context duplicateContext = getApplicationContext();
                CharSequence duplicateDisplay = "Duplicate Item Found, Entry Not Saved";
                int duplicateDuration = Toast.LENGTH_SHORT;
                Toast duplicateAlert = Toast.makeText(duplicateContext,duplicateDisplay, duplicateDuration);

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

                findAverage(currentCount);

                int indexHint = currentCount - 1;

                mUserInput.setText("");
                mFindIndex.setHint("Enter Number Between 0 & " + indexHint);

            }
        });

        Button findButton = (Button) findViewById(R.id.findButton);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This will hide the keyboard from the user when they click the submit button
                InputMethodManager keyboardHide = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboardHide.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                int upToDateCount = collectedText.size();
                String userEntry = mFindIndex.getText().toString();
                int userNumber = (Integer.valueOf(userEntry));
                String thisWord;
                ArrayList<String> searchableList = new ArrayList<String>();

                if (userNumber < upToDateCount){

                    Iterator<String> getWord = collectedText.iterator();

                    while (getWord.hasNext()){

                        thisWord = getWord.next();
                        searchableList.add(thisWord);

                    }

                    String yourWord = searchableList.get(userNumber);

                    AlertDialog.Builder showWord = new AlertDialog.Builder(textCollection.this);
                    showWord.setTitle("Found Word!");
                    showWord.setMessage("Your Word Is: " + yourWord);
                    showWord.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog display = showWord.create();

                    display.show();

                    mFindIndex.setText("");

                } else if (userNumber >= upToDateCount) {

                    AlertDialog.Builder noWordFound = new AlertDialog.Builder(textCollection.this);
                    noWordFound.setTitle("Could Not Find A Word!");
                    noWordFound.setMessage("Sorry But It Seems The Number Your Entered Has No Word Linked To It.");
                    noWordFound.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog display = noWordFound.create();

                    display.show();

                    mFindIndex.setText("");

                }

            }
        });

    }

    public void findAverage(int _arrayCount){

       TextView mAverageDisplay;

        mAverageDisplay = (TextView) findViewById(R.id.averageDisplay);

        float average;
        int totalNumber;
        int sumOfTotal = 0;

        Iterator<String> getWords = collectedText.iterator();

        String currentWord;

        while (getWords.hasNext()){

            currentWord = getWords.next();
            totalNumber = currentWord.length();
            sumOfTotal = sumOfTotal + totalNumber;

        }

        average = (float)sumOfTotal/_arrayCount;

        mAverageDisplay.setText("The Average Length Of Entries Is: " + average);

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
