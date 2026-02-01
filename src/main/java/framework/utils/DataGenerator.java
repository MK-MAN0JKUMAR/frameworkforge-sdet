package framework.utils;

import java.security.SecureRandom;

public final class DataGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String ALPHANUMERIC = ALPHABETS + NUMBERS;

    private DataGenerator() {}

    public static String alphabetic(int length) {
        return random(ALPHABETS, length);
    }

    public static String numeric(int length) {
        return random(NUMBERS, length);
    }

    public static String alphanumeric(int length) {
        return random(ALPHANUMERIC, length);
    }

    private static String random(String set, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(set.charAt(RANDOM.nextInt(set.length())));
        }
        return sb.toString();
    }
}
