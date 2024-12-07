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
        switch (currentFlywheelState) {
            case IN:
                flywheel.setPower(0.8);
                gp1.stopRumble();
                gp2.stopRumble();
                break;
            case OUT:
                flywheel.setPower(-0.8);
                gp1.rumble(Gamepad.RUMBLE_DURATION_CONTINUOUS);
                gp2.rumble(Gamepad.RUMBLE_DURATION_CONTINUOUS);
                break;
            case STOP:
                flywheel.setPower(0);
                gp1.stopRumble();
                gp2.stopRumble();
                break;
            default:
                flywheel.setPower(0);
                gp1.rumble(20);
                gp2.rumble(20);
                break;
        }
    }

    public void setState(FlywheelDirection state) {
        currentFlywheelState = state;
    }
    public FlywheelDirection getState() {return currentFlywheelState;}
}
