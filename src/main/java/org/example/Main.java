package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Hello world!");

        /*RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        System.out.println(nf.format(105947));*/

        long testNumber = 11345;
        System.out.println("testNumber = " + testNumber);
        //System.out.println(testNumber % (int) Math.pow(10, 6));

        //BigDigitBuilder.newBuild((int) testNumber, true);


        LOGGER.info(BigDigitBuilder.newBuild( 123456789123456L));
        LOGGER.info(BigDigitBuilder.numberSeparator((int) testNumber));
        //System.out.println(BigDigitBuilder.newBuild( 123456789123456L));
        //System.out.println(BigDigitBuilder.numberSeparator((int) testNumber));
    }
}