package gatech.cs2340.android.drop.controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gatech.cs2340.android.drop.R;

import static android.R.id.input;
import static gatech.cs2340.android.drop.R.layout.login;
import static gatech.cs2340.android.drop.R.string.email;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText _emailField;
    private EditText _passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(login);

        //Login button onClick
        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        Log.d("LoginActivity", "Login Button Clicked");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        //Grab email and password input from login screen
        _emailField = (EditText) findViewById(R.id.login_email_input);
        _passwordField = (EditText) findViewById(R.id.login_password_input);


        String email = _emailField.getText().toString();
        String password = _passwordField.getText().toString();


        Intent registerIntent = new Intent(LoginActivity.this, WelcomeActivity.class);
        startActivity(registerIntent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Incorrect email or password!", Toast.LENGTH_LONG).show();
    }

    private boolean validate() {
        Log.d("LoginActivity", "Validate");
        boolean valid = true;

        //Grab email and password input from login screen
        _emailField = (EditText) findViewById(R.id.login_email_input);
        _passwordField = (EditText) findViewById(R.id.login_password_input);

        String email = _emailField.getText().toString();
        String password = _passwordField.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailField.setError("Enter a valid email address");
            valid = false;
        } else {
            _emailField.setError(null);
        }

        if (password.isEmpty()) {
            _passwordField.setError("Password is empty");
            valid = false;
        } else {
            _passwordField.setError(null);
        }

        return valid;
    }
}
