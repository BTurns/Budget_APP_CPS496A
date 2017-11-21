package lonie1gr.cps496.cmich.edu.ourapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.BudgetBt);
        b1.setOnClickListener(this);
    }
// click event that calculates final budget by removing expenses from total income. Then puts into intent J for access in other class.
    public void onClick(View v) {
        if (b1.isPressed()) {

            EditText income = (EditText) findViewById(R.id.IncomeInput);
            String inincome = income.getText().toString();
            int finalincome = Integer.valueOf(inincome);

            EditText inputrent = (EditText) findViewById(R.id.RentInput);
            String inrent = inputrent.getText().toString();
            int finalrent = Integer.valueOf(inrent);

            EditText inputgas = (EditText) findViewById(R.id.GasInput);
            String ingas = inputgas.getText().toString();
            int finalgas = Integer.valueOf(ingas);

            EditText inputelec = (EditText) findViewById(R.id.ElectricInput);
            String inelec = inputelec.getText().toString();
            int finalelec = Integer.valueOf(inelec);



           int finaltext=(finalincome-(finalrent+finalgas+finalelec));

            Intent j = new Intent(getApplicationContext(), results.class);
            j.putExtra("finaltext", finaltext);
            startActivity(j);
            setContentView(R.layout.activity_results);
        }
    }
}
