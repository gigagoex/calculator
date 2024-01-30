import java.util.HashSet;

public class Operators {
    protected final String[][] orderedOperatorArray = {
            {
                    "^"
            },
            {
                    "*", "/"
            },
            {
                    "+", "-"
            }
    };
    protected final HashSet<String> operatorSet = createStringSetOfOperators();

    private HashSet<String> createStringSetOfOperators(){
        HashSet<String> operatorSet = new HashSet<>();
        for (String[] order : this.orderedOperatorArray){
            for (String operator : order){
                operatorSet.add(operator);
            }
        }
        return operatorSet;
    }

    public HashSet<String> getOperatorSet() {
        return operatorSet;
    }

    public String[][] getOrderedOperatorArray(){
        return orderedOperatorArray;
    }
}
