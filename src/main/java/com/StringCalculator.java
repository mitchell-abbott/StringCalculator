package com;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
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
            numbers = numbers.length() > 1 && numbers.substring(0, 2).equals("//") ? numbers.substring(3 + findNewLineLocationAndAddDelimiters(numbers.substring(2), delimiters)) : numbers;
            Set<Integer> negativeNumbers = new TreeSet<>();
            for (String num : numbers.split(createDelimiterRegex(delimiters))) {
                for (String s : num.split("\\n")) {
                    int number = Integer.parseInt(s);
                    if (number < 0) negativeNumbers.add(number);
                    else if (number <= 1000 && number > 0) allNumbers.add(s);
                }
            }
            if (CollectionUtils.isNotEmpty(negativeNumbers)) {
                throw new UnsupportedOperationException(createNegativeNumberErrorMessage(negativeNumbers));
            }
        }
        return Stream.of(allNumbers.toArray(new String[0])).mapToInt(Integer::parseInt).toArray();
    }

    private String createNegativeNumberErrorMessage(Set<Integer> negativeNumbers) {
        StringBuilder sb = new StringBuilder("Negative numbers not allowed");
        if (negativeNumbers.size() > 1) {
            sb.append(": ");
            negativeNumbers.stream().filter(i -> i != null).forEach(i -> appendNegativeNumber(sb, i));
        }
        String message = sb.toString();
        return negativeNumbers.size() > 1 ? message.substring(0, message.length() - 2) : message;
    }

    private void appendNegativeNumber(StringBuilder sb, int number) {
        sb.append(number);
        sb.append(", ");
    }

    private int findNewLineLocationAndAddDelimiters(String line, List<String> delimiters) {
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

    private String createDelimiterRegex(List<String> delimiters) {
        StringBuilder sb = new StringBuilder("");
        if (CollectionUtils.isNotEmpty(delimiters))
            delimiters.stream().filter(d -> d != null).forEach(d -> appendRegex(sb, d));
        String regex = sb.toString();
        return CollectionUtils.isNotEmpty(delimiters) ? regex.substring(0, regex.length() - 1) : regex;
    }

    private void appendRegex(StringBuilder sb, String delimiter) {
        sb.append("\\Q");
        sb.append(delimiter);
        sb.append("\\E|");
    }
}
