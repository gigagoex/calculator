import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StringAnalyzerTest {

    StringAnalyzer stringAnalyzer = new StringAnalyzer();

    @Test
    public void setString() {
        // Invalid input
        Assert.assertThrows("No input", InvalidInputFormatException.class, () -> stringAnalyzer.setString(""));
        Assert.assertThrows("Correct term, missing single quotation marks", InvalidInputFormatException.class, () -> stringAnalyzer.setString("1 + 3"));
        Assert.assertThrows("Incorrect term, correct quotation marks -> String instead of arithmetic expression", InvalidInputFormatException.class, () -> stringAnalyzer.setString("'abc'"));
        Assert.assertThrows("Incorrect term, correct quotation marks -> double '-' in first operand", InvalidInputFormatException.class, () -> stringAnalyzer.setString("'--1 + 3"));
        Assert.assertThrows("Incorrect term, correct qoutation marks -> double '-' in second operand", InvalidInputFormatException.class, () -> stringAnalyzer.setString("'1 + --3"));
        Assert.assertThrows("Incorrect term, correct qoutation marks -> two operators", Exception.class, () -> stringAnalyzer.setString("'1 // 3'"));
    }


    StringAnalyzer stringAnalyzer2 = new StringAnalyzer();



    @Test
    public void getFirstNumber() throws Exception{
        //positive Number
        stringAnalyzer.setString("'1 + 3'");
        double result = stringAnalyzer.getFirstNumber();
        Assert.assertEquals(1, result, 0.001);
        //negative Number
        stringAnalyzer.setString("'-1 + 3'");
        result = stringAnalyzer.getFirstNumber();
        Assert.assertEquals(-1, result, 0.001);

        //
        Assert.assertThrows("First number never initialized", AssertionError.class, () -> stringAnalyzer2.getFirstNumber());

    }

    @Test
    public void getSecondNumber() throws Exception{
        //positive Number
        stringAnalyzer.setString("'1 - 3'");
        double result = stringAnalyzer.getSecondNumber();
        Assert.assertEquals(3, result, 0.001);
        //negative Number
        stringAnalyzer.setString("'1 - -3'");
        result = stringAnalyzer.getSecondNumber();
        Assert.assertEquals(-3, result, 0.001);
    }

    @Test
    public void getOperator() throws Exception{
        stringAnalyzer.setString("'1 + 3'");
        char result = stringAnalyzer.getOperator();
        Assert.assertEquals('+', result);
    }

    @Test
    public void splitStringsTest(){
        ArrayList<String> result = stringAnalyzer.splitString("-1+22+-3");
        System.out.println(result);
    }
}