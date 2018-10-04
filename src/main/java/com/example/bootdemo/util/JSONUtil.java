package com.example.bootdemo.util;

import com.example.bootdemo.exceptions.UnrecoverableException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JSONUtil {

    private static final Logger LOGGER = LogManager.getLogger(JSONUtil.class);

    private JSONUtil() {
    }

    public static <T> List<T> loadJsonArray(final JsonNode jsonNode, final Class<T> type) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readerFor(mapper.getTypeFactory().constructCollectionType(List.class, type)).readValue(jsonNode);
        } catch (IOException e) {
            throw new UnrecoverableException (e);
        }
    }

    public static <T> T loadJson(final JsonNode jsonNode, final Class<T> clazz) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readerFor(clazz).readValue(jsonNode);
        } catch (IOException e) {
            throw new UnrecoverableException(e);
        }
    }

    public static <T> T loadJson(final String json, final Class<T> clazz, final T obj) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readerForUpdating(obj).forType(clazz).readValue(json);
        } catch (Exception e) {
            throw new UnrecoverableException(e);
        }
    }

    public static <T> T loadJson(final String json, final Class<T> clazz) {
           final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
           try {
            return mapper.readerFor(clazz).readValue(json);
        } catch (Exception e) {
            throw new UnrecoverableException(e);
        }
    }

    public static Map<String, Object> loadJson(final String json) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readValue(json, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            throw new UnrecoverableException(e);
        }
    }

    public static JsonNode getJsonNode(final String json) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readTree(json);
        } catch (Exception e) {
            throw new UnrecoverableException(e);
        }
    }

    public static String toJson(final Object o) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            throw new UnrecoverableException(e);
        }
    }
}
