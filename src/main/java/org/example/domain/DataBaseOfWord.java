package org.example.domain;

import java.util.Arrays;

public class DataBaseOfWord {

    private String[] arrDataBaseOfWord;
    private String[] arrDataBaseOfPartWord;

    public String[] getArrDataBaseOfWord() {
        return arrDataBaseOfWord;
    }

    public String[] getArrDataBaseOfPartWord() {
        return arrDataBaseOfPartWord;
    }

    @Override
    public String toString() {
        return "DataBaseOfWord{" +
                "arrDataBaseOfWord=" + Arrays.toString(arrDataBaseOfWord) +
                ", arrDataBaseOfPartWord=" + Arrays.toString(arrDataBaseOfPartWord) +
                '}';
    }

}
