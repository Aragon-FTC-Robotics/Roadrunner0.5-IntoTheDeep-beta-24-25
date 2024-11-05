package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="ClawTest", group="Test OP")
public class ClawTestOP extends LinearOpMode {
    public ClawTest clawTest = new ClawTest();
    public Gamepad gp1;
    public Gamepad gp2;

    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        clawTest.init(hardwareMap);

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            clawTest.Loop(gp1, gp2);

            telemetry.addData("claw.value", clawTest.getcPos());
            telemetry.addLine();

        }
    }
}
