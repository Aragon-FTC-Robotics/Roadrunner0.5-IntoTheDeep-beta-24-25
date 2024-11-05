package org.firstinspires.ftc.teamcode.TestOP;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class SlidesTest {

    //horizontal slides, wrist (servo), flywheels
    // vert slides, bar (motor), claw (servo), wrist (servo)

    Gamepad gp;
    DcMotor slideLeft;
    DcMotor slideRight;;
    int sPos;

    public void init(HardwareMap hm) {
        slideLeft = hm.get(DcMotor.class, "slideLeft");
        slideRight = hm.get(DcMotor.class, "slideRight");
        sPos = 0;
        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp2.dpad_up) {
            sPos = slideLeft.getCurrentPosition() + 10;
        } else if (gp2.dpad_down) {
            sPos = slideLeft.getCurrentPosition() - 10;
        } else {
            sPos = slideLeft.getCurrentPosition();
        }
        moveToPos(sPos);
    }

    private void moveToPos(int position) {
        slideLeft.setTargetPosition(position);
        slideRight.setTargetPosition(position);
        slideLeft.setPower(0.5); // change if its too high or low
        slideRight.setPower(0.5);
        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public int leftPos() {
        return sPos;
    }

    public int rightPos() {
        return sPos;
    }
}
