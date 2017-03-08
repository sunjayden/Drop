package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        View view = bottomNavigationView.findViewById(R.id.newspaper);
        view.performClick();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.newspaper:
                        startActivity(new Intent(ReportActivity.this, ReportActivity.class));
                        break;
                    case R.id.placeholder:
                        startActivity(new Intent(ReportActivity.this, MapActivity.class));
                        break;
                    case R.id.plus:
                        startActivity(new Intent(ReportActivity.this, AddReportActivity.class));
                        break;
                    case R.id.graph:
                        startActivity(new Intent(ReportActivity.this, HistoricalActivity.class));
                        break;
                    case R.id.setting:
                        startActivity(new Intent(ReportActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }

        });

        Button sourceReport = (Button) findViewById(R.id.report_sourceReport);
        sourceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Source Report", "Go to source Report");
                Intent sourceIntent = new Intent(ReportActivity.this, SourceReportActivity.class);
                startActivity(sourceIntent);
            }
        });

        Button purityReport = (Button) findViewById(R.id.report_purityReport);
        purityReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Purity Report", "Go to purity Report");
                Intent purityIntent = new Intent(ReportActivity.this, EditPurityReport.class);
                startActivity(purityIntent);
            }
        });
    }
}
