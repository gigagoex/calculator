import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void solveTerm() {
        {
            calculator.setLine("'4+3'");
            try {
                calculator.calculate();
            } catch (Exception e) {
                System.out.println(e);
            }
            double result = calculator.getResult();
            Assert.assertEquals(7, result, 0.0001);

            calculator.setLine("'2.5 + 1'");
            try {
                calculator.calculate();
            } catch (Exception e) {
                System.out.println(e);
            }
            result = calculator.getResult();
            Assert.assertEquals(3.5, result, 0.0001);

            calculator.setLine("'4 - 2'");
            try {
                calculator.calculate();
            } catch (Exception e) {
                System.out.println(e);
            }
            result = calculator.getResult();
            Assert.assertEquals(2, result, 0.0001);

            calculator.setLine("'4 * 2'");
            try {
                calculator.calculate();
            } catch (Exception e) {
                System.out.println(e);
            }
            result = calculator.getResult();
            Assert.assertEquals(8, result, 0.0001);

            calculator.setLine("'5 / 2'");
            try {
                calculator.calculate();
            } catch (Exception e) {
                System.out.println(e);
            }
            result = calculator.getResult();
            Assert.assertEquals(2.5, result, 0.0001);

            calculator.setResult(4);
            calculator.setLine("'% / 2'");
            try {
                calculator.calculate();
            } catch (Exception e) {
                System.out.println(e);
            }
            result = calculator.getResult();
            Assert.assertEquals(2, result, 0.0001);

            //Test of error handling

        }
    }

    @Test(expected = Exception.class)
    public void solveTermNegative()throws Exception{
        calculator.setLine("% / 2'");
        calculator.calculate();
    }

    @Test
    public void solveSplitTerm() {
    }

    @Test
    public void calculateFromLeftToRight() {
    }

    @Test
    public void divide() {
    }

    @Test
    public void deleteUsedTerm() {
    }

    @Test
    public void printResult() {
    }
}