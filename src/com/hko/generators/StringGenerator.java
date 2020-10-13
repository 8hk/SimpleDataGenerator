package com.hko.generators;

import java.util.Random;
import java.util.concurrent.Callable;

public class StringGenerator implements Callable<String> {

    int string_length;

    StringGenerator(int string_length) {
        this.string_length = string_length;
    }


    @Override
    public String call() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = this.string_length;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
