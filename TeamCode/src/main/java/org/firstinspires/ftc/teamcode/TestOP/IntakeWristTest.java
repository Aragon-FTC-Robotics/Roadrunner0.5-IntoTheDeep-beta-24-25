package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWristTest {
    Servo iWrist;
    iwristState  currentiWrist;
    enum iwristState {IN, OUT};
    double iwpos;

    public void init(HardwareMap hm) {
        iWrist = hm.get(Servo.class, "iWrist");
        iwpos = 0.32;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.dpad_left) {
            iwpos += 0.001;
        } else if (gp1.dpad_right) {
            iwpos -= 0.001;
        }
        iWrist.setPosition(iwpos);
    }

    public double getiwPos() {
        return iwpos;
    }
}
