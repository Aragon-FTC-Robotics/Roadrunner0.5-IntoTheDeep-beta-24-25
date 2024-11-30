package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WristTest {
    Servo wristServo;
    double pos;

    public void init(HardwareMap hm) {
        wristServo = hm.get(Servo.class, "wrist");
        pos = 0.320;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp2.left_bumper) {
            pos += 0.001;
        } else if (gp2.right_bumper) {
            pos -= 0.001;
        }
        wristServo.setPosition(pos);
    }

    public double getwPos() {
        return pos;
    }
    public int portNumber(){return wristServo.getPortNumber();}
    public Object getDirection(){return wristServo.getDirection();}
}