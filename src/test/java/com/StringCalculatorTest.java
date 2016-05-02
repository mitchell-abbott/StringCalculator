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

}