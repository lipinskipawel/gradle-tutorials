package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Library {
    public boolean someLibraryMethod() {
        Some.hello();
        return true;
    }

    public int zeroOrOne() {
        return ThreadLocalRandom.current().nextInt(0, 2);
    }

    public String format(String text) {
        return STR."custom text: \{text}";
    }
}
