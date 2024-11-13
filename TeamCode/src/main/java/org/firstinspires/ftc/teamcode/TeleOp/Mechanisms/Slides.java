package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {

    DcMotor slideLeft;
    DcMotor slideRight;

    int groundPos = 0;
    int lowPos = 200;
    int medPos = 400;
    int highPos = 1200;

    public enum slideState {GROUND, LOW, MED, HIGH}

    slideState currentSlideState = slideState.GROUND;

    public void init(HardwareMap hm) {
        slideLeft = hm.get(DcMotor.class, "slideLeft");
        slideRight = hm.get(DcMotor.class, "slideRight");

        slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if(gp2.dpad_down) {
            setState(slideState.GROUND);
        } else if(gp2.dpad_right) {
            setState(slideState.LOW);
        } else if(gp2.dpad_up) {
            setState(slideState.MED);
        } else if(gp2.dpad_left) {
            setState(slideState.HIGH);
        }

        switch(currentSlideState) {
            case GROUND:
                slidePosition(0.8, groundPos);
                break;
            case LOW:
                slidePosition(0.8, lowPos);
                break;
            case MED:
                slidePosition(0.8, medPos);
                break;
            case HIGH:
                slidePosition(0.8, highPos);
                break;
            default:
                slidePosition(0.8, groundPos);
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

        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
