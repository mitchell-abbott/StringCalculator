package com;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StringCalculatorTest {

    StringCalculator calculator = new StringCalculator();

    @Test
    public void testAdd_nullNumbers() throws Exception {
        int result = calculator.add(null);
        int expected = 0;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_noNumbers() throws Exception {
        String numbers = "";
        int result = calculator.add(numbers);
        int expected = 0;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_oneNumbers() throws Exception {
        String numbers = "1";
        int result = calculator.add(numbers);
        int expected = 1;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_twoNumbers() throws Exception {
        String numbers = "1,2";
        int result = calculator.add(numbers);
        int expected = 3;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_unknownNumbers1() throws Exception {
        String numbers = "1,2,3,4,5";
        int result = calculator.add(numbers);
        int expected = 15;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_unknownNumbers2() throws Exception {
        String numbers = "3,6,7,2,1,2,3,4,5,10";
        int result = calculator.add(numbers);
        int expected = 43;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_newLineWithNumber() throws Exception {
        String numbers = "1\n2,3";
        int result = calculator.add(numbers);
        int expected = 6;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_newLineWithAnyDelimiter() throws Exception {
        String numbers = "//;\n1;2";
        int result = calculator.add(numbers);
        int expected = 3;
        assertThat(result, is(expected));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAdd_negativeNumberNotAllowed() throws Exception {
        String numbers = "3,6,7,-2,1,2,3,4,5,10";
        calculator.add(numbers);
    }

    @Test
    public void testAdd_numbersGreaterThan1000Ignored() throws Exception {
        String numbers = "3,6,7,2,1,2,3,4,5,1001";
        int result = calculator.add(numbers);
        int expected = 33;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_delimiterWithAnyLengthAndFormat() throws Exception {
        String numbers = "//[***]\n1***2***3";
        int result = calculator.add(numbers);
        int expected = 6;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_multipleDelimitersWithAnyFormat() throws Exception {
        String numbers = "//[*][%]\n1*2%3";
        int result = calculator.add(numbers);
        int expected = 6;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_multipleDelimitersWithAnyLengthAndFormat() throws Exception {
        String numbers = "//[*!?][%#$]\n1*!?2%#$3";
        int result = calculator.add(numbers);
        int expected = 6;
        assertThat(result, is(expected));
    }


}