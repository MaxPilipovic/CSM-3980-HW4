import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {

    private static final int i = 10; //Range
    //private static final int numberOfJobs = 26;
    private static final int sizeOfPool = 12; //Number of threads in pool
    private static CountDownLatch latch;
    private static AtomicInteger test = new AtomicInteger(0); //Atomic used for counter

    public static void executeThreadPool() {
        final Stopwatch watch = new Stopwatch();
        // Build a pool of threads
        latch = new CountDownLatch(sizeOfPool);
        int tasks = 2 * i + 1; //Add 1 for 0
        int taskEachThread = (tasks/sizeOfPool); //Tasks each thread should handle
        ExecutorService service = Executors.newFixedThreadPool(sizeOfPool);
        //latch = new CountDownLatch(tasks);

        //int test = 2 * tasks;

        for (int j = 0; j < sizeOfPool; j++) {
            int start = -i + j * taskEachThread; //Start for current thread (range)
            int end = start + taskEachThread - 1; //End for current thread
            if (j == sizeOfPool - 1) { //Adjust end point for last thread
                end = i;
            }
            service.execute(new WorkerThread(start, end));
        }

        // Wait for all of the jobs to finish
        try {
            latch.await();
        } catch (InterruptedException ex) {
            System.err.println("Wait on latch Interrupted.");
            System.exit(1);
        }

        // Stop ExecutorService, if we do not do this then our program will still be
        // running even after main ends.
        service.shutdown();
        System.out.println("Thread Pool finished and value is " + test.get());
        System.out.printf("Program took %f seconds\n", watch.elapsedTime());
    }

    private static class WorkerThread extends Thread {

        private final int start;
        private final int end;

        public WorkerThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int count = 0;
            for (int a = start; a <= end; a++) {
                for (int b = -i; b <= i; b++) {
                    for (int c = -i; c <= i; c++) {
                        if (a != 0 && b != 0 && c != 0) {
                            int check = b*b -4*a*c;
                            if (check >= 0) {
                                int squareRoot = (int) Math.sqrt(check);
                                if (squareRoot * squareRoot == check) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
            test.addAndGet(count); //Count++ did not work, was not counting properly so used atomicinteger
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        final Stopwatch watch = new Stopwatch();
        // Build a pool of threads
        latch = new CountDownLatch(sizeOfPool);
        int tasks = 2 * i + 1; //Add 1 for 0
        int taskEachThread = (tasks/sizeOfPool); //Tasks each thread should handle
        ExecutorService service = Executors.newFixedThreadPool(sizeOfPool);
        //latch = new CountDownLatch(tasks);

        //int test = 2 * tasks;

        for (int j = 0; j < sizeOfPool; j++) {
            int start = -i + j * taskEachThread; //Start for current thread (range)
            int end = start + taskEachThread - 1; //End for current thread
            if (j == sizeOfPool - 1) { //Adjust end point for last thread
                end = i;
            }
            service.execute(new WorkerThread(start, end));
        }

        // Wait for all of the jobs to finish
        try {
            latch.await();
        } catch (InterruptedException ex) {
            System.err.println("Wait on latch Interrupted.");
            System.exit(1);
        }

        // Stop ExecutorService, if we do not do this then our program will still be
        // running even after main ends.
        service.shutdown();
        System.out.println("Thread Pool finished and value is " + test.get());
        System.out.printf("Program took %f seconds\n", watch.elapsedTime());
    }
}