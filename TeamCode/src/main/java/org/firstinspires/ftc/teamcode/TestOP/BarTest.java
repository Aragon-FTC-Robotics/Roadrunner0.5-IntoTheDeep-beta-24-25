package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BarTest {
    Gamepad gp;
    Servo barLeft;
    Servo barRight;

    int bPos;
    public void init(HardwareMap hm) {
        barLeft = hm.get(Servo.class, "barLeft");
        barRight = hm.get(Servo.class, "barRight");
        bPos = 0;
        barRight.setDirection(Servo.Direction.REVERSE);
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp2.x) {
            bPos += 10;
        } else if (gp2.a) {
            bPos -= 10;
        }
        moveToPos(bPos);
    }

    private void moveToPos(int position) {
        barLeft.setPosition(bPos);
        barRight.setPosition(bPos);
    }

    public int getleftPos() {
        return bPos;
    }
    public int getrightPos() {
        return bPos;
    }
}