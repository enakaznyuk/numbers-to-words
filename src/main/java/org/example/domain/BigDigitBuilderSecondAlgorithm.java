package org.example.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.domain.exception.InvalidNumberException;

import java.io.File;
import java.io.IOException;

import static org.example.domain.HundredBuilder.convertHundredPartToString;

public class BigDigitBuilderSecondAlgorithm {

    private static final int DIGIT_DIVIDER = 1000;

    private static DataBaseOfWord dataBaseOfWord;

    public static StringBuilder separationAlgorithmSecond(long number) throws IOException {

        StringBuilder numberToString = new StringBuilder();
        initDataBaseWord();

        if (number <= 0) {
            throw new InvalidNumberException("Number must be > 0!");
        }

        double length = String.valueOf(number).length();

        for (double numberOfDigits = Math.ceil(length / 3), dynamicNumberLength = (int) length; numberOfDigits >= 0; numberOfDigits--, dynamicNumberLength -= 3) {
            if (number < DIGIT_DIVIDER) {
                numberToString.append(convertHundredPartToString((int) number, true));
                return numberToString;
            } else if (dynamicNumberLength != 6 & dynamicNumberLength % 3 == 0) {
                numberToString.append(choiceOfSeparatorWordSecond((long) (number / Math.pow(DIGIT_DIVIDER, numberOfDigits - 1)), 2, numberOfDigits));
                number %= Math.pow(DIGIT_DIVIDER, numberOfDigits - 1);
            } else {
                if (number < Math.pow(DIGIT_DIVIDER, 2)) {
                    numberToString.append(choiceOfSeparatorWordSecond(number / DIGIT_DIVIDER, 1, numberOfDigits - 1));
                    number %= DIGIT_DIVIDER;
                } else {
                    numberToString.append(choiceOfSeparatorWordSecond((number / (long) Math.pow(DIGIT_DIVIDER, numberOfDigits - 1)), 2, numberOfDigits));
                    number %= (long) Math.pow(DIGIT_DIVIDER, numberOfDigits - 1);
                }
            }
        }
        return numberToString;
    }

    private static StringBuilder choiceOfSeparatorWordSecond(long number, int counterOneAndTwo, double counterToken) {

        StringBuilder numberToString = new StringBuilder();
        boolean isSeparatorOfOneAndTwo = true;

        switch (counterOneAndTwo) {
            case 1:
                if (number % 10 <= 2)
                    isSeparatorOfOneAndTwo = false;
                numberToString.append(convertHundredPartToString((int) number, isSeparatorOfOneAndTwo)).append(" ");
                numberToString.append(separatorWord(number % 100, (int) (counterToken - 1))).append(" ");
                break;
            case 2:
                numberToString.append(convertHundredPartToString((int) number, true)).append(" ");
                numberToString.append(separatorWord(number % 100, (int) (counterToken - 2))).append(" ");
                break;
        }
        return numberToString;
    }

    private static StringBuilder separatorWord(long number, int counterToken) {

        StringBuilder numberToString = new StringBuilder();

        int firstPlace = 0;
        int secondPlace = 1;
        int thirdPlace = 2;

        if (number / 10 == 1) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[firstPlace + 3 * counterToken]);
        } else if (number % 10 == 1) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[thirdPlace + 3 * counterToken]);
        } else if (number % 10 > 4 || number % 10 == 0) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[firstPlace + 3 * counterToken]);
        } else if (number % 10 > 1) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[secondPlace + 3 * counterToken]);
        }
        return numberToString;
    }

    private static void initDataBaseWord() throws IOException {
        File fileWithTestData = new File("./src/main/resources/DataOfWords.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        dataBaseOfWord = mapper.readValue(fileWithTestData, DataBaseOfWord.class);
    }
}
