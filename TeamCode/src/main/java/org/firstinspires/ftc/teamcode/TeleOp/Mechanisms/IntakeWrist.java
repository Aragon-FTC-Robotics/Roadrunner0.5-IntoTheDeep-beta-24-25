package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWrist {
    Servo iWrist;
    public enum iwristState {IN, OUT};
    public iwristState currentiWrist = iwristState.IN;
    public final double INPOS = 0.134;
    public final double OUTPOS = 1.000;
    public void init(HardwareMap hm) {
        iWrist = hm.get(Servo.class, "iWrist");
        currentiWrist = iwristState.IN;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        switch (currentiWrist){
            case IN:
                inPosition();
                break;
            case OUT:
                outPosition();
                break;
            default:
                inPosition();
                break;
        }
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
}
