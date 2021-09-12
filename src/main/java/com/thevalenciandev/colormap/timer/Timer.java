package com.thevalenciandev.colormap.timer;

public class Timer {

    private static final double NS = 1000000000.0 / 60.0;

    private final TimerListener timerListener;

    private double delta = 0;
    private double updates = 0;
    private double frames = 0;

    private boolean running = true;

    public Timer(TimerListener timerListener) {
        this.timerListener = timerListener;
    }

    public void start() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / NS;
            lastTime = now;
            while (delta >= 1) {
                timerListener.onUpdate();
                updates++;
                delta--;
            }
            timerListener.onRender();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                timerListener.onSecondElapsed(updates + " ups, " + frames + " fps");
                updates = frames = 0;
            }
        }
    }

    public void stop() {
        running = false;
    }
}
