import java.util.HashSet;

public class StringAnalyzer {

    private String s;
    private double firstNumber;
    private double secondNumber;
    private char operator;

    public StringAnalyzer(String s)
    {
        setString(s);
    }

    public void setString(String s){
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
    private void checkFormatOfString(){
        s = s.replaceAll(" ","");
        //check correct input format
        if (!s.startsWith("'")) {
            System.err.println("Invalid input format: Start with \"'\"");
        }
        if (!s.endsWith("'")){
            System.err.println("Invalid input format: End with \"'\"");
        }
        if (s.indexOf("'", 1) < s.length() - 1){
            System.err.println("Invalid input format: Too many \"'\"");
        }
        //System.out.println(s);
    }


    private void analyzeString(){
        HashSet<String> validOperators = new HashSet<String>();
        validOperators.add("+");
        validOperators.add("-");
        validOperators.add("*");
        validOperators.add("/");
        char[] chars = s.toCharArray();
        //analyze string step by step
        String firstNumberString = "";
        char operator;
        String secondNumberString = "";

        int counter = 0;
        for (char c : chars){
            if (c >= 48 && c <= 57 || c == 46){
                firstNumberString += c;
                System.out.println(firstNumberString);
            } else if (c == '\''){
                System.out.println(c);
            } else if (validOperators.contains(String.valueOf(c))){
                operator = c;
                break;
            }else { //Either no operator was found or an invalid char was found
                System.err.println("Invalid input");
                break;
            };
            counter += 1;
        }
        //System.out.println(firstNumber);
        this.firstNumber = Double.parseDouble(firstNumberString);
    }

}
