package com.example.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        }

    public static int input(int x){

        if(0<x && x<100) {

        }else{
            throw new RuntimeException(String.valueOf(R.string.valid));
        }

        return 0;

    }
}
