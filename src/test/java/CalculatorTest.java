import exceptions.DividedByZeroException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    Calculator calculator = new Calculator();
    Term term;
    @Test
    public void calculateTest() throws Exception{

        term = new Term("2*2");
        Assertions.assertEquals(4, calculator.calculate(term), 0.0001);

        term = new Term("3/2");
        Assertions.assertEquals(1.5, calculator.calculate(term), 0.0001);

        term = new Term("1+9");
        Assertions.assertEquals(10, calculator.calculate(term), 0.0001);

        term = new Term("%+10");
        Assertions.assertEquals(20, calculator.calculate(term), 0.0001);

        term = new Term("4*2.5+10");
        Assertions.assertEquals(20, calculator.calculate(term), 0.0001);
    }

    @Test
    public void calculateExceptionTest(){
        DividedByZeroException thrown = Assertions.assertThrows(DividedByZeroException.class, () -> {
            term = new Term("5/0");
            calculator.calculate(term);
        });
        Assertions.assertEquals("Divided by zero!", thrown.getMessage());
    }
}