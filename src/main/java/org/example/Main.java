package org.example;

import com.ibm.icu.text.RuleBasedNumberFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.NumberStringBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        LOGGER.info("Enter a positive integer: ");
        Scanner scanner = new Scanner(System.in);
        //long pages = scanner.nextLong();
        BigDecimal bigDecimal = scanner.nextBigDecimal();
        //BigDecimal bigDecimal1 = bigDecimal.remainder(BigDecimal.ONE);
        /*RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);
        LOGGER.info(nf.format(bigDecimal));
        try {
            System.out.println("try block");
            LOGGER.info(NumberStringBuilder.secondAlgorithm(bigDecimal.toBigIntegerExact()));
        } catch (ArithmeticException e){
            System.out.println("catch block");
            LOGGER.info(NumberStringBuilder.secondAlgorithm(bigDecimal.toBigInteger()));
            bigDecimal1 = bigDecimal.remainder(BigDecimal.ONE);
            bigDecimal = bigDecimal1.movePointRight(bigDecimal1.precision());
            LOGGER.info(NumberStringBuilder.secondAlgorithm(bigDecimal.toBigInteger()));
        }*/

        /*while (bigDecimal.compareTo(BigDecimal.valueOf(0)) != 0){
            bigDecimal = scanner.nextBigDecimal();
            RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);
            LOGGER.info(nf.format(bigDecimal));
        }*/

        /*RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);
        LOGGER.info(nf.format(bigDecimal));*/

        /*BigInteger bigInteger = scanner.nextBigInteger();
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);
        LOGGER.info(nf.format(bigInteger));*/
        while (bigDecimal.compareTo(BigDecimal.valueOf(0)) != 0){
            bigDecimal = scanner.nextBigDecimal();
            RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);
            LOGGER.info(nf.format(bigDecimal));
            LOGGER.info(NumberStringBuilder.secondAlgorithm(bigDecimal));
        }
        //LOGGER.info(NumberStringBuilder.secondAlgorithm(bigDecimal));
        //LOGGER.info(NumberStringBuilder.firstAlgorithm((int) pages));

        /*
        1. Алгоритм ограничен типом int (MAX_INT = 2'147'483'647) и даже 10^9, нельзя передать ноль и отрицательное число, хотя в условиях этих ограничений нет. Первое условие ТЗ не соблюдено.
        2. В тестах в assertEquals спутаны параметры expected и actual result.
        3. Формат файла с тестовыми данными неудобен: я должен в одном поле вставить число, в другом поле найти ту же позицию и вставить ожидаемый результат. Переделать в табличный вид или хотя бы в массив ключ-значение.
        4. В коде оставлен мусор в виде тестирования результатов работы библиотеки icu4j.
        5. Неверное именование переменных в методах initDataBaseWord(), separatorWord() (класса BigDigitBuilderFirstAlgorithm).
        6. Спагетти-код в методе separationAlgorithmSecond(). Очень сложно читаемый код.
        7. Из-за искусственного ограничения в 1 миллиард - скудный словарь.
        */
    }
}