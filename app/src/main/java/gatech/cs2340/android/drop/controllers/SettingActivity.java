package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.User;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = "SettingActivity";
    private DatabaseReference mDatabase;
    //private FireBaseUser user;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //hide action bar
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_view);
        View view = bottomNavigationView.findViewById(R.id.ic_setting);
        view.performClick();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_report:
                        startActivity(new Intent(SettingActivity.this, SourceReportActivity.class));
                        break;
                    case R.id.ic_map:
                        startActivity(new Intent(SettingActivity.this, MapsActivity.class));
                        break;
                    case R.id.ic_add:
                        startActivity(new Intent(SettingActivity.this, PurityReportActivity.class));
                        break;
                    case R.id.ic_graph:
                        startActivity(new Intent(SettingActivity.this, HistoricalActivity.class));
                        break;
                    case R.id.ic_setting:
                        startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }

        });

        final ImageButton submitSp = (ImageButton) findViewById(R.id.security_log);


        //get profile from database
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        final String uid = user.getUid();
        // Read from the database
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User userInfo = dataSnapshot.child(uid).getValue(User.class);
                if (!userInfo._userType.equalsIgnoreCase("Admin")) {
                    submitSp.setEnabled(false);
                    //submitSp.setBackgroundColor(Color.parseColor("#D3D3D3"));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //Edit profile button clicked
        ImageButton editProfile = (ImageButton) findViewById(R.id.edit_profile_icon);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Edit profile Button Clicked");
                Intent editProfileInIntent = new Intent(SettingActivity.this, EditProfileActivity.class);
                startActivity(editProfileInIntent);
            }
        });

        //Security SLog button clicked
        ImageButton securityLog = (ImageButton) findViewById(R.id.security_log);
        securityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Security SLog Button Clicked");
                Intent logIntent = new Intent(SettingActivity.this, SecurityLogActivity.class);
                startActivity(logIntent);
//                Toast.makeText(SettingActivity.this, "Security SLog not yet implemented!",
//                        Toast.LENGTH_LONG).show();
            }
        });

        //SLog out button clicked
        ImageButton logout = (ImageButton) findViewById(R.id.setting_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "SLog Out Button Clicked");
                FirebaseAuth.getInstance().signOut();
                Intent editProfileInIntent = new Intent(SettingActivity.this, WelcomeActivity.class);
                startActivity(editProfileInIntent);
            }
        });
    }
}
