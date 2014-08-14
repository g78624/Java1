package com.example.kyle.javaoneprojectone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


public class textCollection extends Activity {

    //Setting Up Global Variables
    private TextView mUserInput;
    private TextView mDataCount;
    private ListView mWordList;
    private HashSet<String> collectedText = new HashSet<String>();
    private int currentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_collection);

        //Get the ID and setup variables for TextViews/ListView
        mUserInput = (TextView) findViewById(R.id.enteredText);
        mDataCount = (TextView) findViewById(R.id.dataEntries);
        mWordList  = (ListView) findViewById(R.id.displayList);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button displayAllButton = (Button) findViewById(R.id.displayAll);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This will close the keyboard when the user clicks the submit button
                InputMethodManager hideKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                hideKeyboard.hideSoftInputFromWindow(mUserInput.getWindowToken(), 0);

                //Creates my Save Alert Toast
                Context saveContext = getApplicationContext();
                CharSequence display = "Successfully Saved Entry";
                int duration = Toast.LENGTH_SHORT;
                Toast saveAlert = Toast.makeText(saveContext, display, duration);

                //Creates my Duplicate Alert Toast
                Context duplicateContext = getApplicationContext();
                CharSequence duplicateDisplay = "Duplicate Item Found, Entry Not Saved";
                int duplicateDuration = Toast.LENGTH_SHORT;
                Toast duplicateAlert = Toast.makeText(duplicateContext, duplicateDisplay, duplicateDuration);

                //Saves the information from the user input into a string to then be stored in the HashSet
                String currentText = mUserInput.getText().toString();
                collectedText.add(currentText);

                //standardCount to compare the old number of entries to recently added to show proper toast message
                int standardCount = currentCount;
                currentCount = collectedText.size();
                String currentCountString = Integer.toString(currentCount);
                mDataCount.setText("Current Number of Entries Is: " + currentCountString);

                if (standardCount == currentCount) {

                    duplicateAlert.show();

                } else {

                    saveAlert.show();

                }

                //Call for the findAverage Method, sending in the currentCount of array items
                findAverage();

                //Made a new String to store words as the foreach loop goes through the HashSet to add items to the Array List
                String thisWord;
                ArrayList<String> listArray = new ArrayList<String>();

                for (String myWord : collectedText) {

                    thisWord = myWord;
                    listArray.add(thisWord);

                }

                //Make a new ArrayAdapter to add items from the recently created Array List adding each one into the ListView
                ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(textCollection.this, R.layout.text_view1, listArray);

                mWordList.setAdapter(myArrayAdapter);

                //Resets the editText field to default.
                mUserInput.setText("");

            }

        });

        displayAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String punctuation = ", ";
                String words = "";
                int  number = 0;

                for (String thisWord : collectedText){

                    number ++;

                    if (number == collectedText.size()){

                       words += thisWord;

                    } else {

                        words += thisWord + punctuation;

                    }
                }

                AlertDialog.Builder showAllWords = new AlertDialog.Builder(textCollection.this);
                showAllWords.setTitle("The Entered Words Are:");
                showAllWords.setIcon(R.drawable.ic_launcher);
                showAllWords.setMessage(words);
                showAllWords.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog display = showAllWords.create();

                display.show();

            }
        });

        //This lets the user select a certain index from the ListView and then shows an Alert Dialog that shows the word that was selected.
        mWordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView wordView = (TextView) view;



                AlertDialog.Builder showWord = new AlertDialog.Builder(textCollection.this);
                showWord.setTitle("Found Word!");
                showWord.setIcon(R.drawable.ic_launcher);
                showWord.setMessage("Your Word Is: " + wordView.getText().toString());
                showWord.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog display = showWord.create();

                display.show();

            }
        });
    }

    //This is my find Average method that will calculate the average length of all words in my HashSet by iterating over them and then updating the TextView.
    public void findAverage(){

       TextView mMedianDisplay;

        mMedianDisplay = (TextView) findViewById(R.id.medianDisplay);

        Integer totalNumber;
        float medianNumber;

        ArrayList<Integer> wordCounts = new ArrayList<Integer>();

        Iterator<String> getWords = collectedText.iterator();

        String currentWord;

        while (getWords.hasNext()){

            currentWord = getWords.next();
            totalNumber = currentWord.length();
            wordCounts.add(totalNumber);

        }

        Collections.sort(wordCounts);

        int middle = wordCounts.size()/2;

        if (wordCounts.size() % 2 == 0){

            int middleEven = middle-1;
            medianNumber = (float) (wordCounts.get(middleEven) + wordCounts.get(middle)) / 2;
            mMedianDisplay.setText("The Median Length of Entries Is: " + medianNumber);

        } else {

            medianNumber = wordCounts.get(middle);
            mMedianDisplay.setText("The Median Length of Entries Is: " + medianNumber);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.text_collection, menu);
        return true;
    }
}
