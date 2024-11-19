package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWrist {
    Servo iWrist;
    iwristState  currentiWrist;
    enum iwristState {IN, OUT};

    public void init(HardwareMap hm) {
        iWrist = hm.get(Servo.class, "iWrist");
        currentiWrist = iwristState.IN;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.a){
            setCurrentiWrist(iwristState.IN);
        } else if (gp1.y) {
            setCurrentiWrist(iwristState.OUT);
        }

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

    public void setCurrentiWrist(iwristState state) {
        currentiWrist = state;
    }

    private void inPosition() {
        double openPos = 1;
        iWrist.setPosition(openPos);
    }

    private void outPosition() {
        double closePos = 1;
        iWrist.setPosition(closePos);
    }
}
