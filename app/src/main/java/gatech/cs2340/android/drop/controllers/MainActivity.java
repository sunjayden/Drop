package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gatech.cs2340.android.drop.R;

/**
 * Created by jaydensun on 2/14/17.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button sign_out = (Button) findViewById(R.id.sign_out_button);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        final Button edit_profile = (Button) findViewById(R.id.edit__profile_button);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(MainActivity.this, EditProfileActivity.class);
                editIntent.putExtra("name", getIntent().getSerializableExtra("name"));
                editIntent.putExtra("email", getIntent().getSerializableExtra("email"));
                editIntent.putExtra("password", getIntent().getSerializableExtra("password"));
                startActivity(editIntent);
            }
        });
    }
}
