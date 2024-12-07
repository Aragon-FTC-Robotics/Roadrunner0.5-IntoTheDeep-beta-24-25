package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist {
    Servo wrist;
    public final double transfer = 0.070;
    public final double bucket = 1.000;
    public final double wall = 0.472;
    public final double clip1 = 0.273;
    public final double clip2 = 0.273;
    public final double EJECT = 1.000;
    double speed;

    public enum wristState {TRANSFER, BUCKET, WALL, CLIP1, CLIP2, EJECT}
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
                case EJECT:
                    wristPos(EJECT);
                default:
                    wristPos(transfer);
                    break;
            }
    }

    public void setState(wristState state) {
        currentWristState = state;
    }
    public void setPos(double pos) {
        wrist.setPosition(pos);
    }

    public void wristPos(double pos){
        wrist.setPosition(pos);
    }
    public int portNumber(){return wrist.getPortNumber();}
    public Object getDirection(){return wrist.getDirection();}
}
