package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import gatech.cs2340.android.drop.R;

public class SourceReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_report);


        Button editSourceReport = (Button) findViewById(R.id.Edit_SourceReportAdd);
        editSourceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("add Source Report", "Go to edit source Report");
                Intent intent = new Intent(SourceReportActivity.this, EditSourceReport.class);
                startActivity(intent);
            }
        });

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.  Pass the course info to
//            //the fragment
//            Bundle arguments = new Bundle();
//            arguments.putInt(SRFragment.ARG_SR_ID,
//                    getIntent().getIntExtra(SRFragment.ARG_SR_ID, 0));
//
//            SRFragment fragment = new SRFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.SR_detail_container, fragment)
//                    .commit();
//        }


    }






}
