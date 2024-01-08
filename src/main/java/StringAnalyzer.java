import java.util.HashSet;

public class StringAnalyzer {

    private String inputString;
    private String shortenedString;
    private double firstNumber;
    private double secondNumber;
    private char operator;

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
        HashSet<String> validOperators = new HashSet<>();
        validOperators.add("+");
        validOperators.add("-");
        validOperators.add("*");
        validOperators.add("/");
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
}
