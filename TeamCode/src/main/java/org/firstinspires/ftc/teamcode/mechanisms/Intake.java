package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor flywheel;
    enum IntakeDirection {IN, OUT, STOP};
    IntakeDirection currentIntakeState = IntakeDirection.IN;
    public void init(HardwareMap hm) {
        flywheel = hm.get(DcMotor.class, "flywheel");
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.a) {
            currentIntakeState = IntakeDirection.IN;
        } else if (gp1.b){
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
}
