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
 * Created by jaydensun on 3/6/17.
 */

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        //hide action bar
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        View view = bottomNavigationView.findViewById(R.id.setting);
        view.performClick();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.newspaper:
                        startActivity(new Intent(SettingActivity.this, ReportActivity.class));
                        break;
                    case R.id.placeholder:
                        startActivity(new Intent(SettingActivity.this, MapActivity.class));
                        break;
                    case R.id.plus:
                        startActivity(new Intent(SettingActivity.this, AddReportActivity.class));
                        break;
                    case R.id.graph:
                        startActivity(new Intent(SettingActivity.this, HistoricalActivity.class));
                        break;
                    case R.id.setting:
                        startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }

        });
    }
}
