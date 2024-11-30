package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "SecPerDegTestOP", group = "Test")
public class crWristSecPerDegTestOP extends LinearOpMode {

    @Override
    public void runOpMode() {
        CRServo wristServo = hardwareMap.get(CRServo.class, "CRWrist");

        waitForStart();

        if (opModeIsActive()) {
            wristServo.setPower(0.5);
            telemetry.addData("Servo Status", "Running at 0.5 power");
            telemetry.update();

            sleep(1000);

            wristServo.setPower(0.0);
            telemetry.addData("Servo Status", "Stopped or Neutralized");
            telemetry.update();
        }
    }
}