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
    public double getSubtractionResult(){
        return a - b;
    }
    public double getMultiplicationResult(){
        return a * b;
    }
    public double getDivisionResult() throws Exception{
            //As this is floating point math, division by 0 is allowed. Yet it should throw an exception
            if(b == 0){
                throw new InvalidInputFormatException("Divided by 0");
            }
            return a / b;
    }
}

