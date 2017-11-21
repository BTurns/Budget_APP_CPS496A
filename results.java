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

public class results extends MainActivity implements View.OnClickListener {
    Button b1;
    private int[] yData = {0,0,0};
    private String[] xData ={"","",""};
    PieChart pieChart;

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
        int food = Integer.valueOf(((CameFrom*60)/100));
        int Savings = Integer.valueOf(((CameFrom*10)/100));
        int internet = Integer.valueOf(((CameFrom*30)/100));
        yData[0] = food;
        yData[1] = Savings;
        yData[2] = internet;
        xData[0] = "Food";
        xData[1]= "Savings";
        xData[2] = "Internet";
        pieChart= (PieChart) findViewById(R.id.idPieChart);
        addDataSet();
        //set the budget results with the three fields below
        TextView budgetfood = (TextView) findViewById(R.id.textView5);
        String foodbud = Double.toString(food);
        budgetfood.setText("  Food Budget:  " + foodbud);

        TextView Internetbud = (TextView) findViewById(R.id.textView8);
        String interbud = Double.toString(internet);
        Internetbud.setText("  Internet Budget:  " + interbud);

        TextView Funbud = (TextView) findViewById(R.id.textView9);
        String funbudget = Double.toString(Savings);
        Funbud.setText(" Savings:  " + funbudget);



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

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}