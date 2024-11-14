package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Bar {
    Servo barServoLeft;
    Servo barServoRight;

    enum BarState {TRANSFER, WALL, BUCKET, CLIPUP, CLIPDOWN}
    double pos;
    double transferpos = 0;
    double wallpos = 0;
    double bucketpos = 0;
    double clipuppos = 0;
    double clipdownpos = 0;

    BarState currentBarState;

    public void init(HardwareMap hm) {
        barServoLeft = hm.get(Servo.class, "barLeft");
        barServoRight = hm.get(Servo.class, "barRight");
        barServoRight.setDirection(Servo.Direction.REVERSE);
        currentBarState = BarState.TRANSFER;
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.a) { //ALL OF THESE ARE PLACEHOLDERS type baet
            setState(BarState.TRANSFER);
        } else if (gp2.dpad_up || gp2.dpad_right) {
            setState(BarState.BUCKET);
        } else if (gp2.b) {
            setState(BarState.WALL);
        } else if (gp2.a) {
            setState(BarState.CLIPUP);
        } else if (gp2.y) {
            setState(BarState.CLIPDOWN);
        }

        switch(currentBarState) {
            case TRANSFER:
                setPos(transferpos);
                break;
            case BUCKET:
                setPos(bucketpos);
                break;
            case WALL:
                setPos(wallpos);
                break;
            case CLIPUP:
                setPos(clipuppos);
            case CLIPDOWN:
                setPos(clipdownpos);
                break;
            default:
                setState(BarState.TRANSFER);
                break;
        }
    }
    public void setPos(double pos) {
        barServoLeft.setPosition(pos);
        barServoRight.setPosition(pos);
    }

    public void setState(BarState bar) {
        currentBarState = bar;
    }

    public double getPos() {return pos;}
}
