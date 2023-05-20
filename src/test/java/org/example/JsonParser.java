package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class JsonParser {

    public HelperClassForData helperClassForDataParse(File file) throws IOException {

        HelperClassForData helperClassForData;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        helperClassForData = mapper.readValue(file, HelperClassForData.class);
        return helperClassForData;
    }
}
