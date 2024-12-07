package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWrist {
    Servo iWrist;
    public enum iwristState {IN, OUT, SUPEROUT};
    public iwristState currentiWrist = iwristState.IN;
    public final double INPOS = 0.00;
    public final double OUTPOS = 0.840;
    public final double SUPEROUTPOS = 0.710;
    public void init(HardwareMap hm) {
        iWrist = hm.get(Servo.class, "iWrist");
        currentiWrist = iwristState.SUPEROUT;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        switch (currentiWrist){
            case IN:
                inPosition();
                break;
            case OUT:
                outPosition();
                break;
            case SUPEROUT:
                superOutPosition();
                break;
            default:
                inPosition();
                break;
        }
    }
    public void setPos(double pos) {
        iWrist.setPosition(pos);
    }
    public void setState(iwristState state) {
        currentiWrist = state;
    }

    private void inPosition() {
        double openPos = INPOS;
        iWrist.setPosition(openPos);
    }

    private void outPosition() {
        double closePos = OUTPOS;
        iWrist.setPosition(closePos);
    }
    private void superOutPosition() {
        iWrist.setPosition(SUPEROUTPOS);
    }
}
