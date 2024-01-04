public class InvalidInputFormatException extends RuntimeException{
    public InvalidInputFormatException(String str){
        super("Invalid Input: " + str);
    }
}
