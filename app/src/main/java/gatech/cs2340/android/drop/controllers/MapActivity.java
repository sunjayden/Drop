package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import gatech.cs2340.android.drop.R;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //hide action bar
        getSupportActionBar().hide();


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        View view = bottomNavigationView.findViewById(R.id.ic_map);
        view.performClick();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_report:
                        startActivity(new Intent(MapActivity.this, ReportActivity.class));
                        break;
                    case R.id.ic_map:
                        startActivity(new Intent(MapActivity.this, MapActivity.class));
                        break;
                    case R.id.ic_add:
                        startActivity(new Intent(MapActivity.this, AddReportActivity.class));
                        break;
                    case R.id.ic_graph:
                        startActivity(new Intent(MapActivity.this, HistoricalActivity.class));
                        break;
                    case R.id.ic_setting:
                        startActivity(new Intent(MapActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }

        });
    }
}
