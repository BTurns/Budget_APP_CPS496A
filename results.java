package lonie1gr.cps496.cmich.edu.ourapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import static android.R.attr.defaultValue;

public class results extends MainActivity implements View.OnClickListener {
    Button b1;
    private int[] yData = {0,0,0,0,0};
    private String[] xData ={"","","","",""};
    PieChart pieChart;
    final static int UPDATE_RESULT_CODE = 1;
    TextView Funbud;
    String funbudget;
    int gas, phone, food, internet, Savings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        //button for returning to home screen
        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(this);
        //Intent used to get income minus expenses from main screen
        Intent myIntent = getIntent();
        Bundle b = myIntent.getExtras();
        //setting income to camefrom
        int CameFrom = b.getInt("finaltext");
        //splits the budget into 3 equal amounts not the final math for splitting expenses. Still need to format to only two decimal places.
        if(CameFrom > 100){
            food = 100;
            yData[0] = food;
            TextView budgetfood = (TextView) findViewById(R.id.textView5);
            String foodbud = Double.toString(food);
            budgetfood.setText("  Food: " + foodbud);
           CameFrom= CameFrom - 100;
        }
            else{
            food = CameFrom;
            yData[0] = food;
            TextView budgetfood = (TextView) findViewById(R.id.textView5);
            String foodbud = Double.toString(food);
            budgetfood.setText("  Food: " + foodbud);
            CameFrom = 0;
        }

        if(CameFrom > 60){
            phone = 60;
            yData[1] = phone;
            TextView phonebud = (TextView) findViewById(R.id.textView12);
            String phonebudget = Double.toString(phone);
            phonebud.setText(" Phone: " + phonebudget);
            CameFrom= CameFrom - 60;
        }
        else {
            phone = CameFrom;
            yData[1] = phone;
            TextView phonebud = (TextView) findViewById(R.id.textView12);
            String phonebudget = Double.toString(phone);
            phonebud.setText(" Phone: " + phonebudget);
            CameFrom = 0;
        }

        if(CameFrom > 45){
            internet = 45;
            yData[2] = internet;

            TextView Internetbud = (TextView) findViewById(R.id.textView8);
            String interbud = Double.toString(internet);
            Internetbud.setText("Internet: " + interbud);
            CameFrom= CameFrom - 100;
        }
        else{
            internet = CameFrom;
            yData[2] = internet;

            TextView Internetbud = (TextView) findViewById(R.id.textView8);
            String interbud = Double.toString(internet);
            Internetbud.setText("Internet: " + interbud);
            CameFrom = 0;
        }

        if(CameFrom > 40){
            gas = 40;
            yData[3] = gas;

            TextView gasbud = (TextView) findViewById(R.id.textView13);
            String gasbudget = Double.toString(gas);
            gasbud.setText("Gas: " + gasbudget);
            CameFrom= CameFrom - 40;
        }
        else{
            gas = CameFrom;
            yData[3] = gas;

            TextView gasbud = (TextView) findViewById(R.id.textView13);
            String gasbudget = Double.toString(gas);
            gasbud.setText("Gas: " + gasbudget);
            CameFrom = 0;
        }

        if(CameFrom >= 0){
            Savings = CameFrom;
            yData[4] = Savings;
            Funbud = (TextView) findViewById(R.id.textView9);
            funbudget = Double.toString(Savings);
            Funbud.setText("Savings: " + funbudget);
        }







        xData[0] = "Food";
        xData[1]= "Phone";
        xData[2] = "Internet";
        xData[3] = "Gas";
        xData[4] = "Savings";
        pieChart= (PieChart) findViewById(R.id.idPieChart);
        addDataSet();
        //set the budget results with the three fields below







    }
    // click event for returning to home.
    public void onClick(View v) {
        if (b1.isPressed()) {
            Intent j = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(j);
            setContentView(R.layout.activity_main);
        }

    }
    public void addDataSet(){
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i <yData.length; i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }

        for(int i = 1; i <xData.length; i++){
            xEntrys.add(xData[i]);
        }
        PieDataSet pieDataSet= new PieDataSet(yEntrys, "Spending");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setColors(getResources().getColor(R.color.red),getResources().getColor(R.color.coolBlue),
                getResources().getColor(R.color.yellow), getResources().getColor(R.color.limegreen),getResources().getColor(R.color.purple));

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    public void updateSavings(View v) {
        Intent update = new Intent(this, UpdateSavings.class);
        startActivityForResult(update, UPDATE_RESULT_CODE);
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == UPDATE_RESULT_CODE) {
            if(resultCode == RESULT_OK) {
                double savings = Double.parseDouble(funbudget);
                double resultValue = data.getDoubleExtra("value", defaultValue);
                double newSavings = savings + resultValue;

                Funbud.setText(" Savings:  " + Double.toString(newSavings));
            }
        }
    }
}