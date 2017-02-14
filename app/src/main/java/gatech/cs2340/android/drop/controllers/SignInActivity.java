package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import gatech.cs2340.android.drop.R;

public class SignInActivity extends AppCompatActivity {

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
    }
}
