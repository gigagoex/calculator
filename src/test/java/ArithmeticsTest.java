import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticsTest {

    Arithmetics arithmetics = new Arithmetics();

    double[][] testValuesArray = {
            {1, 5},
            {-1 , 5},
            {1, -5},
            {0, 5},
            {1, 0},
            {1.1, 5},
            {1, 4.9}
    };

    @Test
    public void getAdditionResult() {
        for(int i = 0; i < testValuesArray.length; i++){
            arithmetics.setValues(testValuesArray[i][0], testValuesArray[i][1]);
            double result = arithmetics.getAdditionResult();
            Assert.assertEquals(testValuesArray[i][0] + testValuesArray[i][1], result, 0.001);
        }
    }

    @Test
    public void getSubtractionResult() {
        for(int i = 0; i < testValuesArray.length; i++){
            arithmetics.setValues(testValuesArray[i][0], testValuesArray[i][1]);
            double result = arithmetics.getSubtractionResult();
            Assert.assertEquals(testValuesArray[i][0] - testValuesArray[i][1], result, 0.001);
        }
    }

    @Test
    public void getMultiplicationResult() {
        for(int i = 0; i < testValuesArray.length; i++){
            arithmetics.setValues(testValuesArray[i][0], testValuesArray[i][1]);
            double result = arithmetics.getMultiplicationResult();
            Assert.assertEquals(testValuesArray[i][0] * testValuesArray[i][1], result, 0.001);
        }
    }

    @Test
    public void getDivisionResult() throws Exception{
        for(int i = 0; i < testValuesArray.length; i++){
            if(i == 4){
                //divided by 0 -> will be tested later
                continue;
            }
            arithmetics.setValues(testValuesArray[i][0], testValuesArray[i][1]);
            double result = arithmetics.getDivisionResult();
            Assert.assertEquals(testValuesArray[i][0] / testValuesArray[i][1], result, 0.001);
        }
        arithmetics.setValues(1, 0);
        Assert.assertThrows("Divided by 0", InvalidInputFormatException.class, () -> arithmetics.getDivisionResult());
    }
}