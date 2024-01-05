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

import java.util.Objects;
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
            System.out.print("calc ");
            String line = reader(in);
            if (Objects.equals(line, "stop")){
                break;
            }
            try {
                stringAnalyzer.setString(line);
            } catch (Exception e){
                System.out.println("Invalid string: " + e);
                //wait for next input
                continue;
            }
            arithmetics.setValues(stringAnalyzer.getFirstNumber(), stringAnalyzer.getSecondNumber());
            switch (stringAnalyzer.getOperator()){
                case '+': System.out.println(arithmetics.getAdditionResult());break;
                case '-': System.out.println(arithmetics.getSubstractionResult());break;
                case '*': System.out.println(arithmetics.getMultiplicationResult());break;
                case '/': try{
                    System.out.println(arithmetics.getDivisionResult());
                    } catch (Exception e){
                    System.out.println("Exception: "+ e);
                    } break;
                default: System.out.println("error");
            }
        }

    }

    public String reader(Scanner s){
        return s.nextLine();
    }
}