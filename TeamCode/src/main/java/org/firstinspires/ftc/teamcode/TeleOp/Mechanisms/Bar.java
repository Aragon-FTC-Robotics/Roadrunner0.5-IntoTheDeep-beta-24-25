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

    public enum BarState {TRANSFER, WALL, BUCKET, CLIP2, CLIP1}
    double pos;
    double transferpos = 0.948;
    double wallpos = 0.917;
    double bucketpos = 0.287;
    double clip2 = 0.348;
    double clip1 = 0.181;

    BarState currentBarState;

    public void init(HardwareMap hm) {
        barServoLeft = hm.get(Servo.class, "barLeft");
        barServoRight = hm.get(Servo.class, "barRight");
        barServoRight.setDirection(Servo.Direction.REVERSE);
        barServoLeft.setDirection(Servo.Direction.FORWARD);
        currentBarState = BarState.WALL;
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
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
            case CLIP2:
                setPos(clip2);
                break;
            case CLIP1:
                setPos(clip1);
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

    public void setState(BarState bStateyeah) {
        this.currentBarState = bStateyeah;
    }

    public double getPos() {return pos;}
}
