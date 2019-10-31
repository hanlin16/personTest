package lock;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUPTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        while (true) {
            new Thread(() -> {
                int num = atomicInteger.incrementAndGet();
                System.out.println(num);

                try {
                    Thread.sleep(60*60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}
