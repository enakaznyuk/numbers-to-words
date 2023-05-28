package org.example.domain;

public class HundredBuilder {

    private static final String[] TOKENS_DIGITAL_BIG = new String[]{"одна", "две"};

    private static final String[] TOKENS_DIGITAL = new String[]{"один", "два", "три", "четыре",
            "пять", "шесть", "семь", "восемь", "девять"};

    private static final String[] TOKENS_DIGITAL_FIRST = new String[]{"десять", "одиннадцать", "двенадцать", "тринадцать",
            "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};

    private static final String[] TOKENS_DECADES = new String[]{"двадцать", "тридцать", "сорок",
            "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};

    private static final String[] TOKENS_HUNDREDS = new String[]{"сто", "двести", "триста",
            "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};

    private static final int BASE_VALUE = -1;

    protected static StringBuilder convertHundredPartToString(int number, boolean isSeparatorOfOneAndTwo) {

        StringBuilder numberToString = new StringBuilder();

        if (number == 0)
            return numberToString;
        if (number / 100 < 1)
            numberToString.append(convertDecadePartToString(number, isSeparatorOfOneAndTwo));
        if (number / 100 >= 1) {
            numberToString.append(TOKENS_HUNDREDS[number / 100 + BASE_VALUE]);
            if (number % 100 > 0) {
                numberToString.append(" ").append(convertDecadePartToString(number % 100, isSeparatorOfOneAndTwo));
            }
        }
        return numberToString;
    }

    private static StringBuilder convertDecadePartToString(int number, boolean isSeparatorOfOneAndTwo) {

        StringBuilder numberToString = new StringBuilder();

        if (number / 10 < 1)
            numberToString.append(convertDigitPartToString(number, isSeparatorOfOneAndTwo));
        if (number / 10 == 1)
            numberToString.append(TOKENS_DIGITAL_FIRST[number % 10]);
        if (number / 10 > 1) {
            numberToString.append(TOKENS_DECADES[number / 10 + 2 * BASE_VALUE]);
            if (number % 10 > 0) {
                numberToString.append(" ").append(convertDigitPartToString(number % 10, isSeparatorOfOneAndTwo));
            }
        }
        return numberToString;
    }

    private static String convertDigitPartToString(int number, boolean isSeparatorOfOneAndTwo) {

        if (!isSeparatorOfOneAndTwo & number <= 2)
            return TOKENS_DIGITAL_BIG[number + BASE_VALUE];
        return TOKENS_DIGITAL[number + BASE_VALUE];
    }

}

