package com.example.kyle.crunchtime;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    public static double[] calorieCounts = {100.0, 350.0, 200.0, 225.0, 25.0, 25.0, 10.0, 100.0, 12.0, 20.0, 12.0, 13.0, 15.0};
    public static int[] exerciseIDs = {R.id.calories, R.id.ex1, R.id.ex2, R.id.ex3, R.id.ex4, R.id.ex5, R.id.ex6,
            R.id.ex7,R.id.ex8,R.id.ex9,R.id.ex10,R.id.ex11, R.id.ex12};
    public static List<String> exercises = Arrays.asList("Calories", "Push-ups", "Sit-ups", "Squats", "Leg-lifts", "Plank",
            "Jumping Jacks", "Pull-ups", "Cycling", "Walking", "Jogging", "Swimming", "Stair-Climbing");
    public static List<String> exerciseType = Arrays.asList("Calories", "reps", "reps", "reps", "min", "min", "min",
            "reps", "min", "min", "min", "min", "min");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView input = (TextView) findViewById(R.id.repsInput);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculate(null);
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.exercises_spinner);
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this,
                R.array.exercises, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parentView, View selectedItemView, int position, long id) {
                TextView inputUnit;
                String exercise;
                int exerciseIndex;

                inputUnit = (TextView) findViewById(R.id.inputUnits);
                exercise = parentView.getItemAtPosition(position).toString();
                exerciseIndex = exercises.indexOf(exercise);
                inputUnit.setText(exerciseType.get(exerciseIndex));
                calculate(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spinner.setAdapter(adapter);

    }
    public void calculate(View view) {
        EditText repsInput;
        Spinner exerInput;
        double reps, newVal;
        TextView temp;
        String exercise, repsText;
        int exerciseIndex;

        repsInput = (EditText)findViewById(R.id.repsInput);
        exerInput = (Spinner) findViewById(R.id.exercises_spinner);
        repsText =  repsInput.getText().toString();
        if(repsText.equals("")) {
            for (int i = 0; i < 13; i++) {
                temp = (TextView) findViewById(exerciseIDs[i]);
                temp.setText("");
            }
            return;
        }
        reps = Double.parseDouble(repsText);
        exercise = exerInput.getSelectedItem().toString();
        exerciseIndex = exercises.indexOf(exercise);

        newVal = reps / calorieCounts[exerciseIndex] * calorieCounts[0];
        temp = (TextView) findViewById(R.id.calories);
        temp.setText("You will burn " + Integer.toString((int) newVal) + " Calories. :D");

        for (int i = 1; i < 13; i++) {
            temp = (TextView) findViewById(exerciseIDs[i]);
            newVal = reps / calorieCounts[exerciseIndex] * calorieCounts[i];
            temp.setText(Integer.toString((int) newVal) + " " + exerciseType.get(i));

        }
    }
}
