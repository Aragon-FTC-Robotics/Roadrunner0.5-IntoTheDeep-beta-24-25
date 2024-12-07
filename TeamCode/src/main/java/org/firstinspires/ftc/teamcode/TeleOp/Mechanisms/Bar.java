package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Bar {
    //Servo barServoLeft;
    Servo barServoRight;

    public enum BarState {TRANSFER, WALL, BUCKET, CLIP2, CLIP1, NEUTRAL, EJECT}
    double pos;
    public double transferpos = 0.874;
    public double wallpos = 0.817;
    public double bucketpos = 0.264;
    public double clip2 = 0.430;
    public double clip1 = 0.430;
    public double neutralpos = 0.718;
    final double EJECT = 0.000;
    BarState currentBarState;

    public void init(HardwareMap hm) {
    //    barServoLeft = hm.get(Servo.class, "barLeft");
        barServoRight = hm.get(Servo.class, "barRight");
        barServoRight.setDirection(Servo.Direction.REVERSE);
    //    barServoLeft.setDirection(Servo.Direction.FORWARD);
        currentBarState = BarState.NEUTRAL;
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
            case NEUTRAL:
                setPos(neutralpos);
                break;
            case EJECT:
                setPos(EJECT);
                break;
            default:
                setState(BarState.WALL);
                break;
        }
    }
    public void setPos(double pos) {
    //    barServoLeft.setPosition(pos);
        barServoRight.setPosition(pos);
    }
    public String getBarState(){
        switch(currentBarState) {
            case TRANSFER: return "transfer";
            case BUCKET: return "bucket";
            case WALL: return "wall";
            case CLIP2: return "clip2";
            case CLIP1: return "clip1";
            case NEUTRAL: return "neutral";
            case EJECT: return "ejecting";
            default: return "Default!! (transfer)";
        }
    }
    public void setState(BarState bStateyeah) {
        this.currentBarState = bStateyeah;
    }

    public double getPos() {return pos;}
}
