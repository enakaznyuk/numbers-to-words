package org.example;

import java.util.Arrays;

public class HelperClassForData {

    private Integer[] arrDataTest;
    private String[] arrExpectedResult;

    public Integer[] getArrDataTest() {
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
