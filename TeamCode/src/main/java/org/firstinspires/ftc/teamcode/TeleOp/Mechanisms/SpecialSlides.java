package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SpecialSlides {
    private PIDController controller;
    public static double p=0.0055, i=0, d=0.0001;
    public static double f=0;
    private final double ticks_in_degree = 1 / 1;
    DcMotorEx slideLeft;
    DcMotorEx slideRight;

    public final int groundPos = -30;
    public final int lowPos = 200;
    public final int medPos = 1400;
    public final int highPos = 2600;
    public int target = -30;
    double armPos, power, pid;
    public enum slideState {GROUND, CLIP, MED, HIGH}

    slideState currentSlideState = slideState.GROUND;

    public void init(HardwareMap hm) {
        controller = new PIDController(p,i,d);
        slideLeft = hm.get(DcMotorEx.class, "slideLeft");
        slideRight = hm.get(DcMotorEx.class, "slideRight");

        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);

        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setState(slideState.GROUND);
    }

    public void loop() {
        power = 0.85;
        slidePosition(power, target);
    }
    public void setState(slideState state) {
        currentSlideState = state;
    }
    public void setPos(int pos) {
        slidePosition(0.85, pos);
    }
    public void slidePosition(double power, int pos) {
        slideLeft.setTargetPosition(pos);
        slideRight.setTargetPosition(pos);
        target = pos;

        slideLeft.setPower(power);
        slideRight.setPower(power);
        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public int getLPos(){return slideLeft.getCurrentPosition();}
    public int getRPos(){return slideRight.getCurrentPosition();}
}
