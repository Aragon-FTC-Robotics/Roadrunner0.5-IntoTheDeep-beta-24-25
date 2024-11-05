package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Extendo {

    DcMotorEx extendoMotor;

    int pos;
    double speed = 5;
    final int max = 1500;
    final int min = 0;
    public void init(HardwareMap hm) {
        extendoMotor = hm.get(DcMotorEx.class, "extendo");

//        extendoMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        extendoMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pos = 0;
    }

    public void loop(Gamepad gp1, Gamepad gp2) {
//        pos += (speed*gp2.right_stick_y);
//        if(gp2.touchpad_finger_1){pos += (speed*gp2.touchpad_finger_1_x);}
        pos += (speed*(gp2.left_trigger - gp2.right_trigger));
        if (pos>max) {
            pos=max;
        }
        if (pos<min) {
            pos=min;
        }
        extendoMotor.setTargetPosition(pos);
    }

    public int getPos() {
        return pos;
    }
}
