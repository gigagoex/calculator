package com.github.gigagoex.exercises.exceptions;

public class InvalidInputFormatException extends Exception{
    public InvalidInputFormatException(String str){
        super("Invalid Input: " + str);
    }
}