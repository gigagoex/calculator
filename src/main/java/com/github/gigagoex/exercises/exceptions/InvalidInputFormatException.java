package com.github.gigagoex.exercises.exceptions;

/**
 * Exception for Inputs which deviate from the required input format
 */
public class InvalidInputFormatException extends Exception{
    public InvalidInputFormatException(String str){
        super("Invalid Input: " + str);
    }
}
