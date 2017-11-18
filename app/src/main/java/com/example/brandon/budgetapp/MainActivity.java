package com.example.brandon.budgetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText funds, earnings, rent, utilities;
    double mFunds, mEarning, mRent, mUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        funds = findViewById(R.id.funds);
        earnings = findViewById(R.id.earnings);
        rent = findViewById(R.id.rent);
        utilities = findViewById(R.id.utilities);
    }

    public void doneClick(View view) {
        mFunds = Double.parseDouble(funds.getText().toString());
        mEarning = Double.parseDouble(earnings.getText().toString());
        mRent = Double.parseDouble((rent.getText().toString()));
        mUtilities = Double.parseDouble(utilities.getText().toString());

        Intent intent = new Intent(this, DisplayBudget.class);
        Bundle b = new Bundle();
        b.putDouble("fundsValue", mFunds);
        b.putDouble("earningsValue", mEarning);
        b.putDouble("rentValue", mRent);
        b.putDouble("utilitiesValue", mUtilities);
        intent.putExtras(b);
        startActivity(intent);
    }
}
