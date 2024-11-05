package org.firstinspires.ftc.teamcode.TestOP;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Extendo {

    //horizontal slides, wrist (servo), flywheels
    // vert slides, bar (motor), claw (servo), wrist (servo)

    Gamepad gp;
    DcMotor extendo;
    int sPos;

    public void init(HardwareMap hm) {
        extendo = hm.get(DcMotor.class, "extendo");
        sPos = 0;
        extendo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp2.dpad_up) {
            sPos += 10;
        } else if (gp2.dpad_down) {
            sPos -= 10;
        }
        moveToPos(sPos);
    }

    private void moveToPos(int position) {
        extendo.setTargetPosition(position);
        extendo.setPower(0.5); // change if its too high or low
        extendo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public int extendoPos() {
        return sPos;
    }
}