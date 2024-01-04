public class Arithmetics {

    private double a;
    private double b;
    public void setValues(double a, double b){
        this.a = a;
        this.b = b;
    }
    public double getAdditionResult() {
        return a + b;
    }
    public double getSubstractionResult(){
        return a - b;
    }
    public double getMultiplicationResult(){
        return a * b;
    }
    public double getDivisionResult() throws Exception{
        // div by 0!!!
        try{
            //As this is floating point math, division by 0 is allowed. To overcome, this line is added
            if (b == 0){
                throw new InvalidInputFormatException("Divided by Zero");
            }
            return a / b;
        } catch (Exception e){
            throw e;
        }

    }
}
