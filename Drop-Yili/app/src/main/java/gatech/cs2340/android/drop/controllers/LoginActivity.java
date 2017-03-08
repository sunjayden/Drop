package gatech.cs2340.android.drop.controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import gatech.cs2340.android.drop.R;

import static gatech.cs2340.android.drop.R.layout.login;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText _emailField;
    private EditText _passwordField;

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        //Retrieve Password link
        TextView retrievePassword = (TextView) findViewById(R.id.login_forgot_password);
        retrievePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passwordIntent = new Intent(LoginActivity.this, RetrievePasswordActivity.class);
                startActivity(passwordIntent);
            }
        });

        //Sign up link
        TextView signUp = (TextView) findViewById(R.id.login_sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(signUpIntent);
            }
        });
    }

    private void login() {
        Log.d(TAG, "Login Button Clicked");

        //Grab email and password input from login screen
        _emailField = (EditText) findViewById(R.id.login_email_input);
        _passwordField = (EditText) findViewById(R.id.login_password_input);

        if (!validate()) {
            onLoginFailed();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        String email = _emailField.getText().toString().trim();
        String password = _passwordField.getText().toString().trim();

        //FireBase code
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, ReportActivity.class));
                            finish();
                        } else {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Incorrect email or password!", Toast.LENGTH_LONG).show();
    }

    private boolean validate() {
        Log.d(TAG, "Validate");
        boolean valid = true;

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