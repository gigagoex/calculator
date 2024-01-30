import exceptions.InvalidInputFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputStringModifierTest {
    @Test
    public void extractTermTest()throws Exception{
        String expected = "1+2+3";
        String actual = InputStringModifier.extractTerm("'1 + 2 + 3'");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void extractTermMissingLeadingQuotationMarkExceptionTest(){
        InvalidInputFormatException thrown = Assertions.assertThrows(InvalidInputFormatException.class, () -> InputStringModifier.extractTerm("1 + 2 + 3'"));
        Assertions.assertEquals("Invalid Input: Missing leading quotation mark", thrown.getMessage());
    }

    @Test
    public void extractTermMissingFinalQuotationMarkExceptionTest(){
        InvalidInputFormatException thrown = Assertions.assertThrows(InvalidInputFormatException.class, () -> InputStringModifier.extractTerm("'1 + 2 + 3"));
        Assertions.assertEquals("Invalid Input: Missing final quotation mark", thrown.getMessage());
    }


}