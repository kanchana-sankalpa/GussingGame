package com.example.guessinggame;

public class InputValidation {

    public static boolean input(int x) {

        try{
           if(1<x && x<100) {

            }else{
                throw new RuntimeException("Number should between 0-100");
            }

        }catch(Exception e){
                return false;
        }
        return true;
    }
}
