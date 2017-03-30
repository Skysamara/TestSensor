package ru.panorobot.testsensor;


public class VRPos {
    int pan;
    int tilt;

    public VRPos(int pan, int tilt) {
        this.pan = pan;
        this.tilt = tilt;
    }

    @Override
    public String toString() {
//        return super.toString();
        return String.valueOf(pan) + ":" + String.valueOf(tilt);
    }
}
