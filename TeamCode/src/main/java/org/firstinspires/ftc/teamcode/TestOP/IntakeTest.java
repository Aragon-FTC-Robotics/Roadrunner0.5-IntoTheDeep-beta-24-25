package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeTest {
    Servo intakeServo;

    double iPos;

    public void init(HardwareMap hm) {
        intakeServo = hm.get(Servo.class, "intake");
        iPos = 0.32;
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.dpad_left) {
            iPos += 0.001;
        } else if (gp1.dpad_right) {
            iPos -= 0.001;
        }

        intakeServo.setPosition(iPos);
    }

    public double getiPos() {
        return iPos;
    }
}