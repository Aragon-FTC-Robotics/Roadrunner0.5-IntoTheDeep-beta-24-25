package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CRWrist {
    CRServo wrist;
    double power;
    double currentDeg = 0;
    double moveDeg;
    final double secPerDeg = 0.1 / 360; // time in sec to move 360

    public void init (HardwareMap hm){
        wrist = hm.get(CRServo.class, "CRWrist");
        power = 0.5;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) throws InterruptedException {
        move(90);

        if (gp1.dpad_left){
            power += 0.01;
        } else if (gp1.dpad_right) {
            power -= 0.01;
        }
    }

    public void move(double targetDeg) throws InterruptedException {
        moveDeg = targetDeg - currentDeg;

        double timeToMove = Math.abs(moveDeg * secPerDeg );

        if (moveDeg > 0) {
            wrist.setPower(0.5); //0.5 is placeholder
            wait((long) (timeToMove * 1000));
            wrist.setPower(0);
        } else if (moveDeg < 0) {
            wrist.setPower(-0.5); //-.5 is placeholder
            wait((long) (timeToMove * 1000));
            wrist.setPower(0);
        }

        currentDeg = targetDeg;
    }

    public double getPower() {
        return power;
    }

    public double getCurrentDeg() {
        return currentDeg;
    }
}
