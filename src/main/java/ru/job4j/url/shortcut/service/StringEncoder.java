package ru.job4j.url.shortcut.service;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class StringEncoder {
    public static String encode(String str) {
        return Hashing.murmur3_32_fixed()
                .hashString(str, StandardCharsets.UTF_8)
                .toString();
    }
}
