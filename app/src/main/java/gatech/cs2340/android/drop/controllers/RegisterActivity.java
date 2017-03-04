package gatech.cs2340.android.drop.controllers;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.UserType;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class RegisterActivity extends AppCompatActivity {

    private Spinner userTypeSpinner;

    public static List<UserType> legalUserType = Arrays.asList(UserType.values());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //Set Spinner
        userTypeSpinner = (Spinner) findViewById(R.id.user_type_spinner);

        //show in spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this,R.layout.spinner_item, legalUserType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(typeAdapter);

        //change spinner triangle color to white
        userTypeSpinner.getBackground().setColorFilter(ContextCompat.getColor(this,R.color.white),
                PorterDuff.Mode.SRC_ATOP);
    }
}
