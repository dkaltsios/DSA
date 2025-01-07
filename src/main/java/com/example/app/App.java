package com.example.app;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
    public static void main(String[] args) {
        // Load environment variables
        Dotenv dotenv = Dotenv.load();
        String key = dotenv.get("API_KEY");

        if (key == null) {
            System.out.println("One or more environment variables were not found");
            return;
        }
        System.out.println(key);

        // Hashmap
        HashMap<String, String> map = new HashMap<>();
        map.put("test", "test");
        System.out.println(map.toString());
    }
}