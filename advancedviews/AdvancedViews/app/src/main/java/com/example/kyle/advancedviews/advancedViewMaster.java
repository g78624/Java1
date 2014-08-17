package com.example.kyle.advancedviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;


public class advancedViewMaster extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_view_master);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.advanced_view_master, menu);
        return true;
    }

}
