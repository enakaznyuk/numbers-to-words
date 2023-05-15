package org.example;

import static org.example.HundredBuilder.hundredStr;

public class BigDigitBuilder {

    static private final String[] TOKENS_THOUSAND = new String[]{"тысяча", "тысячи", "тысяч"};

    static private final String[] TOKENS_MILLION = new String[]{"миллионов", "миллиона", "миллион"};

    private enum Ranges {
        THOUSANDS, MILLIONS, BILLIONS
    }

    public static StringBuilder creator(int number) {

        StringBuilder stringBuilder = new StringBuilder();

        if (number < Math.pow(10, 6)) {
            stringBuilder.append(thousandStr(number, true));
            /*stringBuilder.append(" ");
            stringBuilder.append(choice(number / 1000 % 10, Ranges.THOUSANDS));
            stringBuilder.append(" ");*/
            return stringBuilder;
        } else if (number < Math.pow(10, 9)) {
            stringBuilder.append(millionStr(number, true));
            return stringBuilder;
        }
        return stringBuilder;
    }

    public static StringBuilder millionStr(int number, boolean bool) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(hundredStr(number / (int) Math.pow(10, 6), bool));
        stringBuilder.append(" ");
        stringBuilder.append(choice(number / (int) Math.pow(10, 6) % 100, Ranges.MILLIONS));
        stringBuilder.append(" ");
        if ((number % (int) Math.pow(10, 6)) / 1000 > 0)
            stringBuilder.append(thousandStr(number % (int) Math.pow(10, 6), bool));
        else
            stringBuilder.append(hundredStr(number % (int) Math.pow(10, 3), bool));
        return stringBuilder;
    }

    public static StringBuilder thousandStr(int number, boolean bool) {

        StringBuilder stringBuilder = new StringBuilder();

        /*if (number / 1000 < 1)
            stringBuilder.append(hundredStr(number, !bool)); */// ошибка в не равно бул
        if (number / 1000 > 0) {
            if (number / 1000 % 10 <= 2)
                bool = false;
            stringBuilder.append(hundredStr(number / 1000, bool));
            stringBuilder.append(" ");
            stringBuilder.append(choice(number / 1000 % 100, Ranges.THOUSANDS));
            stringBuilder.append(" ");
            if (number % 1000 > 0)
                stringBuilder.append(hundredStr(number % 1000, true));
            return stringBuilder;
        }
        stringBuilder.append(hundredStr(number, true));

        return stringBuilder;
    }

    public static StringBuilder choice(int number, Ranges ranges) {

        StringBuilder stringBuilder = new StringBuilder();

        //System.out.println("number = " + number);

        //0 = тысяча" 1 = "тысячи" 2 =  "тысяч"
        //    миллионов   миллиона      миллион

        //System.out.println("number%10 = " + number % 10);

        switch (ranges) {
            case THOUSANDS:

                if (number / 10 == 1){
                    stringBuilder.append(TOKENS_THOUSAND[2]);
                } else if (number % 10 == 1/* || number % 10 == 0*/) {
                    stringBuilder.append(TOKENS_THOUSAND[0]);
                } else if (number % 10 > 4 || number % 10 == 0) {
                    stringBuilder.append(TOKENS_THOUSAND[2]);
                } else if (number % 10 > 0) {
                    stringBuilder.append(TOKENS_THOUSAND[1]);
                }
                break;
            case MILLIONS:
                if (number / 10 == 1){
                    stringBuilder.append(TOKENS_MILLION[0]);
                } else if (number % 10 == 1/* || number % 10 == 0*/) {
                    stringBuilder.append(TOKENS_MILLION[2]);
                } else if (number % 10 > 4 || number % 10 == 0) {
                    stringBuilder.append(TOKENS_MILLION[0]);
                } else if (number % 10 > 1) {
                    stringBuilder.append(TOKENS_MILLION[1]);
                }
                break;
            case BILLIONS:
                break;
            default:
                break;
        }

        return stringBuilder;
    }
}
