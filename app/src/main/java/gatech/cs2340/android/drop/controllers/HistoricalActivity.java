package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import gatech.cs2340.android.drop.R;

public class HistoricalActivity extends AppCompatActivity {

    private EditText _location;
    private EditText _year;
    private Spinner _typeSpinner;
    private List legalType = Arrays.asList("Virus", "Contaminant");
    private DatabaseReference mDatabasel;
    FirebaseDatabase database;

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

        // set spinner
        _typeSpinner = (Spinner) findViewById(R.id.vSpinner);

        // show in spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this, R.layout.spinner_item, legalType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _typeSpinner.setAdapter(typeAdapter);

        // change spinner triangle color to white
        _typeSpinner.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.white),
                PorterDuff.Mode.SRC.SRC_ATOP);


        Button submitSp = (Button) findViewById(R.id.submit_his);
        submitSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHistory();
            }
        });



    }

    private void showHistory() {
        _location = (EditText) findViewById(R.id.editLocation);
        _year = (EditText) findViewById(R.id.editYear);
        final String location = _location.getText().toString().trim();
        final String year = _year.getText().toString().trim();

        if (!verify(location, year)) {
            return;
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);

        //dummy example:
        List<String> dummyYear = new ArrayList<>();
        dummyYear.add("2015/02/02");
        dummyYear.add("2015/05/02");
        dummyYear.add("2016/01/02");
        dummyYear.add("2017/01/02");
        dummyYear.add("2015/09/02");
        int[] dummyPPM = {5,8,6,8,2};

        List<DataPoint> dummyDataPoint = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        for (int i = 0; i < dummyYear.size(); i++) {
            String[] ayear = dummyYear.get(i).split("/");
            if (ayear[0].equals(year)) {
                try {
                    Date date = dateFormat.parse(dummyYear.get(i));
                    dummyDataPoint.add(new DataPoint(date, dummyPPM[i]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        //conver from list to array
        DataPoint[] dataArray = dummyDataPoint.toArray(new DataPoint[dummyDataPoint.size()]);


        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataArray);


        graph.addSeries(series);




        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(graph.getContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);

        // set manual x bounds to have nice steps
        graph.getViewport().setMinX(dummyDataPoint.get(0).getX());
        graph.getViewport().setMaxX(dummyDataPoint.get(2).getX());
        graph.getViewport().setXAxisBoundsManual(true);


    }

    private boolean verify(String location, String year) {
        if (location.equals("Atlanta") && year.equals("2015")) {
            return true;
        } else {
            Toast.makeText(getBaseContext(), "Invalid, Use Atlanta, 2015", Toast.LENGTH_LONG).show();
            return false;
        }
    }





}
