package gatech.cs2340.android.drop.controllers;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.AccountType;

/**
 * Created by jaydensun on 2/12/17.
 */

public class SignUpActivity extends AppCompatActivity {

    private Spinner acctTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        TextView signIn = (TextView) findViewById(R.id.member_already);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        //Set Spinner
        acctTypeSpinner = (Spinner) findViewById(R.id.account_type_spinner);

        //temp
        List<AccountType> legalAcctType = Arrays.asList(AccountType.values());

        //show in spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this,R.layout.spinner_item, legalAcctType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acctTypeSpinner.setAdapter(typeAdapter);

        //change spinner triangle color to white
        acctTypeSpinner.getBackground().setColorFilter(ContextCompat.getColor(this,R.color.white), PorterDuff.Mode.SRC_ATOP);

    }
}
