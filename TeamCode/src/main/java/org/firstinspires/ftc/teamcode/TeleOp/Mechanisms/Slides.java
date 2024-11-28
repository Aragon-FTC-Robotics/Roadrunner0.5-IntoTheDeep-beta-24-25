package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    private PIDController controller;
    public static double p=0.0055, i=0, d=0.0001;
    public static double f=0;
    private final double ticks_in_degree = 1 / 1;
    DcMotorEx slideLeft;
    DcMotorEx slideRight;

    int groundPos = 0;
    int lowPos = 200;
    int medPos = 930;
    int highPos = 2600;
    public int target = 0;
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
        slideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        setState(slideState.GROUND);
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        controller.setPID(p,i,d);
        pid = controller.calculate(slideLeft.getCurrentPosition(), target);
//        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;
        double ff = 0;
        power = pid + ff;
        if(gp2.dpad_down) {
            setState(slideState.GROUND);
        } else if(gp2.dpad_right) {
            setState(slideState.CLIP);
        } else if(gp2.dpad_up) {
            setState(slideState.HIGH);
        } else if(gp2.dpad_left) {
            setState(slideState.MED);
        }

        switch(currentSlideState) {
            case GROUND:
                slidePosition(power, groundPos);
                break;
            case CLIP:
                slidePosition(power, lowPos);
                break;
            case MED:
                slidePosition(power, medPos);
                break;
            case HIGH:
                slidePosition(power, highPos);
                break;
            default:
                slidePosition(power, groundPos);
                break;
        }
    }

    public void setState(slideState state) {
        currentSlideState = state;
    }

    public void slidePosition(double power, int pos) {
        slideLeft.setTargetPosition(pos);
        slideRight.setTargetPosition(pos);

        slideLeft.setPower(power);
        slideRight.setPower(power);

//        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public int getLPos(){return slideLeft.getCurrentPosition();}
    public int getRPos(){return slideRight.getCurrentPosition();}
}
