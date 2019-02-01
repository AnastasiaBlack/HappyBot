package com.happybot.quote;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quote {
    public String getQuote() {
        String quote = ":)";

        try (Stream<String> stream = Files.lines(Paths.get("E:\\learning\\my java\\HappyBot\\src\\main\\resources\\Quotes"))) {
            quote = stream.collect(Collectors.toList()).get((int) (Math.random() * 50));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quote;
    }
}
