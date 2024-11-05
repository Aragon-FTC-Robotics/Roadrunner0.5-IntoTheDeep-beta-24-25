package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist {
    Servo wrist;

    double defPos = 0;
    double transfer;
    double bucket;
    double wall;
    double clip;

    double speed;

    public enum wristState {TRANSFER, BUCKET, WALL, CLIP}
    wristState currentWristState = wristState.TRANSFER;

    public void init(HardwareMap hm) {
        wrist = hm.get(Servo.class, "wrist");
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (!(gp1.a&&(gp1.dpad_up||gp2.dpad_right)&&gp2.b)){
            defPos += (gp1.left_trigger - gp1.right_trigger) * speed;
            if (defPos > 1) {
                defPos = 1;
            } else if (defPos < 0) {
                defPos = 0;
            }

            if(gp2.dpad_down) {
                setState(wristState.TRANSFER);
            } else if(gp1.x) {
                setState(wristState.WALL);
            } else if(gp1.y) {
                setState(wristState.CLIP);
            } else if(gp2.dpad_left) {
                setState(wristState.BUCKET);
            }

            switch(currentWristState) {
                case TRANSFER:
                    wristPos(transfer);
                    break;
                case WALL:
                    wristPos(wall);
                    break;
                case CLIP:
                    wristPos(clip);
                    break;
                case BUCKET:
                    wristPos(bucket);
                    break;
                default:
                    wristPos(transfer);
                    break;
            }
        }
    }

    public void setState(wristState state) {
        currentWristState = state;
    }

    public void wristPos(double pos){
        wrist.setPosition(pos);
    }
}
