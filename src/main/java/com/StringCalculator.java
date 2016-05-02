package com;

import org.apache.commons.lang3.StringUtils;

public class StringCalculator {

    int add(String numbers){
        int value;
        if (StringUtils.isBlank(numbers)) {
            value = 0;
        } else {
            String[] split = numbers.split(",");
            if (split.length > 1) {
                value = Integer.valueOf(split[0]) + Integer.valueOf(split[1]);
            } else {
                value = Integer.valueOf(split[0]);
            }
        }
        return value;
    }


}
