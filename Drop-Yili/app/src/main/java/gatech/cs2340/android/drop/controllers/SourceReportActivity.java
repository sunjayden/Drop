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
                Intent sourceIntent = new Intent(SourceReportActivity.this, EditSourceReport.class);
                startActivity(sourceIntent);
            }
        });
    }

}
