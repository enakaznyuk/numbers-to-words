package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.NumberStringBuilder;

import java.math.BigDecimal;
import java.util.Scanner;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Enter a positive integer: ");
        Scanner scanner = new Scanner(System.in);
        BigDecimal bigDecimal = scanner.nextBigDecimal();
        LOGGER.info(NumberStringBuilder.secondAlgorithmBuilder(bigDecimal));
    }
}