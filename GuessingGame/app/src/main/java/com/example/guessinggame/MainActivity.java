package com.example.guessinggame;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnGuess, btnQuit;
    EditText editTxtGuessedNumber;
    private static final Random rand = new Random();
    TextView txtViewGeneratred, txtViewDecision, txtViewGuessed, txtViewNumberOfAttempts;
    LinearLayout evaluate, no_of_attempts;
    int numberOfAttempts = 0, numberOfQuitClicked = 0;
    int randInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuess = findViewById(R.id.btnGuess);
        btnQuit = findViewById(R.id.btnQuit);
        editTxtGuessedNumber = findViewById(R.id.editTxtGuessedNumber);
        txtViewGeneratred = findViewById(R.id.txtViewGeneratred);
        txtViewDecision = findViewById(R.id.txtViewDecision);
        evaluate = findViewById(R.id.evaluate);
        txtViewGuessed = findViewById(R.id.txtViewGuessed);
        evaluate.setVisibility(View.GONE);
        txtViewNumberOfAttempts = findViewById(R.id.txtViewNumberOfAttempts);
        no_of_attempts = findViewById(R.id.no_of_attempts);


        btnGuess.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                setAttempt();

                String input = editTxtGuessedNumber.getText().toString();

                if (InputValidation.input(Integer.parseInt(input))) {
                   //      randInt = rand.nextInt(99)+1;

                    if (InputEvaluation.evaluate(Integer.parseInt(input), randInt)) {

                        displayResult(Integer.parseInt(input), randInt, numberOfQuitClicked, true);
                       no_of_attempts.setVisibility(View.VISIBLE);
                        btnGuess.setClickable(false);
                    } else {
                        displayResult(Integer.parseInt(input), randInt, numberOfQuitClicked, false);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please input a number between 0-100", Toast.LENGTH_SHORT).show();
                    evaluate.setVisibility(View.GONE);
                }


            }


        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGuess.setClickable(false);
                txtViewNumberOfAttempts.setText(""+numberOfAttempts);
                if(numberOfQuitClicked==0) {
                    Toast.makeText(MainActivity.this, "Click again to close application", Toast.LENGTH_SHORT).show();
                     no_of_attempts.setVisibility(View.VISIBLE);
                    numberOfQuitClicked++;
                }else{
                    finish();
                }
            }
        });

    }




    @SuppressLint({"NewApi", "SetTextI18n"})
        public void displayResult(int input, int rand, int attemptno, boolean result){
        evaluate.setVisibility(View.VISIBLE);
        txtViewGeneratred.setText(String.valueOf(rand));
        txtViewNumberOfAttempts.setText("" + attemptno);
        txtViewGuessed.setText(Integer.toString(input));

        if(result){
            txtViewDecision.setText(getText(R.string.correct));
            txtViewDecision.setTextColor(getColor(R.color.green));
        }else{
            txtViewDecision.setText(getText(R.string.wrong));
            txtViewDecision.setTextColor(getColor(R.color.red));
        }

    }

    /*




     */

    @SuppressLint("SetTextI18n")
    public void setAttempt(){
        numberOfAttempts++;
        txtViewNumberOfAttempts.setText(""+numberOfAttempts);

    }

/*

 */
}
