package lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class UnLockTest {
    public static final Semaphore a1 = new Semaphore(1);
    public static final Semaphore a2 = new Semaphore(1);

    public static void main(String[] args) {
        LockA la = new LockA();
        new Thread(la).start();

        LockB lb = new LockB();
        new Thread(lb).start();

    }
}

class LockA implements Runnable {
    public void run() {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(sf.format(new Date()) + " LockA 开始执行...");
            boolean flag = false;
            while (true) {
                if (UnLockTest.a1.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println(sf.format(new Date()) + " LockA 锁住 obj1");
                    if (UnLockTest.a2.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(sf.format(new Date()) + " LockA 锁住 obj2");
                        Thread.sleep(2000); // do something
                        flag = true;
                    } else {
                        System.out.println(sf.format(new Date()) + "LockA 锁 obj2 失败");
                    }
                } else {
                    System.out.println(sf.format(new Date()) + "LockA 锁 obj1 失败");
                }

                UnLockTest.a1.release(); // 释放
                UnLockTest.a2.release(); // 释放

                if (flag) {
                    System.out.println("LockA 结束....");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class LockB implements Runnable {
    public void run() {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(sf.format(new Date()) + " LockB 开始执行...");
            boolean flag = false;
            while (true) {
                if (UnLockTest.a2.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println(sf.format(new Date()) + " LockB 锁住 obj2");
                    if (UnLockTest.a1.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(sf.format(new Date()) + " LockB 锁住 obj1");
                        Thread.sleep(1000); // do something
                        flag = true;
                    } else {
                        System.out.println(sf.format(new Date()) + "LockB 锁 obj1 失败");
                    }
                } else {
                    System.out.println(sf.format(new Date()) + "LockB 锁 obj2 失败");
                }

                UnLockTest.a1.release(); // 释放
                UnLockTest.a2.release(); // 释放

                if (flag) {
                    System.out.println("LockB 结束....");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}