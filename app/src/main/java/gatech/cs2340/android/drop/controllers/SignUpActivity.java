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

import java.util.Arrays;
import java.util.List;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.AccountType;
import gatech.cs2340.android.drop.model.Admin;
import gatech.cs2340.android.drop.model.Manager;
import gatech.cs2340.android.drop.model.Model;
import gatech.cs2340.android.drop.model.User;
import gatech.cs2340.android.drop.model.Worker;

import static gatech.cs2340.android.drop.model.User.legalAcctType;

/**
 * Created by jaydensun on 2/12/17.
 */

public class SignUpActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;

    private Spinner acctTypeSpinner;

    //private User _user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        TextView signIn = (TextView) findViewById(R.id.member_already);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Grab the dialog widgets so we can get info for later
         */
        nameField = (EditText) findViewById(R.id.sign_up_name);
        emailField = (EditText) findViewById(R.id.sign_up_email);
        passwordField = (EditText) findViewById(R.id.sign_up_password);
        acctTypeSpinner = (Spinner) findViewById(R.id.account_type_spinner);

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


        /**
         * Create Account Button
         */
        Button createAcctButton = (Button) findViewById(R.id.sign_up_button);
        createAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                Log.d("Button", "Create Account Button");
                onCreatePressed(view);
            }
        });
    }

    /**
     * Button handler for the add new student button
     * @param view the button
     */
    protected void onCreatePressed(View view) {
        Log.d("Add", "Add User");
        Model model = Model.getInstance();

        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        AccountType accountType = (AccountType)acctTypeSpinner.getSelectedItem();

        Log.d("Edit", "Got new user data: " + name);

        if (accountType.equals(AccountType.ADMIN)) {
            model.addUser(new Admin(name, email, password));
        } else if (accountType.equals(AccountType.MANAGER)) {
            model.addUser(new Manager(name, email, password));
        } else if (accountType.equals(AccountType.WORKER)) {
            model.addUser(new User(name, email, password));
        } else {
            model.addUser(new User(name, email, password));

        }

        finish();
    }
}
