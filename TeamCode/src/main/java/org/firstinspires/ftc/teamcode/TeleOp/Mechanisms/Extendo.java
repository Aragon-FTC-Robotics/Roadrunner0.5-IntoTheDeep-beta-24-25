package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Extendo {
    private PIDController controller;
    public static double p=0.010, i=0, d=0.0001; //find values
    public static double f=0;
    private final double ticks_in_degree = 1 / 1;
    DcMotorEx extendoMotor;

    double pos;
    double speed = 5;
    double pid, power;
    final int max = 620; //580 mm extendo
    final int min = 0;
    public void init(HardwareMap hm) {
        controller = new PIDController(p,i,d);
        extendoMotor = hm.get(DcMotorEx.class, "extendo");

//        extendoMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        extendoMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendoMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
        controller.setPID(p,i,d);
        pid = controller.calculate(extendoMotor.getCurrentPosition(), intpos);
//        double ff = Math.cos(Math.toRadians(intpos / ticks_in_degree)) * f;
        double ff = f;
        power = pid + ff;
        extendoMotor.setTargetPosition(intpos);
        extendoMotor.setPower(power);
        //extendoMotor.setPower(0.8);
    }


    public double getPos() {
        return pos;
    }
}
