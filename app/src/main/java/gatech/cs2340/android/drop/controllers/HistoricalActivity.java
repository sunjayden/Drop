package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import gatech.cs2340.android.drop.R;

public class HistoricalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);

        //hide action bar
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        View view = bottomNavigationView.findViewById(R.id.ic_graph);
        view.performClick();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_report:
                        startActivity(new Intent(HistoricalActivity.this, SourceReportActivity.class));
                        break;
                    case R.id.ic_map:
                        startActivity(new Intent(HistoricalActivity.this, MapsActivity.class));
                        break;
                    case R.id.ic_add:
                        startActivity(new Intent(HistoricalActivity.this, PurityReportActivity.class));
                        break;
                    case R.id.ic_graph:
                        startActivity(new Intent(HistoricalActivity.this, HistoricalActivity.class));
                        break;
                    case R.id.ic_setting:
                        startActivity(new Intent(HistoricalActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }

        });
    }
}
