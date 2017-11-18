package com.example.brandon.budgetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DisplayBudget extends AppCompatActivity {

    double mFunds, mEarnings, mRent, mUtilites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_budget);

        Bundle b = getIntent().getExtras();
        mFunds = b.getDouble("fundsValue");
        mEarnings = b.getDouble("earningsValue");
        mRent = b.getDouble("rentValue");
        mUtilites = b.getDouble("utilitiesValue");

    }
}
