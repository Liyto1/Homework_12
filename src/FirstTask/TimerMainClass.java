package FirstTask;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimerMainClass {
    public static void main(String[] args) {
        Instant start = Instant.now();
        AtomicBoolean flag = new AtomicBoolean(false);

        Thread firstTimerThread = new Thread(() -> {
            while (true) {
                Instant current = Instant.now();
                Duration passed = Duration.between(start, current);
                if (Math.toIntExact(passed.toSeconds()) % 5 != 0) System.out.println("Passed time: " + passed.getSeconds() + " seconds");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread secondTimerThread = new Thread(() -> {
            while (true) {
                if (flag.get() == true) System.out.println("5 seconds have passed");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag.set(true);
            }
        });

        firstTimerThread.start();
        secondTimerThread.start();
    }
}
