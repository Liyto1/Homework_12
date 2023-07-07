package SecondTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzzClass {
    private static final int number = 15;
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static final Object lock = new Object();
    private static int counter = 1;

    public static void main(String[] args) {

        Thread aThread = new Thread(() -> {
            while (counter <= number) {
                synchronized (lock) {
                    if (counter % 3 == 0 && counter % 5 != 0) {
                        try {
                            queue.put("fizz");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        counter++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread bThread = new Thread(() -> {
            while (counter <= number) {
                synchronized (lock) {
                    if (counter % 5 == 0 && counter % 3 != 0) {
                        try {
                            queue.put("buzz");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        counter++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread cThread = new Thread(() -> {
            while (counter <= number) {
                synchronized (lock) {
                    if (counter % 3 == 0 && counter % 5 == 0) {
                        try {
                            queue.put("fizzbuzz");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        counter++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread dThread = new Thread(() -> {
            while (counter <= number) {
                synchronized (lock) {
                    if (counter % 3 != 0 && counter % 5 != 0) {
                        try {
                            queue.put(Integer.toString(counter));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        counter++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        aThread.start();
        bThread.start();
        cThread.start();
        dThread.start();

        try {
            aThread.join();
            bThread.join();
            cThread.join();
            dThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}