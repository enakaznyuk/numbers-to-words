package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.platform.commons.util.ToStringBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class MainTest {

    private static final Logger LOGGER = LogManager.getLogger(MainTest.class);

    static HelperClassForData prepareData() throws IOException {

        File jsonFile = new File("./src/test/java/resources/Data.json");
        JsonParser jsonParser = new JsonParser();
        HelperClassForData helperClassForData;

        helperClassForData = jsonParser.helperClassForDataParse(jsonFile);
        return helperClassForData;
    }

    @ParameterizedTest
    @CsvSource({ "1, один", "2, два", "3, три"})
    void Test(int number, String expectedResult){
        Assertions.assertEquals( BigDigitBuilder.numberSeparator(number).toString(), expectedResult);
    }

    @ParameterizedTest
    @MethodSource("StringArgs")
    void TestFirstAlgorithm(Integer[] numbers, String[] expectedResults){

        for(int i = 0; i < numbers.length; i++){
            LOGGER.info(i);
            LOGGER.info(" separator = " + BigDigitBuilder.numberSeparator(numbers[i]));
            LOGGER.info(" Results = " + expectedResults[i]);
            Assertions.assertEquals(BigDigitBuilder.numberSeparator(numbers[i]).toString(), expectedResults[i]);
        }

    }

    @ParameterizedTest
    @MethodSource("StringArgs")
    void TestSecondAlgorithm(Integer[] numbers, String[] expectedResults){

        for(int i = 0; i < numbers.length; i++){
            LOGGER.info(i);
            LOGGER.info(" separator = " + BigDigitBuilder.newBuild(numbers[i]));
            LOGGER.info(" Results = " + expectedResults[i]);
            Assertions.assertEquals(BigDigitBuilder.newBuild(numbers[i]).toString(), expectedResults[i]);
        }
    }

    static Stream<Arguments> StringArgs() throws IOException {
        return Stream.of(Arguments.of(prepareData().getArrDataTest(), prepareData().getArrExpectedResult()));
    }
}
