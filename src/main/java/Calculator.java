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
 *
 * Main loop should contain three methods:
 * readCurrentLine()
 * calculateFromString()
 * printResult()
 *
 * Rules:
 * First exponent second * / third + -
 * from left to right
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }

    private String line;
    private double result;
    private final StringAnalyzer stringAnalyzer;
    private ArrayList<String> operatorList;
    private ArrayList<Double> operandList;
    private final Scanner scanner;

    private final String[][] operatorsOrdered;
    private final HashSet<String> operatorSet;

    public Calculator(){
        scanner = new Scanner(System.in);
        stringAnalyzer = new StringAnalyzer();
        operatorsOrdered  = new String[][]{
//            {
//                "^"
//            },
                {
                        "*", "/"
                },
                {
                        "+", "-"
                }
        };
        operatorSet = makeStringSetFromOperators(operatorsOrdered);
        stringAnalyzer.setOperatorSet(operatorSet);
    }
    public void run(){
        System.out.println("Stop calculator by typing 'stop'");
        while (true){
            readLine(scanner);
            if (line.equals("stop")){
                break;
            }
            try {
                calculate();
            } catch (Exception e){
                System.out.println("Invalid string: " + e);
                //wait for next input
            }
            printResult();
        }
    }
    private HashSet<String> makeStringSetFromOperators(String[][] operatorsOrdered){
        HashSet<String> operatorSet = new HashSet<>();
        for (String[] order : operatorsOrdered){
            for (String operator : order){
                operatorSet.add(operator);
            }
        }
        return operatorSet;
    }

    //Getters and setters (for testing)
    public void setLine(String line){
        this.line = line;
    }
    public double getResult() {
        return result;
    }
    public String getLine(){
        return line;
    }
    public ArrayList<String> getOperatorList(){
        return operatorList;
    }
    public ArrayList<Double> getOperandList(){
        return operandList;
    }

    public void setResult(double result){
        this.result = result;
    }

    public void readLine(Scanner s){
        System.out.print("calc ");
        this.line = s.nextLine();
    }

    public void calculate() throws Exception {
        if(StringAnalyzer.stringIsValid(this.line, operatorSet)){
            stringAnalyzer.setString(this.line);
        } else {
            throw new InvalidInputFormatException("Invalid input String provided");
        }
        this.result = solveSplitTerm(stringAnalyzer.getListOfStrings(), stringAnalyzer.getPossibleOperators());
    }
    public double solveSplitTerm(ArrayList<String> inputStringList, HashSet<String> operators)throws Exception{
        //Must be Operand - Operator - Operand - Operator - ... - Operand
        this.operatorList = new ArrayList<>();
        for (String inputString : inputStringList) {
            if (operators.contains(inputString)) {
                operatorList.add(inputString);
            }
        }
        this.operandList = new ArrayList<>();
        double operand;
        for (String inputString : inputStringList) {
            if (!operators.contains(inputString)) {
                if (inputString.equals("%")){
                    operand = this.result;
                } else {
                    operand = Double.parseDouble(inputString);
                }

                operandList.add(operand);
            }
        }
        //calculate in correct order:
        //order by operatorsOrdered
        // * and / before + and -, from left to right
        for (int i = 0; i < operatorsOrdered.length; i++){
            calculateFromLeftToRight(i);
        }
        return this.operandList.get(0);
    }
    public void calculateFromLeftToRight(int orderNo) throws Exception{
        ArrayList<String> allowedOperators = new ArrayList<>();
        for (String operator : operatorsOrdered[orderNo]){
            allowedOperators.add(operator);
        }
        int i = 0;
        while (i < operatorList.size()){
            double firstOperand = this.operandList.get(i);
            double secondOperand = this.operandList.get(i + 1);
            String operator = operatorList.get(i);
            double result;
            if (allowedOperators.contains(operator)){
                result = switch (operator) {
                    //case "^" -> Math.pow(firstOperand, secondOperand);
                    case "*" -> firstOperand * secondOperand;
                    case "/" -> divide(firstOperand, secondOperand);
                    case "+" -> firstOperand + secondOperand;
                    case "-" -> firstOperand - secondOperand;
                    //cannot happen
                    default -> 0;
                };
                operandList.set(i + 1, result);
                deleteUsedTerm(i);
            } else {
                i++;
            }
        }
    }
    static double divide(double numerator, double denominator) throws Exception{
        //this method is used for appropriate div by 0 errors for double arithmetics
        if (denominator == 0)
        {
            throw new InvalidInputFormatException("Divided by 0!");
        }
        return numerator / denominator;
    }
    public void deleteUsedTerm(int index){
        this.operandList.remove(index);
        this.operatorList.remove(index);
    }
    public void printResult(){
        System.out.println(this.result);
    }
}
