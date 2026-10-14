package com.example.praktinisdarbas2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCounter {

    private static final Pattern WORD = Pattern.compile("\\p{L}+(?:['’-]\\p{L}+)*");
    private static final Pattern DIGIT = Pattern.compile("\\d");
    private static final Pattern PUNCTUATION = Pattern.compile("\\p{P}");
    private static final Pattern SENTENCE_SPLIT = Pattern.compile("(?<=[.!?])\\s+");

    public int countSentences(String text) {
        int n = 0;
        for (String part : SENTENCE_SPLIT.split(text)) {
            if (!part.trim().isEmpty()) n++;
        }
        return n;
    }

    public int countWords(String text) {
        Matcher m = WORD.matcher(text);
        int n = 0;
        while (m.find()) n++;
        return n;
    }

    public int countNumbers(String text) {
        Matcher m = DIGIT.matcher(text);
        int n = 0;
        while (m.find()) n++;
        return n;
    }

    public int countPunctuation(String text) {
        Matcher m = PUNCTUATION.matcher(text);
        int n = 0;
        while (m.find()) n++;
        return n;
    }
}