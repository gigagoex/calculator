package com.github.gigagoex.exercises;

import com.github.gigagoex.exercises.exceptions.InvalidInputFormatException;
import java.util.ArrayList;

/**
 * This class contains a mathematical term.
 * The term is provided as String. The String must not contain white spaces or any other characters except for numbers, '^', '*', '/', '+', '-', '.'.
 * The class splits the input string into two ArrayLists. operatorList contains the operators of the term, operandList the operands.
 */
public class Term extends Operators {
    public Term(String termString) throws InvalidInputFormatException {
        this.inputString = termString;
        splitString();
        compareOperatorsWithOperands();
    }
    private final String inputString;
    private final ArrayList<String> operatorList = new ArrayList<>();
    private final ArrayList<String> operandList = new ArrayList<>();

    public ArrayList<String> getOperatorList() {
        return this.operatorList;
    }

    public ArrayList<String> getOperandList() {
        return this.operandList;
    }

    private void splitString() throws InvalidInputFormatException{
        //analyze input String char by char
        char[] chars = this.inputString.toCharArray();
        //must start either with sign or with number so first char can be set safely
        StringBuilder numberString = new StringBuilder(String.valueOf(chars[0]));
        for (int i = 1; i < this.inputString.length(); i++){
            if (charCanBePartOfANumber(chars[i])){
                numberString.append(chars[i]);
            } else {
                //it is either an operator or a sign if the string is valid
                //if the previous char was a number, it must be an operator else it is a sign or invalid
                //therefore, the number string ended
                if (chars[i - 1] >=  48 && chars[i - 1] <= 57 || chars[i - 1] == '%'){
                    operandList.add(numberString.toString());
                    numberString = new StringBuilder();
                    if (characterIsValidOperator(chars[i])){
                        operatorList.add(String.valueOf(chars[i]));
                    }
                } else {
                    throw new InvalidInputFormatException("Invalid character found");
                }
            }
        } if (!numberString.isEmpty()){
            operandList.add(numberString.toString());
        }
    }

    private boolean charCanBePartOfANumber(char c){
        //0 - 9 -> Ascii 48 - 57
        return ((c >= 48 && c <= 57) || c == '.' || c == '%');
    }

    private boolean characterIsValidOperator(char character){
        return operatorSet.contains(String.valueOf(character));
    }

    private void compareOperatorsWithOperands()throws InvalidInputFormatException{
        int difference = operandList.size() - operatorList.size();
        if (difference < 1) {
            throw new InvalidInputFormatException("Too many operators");
        }
        // too many operands is impossible!
    }
}
