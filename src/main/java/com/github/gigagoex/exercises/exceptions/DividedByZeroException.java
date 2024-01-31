package com.github.gigagoex.exercises.exceptions;

/**
 * Exception for division by 0
 */
public class DividedByZeroException extends Exception{
    public DividedByZeroException(){
        super("Divided by zero!");
    }
}
