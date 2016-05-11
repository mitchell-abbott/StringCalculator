package com;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StringCalculatorTest {

    StringCalculator calculator = new StringCalculator();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAdd_nullNumbers() {
        int result = calculator.add(null);
        int expected = 0;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_noNumbers() {
        String numbers = "";
        int result = calculator.add(numbers);
        int expected = 0;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_oneNumbers() {
        String numbers = "1";
        int result = calculator.add(numbers);
        int expected = 1;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_twoNumbers() {
        String numbers = "1,2";
        int result = calculator.add(numbers);
        int expected = 3;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_unknownNumbers1() {
        String numbers = "1,2,3,4,5";
        int result = calculator.add(numbers);
        int expected = 15;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_unknownNumbers2() {
        String numbers = "3,6,7,2,1,2,3,4,5,10";
        int result = calculator.add(numbers);
        int expected = 43;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_newLineWithNumber() {
        String numbers = "1\n2,3";
        int result = calculator.add(numbers);
        int expected = 6;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_newLineWithAnyDelimiter() {
        String numbers = "//;\n1;2";
        int result = calculator.add(numbers);
        int expected = 3;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_negativeNumberNotAllowed() {
        expectedException.expect(UnsupportedOperationException.class);
        expectedException.expectMessage("Negative numbers not allowed");
        String numbers = "3,6,7,-2,1,2,3,4,5,10";
        calculator.add(numbers);
    }

    @Test
    public void testAdd_negativeNumberNotAllowed_multipleNegatives() {
        expectedException.expect(UnsupportedOperationException.class);
        expectedException.expectMessage("Negative numbers not allowed: -4, -2, -1");
        String numbers = "3,6,7,-2,-1,2,3,-4,5,10";
        calculator.add(numbers);
    }

    @Test
    public void testAdd_numbersEqualTo1000NotIgnored() {
        String numbers = "3,6,7,2,1,2,3,4,5,1000";
        int result = calculator.add(numbers);
        int expected = 1033;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_numbersGreaterThan1000Ignored() {
        String numbers = "3,6,7,2,1,2,3,4,5,1001";
        int result = calculator.add(numbers);
        int expected = 33;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_delimiterWithAnyLengthAndFormat() {
        String numbers = "//[***]\n1***2***3";
        int result = calculator.add(numbers);
        int expected = 6;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_multipleDelimitersWithAnyFormat() {
        String numbers = "//[*][%]\n1*2%3";
        int result = calculator.add(numbers);
        int expected = 6;
        assertThat(result, is(expected));
    }

    @Test
    public void testAdd_multipleDelimitersWithAnyLengthAndFormat() {
        String numbers = "//[*,?][%#$]\n1*,?2%#$3";
        int result = calculator.add(numbers);
        int expected = 6;
        assertThat(result, is(expected));
    }


}