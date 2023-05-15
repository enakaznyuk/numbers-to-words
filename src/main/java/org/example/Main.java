package org.example;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        /*RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        System.out.println(nf.format(105947));*/

        long testNumber = 99;
        System.out.println("testNumber = " + testNumber);
        System.out.println(testNumber % (int) Math.pow(10, 6));

        /*for(int i = 100000; i < 100000000; i += 111){
            System.out.println("i = " + i);
            System.out.println(BigDigitBuilder.creator(i));
            System.out.println('\t');
        }*/
        System.out.println(BigDigitBuilder.creator((int) testNumber));
    }
}