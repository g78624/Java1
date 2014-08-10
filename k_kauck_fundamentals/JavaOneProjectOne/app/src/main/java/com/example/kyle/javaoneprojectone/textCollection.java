package com.example.kyle.javaoneprojectone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                findAverage(currentCount);

                //Made a new String to store words as the foreach loop goes through the HashSet to add items to the Array List
                String thisWord;
                ArrayList<String> listArray = new ArrayList<String>();

                for (String myWord : collectedText) {

                    thisWord = myWord;
                    listArray.add(thisWord);

                }

                //Make a new ArrayAdapter to add items from the recently created Array List adding each one into the ListView
                ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(textCollection.this, android.R.layout.simple_list_item_1, listArray);

                mWordList.setAdapter(myArrayAdapter);

                //Resets the editText field to default.
                mUserInput.setText("");

            }

        });

        //This lets the user select a certain index from the ListView and then shows an Alert Dialog that shows the word that was selected.
        mWordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView wordView = (TextView) view;

                AlertDialog.Builder showWord = new AlertDialog.Builder(textCollection.this);
                showWord.setTitle("Found Word!");
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
}
