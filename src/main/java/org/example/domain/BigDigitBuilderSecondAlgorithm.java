package org.example.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import static org.example.domain.HundredBuilder.convertHundredPartToString;

public class BigDigitBuilderSecondAlgorithm {

    private static final BigInteger DIGIT_DIVIDER = BigInteger.valueOf(1000);

    private static DataBaseOfWord dataBaseOfWord;

    public static StringBuilder separationAlgorithmSecond(BigInteger number, boolean isThereAFractionalPartOfTheNumber) throws IOException {

        StringBuilder numberToString = new StringBuilder();
        initDataBaseWord();

        if (number.compareTo(BigInteger.valueOf(0)) == 0)
            return numberToString.append("ноль");

        double length = String.valueOf(number).length();

        for (double numberOfDigits = Math.ceil(length / 3); numberOfDigits >= 0; numberOfDigits--) {

            if (number.compareTo(DIGIT_DIVIDER) < 0) {
                numberToString.append(convertHundredPartToString(number.intValue(), isThereAFractionalPartOfTheNumber));
                return numberToString;
            }
            if (number.compareTo(BigInteger.valueOf((long) Math.pow(DIGIT_DIVIDER.doubleValue(), 2))) < 0) {
                numberToString.append(choiceBetweenOneAndTwoWords(number.divide(DIGIT_DIVIDER).intValue(), true, numberOfDigits - 1));
                number = number.mod(DIGIT_DIVIDER);
            } else {
                //BigInteger numberOverAMillion= BigInteger.valueOf((long) Math.pow(DIGIT_DIVIDER.doubleValue(), numberOfDigits - 1));
                numberToString.append(choiceBetweenOneAndTwoWords((number.divide(BigInteger.valueOf((long) Math.pow(DIGIT_DIVIDER.doubleValue(), numberOfDigits - 1)))).intValue(), false, numberOfDigits));
                number = number.mod(BigInteger.valueOf((long) Math.pow(DIGIT_DIVIDER.doubleValue(), numberOfDigits - 1)));
            }
        }
        return numberToString;
    }

    // number.divide(BigInteger.valueOf((long) Math.pow(DIGIT_DIVIDER.doubleValue(), numberOfDigits - 1)))).intValue() повыносить всё в отдельные переменые

    private static StringBuilder choiceBetweenOneAndTwoWords(long number, boolean isSeparatorOfOneAndTwo, double counterToken) {

        StringBuilder numberToString = new StringBuilder();

        if (isSeparatorOfOneAndTwo) {
            if (number % 10 <= 2)
                isSeparatorOfOneAndTwo = false;
            numberToString.append(convertHundredPartToString((int) number, isSeparatorOfOneAndTwo)).append(" ");
            numberToString.append(choiceOfSeparatingWordBetweenNumberPosition(number % 100, (int) (counterToken - 1))).append(" ");
        } else {
            numberToString.append(convertHundredPartToString((int) number, true)).append(" ");
            numberToString.append(choiceOfSeparatingWordBetweenNumberPosition(number % 100, (int) (counterToken - 2))).append(" ");
        }
        return numberToString;
    }

    private static StringBuilder choiceOfSeparatingWordBetweenNumberPosition(long number, int counterToken) {

        StringBuilder numberToString = new StringBuilder();

        int firstPlace = 0;
        int secondPlace = 1;
        int thirdPlace = 2;

        //можно передавать тысяч миллион миллиард и тд, а ниже вместо токенов добавлять нужные окончания и всё(словарь станет читабельнее)

        //объединить ветки (number / 10 == 1) и else if (number % 10 > 4 || number % 10 == 0) {

        if (number / 10 == 1 || number % 10 > 4 || number % 10 == 0) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[firstPlace + 3 * counterToken]);
        } else if (number % 10 == 1) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[thirdPlace + 3 * counterToken]);
        }/* else if (number % 10 > 4 || number % 10 == 0) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[firstPlace + 3 * counterToken]);
        } */ else if (number % 10 > 1) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[secondPlace + 3 * counterToken]);
        }
        return numberToString;
    }

    /*private static StringBuilder choiceOfSeparatingWordBetweenNumberPosition(long number, int counterToken) {

        StringBuilder numberToString = new StringBuilder();

        if (number / 10 == 1 || number % 10 > 4 || number % 10 == 0) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[counterToken]).append("ов");
        } else if (number % 10 == 1) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[counterToken]);
        } else if (number % 10 > 1) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[counterToken]).append("а");
        }
        return numberToString;
    }

    private static StringBuilder choiceOfSeparatingWordThousand(long number) {

        StringBuilder numberToString = new StringBuilder();

        if (number / 10 == 1 || number % 10 > 4 || number % 10 == 0) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[0]);
        } else if (number % 10 == 1) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[0]).append("а");
        } else if (number % 10 > 1) {
            numberToString.append(dataBaseOfWord.getArrDataBaseOfWord()[0]).append("и");
        }
        return numberToString;
    }*/

    private static void initDataBaseWord() throws IOException {
        File fileWithWordData = new File("./src/main/resources/DataOfWords.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        dataBaseOfWord = mapper.readValue(fileWithWordData, DataBaseOfWord.class);
    }
}
