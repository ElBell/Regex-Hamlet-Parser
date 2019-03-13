import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class HamletParserTest {
    private String hamletText;
    private HamletParser hamletParser;

    @Before
    public void setUp() {
        this.hamletParser = new HamletParser();
        this.hamletText = hamletParser.getHamletData();
    }


    @Test
    public void testChangeHamletToLeon() {
        //Given
        String expected = "Hi Leon";
        String exampleInput = "Hi Hamlet";

        //When
        String actual = hamletParser.replaceHamlet(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeHamletToLeon2() {
        //Given
        String expected = "Hi Leon Leon Leon";
        String exampleInput = "Hi Hamlet Hamlet Leon";

        //When
        String actual = hamletParser.replaceHamlet(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeHamletToLeonNone() {
        //Given
        String expected = "Hi HamletHamlet Leon";
        String exampleInput = "Hi HamletHamlet Leon";

        //When
        //When
        String actual = hamletParser.replaceHamlet(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeHoratioToTariq() {
        //Given
        String expected = "Hi Tariq";
        String exampleInput = "Hi Horatio";

        //When
        String actual = hamletParser.replaceHoratio(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeHoratioToTariq2() {
        //Given
        String expected = "Hi Tariq Tariq Tariq";
        String exampleInput = "Hi Horatio Horatio Tariq";

        //When
        String actual = hamletParser.replaceHoratio(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeHoratioToTariqNone() {
        //Given
        String expected = "Hi HoratioHoratio Tariq";
        String exampleInput = "Hi HoratioHoratio Tariq";

        //When
        String actual = hamletParser.replaceHoratio(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHoratio() {
        //Given
        int expected = 1;
        String exampleInput = "Hi Horatio";

        //When
        int actual = hamletParser.findHoratio(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHoratioMultiple() {
        //Given
        int expected = 4;
        String exampleInput = "Horatio! Long time no see, Horatio. How are the little \n" +
                "Horatio-lings: Horatio and Horatia?";

        //When
        int actual = hamletParser.findHoratio(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHoratioFalse() {
        //Given
        int expected = 0;
        String exampleInput = "Hi Horatiio";

        //When
        int actual = hamletParser.findHoratio(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHamlet() {
        //Given
        int expected = 1;
        String exampleInput = "Hi Hamlet";

        //When
        int actual = hamletParser.findHamlet(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHamletMultiple() {
        //Given
        int expected = 4;
        String exampleInput = "How's it going, Hamlet? You know, Hamlet's a nice name. Who \n named you Hamlet? Hamlet";

        //When
        int actual = hamletParser.findHamlet(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHamletFalse() {
        //Given
        int expected = 0;
        String exampleInput = "Hi Hammmlet";

        //When
        int actual = hamletParser.findHamlet(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHamletInText() {
        //Given
        int expected = 84;
        String exampleInput = hamletText;

        //When
        int actual = hamletParser.findHamlet(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReplaceHamletInText() {
        //Given
        int expected = 0;

        //When
        String noHamlet = hamletParser.replaceHamlet(hamletText);
        int actual = hamletParser.findHamlet(noHamlet);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHoratioInText() {
        //Given
        int expected = 31;
        String exampleInput = hamletText;

        //When
        int actual = hamletParser.findHoratio(exampleInput);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReplaceHoratioInText() {
        //Given
        int expected = 0;

        //When
        String noHoratio = hamletParser.replaceHoratio(hamletText);
        int actual = hamletParser.findHoratio(noHoratio);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReplaceBothSample() {
        //Given
        String expected = "Hi Leon, Hi Tariq";
        String input = "Hi Hamlet, Hi Horatio";

        //When
        String actual = hamletParser.replaceBoth(input);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReplaceBothInText() {
        //Given
        int expected = 0;

        //When
        String replaced = hamletParser.replaceBoth(hamletText);
        int actual = hamletParser.findHoratio(replaced) + hamletParser.findHamlet(replaced);

        //Then
        Assert.assertEquals(expected, actual);
    }


}