package com.github.gigagoex.exercises;

import com.github.gigagoex.exercises.exceptions.DividedByZeroException;
import com.github.gigagoex.exercises.exceptions.InvalidInputFormatException;

import java.util.Scanner;

/* com.github.gigagoex.exercises.Main program
 * Als Anwender des Terminals möchte ich einfache Rechenoperationen im Terminal ausführen können.
 * Als Anwender reichen mir dazu die Grundoperatoren der
 *      Addition
 *      Substraktion
 *      Multiplikation
 *      Division
 * Es wird nur eine einzige Operation angegeben
 * -> all calculations are based on two inputs and yield one output
 *
 *
 * Beispiele
 * calc '4+3'
 * 7
 *
 * calc '2.5 + 1' -> double input, ignore whitespace
 * 3.5 -> double output
 *
 * ...
 *
 * calc '5 / 2'
 * 2.5 -> double
 * -> Error handling for div by 0
 *
 * -> Every input must contain Number + operator + Number -> Error handling for incorrect input
 *
 *
 * 1. Console output: 'calc'
 * 2. Console input: String
 * 3. Check if string is valid -> Number, Operator, Number
 * 4. Identify Operator (might be done in step before)
 * 5. Calculate Result
 * 6. Console output: result
 *
 * com.github.gigagoex.exercises.Main loop should contain three methods:
 * readCurrentLine()
 * calculateFromString()
 * printResult()
 *
 * Rules:
 * First exponent second * / third + -
 * from left to right
 */

/**
 * Entry Point of the program.
 *
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Stop calculator by typing 'stop'");
        while (true){
            String currentLine = readLine();
            if (currentLine.equals("stop")){
                break;
            }
            try{
                String termString = InputStringModifier.extractTerm(currentLine);
                Term term = new Term(termString);
                double result = calculator.calculate(term);
                System.out.println(result);
            } catch (Exception e){
                if (e.getClass() == DividedByZeroException.class){
                    System.out.println("Divided By Zero!");
                } else if (e.getClass() == InvalidInputFormatException.class){
                    System.out.println(e.getMessage());
                }
                else {
                    System.out.println("An error occurred:" + e);
                    break;
                }
            }
        }
    }
    private static final Scanner scanner = new Scanner(System.in);
    private static final Calculator calculator = new Calculator();
    static String readLine(){
        System.out.print("calc ");
        return Main.scanner.nextLine();
    }
}
