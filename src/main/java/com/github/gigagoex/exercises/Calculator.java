package com.github.gigagoex.exercises;

import com.github.gigagoex.exercises.exceptions.DividedByZeroException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class calculates the result of an expression provided as com.github.gigagoex.exercises.Term object.
 * The calculator solves the term in the order inherited from com.github.gigagoex.exercises.Operators and from left to right.
 * @author Maximilian Göckeler
 */
public class Calculator extends Operators{

    private ArrayList<String> operatorList;
    private ArrayList<String> operandList;
    private ArrayList<Double> operandDoubleList;
    /**
     *
     */
    private double oldResult = 0;

    public void setOldResult(double oldResult){
        this.oldResult = oldResult;
    }

    /**
     * gets operatorList and operandList from the com.github.gigagoex.exercises.Term and solves the term
     * @see "calculate"
     * @param term input com.github.gigagoex.exercises.Term
     * @return result
     * @throws DividedByZeroException forwards DividedByZeroException thrown by calculate to process in the main program
     */
    public double calculate(Term term) throws DividedByZeroException {
        operatorList = term.getOperatorList();
        operandList = term.getOperandList();
        operandDoubleList = stringListToDoubleList();
        double result = solveTerm();
        setOldResult(result);
        return result;
    }

    /**
     * solves the term by passing the current operator order no to calculateFromLeftToRight
     * @see "calculateFromLeftToRight"
     * @return the result of the solved term
     * @throws DividedByZeroException forwards the DividedByZeroException thrown by calculateFromLeftToRight
     */
    private double solveTerm() throws DividedByZeroException{
        for (int i = 0; i < orderedOperatorArray.length; i++){
            calculateFromLeftToRight(i);
        }
        return this.operandDoubleList.get(0);
    }

    /**
     * Calculates the remaining term from left to right
     * @param orderNo the order of the operators processed. '^' is 0th order, '*' + '/' are 1st order, '+' + '-' are 2nd order
     * @throws DividedByZeroException thrown if a division by 0 occurs
     */
    private void calculateFromLeftToRight(int orderNo) throws DividedByZeroException{
        ArrayList<String> allowedOperators = new ArrayList<>(Arrays.asList(orderedOperatorArray[orderNo]));
        int i = 0;
        while (i < operatorList.size()){
            double firstOperand = this.operandDoubleList.get(i);
            double secondOperand = this.operandDoubleList.get(i + 1);
            String operator = operatorList.get(i);
            double result;
            if (allowedOperators.contains(operator)){
                result = switch (operator) {
                    case "^" -> Math.pow(firstOperand, secondOperand);
                    case "*" -> firstOperand * secondOperand;
                    case "/" -> divide(firstOperand, secondOperand);
                    case "+" -> firstOperand + secondOperand;
                    case "-" -> firstOperand - secondOperand;
                    //cannot happen
                    default -> 0;
                };
                operandDoubleList.set(i + 1, result);
                deleteUsedTerm(i);
            } else {
                i++;
            }
        }
    }

    /**
     * necessary method for appropriate error handling of div by 0 errors for double arithmetics
     * @param numerator the numerator
     * @param denominator the denominator
     * @return the result if no div by 0 error occurs
     * @throws DividedByZeroException Error message if denominator == 0
     */
    static double divide(double numerator, double denominator) throws DividedByZeroException{

        if (denominator == 0)
        {
            throw new DividedByZeroException();
        }
        return numerator / denominator;
    }

    /**
     * removes the processed operators and operands
     * @param index the index of the value to be removed
     */
    private void deleteUsedTerm(int index){
        this.operandDoubleList.remove(index);
        this.operatorList.remove(index);
    }

    /**
     * Casts the StringListArray obtained from com.github.gigagoex.exercises.Term into a DoubleListArray
     * @return ListArray of the operands as Double
     */
    private ArrayList<Double> stringListToDoubleList(){
        ArrayList<Double> returnList = new ArrayList<>();
        for (int i = 0; i< operandList.size(); i++){
            if(operandList.get(i).equals("%")){
                returnList.add(oldResult);
            } else {
                returnList.add(i, Double.parseDouble(operandList.get(i)));
            }
        }
        return returnList;
    }
}
