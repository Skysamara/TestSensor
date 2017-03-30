package ru.panorobot.testsensor;


public class VRPano {
    private final int pans;
    private final int tilts;

    private int stepPan;
    private int stepTilt;


    private final int panMin = 0;     // Нижнее положение
    private VRPos poses[];

    public VRPano(int pans, int tilts) {
        this.pans = pans;
        this.tilts = tilts;
        createPoses();
    }

    private void createPoses(){
        stepPan = (int) (360*1/pans);
        stepTilt = (int) ((180*1 -panMin)/tilts);

        fillPoses();
    }

    private void fillPoses() {
        VRPos poses[] = new VRPos[pans * tilts];

        for (int t = 0; t < tilts; t++) {
            for (int p = 0; p < pans; p++) {
                VRPos pos = new VRPos(p*stepPan, t*stepTilt);
                poses[p + p * t] = pos;
            }
        }
    }


    @Override
    public String toString() {
//        return super.toString();

        String str;
        str = String.valueOf(pans) + "x" + String.valueOf(tilts) + "\n";
        str += "angle: p-" + String.valueOf(stepPan) + " t-" + String.valueOf(stepTilt) + "\n";

        for (int t = 0; t < tilts; t++) {
            for (int p = 0; p < pans; p++) {
                str += poses[p + p * t].toString() + "  ";
            }
            str += "\n";
        }


        return str;

    }
}
