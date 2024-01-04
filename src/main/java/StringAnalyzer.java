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
        //System.out.println(s);
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
        for (char c : chars){
            if (c == '-' && counter == 1){ //minus only allowed at first place after "'"
                firstNumberString += c;
            }
            if (c >= 48 && c <= 57 || c == 46){
                firstNumberString += c;
            } else if (c == '\''){
                counter++;
                continue;
            } else if (validOperators.contains(String.valueOf(c))){
                this.operator = c;
                break;
            }else { //Either no operator was found or an invalid char was found
                throw new InvalidInputFormatException("Invalid first argument");
            }
            counter++;
        }
        for (int i = counter + 1; i < s.length() - 1; i++){
            char c = chars[i];
            if (c >= 48 && c <= 57 || c == 46){
                secondNumberString += c;
            } else{
                throw new InvalidInputFormatException("Invalid second argument");
            }
        }
        //System.out.println(firstNumber);
        this.firstNumber = Double.parseDouble(firstNumberString);
        this.secondNumber = Double.parseDouble(secondNumberString);
    }
}
