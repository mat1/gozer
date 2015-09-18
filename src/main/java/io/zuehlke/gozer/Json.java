package io.zuehlke.gozer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;

public class Json {

    private static final Logger log = Logger.getLogger(App.class);

    private final static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e);
            return "";
        }
    }

    public static <T> T parseJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error(e);
            return null;
        }
    }

    public static <T> T readInputStream(InputStream inputStream, Class<T> clazz) {
        try {
            return mapper.readValue(inputStream, clazz);
        } catch (IOException e) {
            log.error(e);
            return null;
        }
    }

}
