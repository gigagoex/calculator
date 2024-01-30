import exceptions.InvalidInputFormatException;

public class InputStringModifier {

    public static String extractTerm(String line) throws InvalidInputFormatException {
        return simplifyString(cutoutExpression(line));
    }

    private static String cutoutExpression(String s) throws InvalidInputFormatException{
        int startOfString = s.indexOf("'");
        if (startOfString < 0){
            throw new InvalidInputFormatException("No quotation marks found");
        }
        int endOfString = s.indexOf("'", startOfString + 1);
        if (endOfString <= startOfString){
            throw new InvalidInputFormatException("No final quotation mark found");
        }
        return s.substring(startOfString + 1, endOfString);
    }

    public static String simplifyString(String s){
        //remove all whitespace
        return s.replaceAll(" ","");
    }
}
