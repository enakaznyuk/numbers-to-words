package org.example.service;

import org.example.domain.BigDigitBuilderFirstAlgorithm;
import org.example.domain.BigDigitBuilderSecondAlgorithm;
import org.example.domain.DataBaseOfWord;
import org.example.domain.DataBaseOfWordsInitializer;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberStringBuilder {

    private static final DataBaseOfWord dataBaseOfWord = DataBaseOfWordsInitializer.getDataBaseOfWord();

    private static final String[] TOKENS_WHOLE = new String[]{"целая", "целых"};
    private static final String[] END_FRACTIONAL_WORDS = new String[]{"ых", "ая"};

    public static StringBuilder firstAlgorithmBuilder(int number) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BigDigitBuilderFirstAlgorithm.separationAlgorithmFirst(number));
        return stringBuilder;
    }

    public static StringBuilder secondAlgorithmBuilder(BigDecimal number) {

        StringBuilder processedNumber = new StringBuilder();

        number = checkForMinus(number, processedNumber);

        try {
            isTheDatabaseBigEnough(number.toBigIntegerExact(), true);
            processedNumber.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmIntegers(number.toBigIntegerExact(), true));
        } catch (ArithmeticException e) {
            isTheDatabaseBigEnough(number.toBigInteger(), true);
            processedNumber.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmIntegers(number.toBigInteger(), false)).append(" ");

            choiceOfWordsForIntegerAndFractionalParts(number, processedNumber, true);
            number = preparingANumberForFractionalProcessing(number);

            isTheDatabaseBigEnough(number.toBigInteger(), false);
            processedNumber.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmIntegers(number.toBigInteger(), false)).append(" ");
            choiceOfWordsForIntegerAndFractionalParts(number, processedNumber, false);
        }
        return processedNumber;
    }

    private static StringBuilder choiceWholeWord(BigInteger number) {

        StringBuilder wordBetweenIntegerAndFractionalParts = new StringBuilder();

        if (number.divide(BigInteger.TEN).intValue() == 1)
            return wordBetweenIntegerAndFractionalParts.append(TOKENS_WHOLE[1]);
        else if (number.mod(BigInteger.TEN).intValue() == 1)
            return wordBetweenIntegerAndFractionalParts.append(TOKENS_WHOLE[0]);

        return wordBetweenIntegerAndFractionalParts.append(TOKENS_WHOLE[1]);
    }

    private static StringBuilder choicePartWord(BigInteger number, int numberOfDigits) {

        StringBuilder fractionalSize = new StringBuilder();

        if (number.divide(BigInteger.TEN).intValue() == 1) {
            fractionalSize.append(dataBaseOfWord.getArrDataBaseOfPartWord()[numberOfDigits]);
            return fractionalSize.append(END_FRACTIONAL_WORDS[0]);
        } else if (number.mod(BigInteger.TEN).intValue() == 1) {
            fractionalSize.append(dataBaseOfWord.getArrDataBaseOfPartWord()[numberOfDigits]);
            return fractionalSize.append(END_FRACTIONAL_WORDS[1]);
        }
        return fractionalSize.append(dataBaseOfWord.getArrDataBaseOfPartWord()[numberOfDigits]).append(END_FRACTIONAL_WORDS[0]);
    }

    private static BigDecimal checkForMinus(BigDecimal number, StringBuilder minus) {

        if (number.compareTo(BigDecimal.valueOf(0)) < 0) {
            minus.append("минус").append(" ");
            number = number.multiply(BigDecimal.valueOf(-1));
        }
        return number;
    }

    private static void choiceOfWordsForIntegerAndFractionalParts(BigDecimal number, StringBuilder separatorWord, boolean isIntegerOrFractional) {

        int length = number.toBigInteger().bitCount();

        BigInteger divideByOneHundred = number.toBigInteger().mod(BigInteger.valueOf(100));

        if (isIntegerOrFractional) {
            if (length < 3)
                separatorWord.append(choiceWholeWord(number.toBigInteger())).append(" ");
            else
                separatorWord.append(choiceWholeWord(divideByOneHundred)).append(" ");
        } else {
            length = number.toString().length();
            if (length < 3)
                separatorWord.append(choicePartWord(number.toBigInteger(), length - 1)).append(" ");
            else
                separatorWord.append(choicePartWord(divideByOneHundred, length - 1)).append(" ");
        }
    }

    private static BigDecimal preparingANumberForFractionalProcessing(BigDecimal number) {

        number = number.stripTrailingZeros();
        number = number.remainder(BigDecimal.ONE);
        int length = number.scale();
        number = number.movePointRight(length);

        return number;
    }

    private static void isTheDatabaseBigEnough(BigInteger number, boolean isIntegerOrFractionalPartOfDatabase){

        int length = number.toString().length();
        if(isIntegerOrFractionalPartOfDatabase){
            if(length > dataBaseOfWord.getArrDataBaseOfWord().length)
                throw new RuntimeException("Database of integers is too short");
        } else {
            if(length > dataBaseOfWord.getArrDataBaseOfPartWord().length)
                throw new RuntimeException("Database of fractional part is too short");
        }

    }
}
