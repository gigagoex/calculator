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
    public double getDivisionResult(){
        // div by 0!!!
        return a / b;
    }
}
