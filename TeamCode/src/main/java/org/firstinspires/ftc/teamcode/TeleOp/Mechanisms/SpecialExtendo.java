package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SpecialExtendo {
    private PIDController controller;
    public static double p=0.010, i=0, d=0.0001; //find values
    public static double f=0;
    DcMotorEx extendoMotor;

    double pos;
    double speed = 15;
    double pid, power;
    public final int max = 600; //580 mm extendo
    public final int min = -200;
    public final int MED = 300;
    public final int mid = (int)Math.round((max+min)*0.5);
    boolean leftLastPressed = false;
    boolean rightLastPressed = false;
    int intpos = 0;
    public void init(HardwareMap hm) {
        controller = new PIDController(p,i,d);
        extendoMotor = hm.get(DcMotorEx.class, "extendo");

//        extendoMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        pos = min;
    }

    public void Loop() {
        extendoMotor.setPower(-0.1);
        //extendoMotor.setPower(0.8);
    }

    public double getPos() {
        return (int) Math.round(pos);
    }
    public double getExtendopos() {return extendoMotor.getCurrentPosition();}
    public void DANGEROUS_RESET_ENCODERS() {
        extendoMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendoMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pos = min;
    }
}
