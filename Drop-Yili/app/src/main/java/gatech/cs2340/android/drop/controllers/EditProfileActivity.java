package gatech.cs2340.android.drop.controllers;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import gatech.cs2340.android.drop.R;

import static gatech.cs2340.android.drop.controllers.RegisterActivity.legalUserType;

/**
 * Created by Jayden Sun on 3/6/17.
 */

public class EditProfileActivity  extends AppCompatActivity {
    private static final String TAG = "EditProfileActivity";

    private Spinner _userTypeSpinner;
    private EditText _nameText;
    private EditText _emailText;
    private EditText _passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        //Set Spinner
        _userTypeSpinner = (Spinner) findViewById(R.id.edit_profile_type_spinner);

        //show in spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this,R.layout.spinner_item, legalUserType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _userTypeSpinner.setAdapter(typeAdapter);

        //change spinner triangle color to white
        _userTypeSpinner.getBackground().setColorFilter(ContextCompat.getColor(this,R.color.white),
                PorterDuff.Mode.SRC_ATOP);

        //get email and password
        _passwordText = (EditText) findViewById(R.id.edit_profile_password_input);
        _emailText = (EditText) findViewById(R.id.edit_profile_email_input);
        _nameText = (EditText) findViewById(R.id.edit_profile_name_input);

        Button saveProfile = (Button) findViewById(R.id.edit_profile_save_button);
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    updateFireBaseAccount();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Invalid email or password!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validate() {
        Log.d(TAG, "Validate");
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String name = _nameText.getText().toString();

        if (name.isEmpty()) {
            _nameText.setError("Enter a valid name");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty()) {
            _passwordText.setError("Password is empty");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void updateFireBaseAccount() {

        String password = _passwordText.getText().toString().trim();
        String email = _emailText.getText().toString().trim();

        if (password.length() > 0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String newPassword = password;

            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User password updated.");
                            }
                        }
                    });
        }

        if (email.length() > 0 && email.contains(".") && email.contains("@")) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            user.updateEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User email address updated.");
                            }
                        }
                    });
        }

        Intent settingIntent = new Intent(EditProfileActivity.this, SettingActivity.class);
        startActivity(settingIntent);
    }
}