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
        //try - catch block to avoid breaking the code by dividing by 0
        try{
            double result = operands[0];
            for (int i = 1; i < this.operands.length; i++){
                result += this.operands[i];
            }
            return result;
        } catch (Exception e){
            throw e;
        }

    }
}
