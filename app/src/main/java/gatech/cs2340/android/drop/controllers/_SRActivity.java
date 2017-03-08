package gatech.cs2340.android.drop.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gatech.cs2340.android.drop.R;

public class _SRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new _SRFragment())
                .commit();
    }
}