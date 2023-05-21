package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.BigDigitBuilderFirstAlgorithm;
import org.example.domain.BigDigitBuilderSecondAlgorithm;
import org.example.domain.exception.InvalidNumberException;
import org.example.service.NumberStringBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.File;
import java.io.IOException;
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
    @ValueSource(ints = {-1, 0})
    void shouldHandleAnErrorLessThanZero(int numberToConvert) {
        Assertions.assertThrows(InvalidNumberException.class, () -> NumberStringBuilder.firstAlgorithm(numberToConvert));
        Assertions.assertThrows(InvalidNumberException.class, () -> NumberStringBuilder.secondAlgorithm(numberToConvert));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForTest")
    void shouldConvertNumbersToStringUsingFirstAlgorithm(Integer[] numbers, String[] expectedResults) {

        for (int i = 0; i < numbers.length; i++) {
            LOGGER.info(i);
            LOGGER.info(" separator = " + NumberStringBuilder.firstAlgorithm(numbers[i]));
            LOGGER.info(" Results = " + expectedResults[i]);
            Assertions.assertEquals(NumberStringBuilder.firstAlgorithm(numbers[i]).toString(), expectedResults[i]);
        }
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForTest")
    void shouldConvertNumbersToStringUsingSecondAlgorithm(Integer[] numbers, String[] expectedResults) throws IOException {

        for (int i = 0; i < numbers.length; i++) {
            LOGGER.info(i);
            LOGGER.info(" separator = " + NumberStringBuilder.secondAlgorithm(numbers[i]));
            LOGGER.info(" Results = " + expectedResults[i]);
            Assertions.assertEquals(NumberStringBuilder.secondAlgorithm(numbers[i]).toString(), expectedResults[i]);
        }
    }

    private static Stream<Arguments> provideArgumentsForTest() {
        return Stream.of(Arguments.of(testDataFromFile.getArrDataTest(), testDataFromFile.getArrExpectedResult()));
    }
}
