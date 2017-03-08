package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.Model;
import gatech.cs2340.android.drop.model.SourceReport;
import gatech.cs2340.android.drop.model.Type;
import gatech.cs2340.android.drop.model.Water;

public class EditSourceReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView idnumber;
    private EditText date;
    private EditText namefield;
    private EditText reportNumber;
    private EditText location;
    private Spinner waterSpinner;
    private Spinner conditionSpinner;

    private SourceReport sourceReport;
    private String _loc;
    private static int i = 1;

    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_source_report);

        idnumber = (TextView) findViewById(R.id.sourceReport_reportNumber);
        date = (EditText) findViewById(R.id.edit_sourceReport_date);
        namefield = (EditText) findViewById(R.id.edit_sourceReport_Name);
        reportNumber = (EditText) findViewById(R.id.edit_sr_rn);
        location = (EditText) findViewById(R.id.edit_sr_location);
        waterSpinner = (Spinner) findViewById(R.id.edit_sr_type);
        conditionSpinner = (Spinner) findViewById(R.id.edit_sr_waterCondition);


        /*
          Set up the adapter to display the allowable locations
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gatech.cs2340.android.drop.model.Type.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterSpinner.setAdapter(adapter);

        /*
          Set up the adapter to display the allowable type of water
         */
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Water.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(adapter2);


        /*
        If a source report has been passed in, this was an edit, if not, this is a new add
         */
        if (getIntent().hasExtra(SRFragment.ARG_SR_ID)) {
            sourceReport = (SourceReport) getIntent().getParcelableExtra(SRFragment.ARG_SR_ID);
            waterSpinner.setSelection(SourceReport.findConditionPosition(sourceReport.getWater()));
            conditionSpinner.setSelection(SourceReport.findWaterPosition(sourceReport.getType()));
            editing = true;
        } else {
            sourceReport = new SourceReport();
            editing = false;
        }
        namefield.setText(sourceReport.getName());
        idnumber.setText("" + sourceReport.getId());
        reportNumber.setText(Integer.toString(i));
        i++;
        date.setText(sourceReport.getDate());
        location.setText(sourceReport.getLocation());
    }

       /* Source Report Extra , Not implemented ?*/

    /**
     * Button handler for the add new source Report button
     * @param view the button
     */
    protected void onAddPressed(View view) {
        Log.d("Edit", "Add Source Report");
        Model model = Model.getInstance();

        String name = namefield.getText().toString();
        if (name.isEmpty()) {
            return;
        }

        Log.d("Edit", "Got new sourceReport" + namefield.getText().toString());

        sourceReport.setName(namefield.getText().toString());
        sourceReport.setDate(date.getText().toString());
        sourceReport.setType((Type) waterSpinner.getSelectedItem());
        sourceReport.setWater((Water) conditionSpinner.getSelectedItem());


        if (!editing) {
            model.addSourceReport(sourceReport);
            Log.d("Edit", "here");
        } else {
            model.replaceSourceReport(sourceReport);
            Log.d("Edit", "There");
        }

        Toast.makeText(EditSourceReport.this, "Source Report added", Toast.LENGTH_LONG).show();

        startActivity(new Intent(EditSourceReport.this, SourceReportActivity.class));
        finish();
        /* source report sth. not implemented*/
    }

    /**
     *  On cancel button
     * @param view the button
     */
    protected void onCancelPressed(View view) {
        Log.d("Edit", "Cancel source report");
        finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        _loc= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        _loc = "NA";
    }
//
}
