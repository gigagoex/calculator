import java.util.ArrayList;
import java.util.HashSet;

public class StringAnalyzer {

    private String inputString;
    private HashSet<String> validOperators;

    public HashSet<String> getPossibleOperators(){
        return validOperators;
    }
    public void setString(String inputString) {
        if(!stringIsValid(inputString, validOperators)){
            throw new InvalidInputFormatException("Invalid Input");
        }
        this.inputString = inputString;
    }
        public void setOperatorSet(HashSet<String> operatorsSet){
        this.validOperators = operatorsSet;
    }
    public ArrayList<String> getListOfStrings(){
        if(validOperators.isEmpty()){
            throw new RuntimeException("Missing List of Operators");
        }
        return splitStringAtOperators(simplifyString(cutoutExpression(inputString)));
    }

    public String cutoutExpression(String s){
        int startOfString = s.indexOf("'");
        int endOfString = s.indexOf("'", startOfString + 1);
        return s.substring(startOfString + 1, endOfString);
    }

    public static String simplifyString(String s){
        //remove all whitespace
        return s.replaceAll(" ","");
    }
    public ArrayList<String> splitStringAtOperators(String s){
        var resultList = new ArrayList<String>();
        char[] chars = s.toCharArray();
        //must start either with sign or with number so first char can be set safely
        String numberString = String.valueOf(chars[0]);
        for (int i = 1; i < s.length(); i++){
            if (charCanBePartOfANumber(chars[i])){
                numberString += chars[i];
            } else {
                //it is either an operator or a sign if the string is valid
                //if the previous char was a number, it must be an operator else it is a sign or invalid
                if (chars[i - 1] >=  48 && chars[i - 1] <= 57 || chars[i - 1] == '%'){
                    resultList.add(numberString);
                    numberString="";
                    resultList.add(String.valueOf(chars[i]));
                } else if (characterIsValidOperator(chars[i], validOperators)){
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
    public static boolean charCanBePartOfANumber(char c){
        //0 - 9 -> Ascii 48 - 57
        return (c >= 48 && c <= 57 || c == '.' || c == '%');
    }
    public static boolean stringIsValid(String string, HashSet<String> validOperators){
        String stringWithoutWhitespace = simplifyString(string);
        int startOfString = stringWithoutWhitespace.indexOf("'");
        if(startOfString == -1){
            return false;
        }
        int endOfString = stringWithoutWhitespace.indexOf("'", startOfString + 1);
        if(endOfString == -1){
            return false;
        }
        if(stringWithoutWhitespace.indexOf("'", endOfString + 1) != -1){
            return false;
        }
        //check if string contains anything except operators and numbers
        char[] chars = stringWithoutWhitespace.toCharArray();
        for (int i = 1; i<chars.length - 1; i++){
            if(charCanBePartOfANumber(chars[i])){
                continue;
            } else if (characterIsValidOperator(chars[i], validOperators)){
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean characterIsValidOperator(char character, HashSet<String> operators){
        return operators.contains(String.valueOf(character));
    }
}
