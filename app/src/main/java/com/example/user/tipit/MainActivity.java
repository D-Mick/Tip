package com.example.user.tipit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextAmount, editTextPercent;
    private Button tipButton, clearButton;
    private TextView calculatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.amount_id);
        editTextPercent = findViewById(R.id.tip_percent_edit_text);
        tipButton = findViewById(R.id.calculate_tip_button);
        calculatedText = findViewById(R.id.tip_calculated_text);
        clearButton = findViewById(R.id.clear_tip_button);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
                calculatedText.setVisibility(View.GONE);
                editTextAmount.getText().clear();
                editTextPercent.getText().clear();
            }
        });

        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipButtonClicked();
            }
        });

    }

    private void tipButtonClicked(){
        String amount, percent;
        amount = editTextAmount.getText().toString();
        percent = editTextPercent.getText().toString();

        if(amount.isEmpty() || percent.isEmpty()){
            Toast.makeText(this, "Empty text field", Toast.LENGTH_SHORT).show();
        }else{
            double tip = getTipValue(Integer.parseInt(amount) , Integer.parseInt(percent));
            showTipMessage(tip);
        }
    }

    private double getTipValue(int amount, int percent){
        double percent_amount = (double) percent / 100;
        double tip = percent_amount * amount;
        return tip;
    }

    private void showTipMessage(double tip){
        calculatedText.setText("Tip the waiter "+ String.valueOf(tip));
        calculatedText.setVisibility(View.VISIBLE);
        clearButton.setVisibility(View.VISIBLE);
    }
}
