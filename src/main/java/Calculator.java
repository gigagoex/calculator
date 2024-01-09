/* Main program
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
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    double oldResult;
    public static void main(String[] args) {
        new Calculator();


    }
    public Calculator(){
        Scanner in = new Scanner(System.in);
        StringAnalyzer stringAnalyzer = new StringAnalyzer();
        Arithmetics arithmetics = new Arithmetics();
        System.out.println("Stop calculator by typing 'stop'");
        while (true){
            String line = reader(in);
            if (line.equals("stop")){
                break;
            }
            try {
                stringAnalyzer.setString(line);
            } catch (Exception e){
                System.out.println("Invalid string: " + e);
                //wait for next input
            }
            try{
                double result = calculateFromString(stringAnalyzer.getResultList(), stringAnalyzer.getPossibleOperators());
                System.out.println(result);
                oldResult = result;
            } catch (Exception e){
                System.err.println(e);
            }
        }
    }

    public String reader(Scanner s){
        System.out.print("calc ");
        return s.nextLine();
    }

    public double calculateFromString(ArrayList<String> inputStringList, HashSet<String> operators)throws Exception{
        //Must be Operand - Operator - Operand - Operator - ... - Operand
        ArrayList<String> operatorList = new ArrayList<>();
        for (String inputString : inputStringList) {
            if (operators.contains(inputString)) {
                operatorList.add(inputString);
            }
        }
        ArrayList<Double> operandList = new ArrayList<>();
        double operand;
        for (String inputString : inputStringList) {
            if (!operators.contains(inputString)) {
                if (inputString.equals("%")){
                    operand = this.oldResult;
                } else {
                    operand = Double.parseDouble(inputString);
                }

                operandList.add(operand);
            }
        }


        //calculate multiplication and division
        calculateFromLeftToRight(operatorList, operandList, true);
        //calculate the rest
        calculateFromLeftToRight(operatorList, operandList, false);
        return operandList.get(0);
    }

    public static double divide(double numerator, double denominator) throws Exception{
        //this method is used for appropriate div by 0 errors for double arithmetics
        if (denominator == 0)
        {
            throw new InvalidInputFormatException("Divided by 0!");
        }
        return numerator / denominator;
    }

    public static void deleteUsedTerm(ArrayList<String> operatorList, ArrayList<Double> operandList, int index){
        operandList.remove(index);
        operatorList.remove(index);
    }

    public static void calculateFromLeftToRight(ArrayList<String> operatorList, ArrayList<Double> operandList, boolean firstOrderOperator) throws Exception{
        ArrayList<String> allowedOperators = new ArrayList<>();
        if (firstOrderOperator){
            allowedOperators.add("*");
            allowedOperators.add("/");
        } else {
            allowedOperators.add("+");
            allowedOperators.add("-");
        }
        int i = 0;
        while (i < operatorList.size()){
            double firstOperand = operandList.get(i);
            double secondOperand = operandList.get(i + 1);
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
                operandList.set(i + 1, result);
                deleteUsedTerm(operatorList, operandList, i);
            } else {
                i++;
            }
        }
    }
}
