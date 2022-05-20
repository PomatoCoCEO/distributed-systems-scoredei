package com.scoresDei.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

public class Dotenv {
    private static String dotenv_location;
    private static Map<String, String> map;

    public Dotenv(String filename) {
        map = new TreeMap<String, String>();
        try (
                BufferedReader br = new BufferedReader(
                        new FileReader(
                                new File(filename)))) {
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
