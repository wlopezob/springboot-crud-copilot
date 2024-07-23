package com.wlopezob.personav1.Util;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class  Util {
    public static String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(object);
        return jsonString;
    }


    public static <T> T convertJsonToObject(String jsonFileName, Class<T> clazz) {
        Gson gson = new Gson();
        T object = null;
        try {
            Resource resource = new ClassPathResource(jsonFileName);
            try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                object = gson.fromJson(reader, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
