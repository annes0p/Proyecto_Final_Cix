package com.example.cixoil.utils;

import java.text.Normalizer;

public final class TextUtil {

    private TextUtil() {}

    public static String normalize(String text) {

        if (text == null) return null;

        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .trim();
    }
}
