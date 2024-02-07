// Demonstrate several Stream operations. 

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;

public class StreamAPI {
    private static final int i = 10;
    private static final AtomicInteger test = new AtomicInteger(0); //Atomic Integer
    public static void main(String[] args) {
        final Stopwatch watch = new Stopwatch();

        //For each loop to iterate A, then iterate over B and C and find factors
        IntStream.rangeClosed(-i, i).parallel().forEach(a -> {
            for (int b = -i; b <= i; b++) {
                for (int c = -i; c <= i; c++) {
                    if (a != 0 && b != 0 && c != 0) {
                        int check = b * b - 4 * a * c;
                        if (check >= 0) {
                            int squareRoot = (int) Math.sqrt(check);
                            if (squareRoot * squareRoot == check) {
                                test.incrementAndGet(); //Increments counter
                            }
                        }
                    }
                }
            }
        });
        System.out.println("Stream API finished and value is " + test.get()); //Prints final value
        System.out.printf("Program took %f seconds\n", watch.elapsedTime());

    }

    public static void executeStreamAPI() {
        final Stopwatch watch = new Stopwatch();
        IntStream.rangeClosed(-i, i).parallel().forEach(a -> {
            for (int b = -i; b <= i; b++) {
                for (int c = -i; c <= i; c++) {
                    if (a != 0 && b != 0 && c != 0) {
                        int check = b * b - 4 * a * c;
                        if (check >= 0) {
                            int squareRoot = (int) Math.sqrt(check);
                            if (squareRoot * squareRoot == check) {
                                test.incrementAndGet();
                            }
                        }
                    }
                }
            }
        });
        System.out.println("Stream API finished and value is " + test.get());
        System.out.printf("Program took %f seconds\n", watch.elapsedTime());
    }
}