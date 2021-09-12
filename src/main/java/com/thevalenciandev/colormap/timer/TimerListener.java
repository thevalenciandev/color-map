package com.thevalenciandev.colormap.timer;

public interface TimerListener {

    void onUpdate();
    void onRender();

    void onSecondElapsed(String info);
}
