import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

// Some simple examples of using streams
public class SumStringsWithStreams
{

    public static void main(String[] args)
    {
        // Make a list of random strings of numbers to simulate some data
        final int size = 1000000;
        final int x = 10;
        final Random rand = new Random();
        final AtomicInteger test = new AtomicInteger(0); //Atomic Integer
        final ArrayList<String> data = new ArrayList<>(size);
        for (int a = -x; a <= x; a++) {
            for (int b = -x; b <= x; b++) {
                for (int c = -x; c <= x; c++) {
                    if (a != 0 && b != 0 && c != 0) {
                        int check = b * b - 4 * a * c;
                        if (check >= 0) {
                            int squareRoot = (int) Math.sqrt(check);
                            if (squareRoot * squareRoot == check) {
                                test.incrementAndGet();
                                data.add(String.valueOf(test.get()));
                            }
                        }
                    }
                }
            }
        }

        Stopwatch watch = new Stopwatch();
        int sum = 0;
        for(String item : data)
        {
            sum += Integer.valueOf(item);
        }
        System.out.printf("Sum = %d, Time = %f (For each loop)\n", sum, watch.elapsedTime());

        watch = new Stopwatch();
        sum = data.stream().map(item -> Integer.valueOf(item)).reduce(0, (a, b) -> a + b);
        System.out.printf("Sum = %d, Time = %f (Map reduce 1)\n", sum, watch.elapsedTime());

        watch = new Stopwatch();
        sum = data.stream().map(item -> Integer.valueOf(item)).reduce(0, Integer::sum);
        System.out.printf("Sum = %d, Time = %f (Map reduce 2)\n", sum, watch.elapsedTime());

        watch = new Stopwatch();
        sum = data.stream().mapToInt(item -> Integer.valueOf(item)).sum();
        System.out.printf("Sum = %d, Time = %f (Map reduce 3)\n", sum, watch.elapsedTime());

        watch = new Stopwatch();
        sum = data.parallelStream().map(item -> Integer.valueOf(item)).reduce(0, (a, b) -> a + b);
        System.out.printf("Sum = %d, Time = %f (Parallel Map reduce 1)\n", sum, watch.elapsedTime());

        watch = new Stopwatch();
        sum = data.parallelStream().map(item -> Integer.valueOf(item)).reduce(0, Integer::sum);
        System.out.printf("Sum = %d, Time = %f (Parallel Map reduce 2)\n", sum, watch.elapsedTime());

        watch = new Stopwatch();
        sum = data.parallelStream().mapToInt(item -> Integer.valueOf(item)).sum();
        System.out.printf("Sum = %d, Time = %f (parallel Map reduce 3)\n", sum, watch.elapsedTime());
    }

}