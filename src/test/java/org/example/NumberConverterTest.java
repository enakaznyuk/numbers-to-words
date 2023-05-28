package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.NumberStringBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class NumberConverterTest {

    private static final Logger LOGGER = LogManager.getLogger(NumberConverterTest.class);

    private static HelperClassForData testDataFromFile;

    @BeforeAll
    public static void initTestData() throws IOException {
        File fileWithTestData = new File("./src/test/java/resources/Data.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        testDataFromFile = mapper.readValue(fileWithTestData, HelperClassForData.class);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForTest")
    void shouldConvertNumbersToStringUsingSecondAlgorithm(BigDecimal[] numbers, String[] expectedResults) {

        for (int i = 0; i < numbers.length; i++) {
            LOGGER.info(" separator = " + NumberStringBuilder.secondAlgorithmBuilder(numbers[i]));
            LOGGER.info(" Results = " + expectedResults[i]);
            Assertions.assertEquals(expectedResults[i], NumberStringBuilder.secondAlgorithmBuilder(numbers[i]).toString());
        }
    }

    @ParameterizedTest
    @CsvFileSource(
            files = "./src/test/java/resources/DataOfNumbersAndWordsForTest.csv",
            delimiterString = "|",
            lineSeparator = "||",
            numLinesToSkip = 1)
    void shouldConvertNumbersToStringUsingSecondAlgorithmValueCSVFile(BigDecimal numbers, String expectedResults) {
        LOGGER.info(" separator = " + NumberStringBuilder.secondAlgorithmBuilder(numbers));
        LOGGER.info(" Results = " + expectedResults);
        Assertions.assertEquals(expectedResults, NumberStringBuilder.secondAlgorithmBuilder(numbers).toString());
    }

    private static Stream<Arguments> provideArgumentsForTest() {
        return Stream.of(Arguments.of(testDataFromFile.getArrDataTest(), testDataFromFile.getArrExpectedResult()));
    }
}
