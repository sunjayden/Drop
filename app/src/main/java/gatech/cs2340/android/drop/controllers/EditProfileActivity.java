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
import android.widget.TextView;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.Model;
import gatech.cs2340.android.drop.model.User;

import static gatech.cs2340.android.drop.model.User.legalAcctType;

/**
 * Created by jaydensun on 2/27/17.
 */

public class EditProfileActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private Spinner acctTypeSpinner;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        nameField = (EditText) findViewById(R.id.edit_name);
        nameField.setText((String)getIntent().getSerializableExtra("name"), TextView.BufferType.EDITABLE);
        emailField = (EditText) findViewById(R.id.edit_email);
        emailField.setText((String)getIntent().getSerializableExtra("email"), TextView.BufferType.EDITABLE);
        passwordField = (EditText) findViewById(R.id.edit_password);
        //passwordField.setText((String)getIntent().getSerializableExtra("password"), TextView.BufferType.EDITABLE);

        Button save = (Button) findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent saveIntent = new Intent(EditProfileActivity.this, MainActivity.class);
                String name = nameField.getText().toString();
                Log.d("11", name);

                String email = emailField.getText().toString();
                Log.d("11", email);

                String password = passwordField.getText().toString();
                Log.d("11", password);
                Log.d("11", (String)getIntent().getSerializableExtra("email"));
                //model.updateAccount((String)getIntent().getSerializableExtra("email"), name, email, password);
                startActivity(saveIntent);
            }
        });

        /**
         * Set Spinner
         */
        acctTypeSpinner = (Spinner) findViewById(R.id.account_type_spinner);

        /**
         * show in spinner
         */
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this,R.layout.spinner_item, legalAcctType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acctTypeSpinner.setAdapter(typeAdapter);

        /**
         * change spinner triangle color to white
         */
        acctTypeSpinner.getBackground().setColorFilter(ContextCompat.getColor(this,R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

}
