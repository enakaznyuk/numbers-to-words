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

    private static BigDigitBuilderSecondAlgorithm bigDigitBuilderSecondAlgorithm;

    private static DataBaseOfWord dataBaseOfWord;

    private static final String[] TOKENS_WHOLE = new String[]{"целая", "целых"};

    public static StringBuilder firstAlgorithm(int number){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BigDigitBuilderFirstAlgorithm.separationAlgorithmFirst(number));
        return stringBuilder;
    }

    public static StringBuilder secondAlgorithm(BigDecimal number) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        initDataBaseWord();

        try {
            stringBuilder.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmSecond(number.toBigIntegerExact(), true));
        } catch (ArithmeticException e){
            stringBuilder.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmSecond(number.toBigInteger(), false)).append(" ");

            if (number.toBigInteger().bitCount() < 100)
                stringBuilder.append(choiceWholeWord(number.toBigInteger())).append(" ");
            else
                stringBuilder.append(choiceWholeWord(number.toBigInteger().shiftLeft(2))).append(" ");

            number = number.stripTrailingZeros();
            number = number.remainder(BigDecimal.ONE);
            number = number.movePointRight(number.scale());
            stringBuilder.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmSecond(number.toBigInteger(), false));

            System.out.println(number.scale());

            if (number.toBigInteger().bitCount() < 100)
                stringBuilder.append(choicePartWord(number.toBigInteger(), number.scale() - 1)).append(" ");
            else
                stringBuilder.append(choicePartWord(number.toBigInteger().shiftLeft(2), number.scale() - 1)).append(" ");

        }
        //stringBuilder.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmSecond(number, true));
        return stringBuilder;
    }

    private static StringBuilder choiceWholeWord(BigInteger number){

        StringBuilder stringBuilder = new StringBuilder();

        if (number.divide(BigInteger.valueOf(10)).intValue() == 1) {
            return stringBuilder.append(TOKENS_WHOLE[1]);
        } else if (number.mod(BigInteger.valueOf(10)).intValue() == 1) {
            return stringBuilder.append(TOKENS_WHOLE[0]);
        }
        return stringBuilder.append(TOKENS_WHOLE[1]);
    }

    private static StringBuilder choicePartWord(BigInteger number, int numberOfDigits){

        StringBuilder stringBuilder = new StringBuilder();



        if (number.divide(BigInteger.valueOf(10)).intValue() == 1) {
            stringBuilder.append(dataBaseOfWord.getArrDataBaseOfPartWord()[numberOfDigits]);
            return stringBuilder.append("ых");
        } else if (number.mod(BigInteger.valueOf(10)).intValue() == 1) {
            stringBuilder.append(dataBaseOfWord.getArrDataBaseOfPartWord()[numberOfDigits]);
            return stringBuilder.append("ая");
        }

        return stringBuilder.append(dataBaseOfWord.getArrDataBaseOfPartWord()[numberOfDigits]);
    }

    private static void initDataBaseWord() throws IOException {
        File fileWithTestData = new File("./src/main/resources/DataOfWords.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        dataBaseOfWord = mapper.readValue(fileWithTestData, DataBaseOfWord.class);
    }
}
