import java.util.ArrayList;
import java.util.HashSet;

public class StringAnalyzer {

    private String inputString;
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
    }

    public ArrayList<String> getResultList(){
        return splitString(simplifyString(cutoutExpression(this.inputString)));
    }

    static String cutoutExpression(String s){
        int startOfString = s.indexOf("'");
        if(startOfString == -1){
            throw new InvalidInputFormatException("' expected at start of expression");
        }
        int endOfString = s.indexOf("'", startOfString + 1);
        if(endOfString == -1){
            throw new InvalidInputFormatException("' expected at end of expression");
        }
        if(s.indexOf("'", endOfString + 1) != -1){
            System.out.println(s.indexOf("'", endOfString + 1));
            throw new InvalidInputFormatException("invalid format");
        }
        return s.substring(startOfString + 1, endOfString);
    }

    public static String simplifyString(String s){
        //remove all whitespace
        return s.replaceAll(" ","");
    }
    public static ArrayList<String> splitString(String s){
        var resultList = new ArrayList<String>();

        char[] chars = s.toCharArray();
        String numberString = String.valueOf(chars[0]);
        for (int i = 1; i < s.length(); i++){
            //0 - 9 -> Ascii 48 - 57, % -> Ascii 37
            if (chars[i] >=  48 && chars[i] <= 57 /*|| chars[i] == 37*/){
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
