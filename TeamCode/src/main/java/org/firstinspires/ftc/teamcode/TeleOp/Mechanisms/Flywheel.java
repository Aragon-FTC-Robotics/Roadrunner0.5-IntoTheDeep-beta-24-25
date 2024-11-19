package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Flywheel {
    DcMotor flywheel;
    public enum FlywheelDirection {IN, OUT, STOP};
    FlywheelDirection currentFlywheelState = FlywheelDirection.STOP;
    public void init(HardwareMap hm) {
        flywheel = hm.get(DcMotor.class, "flywheel");
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.dpad_up) {
            currentFlywheelState = FlywheelDirection.IN;
        } else if (gp1.dpad_down){
            currentFlywheelState = FlywheelDirection.OUT;
        } else {
            currentFlywheelState = FlywheelDirection.STOP;
        }
        switch (currentFlywheelState) {
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

    public void setState(FlywheelDirection state) {
        currentFlywheelState = state;
    }
}
