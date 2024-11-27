package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawTest {
    Servo clawTest;
    double cPos;

    public void init(HardwareMap hm) {
        clawTest = hm.get(Servo.class, "claw1");
        cPos = 0;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {

        if (gp2.dpad_up) {
            cPos += 0.001;
        } else if (gp2.dpad_down) {
            cPos -= 0.001;
        }
        clawTest.setPosition(cPos);

    }

    public double getcPos() {
        return cPos;
    }
    public double getClawgetPos() {return clawTest.getPosition();}
}