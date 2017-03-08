package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.Condition;
import gatech.cs2340.android.drop.model.Model;
import gatech.cs2340.android.drop.model.PurityReport;


public class EditPurityReport extends AppCompatActivity {

    private EditText reportDate;
    private EditText reportNo;
    private EditText reportName;
    private EditText Latitude;
    private EditText Longitude;
    private Spinner conditionSpinner;
    private EditText virusPPM;
    private EditText contaminantPPM;

    private PurityReport purityReport;
    private String condition;
    private static int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_report);

        reportDate = (EditText) findViewById(R.id.reportDate);
        reportNo = (EditText) findViewById(R.id.reportNo);
        reportName = (EditText) findViewById(R.id.reportName);
        Latitude = (EditText) findViewById(R.id.Latitude);
        Longitude = (EditText) findViewById(R.id.Longitude);
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        virusPPM = (EditText) findViewById(R.id.virusPPM);
        contaminantPPM = (EditText) findViewById(R.id.contaminantPPM);

        reportNo.setText(Integer.toString(i++));

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Condition.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(adapter);

        PurityReport purityReport= new PurityReport();

    }

    protected void onAddPressed(View view) {
        Log.d("Edit", "Add Purity Report");
        Model model = Model.getInstance();


        purityReport.set_date(reportNo.getText().toString());
        purityReport.set_name(reportName.getText().toString());
        purityReport.set_latitude(Latitude.getText().toString());
        purityReport.set_longitude(Longitude.getText().toString());
        purityReport.set_condition((Condition) conditionSpinner.getSelectedItem());
        purityReport.set_virus(virusPPM.getText().toString());
        purityReport.set_Contaminant(contaminantPPM.getText().toString());


        model.addSourceReport(purityReport);
        Toast.makeText(EditPurityReport.this, "Purity Report added", Toast.LENGTH_LONG).show();

        startActivity(new Intent(EditPurityReport.this, SourceReportActivity.class));
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

        condition= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        condition = "NA";
    }
}