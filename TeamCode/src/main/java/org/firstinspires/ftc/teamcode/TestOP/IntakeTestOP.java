package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="IntakeTestOP", group="Test OP")
public class IntakeTestOP extends LinearOpMode {

    public IntakeTest intakeTest = new IntakeTest();
    public Gamepad gp1;
    public Gamepad gp2;

    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        intakeTest.init(hardwareMap);

        waitForStart();

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            intakeTest.Loop(gp1, gp2);

            telemetry.addData("intake.pos", "%.3f", intakeTest.getiPos());
            telemetry.update();
        }
    }
}