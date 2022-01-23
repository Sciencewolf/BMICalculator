package com.sciencewolf.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //Class variables
    private TextView resultText;
    private Button calculate;
    private RadioButton buttonFemale;
    private RadioButton buttonMale;
    private EditText ageText;
    private EditText feetText;
    private EditText inchesText;
    private EditText weightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButton();
    }

    private void findViews(){
        resultText = findViewById(R.id.text_result);
        buttonMale = findViewById(R.id.radio_button_male);
        buttonFemale = findViewById(R.id.radio_button_female);
        ageText = findViewById(R.id.edit_text_age);
        feetText = findViewById(R.id.edit_text_feet);
        inchesText = findViewById(R.id.edit_text_inches);
        weightText = findViewById(R.id.edit_text_weight);
        calculate = findViewById(R.id.button_calculate);
    }

    private void setupButton() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultOfBMI = calculateBMI();
                String age = ageText.getText().toString();
                int convertAge = Integer.parseInt(age);

                if(convertAge >= 18) displayResult(resultOfBMI);
                else {
                    displayGuidance(resultOfBMI);
                }
            }
        });
    }

    private double calculateBMI() {

        String feet = feetText.getText().toString();
        String inches = inchesText.getText().toString();
        String weight = weightText.getText().toString();


        int convertFeet = Integer.parseInt(feet);
        int convertInches = Integer.parseInt(inches);
        int convertWeight = Integer.parseInt(weight);

        int totalInches = (convertFeet * 12) + convertInches;

        //Convert inches to meter
        double heightInMeters = totalInches * 0.0254;

        //Formula to find BMI
        return convertWeight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(bmi < 18.5){
            //underweight
            fullResultString = bmiTextResult + " - You are underweight";
        }else if(bmi > 25){
            //overweight
            fullResultString = bmiTextResult + " - You are overweight";
        }else{
            //normal weight
            fullResultString = bmiTextResult + " - You are a healthy weight";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double resultOfBMI) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(resultOfBMI);
        String fullResultString;

        if(buttonMale.isChecked()){
            fullResultString = bmiTextResult + " - As you under 18, go to the Doc! Male";
        }else if(buttonFemale.isChecked()){
            fullResultString = bmiTextResult + " - As you under 18, go to the Doc! Female";
        }else{
            fullResultString = bmiTextResult + " - As you under 18, go to the Doc! Unchecked!" ;
        }
        resultText.setText(fullResultString);
    }
}