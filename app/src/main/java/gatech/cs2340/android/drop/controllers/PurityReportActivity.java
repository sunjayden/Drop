package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import gatech.cs2340.android.drop.R;

public class PurityReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_report);

        //hide action bar
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        View view = bottomNavigationView.findViewById(R.id.ic_add);
        view.performClick();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_report:
                        startActivity(new Intent(PurityReportActivity.this, SourceReportActivity.class));
                        break;
                    case R.id.ic_map:
                        startActivity(new Intent(PurityReportActivity.this, MapActivity.class));
                        break;
                    case R.id.ic_add:
                        startActivity(new Intent(PurityReportActivity.this, PurityReportActivity.class));
                        break;
                    case R.id.ic_graph:
                        startActivity(new Intent(PurityReportActivity.this, HistoricalActivity.class));
                        break;
                    case R.id.ic_setting:
                        startActivity(new Intent(PurityReportActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }

        });
    }
}
