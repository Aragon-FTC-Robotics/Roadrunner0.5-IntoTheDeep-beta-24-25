package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor flywheel;
    public enum IntakeDirection {IN, OUT, STOP};
    IntakeDirection currentIntakeState = IntakeDirection.STOP;
    public void init(HardwareMap hm) {
        flywheel = hm.get(DcMotor.class, "flywheel");
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.right_bumper) {
            currentIntakeState = IntakeDirection.IN;
        } else if (gp1.left_bumper){
            currentIntakeState = IntakeDirection.OUT;
        } else {
            currentIntakeState = IntakeDirection.STOP;
        }
        switch (currentIntakeState) {
            case IN:
                flywheel.setPower(0.8);
                break;
            case OUT:
                flywheel.setPower(-0.8);
                break;
            case STOP:
                flywheel.setPower(0);
                break;
            default:
                flywheel.setPower(0);
                break;
        }
    }

    public void setState(IntakeDirection state) {
        currentIntakeState = state;
    }
}
