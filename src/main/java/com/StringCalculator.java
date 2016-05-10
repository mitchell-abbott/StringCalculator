package com;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {

    int add(String numbers) {
        return StringUtils.isBlank(numbers) ? 0 : IntStream.of(getNumbers(numbers)).sum();
    }

    private int[] getNumbers(String numbers) {
        List<String> allNumbers = new ArrayList<>();
        if (StringUtils.isNotBlank(numbers)) {
            List<String> delimiters = new ArrayList<>(Collections.singleton(","));
            if (numbers.length() > 1 && numbers.substring(0, 2).equals("//")) {
                numbers = numbers.substring(3 + getNewLineLocationAndAddDelimiters(numbers.substring(2), delimiters));
            }
            for (String num : numbers.split(getDelimiterRegex(delimiters))) {
                String[] splitByLine = num.split("\\n");
                for (String s : splitByLine) {
                    int number = Integer.parseInt(s);
                    if (number < 0) {
                        throw new UnsupportedOperationException("Negative numbers not allowed");
                    } else if (number <= 1000 && number > 0) {
                        allNumbers.add(s);
                    }
                }
            }
        }
        return Stream.of(allNumbers.toArray(new String[0])).mapToInt(Integer::parseInt).toArray();
    }

    private int getNewLineLocationAndAddDelimiters(String line, List<String> delimiters) {
        if (StringUtils.isBlank(line)) return 0;
        if (line.charAt(0) != '[') {
            delimiters.add(String.valueOf(line.charAt(0)));
            return 1;
        }
        int index = 0;
        Matcher matcher = Pattern.compile("\\[(.*?|\\s)\\]").matcher(line);
        while (matcher.find()) {
            delimiters.add(matcher.group().substring(1, matcher.group().length() - 1));
            index = matcher.end();
        }
        return index;
    }

    private String getDelimiterRegex(List<String> delimiters) {
        StringBuilder sb = new StringBuilder("");
        if (CollectionUtils.isNotEmpty(delimiters)) {
            delimiters.stream().filter(d -> d != null).forEach(d -> sb.append("\\Q").append(d).append("\\E").append("|"));
        }
        String regex = sb.toString();
        return CollectionUtils.isNotEmpty(delimiters) ? regex.substring(0, regex.length()-1) : regex;
    }
}
