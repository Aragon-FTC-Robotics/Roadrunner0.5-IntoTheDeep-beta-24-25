package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Extendo {

    DcMotorEx extendoMotor;

    double pos;
    double speed = 5;
    final int max = 1500; //580 mm extendo
    final int min = 0;
    public void init(HardwareMap hm) {
        extendoMotor = hm.get(DcMotorEx.class, "extendo");

//        extendoMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        extendoMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pos = 0;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
//        pos += (speed*gp2.right_stick_y);

//        if(gp2.touchpad_finger_1){pos = (gp2.touchpad_finger_1_x*((max+min)/2)+((max+min/2)));}

        if (gp1.left_bumper){
            pos = min;
        } else if (gp1.right_bumper) {
            pos = max;
        }

        pos += (speed*(gp1.left_trigger - gp1.right_trigger));
        if (pos>max) {
            pos=max;
        }
        if (pos<min) {
            pos=min;
        }
        int intpos = (int) Math.round(pos);
        extendoMotor.setTargetPosition(intpos);
    }


    public double getPos() {
        return pos;
    }
}
