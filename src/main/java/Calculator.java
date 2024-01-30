import exceptions.DividedByZeroException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class calculates the result of an expression provided as Term object.
 * The calculator solves the term in the order inherited from Operators and from left to right.
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

    public double calculate(Term term) throws DividedByZeroException {
        operatorList = term.getOperatorList();
        operandList = term.getOperandList();
        operandDoubleList = stringListToDoubleList();
        double result = solveTerm();
        oldResult = result;
        return result;
    }

    private double solveTerm() throws DividedByZeroException{
        //Must be Operand - Operator - Operand - Operator - ... - Operand
        //calculate in correct order:
        //order by operatorsOrdered
        // * and / before + and -, from left to right
        for (int i = 0; i < orderedOperatorArray.length; i++){
            calculateFromLeftToRight(i);
        }
        return this.operandDoubleList.get(0);
    }

    private void calculateFromLeftToRight(int orderNo)throws DividedByZeroException{
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

    static double divide(double numerator, double denominator) throws DividedByZeroException{
        //this method is used for appropriate div by 0 errors for double arithmetics
        if (denominator == 0)
        {
            throw new DividedByZeroException();
        }
        return numerator / denominator;
    }

    private void deleteUsedTerm(int index){
        this.operandDoubleList.remove(index);
        this.operatorList.remove(index);
    }

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
