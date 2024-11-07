package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain {
    DcMotor rightFront;
    DcMotor leftFront;
    DcMotor rightRear;
    DcMotor leftRear;
    double minSpeed = -0.8;
    double maxSpeed = 0.8;

    boolean slowMode = false;
    public void init(HardwareMap hm){
        rightFront = hm.get(DcMotor.class, "rightFront");
        leftFront = hm.get(DcMotor.class, "leftFront");
        rightRear = hm.get(DcMotor.class, "rightRear");
        leftRear = hm.get(DcMotor.class, "leftRear");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void slowModeON(){
        slowMode = true;
    }

    public void slowModeOFF(){
        slowMode = false;
    }



    public void Loop(Gamepad gp1, Gamepad gp2){


        double y = gp1.left_stick_y; // Remember, Y stick value is reversed
        double x = -gp1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = -gp1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;

        if(slowMode == false) {
            minSpeed = -0.8;
            maxSpeed = 0.8;

        } else if(slowMode == true) {
            minSpeed = -0.5;
            maxSpeed = 0.5;
        }

        if (gp2.x || gp2.dpad_up || gp2.dpad_right || gp2.y || gp2.right_bumper){
            slowModeON();
        } else if (gp2.b || gp2.y || gp2.a || gp2.dpad_down) {
            slowModeOFF();
        }

        rightFront.setPower(Range.clip(frontRightPower, minSpeed, maxSpeed));
        rightRear.setPower(Range.clip(backRightPower, minSpeed, maxSpeed));
        leftFront.setPower(Range.clip(frontLeftPower, minSpeed, maxSpeed));
        leftRear.setPower(Range.clip(backLeftPower, minSpeed, maxSpeed));
    }
}
