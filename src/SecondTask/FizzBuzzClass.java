package SecondTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzzClass {
    private static final int number = 15;
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread aThread = new Thread(() -> {
            for (int i = 1; i <= number; i++) {
                synchronized (lock) {
                    if (i % 3 == 0) {
                        try {
                            queue.put("fizz");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            queue.put(Integer.toString(i));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread bThread = new Thread(() -> {
            for (int i = 1; i <= number; i++) {
                synchronized (lock) {
                    if (i % 5 == 0) {

                        try {
                            queue.put("buzz");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread cThread = new Thread(() -> {
            for (int i = 1; i <= number; i++) {
                synchronized (lock) {
                    if (i % 3 == 0 && i % 5 == 0) {

                        try {
                            queue.put("fizzbuzz");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread dThread = new Thread(() -> {
            for (int i = 1; i <= number; i++) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        aThread.start();
        bThread.start();
        cThread.start();
        dThread.start();
    }
}