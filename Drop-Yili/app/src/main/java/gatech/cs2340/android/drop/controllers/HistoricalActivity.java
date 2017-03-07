package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import gatech.cs2340.android.drop.R;

/**
 * Created by Jayden Sun on 3/6/17.
 */

public class HistoricalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historical);

        //hide action bar
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        View view = bottomNavigationView.findViewById(R.id.graph);
        view.performClick();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.newspaper:
                        startActivity(new Intent(HistoricalActivity.this, ReportActivity.class));
                        break;
                    case R.id.placeholder:
                        startActivity(new Intent(HistoricalActivity.this, MapActivity.class));
                        break;
                    case R.id.plus:
                        startActivity(new Intent(HistoricalActivity.this, AddReportActivity.class));
                        break;
                    case R.id.graph:
                        startActivity(new Intent(HistoricalActivity.this, HistoricalActivity.class));
                        break;
                    case R.id.setting:
                        startActivity(new Intent(HistoricalActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }

        });
    }
}