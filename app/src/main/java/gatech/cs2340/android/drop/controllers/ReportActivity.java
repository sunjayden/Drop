package gatech.cs2340.android.drop.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gatech.cs2340.android.drop.R;

/**
 * Created by Jayden Sun on 3/4/17.
 */

public class ReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);

        //hide action bar
        getSupportActionBar().hide();
    }
}
