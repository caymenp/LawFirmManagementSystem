package com.example.utilities;

import java.time.LocalTime;

public class Timer {

    public boolean running = false;
    private int time = 0;
    private LocalTime startTime = LocalTime.now();
    public void start() throws InterruptedException {
        LocalTime newTime = LocalTime.now();
        running = true;
        displayTimer(newTime);

    }

    public void displayTimer(LocalTime currentTime) throws InterruptedException {
        while (running) {
            time += currentTime.compareTo(startTime);
            wait(5000);
            System.out.println(time);
            start();
        }
    }
}
