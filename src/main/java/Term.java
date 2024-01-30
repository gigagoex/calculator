import java.util.ArrayList;


public class Term extends Operators{
    public Term(String termString) throws InvalidInputFormatException{
        this.inputString = termString;
        splitString();
    }
    private final String inputString;
    private final ArrayList<String> operatorList = new ArrayList<>();
    private final ArrayList<String> operandList = new ArrayList<>();

    public ArrayList<String> getOperatorList() {
        return this.operatorList;
    }

    public ArrayList<String> getOperandList() {
        return this.operandList;
    }

    private void splitString() throws InvalidInputFormatException{
        //analyze input String char by char
        char[] chars = this.inputString.toCharArray();
        //must start either with sign or with number so first char can be set safely
        String numberString = String.valueOf(chars[0]);
        for (int i = 1; i < this.inputString.length(); i++){
            if (charCanBePartOfANumber(chars[i])){
                numberString += chars[i];
            } else {
                //it is either an operator or a sign if the string is valid
                //if the previous char was a number, it must be an operator else it is a sign or invalid
                if (chars[i - 1] >=  48 && chars[i - 1] <= 57 || chars[i - 1] == '%'){
                    operandList.add(numberString);
                    numberString="";
                    if (characterIsValidOperator(chars[i])){
                        operatorList.add(String.valueOf(chars[i]));
                    } else {
                        throw new InvalidInputFormatException("Invalid character found");
                    }

                } else{
                    numberString += chars[i];
                }
            }
        }
        if(!numberString.isEmpty()){
            operandList.add(numberString);
        }
    }

    private boolean charCanBePartOfANumber(char c){
        //0 - 9 -> Ascii 48 - 57
        return (c >= 48 && c <= 57 || c == '.' || c == '%');
    }

    private boolean characterIsValidOperator(char character){
        return operatorSet.contains(String.valueOf(character));
    }
}
