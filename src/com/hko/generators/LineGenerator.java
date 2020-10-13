package com.hko.generators;

import java.util.Random;
import java.util.concurrent.*;

public class LineGenerator implements Callable<String> {
    int columns;
    int string_length;
    String seperator;

    public LineGenerator(int columns, int string_length, String seperator) {
        this.columns = columns;
        this.string_length = string_length;
        this.seperator = seperator;
    }

    @Override
    public String call() throws ExecutionException, InterruptedException {
        String dummy =  "";
        ExecutorService executor = Executors.newFixedThreadPool(columns);
        for (int i = 0; i < columns; i++) {
            Future<String> task = executor.submit(new StringGenerator(string_length));
            dummy += task.get(); // this blocks until result is ready
//            dummy += givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
            if (i != columns - 1) {
                dummy += seperator;
            }
        }
        dummy = dummy + "\n";
        executor.shutdownNow();
        return dummy;

    }

    //algorithm is taken from https://www.baeldung.com/java-random-string
    public String givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
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
