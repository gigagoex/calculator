package com.github.gigagoex.exercises;

import com.github.gigagoex.exercises.exceptions.InvalidInputFormatException;

public class InputStringModifier {

    public static String extractTerm(String line) throws InvalidInputFormatException {
        return simplifyString(cutoutExpression(line));
    }

    private static String cutoutExpression(String s) throws InvalidInputFormatException{
        int startOfString = s.indexOf("'");
        if (startOfString != 0){
            throw new InvalidInputFormatException("Missing leading quotation mark");
        }
        int endOfString = s.indexOf("'", startOfString + 1);
        if (endOfString <= startOfString){
            throw new InvalidInputFormatException("Missing final quotation mark");
        }
        return s.substring(startOfString + 1, endOfString);
    }

    private static String simplifyString(String s){
        //remove all whitespace
        return s.replaceAll(" ","");
    }
}
