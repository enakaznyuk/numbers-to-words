package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        /*RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        System.out.println(nf.format(105947));*/

        long testNumber = 11111;
        System.out.println("testNumber = " + testNumber);
        //System.out.println(testNumber % (int) Math.pow(10, 6));

        //BigDigitBuilder.newBuild((int) testNumber, true);

        System.out.println(BigDigitBuilder.newBuild( 11111));
        System.out.println(BigDigitBuilder.numberSeparator((int) testNumber));
    }
}