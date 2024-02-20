// Demonstrate several Stream operations. 

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntPredicate;
import java.util.stream.*;

public class StreamAPI {
    private static final int i = 1000;
    private static final AtomicInteger test = new AtomicInteger(0); //Atomic Integer

    public static void main(String[] args) {
        //test.set(0);
        //sequentialStream();

        //test.set(0);
        //parallelStreamAPI();

        test.set(0);
        optimizedparallelStreamAPI();

    }

    public static void Check(int a, int b, int c) {
        int check = b * b - 4 * a * c;
        if (check >= 0) {
            int squareRoot = (int) Math.sqrt(check);
            if (squareRoot * squareRoot == check) {
                test.incrementAndGet();
            }
        }
    }
    /*
    public static IntPredicate Check2(int a, int b, int c) {
        int check = b * b - 4 * a * c;
        if (check >= 0) {
            int squareRoot = (int) Math.sqrt(check);
            if (squareRoot * squareRoot == check) {
                test.incrementAndGet();
            }
        }
    }
    */

    public static void sequentialStream() {
        final Stopwatch watch = new Stopwatch();
        for (int a = -i; a <= i; a++) {
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
        }
        System.out.println("Sequential finished and value is " + test.get());
        System.out.printf("Program took %f seconds\n", watch.elapsedTime());
        System.out.println("");
    }

    public static void parallelStreamAPI() {
        final Stopwatch watch = new Stopwatch();
        //Stream<Integer> stream = .stream();
        IntStream.rangeClosed(-i, i).parallel().forEach(a -> {
            IntStream.rangeClosed(-i, i).parallel().forEach(b -> {
                IntStream.rangeClosed(-i, i).parallel().forEach(c -> {
                    if (a != 0 && b != 0 && c != 0) {
                        Check(a, b, c);
                    }
                });
            });
        });
        System.out.println("parallelStreamAPI finished and value is " + test.get());
        System.out.printf("Program took %f seconds\n", watch.elapsedTime());
        System.out.println("");
    }

    public static void optimizedparallelStreamAPI() {
        final Stopwatch watch = new Stopwatch();
        //check
        IntStream.rangeClosed(-i, i).parallel().forEach(a -> {
            for (int b = -i; b <= i; b++) {
                for (int c = -i; c <= i; c++) {
                    if (a != 0 && b != 0 && c != 0) {
                        Check(a, b, c);
                    }
                }
            }
        });
        System.out.println("parallelStreamAPI finished and value is " + test.get());
        System.out.printf("Program took %f seconds\n", watch.elapsedTime());
        System.out.println("");
    }
    /*
    public static void optimized2parallelStreamAPI() {
        final Stopwatch watch = new Stopwatch();
        IntStream.rangeClosed(-i, i).parallel().flatMap(a -> IntStream.rangeClosed(-i, i)
                .flatMap(b -> IntStream.range(-i, i)
                        .flatMap(c -> IntStream.rangeClosed(-i, i).filter(Check(a,b,c))))); {








                }
            }
        });
        System.out.println("parallelStreamAPI finished and value is " + test.get());
        System.out.printf("Program took %f seconds\n", watch.elapsedTime());
        System.out.println("");
        */
}


