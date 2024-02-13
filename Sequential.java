import java.util.concurrent.atomic.AtomicInteger;

public class Sequential {
    private static final AtomicInteger test = new AtomicInteger(0); //Atomic Integer
    public static void main(String[] args) {
        int i = 10;
        final Stopwatch watch = new Stopwatch();
        int count = 0;
        for (int a = -i; a <= i; a++) {
            for (int b = -i; b <= i; b++) {
                for (int c = -i; c <= i; c++) {
                    if (a != 0 && b != 0 && c != 0) {
                        int check = b * b - 4 * a * c;
                        if (check >= 0) {
                            int squareRoot = (int) Math.sqrt(check);
                            if (squareRoot * squareRoot == check) {
                                test.incrementAndGet();
                                //count++;
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
}