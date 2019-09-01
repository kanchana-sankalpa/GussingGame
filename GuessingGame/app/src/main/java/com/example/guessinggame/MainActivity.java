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

    Button guess, quit;
    EditText number;
    private static final Random rand = new Random();
    TextView generated, correct, guessed, attempt;
    LinearLayout evaluate, no_of_attempts;
    int attemptno = 0, quitn = 0;
    int randInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guess = findViewById(R.id.guess);
        quit = findViewById(R.id.quit);
        number = findViewById(R.id.number);
        generated = findViewById(R.id.generated);
        correct = findViewById(R.id.correct);
        evaluate = findViewById(R.id.evaluate);
        guessed = findViewById(R.id.guessed);
        evaluate.setVisibility(View.GONE);
        attempt = findViewById(R.id.attempt_no);
        no_of_attempts = findViewById(R.id.no_of_attempts);


        guess.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                setAttempt();

                String input = number.getText().toString();

                if (InputValidation.input(Integer.parseInt(input))) {
                   //      randInt = rand.nextInt(99)+1;

                    if (InputEvaluation.evaluate(Integer.parseInt(input), randInt)) {

                        displayResult(Integer.parseInt(input), randInt, attemptno, true);
                       no_of_attempts.setVisibility(View.VISIBLE);
                       guess.setClickable(false);
                    } else {
                        displayResult(Integer.parseInt(input), randInt, attemptno, false);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please input a number between 0-100", Toast.LENGTH_SHORT).show();
                    evaluate.setVisibility(View.GONE);
                }


            }


        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess.setClickable(false);
                attempt.setText(""+attemptno);
                if(quitn==0) {
                    Toast.makeText(MainActivity.this, "Click again to close application", Toast.LENGTH_SHORT).show();
                    no_of_attempts.setVisibility(View.VISIBLE);
                    quitn++;
                }else{
                    finish();
                }
            }
        });

    }




    @SuppressLint({"NewApi", "SetTextI18n"})
        public void displayResult(int input, int rand, int attemptno, boolean result){

            evaluate.setVisibility(View.VISIBLE);
            generated.setText(String.valueOf(rand));
        attempt.setText("" + attemptno);
        guessed.setText(Integer.toString(input));

        if(result){
            correct.setText(getText(R.string.correct));
            correct.setTextColor(getColor(R.color.green));
        }else{
            correct.setText(getText(R.string.wrong));
            correct.setTextColor(getColor(R.color.red));
        }
    }

    @SuppressLint("SetTextI18n")
    public void setAttempt(){
        attemptno++;
        attempt.setText(""+attemptno);
    }


}
