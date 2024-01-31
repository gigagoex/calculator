package com.github.gigagoex.exercises;

import java.util.Collections;
import java.util.HashSet;
/**
 * This package provides the basic mathematical operators.
 * The operators are provided either ordered by the calculation rules '^' > '* /' > '+ -'
 * or as HashSet as this simplifies the usage of compare operations
 *
 * @author Maximilian Göckeler
 */
public class Operators {
    /**
     * Provides an array of string arrays of the ordered mathematical operators
     */
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
    /**
     * Provides a HashSet of the operators
     */
    protected final HashSet<String> operatorSet = createStringSetOfOperators();

    private HashSet<String> createStringSetOfOperators(){
        HashSet<String> operatorSet = new HashSet<>();
        for (String[] order : this.orderedOperatorArray){
            Collections.addAll(operatorSet, order);
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
