package org.example;

import java.util.Arrays;

public class HelperClassForData {

    Integer[] arrDataTest;
    String[] arrExpectedResult;

    public Integer[] getArrDataTest() {
        return arrDataTest;
    }

    public void setArrDataTest(Integer[] arrDataTest) {
        this.arrDataTest = arrDataTest;
    }

    public String[] getArrExpectedResult() {
        return arrExpectedResult;
    }

    public void setArrExpectedResult(String[] arrExpectedResult) {
        this.arrExpectedResult = arrExpectedResult;
    }

    @Override
    public String toString() {
        return "HelperClassForData{" +
                "arrDataTest=" + Arrays.toString(arrDataTest) +
                ", arrExpectedResult=" + Arrays.toString(arrExpectedResult) +
                '}';
    }
}
