package org.example;

import com.ibm.icu.text.RuleBasedNumberFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.NumberStringBuilder;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        LOGGER.info("Enter a positive integer: ");
        Scanner scanner = new Scanner(System.in);
        long pages = scanner.nextLong();

        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);
        System.out.println(nf.format(105947));

        LOGGER.info(NumberStringBuilder.secondAlgorithm(pages));
        LOGGER.info(NumberStringBuilder.firstAlgorithm((int) pages));
    }
}