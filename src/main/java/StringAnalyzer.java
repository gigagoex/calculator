import java.util.HashSet;

public class StringAnalyzer {

    private String s;
    private double firstNumber;
    private double secondNumber;
    private char operator;

    public void setString(String s) throws Exception {
        this.s = s;
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
        s = s.replaceAll(" ","");
        //check correct input format
        if (!s.startsWith("'")) {
            throw new InvalidInputFormatException("Missing leading \"'\"");
        }
        if (!s.endsWith("'")){
            throw new InvalidInputFormatException("Missing final \"'\"");
        }
        if (s.indexOf("'", 1) < s.length() - 1){
            throw new InvalidInputFormatException("Too many \"'\"");
        }
        //if input is correct, the single quotes are removed for easier string handling
        s = s.replaceAll("'", "");
    }

    private void analyzeString () throws Exception{
        HashSet<String> validOperators = new HashSet<>();
        validOperators.add("+");
        validOperators.add("-");
        validOperators.add("*");
        validOperators.add("/");
        char[] chars = s.toCharArray();
        //analyze string step by step
        String firstNumberString = "";
        String secondNumberString = "";
        int counter = 0;
        for (int i = 0; i< s.length(); i++){
            char c = chars[i];
            if (validOperators.contains(String.valueOf(c)) && i > 0){
                //this marks the first appearance of an operator!
                this.operator = c;
                break;
            }
            firstNumberString += c;
            counter++;
            if (i == s.length() - 1){
                //if no operator is found yet, there won't be any since the last char is '''
                throw new InvalidInputFormatException("Missing Operator");
            }
        }
        //if the first part contains invalid chars, an exception will be thrown here
        try{
            this.firstNumber = Double.parseDouble(firstNumberString);
        } catch(Exception e){
            System.err.println("Exception converting first String: " + this.firstNumber);
            throw e;
        }
        for (int i = counter + 1; i < s.length(); i++){
            char c = chars[i];
            secondNumberString += c;
        }
        //System.out.println(firstNumber);
        try{
            this.secondNumber = Double.parseDouble(secondNumberString);
        } catch (Exception e){
            System.err.println("Exception converting second String");
            throw e;
        }
    }
}
