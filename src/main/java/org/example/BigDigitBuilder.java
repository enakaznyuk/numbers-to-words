package org.example;

import static org.example.HundredBuilder.convertHundredPartToString;

public class BigDigitBuilder {

    static private final String[] TOKENS_THOUSAND = new String[]{"тысяча", "тысячи", "тысяч"};

    static private final String[] TOKENS_MILLION = new String[]{"миллионов", "миллиона", "миллион"};

    private enum Ranges {
        THOUSANDS, MILLIONS, BILLIONS
    }

    public static StringBuilder numberSeparator(int number) {

        StringBuilder stringBuilder = new StringBuilder();

        if (number < Math.pow(10, 6)) {
            stringBuilder.append(convertThousandPartToString(number, true));
            /*stringBuilder.append(" ");
            stringBuilder.append(choice(number / 1000 % 10, Ranges.THOUSANDS));
            stringBuilder.append(" ");*/
            return stringBuilder;
        } else if (number < Math.pow(10, 9)) {
            stringBuilder.append(convertMillionPartToString(number, true));
            return stringBuilder;
        }
        return stringBuilder;
    }

    public static StringBuilder convertMillionPartToString(int number, boolean separatorOfOneAndTwo) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(convertHundredPartToString(number / (int) Math.pow(10, 6), separatorOfOneAndTwo));
        stringBuilder.append(" ");
        stringBuilder.append(choiceOfSeparatorWord(number / (int) Math.pow(10, 6) % 100, Ranges.MILLIONS));
        stringBuilder.append(" ");
        if ((number % (int) Math.pow(10, 6)) / 1000 > 0)
            stringBuilder.append(convertThousandPartToString(number % (int) Math.pow(10, 6), separatorOfOneAndTwo));
        else
            stringBuilder.append(convertHundredPartToString(number % (int) Math.pow(10, 3), separatorOfOneAndTwo));
        return stringBuilder;
    }

    public static StringBuilder convertThousandPartToString(int number, boolean separatorOfOneAndTwo) {

        StringBuilder stringBuilder = new StringBuilder();

        /*if (number / 1000 < 1)
            stringBuilder.append(hundredStr(number, !separatorOfOneAndTwo)); */// ошибка в не равно бул
        if (number / 1000 > 0) {
            if (number / 1000 % 10 <= 2)
                separatorOfOneAndTwo = false;
            stringBuilder.append(convertHundredPartToString(number / 1000, separatorOfOneAndTwo));
            stringBuilder.append(" ");
            stringBuilder.append(choiceOfSeparatorWord(number / 1000 % 100, Ranges.THOUSANDS));
            stringBuilder.append(" ");
            if (number % 1000 > 0)
                stringBuilder.append(convertHundredPartToString(number % 1000, true));
            return stringBuilder;
        }
        stringBuilder.append(convertHundredPartToString(number, true));

        return stringBuilder;
    }

    public static StringBuilder choiceOfSeparatorWord(int number, Ranges ranges) {

        StringBuilder stringBuilder = new StringBuilder();

        //System.out.println("number = " + number);

        //0 = тысяча" 1 = "тысячи" 2 =  "тысяч"
        //    миллионов   миллиона      миллион

        //System.out.println("number%10 = " + number % 10);

        switch (ranges) {
            case THOUSANDS:

                if (number / 10 == 1){
                    stringBuilder.append(TOKENS_THOUSAND[2]);
                } else if (number % 10 == 1) {
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
                } else if (number % 10 == 1) {
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

    //////////////////////////////////////////////////////

    public static StringBuilder newBuild(int number){
        StringBuilder stringBuilder = new StringBuilder();

        int length = String.valueOf(number).length();
        System.out.println(length);

        for(int i = 0; i < 10; i++){
            System.out.println("i = " + i + "\ta % 3 = " + i % 3 + "\t a / 3 = " + i / 3 + '\n');
        }
        System.out.println("length / 3 = " + length / 3);

        if((length % 3) == 0)
            System.out.println("number = " +  number / Math.pow(1000, (length/3) - 1));
        else System.out.println("number = " + (int) (number / Math.pow(1000, (length/3))));


        if(length <= 3)
            stringBuilder.append(convertHundredPartToString(number, true));
        else {
            System.out.println("зашло в элс ");
            for(int i = length / 3; i > 0; i--){
                System.out.println("зашло в цикл в элсе ");
                stringBuilder.append(choiceOfSeparatorWordSecond((int) (number / Math.pow(1000, i)), i));
            }
            stringBuilder.append(convertHundredPartToString(number % 1000, true));
        }
        return stringBuilder;
    }

    public static StringBuilder choiceOfSeparatorWordSecond(int number, int counter) {

        StringBuilder stringBuilder = new StringBuilder();
        boolean separatorOfOneAndTwo = true;
        System.out.println("зашло в новый выборщик ");

        switch (counter) {
            case 1:

                System.out.println(number);
                //0 = тысяча" 1 = "тысячи" 2 =  "тысяч"

                if (number % 10 <= 2)
                    separatorOfOneAndTwo = false;
                stringBuilder.append(convertHundredPartToString(number, separatorOfOneAndTwo));
                stringBuilder.append(" ");
                if (number % 100 == 11){
                    stringBuilder.append(TOKENS_THOUSAND[2]);
                } else if (number % 10 == 1) {
                    stringBuilder.append(TOKENS_THOUSAND[0]);
                } else if (number % 10 > 4 || number % 10 == 0) {
                    stringBuilder.append(TOKENS_THOUSAND[2]);
                } else if (number % 10 > 0) {
                    stringBuilder.append(TOKENS_THOUSAND[1]);
                }
                stringBuilder.append(" ");
                /*if (number % 1000 > 0)
                    stringBuilder.append(convertHundredPartToString(number % 1000, true));*/
                //return stringBuilder;

                //stringBuilder.append(convertHundredPartToString(number, true));
                break;
            case 2:
                break;
            default:
                break;
        }

        return stringBuilder;
    }
}
