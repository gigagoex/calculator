import java.util.ArrayList;
import java.util.HashSet;

public class StringAnalyzer {

    private String inputString;
    private String shortenedString;
    private double firstNumber;
    private double secondNumber;
    private char operator;
    private static final HashSet<String> validOperators = new HashSet<>();

    public StringAnalyzer(){
        initializeValidOperators();
    }

    private void initializeValidOperators(){
        validOperators.add("+");
        validOperators.add("-");
        validOperators.add("*");
        validOperators.add("/");
    }

    public void setString(String inputString) throws Exception {
        this.inputString = inputString;
        checkFormatOfString();
        analyzeString();
    }

    public double getFirstNumber(){
        return firstNumber;
    }
    public double getSecondNumber(){
        return secondNumber;
    }

    public char getOperator(){
        return operator;
    }

    //remove whitespaces
    private void checkFormatOfString() throws Exception{
        shortenedString = inputString.replaceAll(" ","");
        //check correct input format
        if (!shortenedString.startsWith("'")) {
            throw new InvalidInputFormatException("Missing leading \"'\"");
        }
        if (!shortenedString.endsWith("'")){
            throw new InvalidInputFormatException("Missing final \"'\"");
        }
        // check for >2 single quotation marks
        if (shortenedString.indexOf("'", 1) < shortenedString.length() - 1){
            throw new InvalidInputFormatException("Too many \"'\"");
        }
        //if input is correct, the single quotes are removed for easier string handling
        shortenedString = shortenedString.replaceAll("'", "");
    }

    private void analyzeString () throws Exception{

        char[] chars = shortenedString.toCharArray();
        //analyze string step by step
        String firstNumberString = "";
        String secondNumberString = "";
        int charCounter = 0;
        for (int i = 0; i< shortenedString.length(); i++){
            char c = chars[i];
            if (validOperators.contains(String.valueOf(c)) && i > 0){
                //this marks the first appearance of an operator!
                this.operator = c;
                break;
            }
            firstNumberString += c;
            charCounter++;
            if (i == shortenedString.length() - 1){
                //if no operator is found yet, there won't be any since the last char is '''
                throw new InvalidInputFormatException("Missing Operator");
            }
        }
        //if the first part contains invalid chars, an exception will be thrown here
        this.firstNumber = Double.parseDouble(firstNumberString);
        for (int i = charCounter + 1; i < shortenedString.length(); i++){
            char c = chars[i];
            secondNumberString += c;
        }
        //System.out.println(firstNumber);
        this.secondNumber = Double.parseDouble(secondNumberString);
    }

    public static ArrayList<String> splitString(String s){
        var resultList = new ArrayList<String>();

        char[] chars = s.toCharArray();
        String numberString = String.valueOf(chars[0]);
        for (int i = 1; i < s.length(); i++){
            //0 - 9 -> Ascii 48 - 57, % -> Ascii 37
            if (chars[i] >=  48 && chars[i] <= 57 || chars[i] == 37){
                numberString += chars[i];
            } else {
                //it is either an operator or a sign if the string is valid
                //if the previous char was a number, it must be an operator else it is a sign or invalid
                if (chars[i - 1] >=  48 && chars[i - 1] <= 57){
                    resultList.add(numberString);
                    numberString="";
                    resultList.add(String.valueOf(chars[i]));
                } else if (validOperators.contains(String.valueOf(chars[i]))){
                    numberString += chars[i];
                } else {
                    throw new InvalidInputFormatException("Invalid character found");
                }
            }
        }
        if(!numberString.isEmpty()){
            resultList.add(numberString);
        }
        return resultList;
    }
}
