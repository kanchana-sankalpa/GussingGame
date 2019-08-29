package com.example.guessinggame;

public class InputValidation {

    public static boolean input(int x) {


        if(0<x && x<100) {

        }else{
            throw new RuntimeException(String.valueOf(R.string.valid));
        }

        return true;

    }
}
