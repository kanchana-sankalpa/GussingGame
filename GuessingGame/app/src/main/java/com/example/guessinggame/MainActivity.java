package com.example.guessinggame;

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

    Button guess,quit;
    EditText number;
    private static final Random rand = new Random();
    TextView generated,correct,guessed,attempt;
    LinearLayout evaluate,no_of_attempts;
    int attemptno,quitn;

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
        attempt = findViewById(R.id.attempt);
        no_of_attempts = findViewById(R.id.no_of_attempts);


        guess.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                String input = number.getText().toString();

                if(InputValidation.input(Integer.parseInt(input))){
                    evaluate.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(MainActivity.this,"Please input a number",Toast.LENGTH_SHORT).show();
                    evaluate.setVisibility(View.GONE);
                }


                }
        });


        }


}
