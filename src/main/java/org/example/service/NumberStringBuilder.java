package org.example.service;

import org.example.domain.BigDigitBuilderFirstAlgorithm;
import org.example.domain.BigDigitBuilderSecondAlgorithm;

import java.io.IOException;

public class NumberStringBuilder {

    public static StringBuilder firstAlgorithm(int number){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BigDigitBuilderFirstAlgorithm.separationAlgorithmFirst(number));
        return stringBuilder;
    }

    public static StringBuilder secondAlgorithm(long number) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BigDigitBuilderSecondAlgorithm.separationAlgorithmSecond(number));
        return stringBuilder;
    }
}
