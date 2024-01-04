public class Arithmetics {

    private double a;
    private double b;
    public Arithmetics(double a, double b){
        this.a = a;
        this.b = b;
    }
    public double add() {
        return a + b;
    }
    public double subtract(){
        return a - b;
    }
    public double multiply(){
        return a * b;
    }
    public double divide(){
        // div by 0!!!
        return a / b;
    }
}
