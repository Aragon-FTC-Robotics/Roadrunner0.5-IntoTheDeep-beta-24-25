package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    Servo claw1;
    public final double OPENPOS = 0.691;
    public final double CLOSEPOS = 0.840;
    public final double SUPEROPENPOS = 0.661;
    public enum ClawState {CLOSE, OPEN, SUPEROPEN}
    ClawState currentClawState;
    public void init(HardwareMap hm) {
        claw1 = hm.get(Servo.class, "claw1");
        currentClawState = ClawState.CLOSE;
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp2.left_bumper) {
            setState(ClawState.OPEN);
        } else if (gp2.right_bumper) {
            setState(ClawState.CLOSE);
        }
        switch(currentClawState) {
            case OPEN:
                openPosition();
                break;
            case CLOSE:
                closePosition();
                break;
            default:
                setState(ClawState.OPEN);
                break;
        }
    }
    public void setState (ClawState state) {
        currentClawState = state;
    }
    private void openPosition() {
        double openPos = OPENPOS;
        claw1.setPosition(openPos);
    }
    public void setPos(double pos) {
        claw1.setPosition(pos);
    }
    private void closePosition() {
        double closePos = CLOSEPOS;
        claw1.setPosition(closePos);
    }
}
