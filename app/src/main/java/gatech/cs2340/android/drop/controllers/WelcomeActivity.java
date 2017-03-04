package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import gatech.cs2340.android.drop.R;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        //hide action bar
        getSupportActionBar().hide();

        //welcome register button onClick
        Button register = (Button) findViewById(R.id.welcome_register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Register", "Register Button Clicked");
                Intent registerIntent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        //welcome login button clicked
        Button login = (Button) findViewById(R.id.welcome_login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Sign In", "Welcome Sign In Button Clicked");
                Intent loginInIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(loginInIntent);
            }
        });
    }
}
