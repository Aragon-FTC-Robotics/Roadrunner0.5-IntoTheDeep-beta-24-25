package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="IntakeWristTestOp", group="Test OP")
public class IntakeWristTestOP extends LinearOpMode {

    public IntakeWristTest iwristTest = new IntakeWristTest();
    public Gamepad gp1;
    public Gamepad gp2;


    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        iwristTest.init(hardwareMap);

        waitForStart();

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            iwristTest.Loop(gp1, gp2);

            telemetry.addData("intakewrist.pos", "%.3f", iwristTest.getiwPos());
            telemetry.update();
        }
    }
}