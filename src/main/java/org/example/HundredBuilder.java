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

    public static StringBuilder hundredStr(int number, boolean bool) {

        StringBuilder stringBuilder = new StringBuilder();

        if (number == 0)
            return stringBuilder;

        if (number / 100 < 1)
            stringBuilder.append(decadesStr(number, bool));

        if (number / 100 >= 1) {
            stringBuilder.append(TOKENS_HUNDREDS[number / 100 + BASE_VALUE]);
            if (number % 100 > 0) {
                stringBuilder.append(" ");
                stringBuilder.append(decadesStr(number % 100, bool));
            }
        }

        return stringBuilder;
    }

    public static StringBuilder decadesStr(int number, boolean bool) {

        StringBuilder stringBuilder = new StringBuilder();

        if (number / 10 < 1)
            stringBuilder.append(digitalStr(number, bool));

        if (number / 10 == 1)
            stringBuilder.append(TOKENS_DIGITAL_FIRST[number % 10]);


        if (number / 10 > 1) {
            stringBuilder.append(TOKENS_DECADES[number / 10 + 2 * BASE_VALUE]);
            if (number % 10 > 0) {
                stringBuilder.append(" ");
                stringBuilder.append(digitalStr(number % 10, bool));
            }
        }

        return stringBuilder;
    }

    public static String digitalStr(int number, boolean bool) {

        if (!bool)
            return TOKENS_DIGITAL_BIG[number + BASE_VALUE];
        return TOKENS_DIGITAL[number + BASE_VALUE];
    }

}

