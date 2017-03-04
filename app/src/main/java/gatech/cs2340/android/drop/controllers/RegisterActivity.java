package gatech.cs2340.android.drop.controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.Model;
import gatech.cs2340.android.drop.model.UserType;

import static gatech.cs2340.android.drop.R.string.email;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText _nameField;
    private EditText _emailField;
    private EditText _passwordField;
    private Spinner _userTypeSpinner;
    private FirebaseAuth mAuth;

    private static final String TAG = "RegisterActivity";


    public static List<UserType> legalUserType = Arrays.asList(UserType.values());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //Set Spinner
        _userTypeSpinner = (Spinner) findViewById(R.id.user_type_spinner);

        //show in spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this,R.layout.spinner_item, legalUserType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _userTypeSpinner.setAdapter(typeAdapter);

        //change spinner triangle color to white
        _userTypeSpinner.getBackground().setColorFilter(ContextCompat.getColor(this,R.color.white),
                PorterDuff.Mode.SRC_ATOP);

        //Create Account button onClick
        Button login = (Button) findViewById(R.id.register_create_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    private void create() {
        Log.d(TAG, "Create Account Button Clicked");

        final Model model = Model.getInstance();

        //Grab email and password input from login screen
        _nameField = (EditText) findViewById(R.id.register_name_input);
        _emailField = (EditText) findViewById(R.id.register_email_input);
        _passwordField = (EditText) findViewById(R.id.register_password_input);
        _userTypeSpinner = (Spinner) findViewById(R.id.user_type_spinner);

        if (!validate()) {
            onRegisterFailed();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String name = _nameField.getText().toString().trim();
        final String email = _emailField.getText().toString().trim();
        final String password = _passwordField.getText().toString().trim();
        final UserType userType = (UserType)_userTypeSpinner.getSelectedItem();

        //FireBase code
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressDialog.dismiss();
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, R.string.acct_successful,
                                    Toast.LENGTH_LONG).show();
                            //add to model
                            model.registerAccount(name, email, password, userType);
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
    }

    public void onRegisterFailed() {
        Toast.makeText(getBaseContext(), "Incorrect name, email or password!", Toast.LENGTH_LONG).show();
    }

    private boolean validate() {
        Log.d(TAG, "Validate");
        boolean valid = true;

        String name = _nameField.getText().toString();
        String email = _emailField.getText().toString();
        String password = _passwordField.getText().toString();

        if (name.isEmpty()) {
            _passwordField.setError("Enter a valid name");
            valid = false;
        } else {
            _passwordField.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailField.setError("Enter a valid email address");
            valid = false;
        } else {
            _emailField.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordField.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordField.setError(null);
        }

        return valid;
    }
}
