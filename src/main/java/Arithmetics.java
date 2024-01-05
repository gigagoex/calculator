public class Arithmetics {

    private double[] operands;


    public void setValues(double... a){
        this.operands = a;
    }
    public double getAdditionResult() {
        double result = operands[0];
        for (int i = 1; i < this.operands.length; i++){
            result += this.operands[i];
        }
        return result;
    }
    public double getSubstractionResult(){
        double result = operands[0];
        for (int i = 1; i < this.operands.length; i++){
            result -= this.operands[i];
        }
        return result;
    }
    public double getMultiplicationResult(){
        double result = operands[0];
        for (int i = 1; i < this.operands.length; i++){
            result *= this.operands[i];
        }
        return result;
    }
    public double getDivisionResult() throws Exception{

        double result = operands[0];
        for (int i = 1; i < this.operands.length; i++){
            //As this is floating point math, division by 0 is allowed. Yet it should throw an exception
            if(this.operands[i] == 0){
                throw new InvalidInputFormatException("Divided by 0");
            }
            result /= this.operands[i];
        }
        return result;
    }
}
