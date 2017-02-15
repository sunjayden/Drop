package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.AccountType;
import gatech.cs2340.android.drop.model.Admin;
import gatech.cs2340.android.drop.model.Manager;
import gatech.cs2340.android.drop.model.Model;
import gatech.cs2340.android.drop.model.User;

import static android.R.attr.accountType;

public class SignInActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        TextView signUp = (TextView) findViewById(R.id.sign_up_link);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

        TextView forgotPassword = (TextView) findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passwordIntent = new Intent(SignInActivity.this, RetrievePasswordActivity.class);
                startActivity(passwordIntent);
            }
        });

        /**
         * Grab the dialog widgets so we can get info for later
         */
        emailField = (EditText) findViewById(R.id.sign_in_email);
        passwordField = (EditText) findViewById(R.id.sign_in_password);

        /**
         * Sign In Button
         */
        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                Log.d("Button", "Sign In Button");
                onSignInPressed(view);
            }
        });
    }

    /**
     * Button handler for the add new student button
     * @param view the button
     */
    protected void onSignInPressed(View view) {
        Log.d("Button", "Sign In Pressed");
        Model model = Model.getInstance();

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (model.isUser(email, password)) {
            Intent mainIntent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(mainIntent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid email or password, please try again!",
                    Toast.LENGTH_SHORT).show();
        }

//        if (accountType.equals(AccountType.ADMIN)) {
//            model.addUser(new Admin(name, email, password));
//        } else if (accountType.equals(AccountType.MANAGER)) {
//            model.addUser(new Manager(name, email, password));
//        } else if (accountType.equals(AccountType.WORKER)) {
//            model.addUser(new User(name, email, password));
//        } else {
//            model.addUser(new User(name, email, password));
//        }

        finish();
    }
}
