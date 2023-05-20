package org.example;

public class HundredBuilder {

    static private final String[] TOKENS_DIGITAL_BIG = new String[]{"одна", "две"};

    static private final String[] TOKENS_DIGITAL = new String[]{"один", "два", "три", "четыре",
            "пять", "шесть", "семь", "восемь", "девять"};

    static private final String[] TOKENS_DIGITAL_FIRST = new String[]{"десять", "одиннадцать", "двенадцать", "тринадцать",
            "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};

    static private final String[] TOKENS_DECADES = new String[]{"двадцать", "тридцать", "сорок",
            "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};

    static private final String[] TOKENS_HUNDREDS = new String[]{"сто", "двести", "триста",
            "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};

    static protected final int BASE_VALUE = -1;

    public static StringBuilder convertHundredPartToString(int number, boolean separatorOfOneAndTwo) {

        StringBuilder stringBuilder = new StringBuilder();

        if (number == 0)
            return stringBuilder;

        if (number / 100 < 1)
            stringBuilder.append(convertDecadePartToString(number, separatorOfOneAndTwo));

        if (number / 100 >= 1) {
            stringBuilder.append(TOKENS_HUNDREDS[number / 100 + BASE_VALUE]);
            if (number % 100 > 0) {
                stringBuilder.append(" ");
                stringBuilder.append(convertDecadePartToString(number % 100, separatorOfOneAndTwo));
            }
        }

        return stringBuilder;
    }

    public static StringBuilder convertDecadePartToString(int number, boolean separatorOfOneAndTwo) {

        StringBuilder stringBuilder = new StringBuilder();

        if (number / 10 < 1)
            stringBuilder.append(convertDigitPartToString(number, separatorOfOneAndTwo));

        if (number / 10 == 1)
            stringBuilder.append(TOKENS_DIGITAL_FIRST[number % 10]);


        if (number / 10 > 1) {
            stringBuilder.append(TOKENS_DECADES[number / 10 + 2 * BASE_VALUE]);
            if (number % 10 > 0) {
                stringBuilder.append(" ");
                stringBuilder.append(convertDigitPartToString(number % 10, separatorOfOneAndTwo));
            }
        }

        return stringBuilder;
    }

    public static String convertDigitPartToString(int number, boolean separatorOfOneAndTwo) {

        if (!separatorOfOneAndTwo)
            return TOKENS_DIGITAL_BIG[number + BASE_VALUE];
        return TOKENS_DIGITAL[number + BASE_VALUE];
    }

}

