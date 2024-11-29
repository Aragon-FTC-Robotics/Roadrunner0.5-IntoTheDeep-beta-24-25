package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="CRWristTestOp", group="Test OP")
public class CRWristTestOP extends LinearOpMode {

    public CRWrist crwristTest = new CRWrist();
    public Gamepad gp1;
    public Gamepad gp2;


    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        crwristTest.init(hardwareMap);

        waitForStart();

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            crwristTest.Loop(gp1, gp2);

            telemetry.addData("wrist.pow", "%.3f", crwristTest.getPower());
            telemetry.addData("wrist.deg", "%.3f", crwristTest.getCurrentDeg());
            telemetry.update();
        }
    }
}