package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.SourceReport;
import gatech.cs2340.android.drop.model.User;

public class AddSourceReportActivity extends AppCompatActivity {

    private static final String TAG = "AddSourceReportActivity";
    private final List<String> legalWaterType = Arrays.asList("Bottled", "Well", "Stream", "Lake", "Spring", "Other");
    private final List<String> legalWaterCondition = Arrays.asList("Waste", "Treatable-Clear", "Treatable-Muddy", "Potable");
    private EditText _lati;
    private EditText _long;
    private Spinner _waterTypeSpinner;
    private Spinner _waterConditionSpinner;
    private DatabaseReference mDatabase;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_source_report);
        EditText lati = (EditText) findViewById(R.id.rp_lat);
        lati.setText(getIntent().getStringExtra("latitude"));
        EditText longi = (EditText) findViewById(R.id.rp_long);
        longi.setText(getIntent().getStringExtra("longitude"));


        //Set Spinner
        _waterTypeSpinner = (Spinner) findViewById(R.id.water_type_spinner);

        //show in spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, legalWaterType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _waterTypeSpinner.setAdapter(typeAdapter);

        //change spinner triangle color to white
        _waterTypeSpinner.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.white),
                PorterDuff.Mode.SRC_ATOP);

        //Set Spinner
        _waterConditionSpinner = (Spinner) findViewById(R.id.water_condition_spinner);

        //show in spinner
        ArrayAdapter<String> conditionAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, legalWaterCondition);
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _waterConditionSpinner.setAdapter(conditionAdapter);

        //change spinner triangle color to white
        _waterConditionSpinner.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.white),
                PorterDuff.Mode.SRC_ATOP);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, user+"");

        Button createSp = (Button) findViewById(R.id.create_sp);
        createSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createReport();
            }
        });

        Button cancelSp = (Button) findViewById(R.id.cancel_sp);
        cancelSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Cancel Button Clicked");
                Toast.makeText(AddSourceReportActivity.this, "Report Cancelled",
                        Toast.LENGTH_LONG).show();
                Intent cancelIntent = new Intent(AddSourceReportActivity.this, SourceReportActivity.class);
                startActivity(cancelIntent);
            }
        });
    }

    private void createReport() {
        _lati = (EditText) findViewById(R.id.rp_lat);
        _long = (EditText) findViewById(R.id.rp_long);

        final String latitude = _lati.getText().toString().trim();
        final String longitude = _long.getText().toString().trim();
        final String waterType = (String)_waterTypeSpinner.getSelectedItem();
        final String waterCondition = (String)_waterConditionSpinner.getSelectedItem();

        DateFormat dateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT,
                Locale.US);
        Date date = new Date();
        final String timeStamp = dateFormat.format(date);
        String id = timeStamp.replace("/", "");
        final String reportId = id.replace(":", "");

        Random rand = new Random(System.currentTimeMillis());
        final String reportNum = Math.abs(rand.nextInt()/1000000)+ "";

        final String uid = user.getUid();
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("users");

        if (!validate()) {
            onRegisterFailed();
            return;
        }

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User userInfo = dataSnapshot.child(uid).getValue(User.class);
                SourceReport sp = new SourceReport(timeStamp, reportNum, userInfo._name,
                        latitude, longitude, waterType, waterCondition);
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("sourceReports").child(reportId).setValue(sp);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        Log.d(TAG, "Create Source Report Clicked");
        Toast.makeText(AddSourceReportActivity.this, "Report Created!",
                Toast.LENGTH_LONG).show();
        Intent sourceIntent = new Intent(AddSourceReportActivity.this, SourceReportActivity.class);
        startActivity(sourceIntent);
    }

    private void onRegisterFailed() {
        Toast.makeText(getBaseContext(), "Invalid Latitude or Longitude!", Toast.LENGTH_LONG).show();
    }

    private boolean validate() {
        Log.d(TAG, "Validate source report");
        boolean valid = true;

        String latitude = _lati.getText().toString().trim();
        String longitude = _long.getText().toString().trim();

        if (latitude.isEmpty() || latitude.length() > 6) {
            _lati.setError("Enter a valid latitude");
            valid = false;
        } else {
            _lati.setError(null);
        }

        if (longitude.isEmpty() || longitude.length() > 6) {
            _long.setError("Enter a valid longitude");
            valid = false;
        } else {
            _long.setError(null);
        }

        return valid;
    }

}
