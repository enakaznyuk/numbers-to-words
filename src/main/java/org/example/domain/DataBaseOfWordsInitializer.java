package org.example.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class DataBaseOfWordsInitializer {

    public static DataBaseOfWord getDataBaseOfWord() {
        try {
            File fileWithWordData = new File("./src/main/resources/DataOfWords.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(fileWithWordData, DataBaseOfWord.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

