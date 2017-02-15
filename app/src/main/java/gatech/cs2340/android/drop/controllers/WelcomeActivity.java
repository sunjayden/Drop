package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import gatech.cs2340.android.drop.R;

/**
 * Created by jaydensun on 2/13/17.
 */

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        //hide action bar
        getSupportActionBar().hide();

        Button register = (Button) findViewById(R.id.home_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Register", "Register Button Clicked");
                Intent registerIntent = new Intent(WelcomeActivity.this, SignUpActivity.class);
                startActivity(registerIntent);
            }
        });

        Button signIn = (Button) findViewById(R.id.home_sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Sign In", "Sign In Button Clicked");
                Intent signInIntent = new Intent(WelcomeActivity.this, SignInActivity.class);
                startActivity(signInIntent);
            }
        });
    }
}
