package org.example;

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
    void TestTwo(Integer[] numbers, String[] expectedResults){

        for(int i = 0; i < numbers.length; i++){
            System.out.println(i);
            System.out.println(" separator = " + BigDigitBuilder.numberSeparator(numbers[i]));
            System.out.println(" Results = " + expectedResults[i]);
            Assertions.assertEquals(BigDigitBuilder.numberSeparator(numbers[i]).toString(), expectedResults[i]);
        }

    }

    static Stream<Arguments> StringArgs() throws IOException {
        return Stream.of(Arguments.of(prepareData().getArrDataTest(), prepareData().getArrExpectedResult()));
    }


}
