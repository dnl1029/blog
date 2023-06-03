package com.example.blog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonUtil {

    private JsonUtil() {
        throw new IllegalStateException("JsonUtil class has static method only");
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    // object -> String json 리턴
    public static String toJson(Object o){
        String json = null;
        try{
            json = mapper.writeValueAsString(o);
        }
        catch (JsonProcessingException e) {
            log.warn(e.getMessage());
        }
        return json;
    }

    // String json -> T object로 리턴
    public static <T> T fromJson(String json, Class<T> type) {
        if (json == null || type == null) {
            return null;
        }
        T result = null;
        try {
            result = mapper.readValue(json, type);
        }
        catch (IOException e) {
            log.warn(e.getMessage());
        }
        return result;
    }

    // String json -> T object로 리턴, param : TypeReference
    public static <T> T fromJson(String json, TypeReference<T> type) {
        if (json == null || type == null) {
            return null;
        }
        T result = null;
        try {
            result = mapper.readValue(json, type);
        }
        catch (IOException e) {
            log.warn(e.getMessage());
        }
        return result;
    }


}
