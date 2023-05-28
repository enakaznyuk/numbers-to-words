package org.example;

import java.math.BigDecimal;
import java.util.Arrays;

public class HelperClassForData {

    private BigDecimal[] arrDataTest;
    private String[] arrExpectedResult;

    public BigDecimal[] getArrDataTest() {
        return arrDataTest;
    }

    public String[] getArrExpectedResult() {
        return arrExpectedResult;
    }

    @Override
    public String toString() {
        return "HelperClassForData{" +
                "arrDataTest=" + Arrays.toString(arrDataTest) +
                ", arrExpectedResult=" + Arrays.toString(arrExpectedResult) +
                '}';
    }
}
