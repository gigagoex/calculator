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

import javax.swing.text.NumberFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Calculator {
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
                System.out.println(calculateFromString(stringAnalyzer.getResultList(), stringAnalyzer.getValidOperators()));
            } catch (Exception e){
                System.err.println(e);
            }

        }
    }

    public String reader(Scanner s){
        System.out.print("calc ");
        return s.nextLine();
    }

    public double calculateFromString(ArrayList<String> inputStringList, HashSet<String> validOperators)throws Exception{
        //Must be Operand - Operator - Operand - Operator - ... - Operand
        List<String> primaryOperatorsList = inputStringList.stream()
                .filter(inputString -> inputString.equals("*") || inputString.equals("/"))
                .toList();

        List<String> secondaryOperatorsList = inputStringList.stream()
                .filter(inputString -> inputString.equals("+") || inputString.equals("-"))
                .toList();
        ArrayList<Double> operandsList = new ArrayList<>();

        inputStringList.stream()
                .filter(inputString -> !validOperators.contains(inputString))
                .mapToDouble(Double::parseDouble)
                .forEach(operandsList::add);

        //calculate multiplication and division




        //Currently, not working properly! if two operations follow each other, the second is ignored
        for (String primaryOperator : primaryOperatorsList){
            double intermediateResult;
            int indexOfOperator = inputStringList.indexOf(primaryOperator);
            int indexOfFirstOperand = (indexOfOperator - 1) / 2;
            int indexOfSecondOperand = (indexOfOperator + 1) / 2;
            if (primaryOperator.equals("*")) {
                System.out.println("Multiplication: " + operandsList.get(indexOfFirstOperand) + " * " + operandsList.get(indexOfSecondOperand));
                intermediateResult = operandsList.get(indexOfFirstOperand) * operandsList.get(indexOfSecondOperand);
            }
            else { //this must be division
                System.out.println("Division");
                double numerator = operandsList.get(indexOfFirstOperand);
                double denominator = operandsList.get(indexOfSecondOperand);
                if (denominator == 0)
                {
                    throw new InvalidInputFormatException("Divided by 0!");
                }
                intermediateResult = numerator / denominator;
            }
            operandsList.set(indexOfFirstOperand, intermediateResult);
        }
        for (int i = primaryOperatorsList.size() - 1 ; i == 1; i--){
            int indexOfOperator = inputStringList.indexOf(primaryOperatorsList.get(i));
            int indexOfSecondOperand = (indexOfOperator + 1) / 2;
            operandsList.remove(indexOfSecondOperand);
        }
        for (String operator : secondaryOperatorsList){
            if (operator.equals("+")){
                operandsList.set(1, operandsList.get(0) + operandsList.get(1));
            } else {
                operandsList.set(1, operandsList.get(0) - operandsList.get(1));
            }
            operandsList.remove(0);
        }
        return operandsList.get(0);
    }
}