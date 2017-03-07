package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.roughike.bottombar.BottomBar;

import gatech.cs2340.android.drop.R;

/**
 * Created by jaydensun on 3/6/17.
 */

public class MapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        //hide action bar
        getSupportActionBar().hide();


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        View view = bottomNavigationView.findViewById(R.id.placeholder);
        view.performClick();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.newspaper:
                        startActivity(new Intent(MapActivity.this, ReportActivity.class));
                        break;
                    case R.id.placeholder:
                        startActivity(new Intent(MapActivity.this, MapActivity.class));
                        break;
                    case R.id.plus:
                        startActivity(new Intent(MapActivity.this, AddReportActivity.class));
                        break;
                    case R.id.graph:
                        startActivity(new Intent(MapActivity.this, HistoricalActivity.class));
                        break;
                    case R.id.setting:
                        startActivity(new Intent(MapActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }

        });
    }
}
