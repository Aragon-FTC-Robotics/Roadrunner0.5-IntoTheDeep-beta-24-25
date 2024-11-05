package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawTest {
    Servo clawTest;
    int cPos;

    public void init(HardwareMap hm) {
        clawTest = hm.get(Servo.class, "claw");
        cPos = 0;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {

//  Bar Testing Values
        if (gp2.dpad_up) {
            cPos += 0.0001;
        } else if (gp2.dpad_down) {
            cPos -= 0.0001;
        }
        clawTest.setPosition(cPos);
    }

    public int getcPos() {
        return cPos;
    }
}