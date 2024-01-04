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

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        new Calculator();
    }
    public Calculator(){
        Scanner in = new Scanner(System.in);
        /*
        System.out.print("calc ");
        String line = reader(in);
        if (line.contains("+")){
            System.out.println(7);
        } else {
            System.out.println(line);
        }
*/
        String s = "'Hello, World 123'";
        //remove whitespaces
        int startOfCalculation = s.indexOf("'");
        if (startOfCalculation > 0) {
            System.err.println("Invalid input format!");
        }



        //remove whitespace
        char[] chars = s.replaceAll("\s","").toCharArray();
        //analyze string step by step
        String firstNumber = "";
        String operator = "";
        String secondNumber = "";
        for (char c : chars){
            if (c >= 48 && c <= 57 || c == 46){
                firstNumber += c;
            } else if (c == '\''){
                System.out.println(c);
            } else continue;
        }
        System.out.println(firstNumber);
    }


    public String reader(Scanner s){
        return s.nextLine();
    }

    //arithmetic operation

}
