package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.domain.BigDigitBuilderFirstAlgorithm;
import org.example.domain.BigDigitBuilderSecondAlgorithm;
import org.example.domain.DataBaseOfWord;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberStringBuilder {

    private static DataBaseOfWord dataBaseOfWord;

    private static final String[] TOKENS_WHOLE = new String[]{"целая", "целых"};

    public static StringBuilder firstAlgorithm(int number){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BigDigitBuilderFirstAlgorithm.separationAlgorithmFirst(number));
        return stringBuilder;
    }

    public static StringBuilder secondAlgorithm(BigDecimal number) throws IOException {

        StringBuilder processedNumber = new StringBuilder();

        initDataBaseWord();

        number = checkForMinus(number, processedNumber);

        try {
            processedNumber.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmSecond(number.toBigIntegerExact(), true));
        } catch (ArithmeticException e){
            processedNumber.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmSecond(number.toBigInteger(), false)).append(" ");

            choiceOfWordsForIntegerAndFractionalParts(number, processedNumber, true);
            number = preparingANumberForFractionalProcessing(number);

            processedNumber.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmSecond(number.toBigInteger(), false)).append(" ");
            choiceOfWordsForIntegerAndFractionalParts(number, processedNumber, false);
        }
        return processedNumber;
    }

    private static StringBuilder choiceWholeWord(BigInteger number){

        StringBuilder wordBetweenIntegerAndFractionalParts = new StringBuilder();

        if (number.divide(BigInteger.TEN).intValue() == 1)
            return wordBetweenIntegerAndFractionalParts.append(TOKENS_WHOLE[1]);
        else if (number.mod(BigInteger.TEN).intValue() == 1)
            return wordBetweenIntegerAndFractionalParts.append(TOKENS_WHOLE[0]);
        
        return wordBetweenIntegerAndFractionalParts.append(TOKENS_WHOLE[1]);
    }

    private static StringBuilder choicePartWord(BigInteger number, int numberOfDigits){

        StringBuilder fractionalSize = new StringBuilder();

        if (number.divide(BigInteger.TEN).intValue() == 1) {
            fractionalSize.append(dataBaseOfWord.getArrDataBaseOfPartWord()[numberOfDigits]);
            return fractionalSize.append("ых");
        } else if (number.mod(BigInteger.TEN).intValue() == 1) {
            fractionalSize.append(dataBaseOfWord.getArrDataBaseOfPartWord()[numberOfDigits]);
            return fractionalSize.append("ая");
        }
        return fractionalSize.append(dataBaseOfWord.getArrDataBaseOfPartWord()[numberOfDigits]).append("ых");
    }

    private static void initDataBaseWord() throws IOException {
        File fileWithWordData = new File("./src/main/resources/DataOfWords.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        dataBaseOfWord = mapper.readValue(fileWithWordData, DataBaseOfWord.class);
    }

    private static BigDecimal checkForMinus(BigDecimal number, StringBuilder minus){

        if (number.compareTo(BigDecimal.valueOf(0)) < 0) {
            minus.append("минус").append(" ");
            number = number.multiply(BigDecimal.valueOf(-1));
        }
        return number;
    }

    private static void choiceOfWordsForIntegerAndFractionalParts(BigDecimal number, StringBuilder separatorWord, boolean isIntegerOrFractional){

        int length = number.toBigInteger().bitCount();

        BigInteger divideByOneHundred = number.toBigInteger().mod(BigInteger.valueOf(100));
        
        if(isIntegerOrFractional){
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

    private static BigDecimal preparingANumberForFractionalProcessing(BigDecimal number){

        number = number.stripTrailingZeros();
        number = number.remainder(BigDecimal.ONE);
        int length = number.scale();
        number = number.movePointRight(length);

        return number;
    }
}
