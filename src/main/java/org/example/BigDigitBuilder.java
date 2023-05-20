package org.example;

import org.example.exception.InvalidNumberException;

import static org.example.HundredBuilder.convertHundredPartToString;

public class BigDigitBuilder {

    static private final String[] TOKENS_THOUSAND = new String[]{"тысяча", "тысячи", "тысяч"};

    static private final String[] TOKENS_MILLION = new String[]{"миллионов", "миллиона", "миллион"};

    static private final String[] TOKENS_BILLION = new String[]{"миллиардов", "миллиарда", "миллиард"};

    private enum Ranges {
        THOUSANDS, MILLIONS, BILLIONS
    }

    public static StringBuilder numberSeparator(int number) {

        if(number <=  0){
            throw new InvalidNumberException("Number must be > 0!");
        }

        StringBuilder stringBuilder = new StringBuilder();

        if (number < Math.pow(10, 6)) {
            stringBuilder.append(convertThousandPartToString(number, true));
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

        System.out.println("number = " + number);
        switch (ranges) {
            case THOUSANDS:
                if (number / 10 == 1) {
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
                if (number / 10 == 1) {
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
                if (number / 10 == 1) {
                    stringBuilder.append(TOKENS_BILLION[0]);
                } else if (number % 10 == 1) {
                    stringBuilder.append(TOKENS_BILLION[2]);
                } else if (number % 10 > 4 || number % 10 == 0) {
                    stringBuilder.append(TOKENS_BILLION[0]);
                } else if (number % 10 > 1) {
                    stringBuilder.append(TOKENS_BILLION[1]);
                }
                break;
            default:
                break;
        }

        return stringBuilder;
    }

    //////////////////////////////////////////////////////

    public static StringBuilder newBuild(long number) {
        StringBuilder stringBuilder = new StringBuilder();

        if(number <=  0){
            throw new InvalidNumberException("Number must be > 0!");
        }

        long length = String.valueOf(number).length();

        for (long i = length / 3, j = length; i >= 0; i--, j -= 3) {
            System.out.println("i = " + i);
            System.out.println("number = " + number);
            System.out.println("i % 3 = " + i % 3);
            if (number < 1000) {
                stringBuilder.append(convertHundredPartToString((int) number, true));
                return stringBuilder;
            } else if (j != 6 & j % 3 == 0) {
                stringBuilder.append(choiceOfSeparatorWordSecond((long) (number / Math.pow(1000, i - 1)), 2));
                number %= Math.pow(1000, i - 1);
            } else {
                if (number < Math.pow(1000, 2)) {
                    System.out.println("i == 2");
                    stringBuilder.append(choiceOfSeparatorWordSecond(number / 1000, 1));
                    number %= 1000;
                } else {
                    System.out.println("else");
                    stringBuilder.append(choiceOfSeparatorWordSecond((number / (long)Math.pow(1000, i)), 2));
                    number %= (long)Math.pow(1000, i);
                }
            }
        }
        return stringBuilder;
    }

    public static StringBuilder choiceOfSeparatorWordSecond(long number, int counter) {

        StringBuilder stringBuilder = new StringBuilder();
        boolean separatorOfOneAndTwo = true;

        switch (counter) {
            case 1:

                System.out.println("number 1 = " + number);

                if (number % 10 <= 2)
                    separatorOfOneAndTwo = false;
                stringBuilder.append(convertHundredPartToString((int) number, separatorOfOneAndTwo));
                stringBuilder.append(" ");
                if (number / 10 == 1) {
                    stringBuilder.append(TOKENS_THOUSAND[2]);
                } else if (number % 10 == 1) {
                    stringBuilder.append(TOKENS_THOUSAND[0]);
                } else if (number % 10 > 4 || number % 10 == 0) {
                    stringBuilder.append(TOKENS_THOUSAND[2]);
                } else if (number % 10 > 0) {
                    stringBuilder.append(TOKENS_THOUSAND[1]);
                }
                stringBuilder.append(" ");
                System.out.println(stringBuilder);
                break;
            case 2:
                System.out.println(number);
                stringBuilder.append(convertHundredPartToString((int) number, true));
                stringBuilder.append(" ");
                if (number / 10 == 1) {
                    stringBuilder.append(TOKENS_MILLION[0]);
                } else if (number % 10 == 1) {
                    stringBuilder.append(TOKENS_MILLION[2]);
                } else if (number % 10 > 4 || number % 10 == 0) {
                    stringBuilder.append(TOKENS_MILLION[0]);
                } else if (number % 10 > 1) {
                    stringBuilder.append(TOKENS_MILLION[1]);
                }
                stringBuilder.append(" ");
                System.out.println(stringBuilder);
                break;
            default:
                break;
        }

        return stringBuilder;
    }
}
