package com;

import org.apache.commons.lang3.StringUtils;

public class StringCalculator {

    int add(String numbers){
        return StringUtils.isBlank(numbers) ? 0 : getAddedNumbers(numbers);
    }

    private int getAddedNumbers(String numbers) {
        int value;
        String[] split = numbers.split(",");
        if (split.length > 1) {
            value = Integer.valueOf(split[0]) + Integer.valueOf(split[1]);
        } else {
            value = Integer.valueOf(split[0]);
        }
        return value;
    }


}
