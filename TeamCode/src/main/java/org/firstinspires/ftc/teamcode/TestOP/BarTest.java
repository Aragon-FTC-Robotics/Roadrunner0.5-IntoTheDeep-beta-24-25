package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BarTest {
    Gamepad gp;
    //Servo barLeft;
    Servo barRight;

    double bPos;
    public void init(HardwareMap hm) {
    //    barLeft = hm.get(Servo.class, "barLeft");
        barRight = hm.get(Servo.class, "barRight");
        bPos = 0.8;
    //    barLeft.setDirection(Servo.Direction.FORWARD);
        barRight.setDirection(Servo.Direction.REVERSE);
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp2.x) {
            bPos += 0.001;
        } else if (gp2.a) {
            bPos -= 0.001;
        }
        moveToPos(bPos);
    }

    private void moveToPos(double position) {
    //    barLeft.setPosition(bPos);
        barRight.setPosition(bPos);
    }

    public double getleftPos() {
        return bPos;
    }
    public double getrightPos() {
        return bPos;
    }
}