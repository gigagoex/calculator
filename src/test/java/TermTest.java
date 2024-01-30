import exceptions.InvalidInputFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TermTest {
    @Test
    public void termTooManyOperatorsExceptionTest(){
        InvalidInputFormatException thrown = Assertions.assertThrows(InvalidInputFormatException.class, () -> {
            Term term = new Term("1+2+");
        });
        Assertions.assertEquals("Invalid Input: Too many operators", thrown.getMessage());
    }

    @Test
    public void termWhiteSpaceExceptionTest(){
        InvalidInputFormatException thrown = Assertions.assertThrows(InvalidInputFormatException.class, () -> {
            Term term = new Term("1+ 2");
        });
        Assertions.assertEquals("Invalid Input: Invalid character found", thrown.getMessage());
    }

    @Test
    public void termTest() throws InvalidInputFormatException {
        ArrayList<String> expectedOperatorList = new ArrayList<>();
        expectedOperatorList.add("+");
        expectedOperatorList.add("+");
        ArrayList<String> expectedOperandList = new ArrayList<>();
        expectedOperandList.add("1");
        expectedOperandList.add("2");
        expectedOperandList.add("2");
        Term term = new Term("1+2+2");
        Assertions.assertEquals(expectedOperatorList, term.getOperatorList());
        Assertions.assertEquals(expectedOperandList, term.getOperandList());
    }
}