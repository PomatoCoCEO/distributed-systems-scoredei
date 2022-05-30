package com.scoresDei.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Dotenv {
    private final String DOTENV_LOCATION = "src/main/java/com/scoresDei/.env";
    private Map<String, String> map;

    public Dotenv() {
        map = new TreeMap<String, String>();
        try (
                BufferedReader br = new BufferedReader(
                        new FileReader(
                                new File(DOTENV_LOCATION)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] sp = line.split("=", 2);
                map.put(sp[0], sp[1]);
            }

        } catch (FileNotFoundException fnf) {
            System.out.println("Working directory: " + System.getProperty("user.dir"));
            fnf.printStackTrace();
        } catch (IOException io) {

            io.printStackTrace();
        }
    }

    public String get(String key) {
        String value = map.get(key);
        if (value == null) {
            throw new IllegalArgumentException("No value for key " + key);
        } else
            return value;
    }

}
