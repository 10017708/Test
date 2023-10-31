import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    //this is test
    private static final int NUMBER_OF_THREADS = 100;
    public static void main(String[] args) {
        Instant instantStart = Instant.now();
        AtomicInteger count = new AtomicInteger(0);
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for(int i = 0; i < NUMBER_OF_THREADS; i++){
            threads.add(new Thread(new Runnable(   ){

                @Override
                public void run() {
                    for(int j = 0; j < 1000000; j++)
                        count.incrementAndGet();
                }

            }));
            threads.get(i).run();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Instant instantEnd = Instant.now();
        long duration = instantEnd.toEpochMilli() - instantStart.toEpochMilli();

        System.out.println("Final count value: " + count.get());
        System.out.println("Duration: " + duration + " milliseconds");


    }

}