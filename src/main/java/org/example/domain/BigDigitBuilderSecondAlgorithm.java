package org.example.domain;

import java.math.BigInteger;

import static org.example.domain.HundredBuilder.convertHundredPartToString;

public class BigDigitBuilderSecondAlgorithm {

    private static final BigInteger DIGIT_DIVIDER = BigInteger.valueOf(1000);

    private static final String[] END_INTEGER_WORDS = new String[]{"ов", "а", "и"};

    private static final DataBaseOfWord dataBaseOfWord = DataBaseOfWordsInitializer.getDataBaseOfWord();

    public static StringBuilder separationAlgorithmIntegers(BigInteger number, boolean isThereAFractionalPartOfTheNumber) {

        StringBuilder numberToString = new StringBuilder();

        if (number.compareTo(BigInteger.valueOf(0)) == 0)
            return numberToString.append("ноль");

        double length = String.valueOf(number).length();

        for (double numberOfDigits = Math.ceil(length / 3); numberOfDigits >= 0; numberOfDigits--) {

            if (number.compareTo(DIGIT_DIVIDER) < 0) {
                numberToString.append(convertHundredPartToString(number.intValue(), isThereAFractionalPartOfTheNumber));
                return numberToString;
            }
            if (number.compareTo(BigInteger.valueOf((long) Math.pow(DIGIT_DIVIDER.doubleValue(), 2))) < 0) {
                numberToString.append(SeparatorWordsPool(number.divide(DIGIT_DIVIDER).intValue(), true, numberOfDigits - 1));
                number = number.mod(DIGIT_DIVIDER);
            } else {
                BigInteger partsOfNumberOverAMillion = BigInteger.valueOf((long) Math.pow(DIGIT_DIVIDER.doubleValue(), numberOfDigits - 1));
                numberToString.append(SeparatorWordsPool((number.divide(partsOfNumberOverAMillion)).intValue(), false, numberOfDigits));
                number = number.mod(partsOfNumberOverAMillion);
            }
        }
        return numberToString;
    }

    private static StringBuilder SeparatorWordsPool(long number, boolean isSeparatorOfOneAndTwo, double counterToken) {

        StringBuilder wordBetweenNumberPosition = new StringBuilder();

        if (isSeparatorOfOneAndTwo) {
            if (number % 10 <= 2)
                isSeparatorOfOneAndTwo = false;
            wordBetweenNumberPosition.append(convertHundredPartToString((int) number, isSeparatorOfOneAndTwo)).append(" ");
            wordBetweenNumberPosition.append(choiceOfSeparatingWordThousand(number % 100)).append(" ");
        } else {
            wordBetweenNumberPosition.append(convertHundredPartToString((int) number, true)).append(" ");
            wordBetweenNumberPosition.append(choiceOfSeparatingWordBetweenBigNumberPosition(number % 100, (int) (counterToken - 2))).append(" ");
        }
        return wordBetweenNumberPosition;
    }

    private static StringBuilder choiceOfSeparatingWordBetweenBigNumberPosition(long number, int counterToken) {

        StringBuilder wordBetweenBigNumberPosition = new StringBuilder();

        if (number / 10 == 1 || number % 10 > 4 || number % 10 == 0) {
            wordBetweenBigNumberPosition.append(dataBaseOfWord.getArrDataBaseOfWord()[counterToken]).append(END_INTEGER_WORDS[0]);
        } else if (number % 10 == 1) {
            wordBetweenBigNumberPosition.append(dataBaseOfWord.getArrDataBaseOfWord()[counterToken]);
        } else if (number % 10 > 1) {
            wordBetweenBigNumberPosition.append(dataBaseOfWord.getArrDataBaseOfWord()[counterToken]).append(END_INTEGER_WORDS[1]);
        }
        return wordBetweenBigNumberPosition;
    }

    private static StringBuilder choiceOfSeparatingWordThousand(long number) {

        StringBuilder thousandWord = new StringBuilder();

        if (number / 10 == 1 || number % 10 > 4 || number % 10 == 0) {
            thousandWord.append(dataBaseOfWord.getArrDataBaseOfWord()[0]);
        } else if (number % 10 == 1) {
            thousandWord.append(dataBaseOfWord.getArrDataBaseOfWord()[0]).append(END_INTEGER_WORDS[1]);
        } else if (number % 10 > 1) {
            thousandWord.append(dataBaseOfWord.getArrDataBaseOfWord()[0]).append(END_INTEGER_WORDS[2]);
        }
        return thousandWord;
    }
}
