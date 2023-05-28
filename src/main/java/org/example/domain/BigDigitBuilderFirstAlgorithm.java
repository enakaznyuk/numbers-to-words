package org.example.domain;

import org.example.domain.exception.InvalidNumberException;

import static org.example.domain.HundredBuilder.convertHundredPartToString;

public class BigDigitBuilderFirstAlgorithm {

    private static final String[] TOKENS_THOUSAND = new String[]{"тысяч", "тысячи", "тысяча"};

    private static final String[] TOKENS_MILLION = new String[]{"миллионов", "миллиона", "миллион"};

    private static final String[] TOKENS_BILLION = new String[]{"миллиардов", "миллиарда", "миллиард"};

    private static final int DIGIT_DIVIDER = 1000;

    private enum Ranges {
        THOUSANDS, MILLIONS, BILLIONS
    }

    public static StringBuilder separationAlgorithmFirst(int number) {

        if (number <= 0) {
            throw new InvalidNumberException("Number must be > 0!");
        }

        StringBuilder numberToString = new StringBuilder();

        if (number < Math.pow(10, 6)) {
            numberToString.append(convertThousandPartToString(number));
            return numberToString;
        } else if (number < Math.pow(10, 9)) {
            numberToString.append(convertMillionPartToString(number));
            return numberToString;
        } else if (number >= Math.pow(10, 9))
            throw new InvalidNumberException("Number must be < 10^9!");
        return numberToString;
    }

    private static StringBuilder convertMillionPartToString(int number) {

        StringBuilder numberToString = new StringBuilder();

        numberToString.append(convertHundredPartToString(number / (int) Math.pow(10, 6), true)).append(" ");
        numberToString.append(choiceOfSeparatorWord(number / (int) Math.pow(10, 6) % 100, Ranges.MILLIONS)).append(" ");
        if ((number % (int) Math.pow(10, 6)) / DIGIT_DIVIDER > 0)
            numberToString.append(convertThousandPartToString(number % (int) Math.pow(10, 6)));
        else
            numberToString.append(convertHundredPartToString(number % (int) Math.pow(10, 3), true));
        return numberToString;
    }

    private static StringBuilder convertThousandPartToString(int number) {

        StringBuilder numberToString = new StringBuilder();

        boolean isSeparatorOfOneAndTwo = true;

        if (number / DIGIT_DIVIDER > 0) {
            if (number / DIGIT_DIVIDER % 10 <= 2)
                isSeparatorOfOneAndTwo = false;
            numberToString.append(convertHundredPartToString(number / DIGIT_DIVIDER, isSeparatorOfOneAndTwo)).append(" ");
            numberToString.append(choiceOfSeparatorWord(number / DIGIT_DIVIDER % 100, Ranges.THOUSANDS)).append(" ");
            if (number % DIGIT_DIVIDER > 0)
                numberToString.append(convertHundredPartToString(number % DIGIT_DIVIDER, true));
            return numberToString;
        }
        numberToString.append(convertHundredPartToString(number, true));

        return numberToString;
    }

    private static StringBuilder choiceOfSeparatorWord(int number, Ranges ranges) {

        StringBuilder numberToString = new StringBuilder();

        switch (ranges) {
            case THOUSANDS:
                numberToString.append(separatorWord(number, TOKENS_THOUSAND));
                break;
            case MILLIONS:
                numberToString.append(separatorWord(number, TOKENS_MILLION));
                break;
            case BILLIONS:
                numberToString.append(separatorWord(number, TOKENS_BILLION));
                break;
        }
        return numberToString;
    }

    private static StringBuilder separatorWord(int number, String[] TOKENS) {

        StringBuilder wordsBetweenNumberPosition = new StringBuilder();

        if (number / 10 == 1) {
            wordsBetweenNumberPosition.append(TOKENS[0]);
        } else if (number % 10 == 1) {
            wordsBetweenNumberPosition.append(TOKENS[2]);
        } else if (number % 10 > 4 || number % 10 == 0) {
            wordsBetweenNumberPosition.append(TOKENS[0]);
        } else if (number % 10 > 1) {
            wordsBetweenNumberPosition.append(TOKENS[1]);
        }
        return wordsBetweenNumberPosition;
    }
}
