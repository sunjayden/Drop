package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import gatech.cs2340.android.drop.R;

/**
 * Created by Jayden Sun on 3/4/17.
 */

public class ReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.newspaper:
                        Toast.makeText(ReportActivity.this, "Action Report clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ReportActivity.this, TestingActivity.class));
                        break;
                    case R.id.placeholder:
                        Toast.makeText(ReportActivity.this, "Action Map clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ReportActivity.this, TestingActivity.class));
                        break;
                    case R.id.plus:
                        Toast.makeText(ReportActivity.this, "Action Add clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ReportActivity.this, TestingActivity.class));
                        break;
                    case R.id.graph:
                        Toast.makeText(ReportActivity.this, "Action Graph clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ReportActivity.this, TestingActivity.class));
                        break;
                    case R.id.setting:
                        Toast.makeText(ReportActivity.this, "Action Setting clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ReportActivity.this, TestingActivity.class));
                        break;
                }
                return true;
            }

        });

        //hide action bar
        getSupportActionBar().hide();
    }
}
