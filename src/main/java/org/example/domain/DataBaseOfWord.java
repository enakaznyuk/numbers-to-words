package org.example.domain;

import java.util.Arrays;

public class DataBaseOfWord {

    private String[] arrDataBaseOfWord;

    public String[] getArrDataBaseOfWord() {
        return arrDataBaseOfWord;
    }

    @Override
    public String toString() {
        return "DataBaseOfWord{" +
                "arrDataBaseOfWord=" + Arrays.toString(arrDataBaseOfWord) +
                '}';
    }
}
