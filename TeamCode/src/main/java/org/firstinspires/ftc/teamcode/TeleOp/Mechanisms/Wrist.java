package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist {
    Servo wrist;
    final double transfer = 0.022;
    final double bucket = 1.000;
    final double wall = 0.419;
    final double clip1 = 0.720;
    final double clip2 = 0.552;

    double speed;

    public enum wristState {TRANSFER, BUCKET, WALL, CLIP1, CLIP2}
    wristState currentWristState = wristState.TRANSFER;

    public void init(HardwareMap hm) {
        wrist = hm.get(Servo.class, "wrist");
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
            switch(currentWristState) {
                case TRANSFER:
                    wristPos(transfer);
                    break;
                case WALL:
                    wristPos(wall);
                    break;
                case CLIP1:
                    wristPos(clip1);
                    break;
                case CLIP2:
                    wristPos(clip2);
                    break;
                case BUCKET:
                    wristPos(bucket);
                    break;
                default:
                    wristPos(transfer);
                    break;
            }
    }

    public void setState(wristState state) {
        currentWristState = state;
    }

    public void wristPos(double pos){
        wrist.setPosition(pos);
    }
    public int portNumber(){return wrist.getPortNumber();}
    public Object getDirection(){return wrist.getDirection();}
}
