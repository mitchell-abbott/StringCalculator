package com;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {

    int add(String numbers){
        return StringUtils.isBlank(numbers) ? 0 : IntStream.of(getNumbers(numbers)).sum();
    }

    private int[] getNumbers(String numbers) {
        return Stream.of(numbers.split(",")).mapToInt(Integer::parseInt).toArray();
    }
}
