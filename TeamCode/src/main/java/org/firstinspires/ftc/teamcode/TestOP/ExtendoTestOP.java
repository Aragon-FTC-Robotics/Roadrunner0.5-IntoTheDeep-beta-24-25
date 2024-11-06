package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="ExtendoTestOP", group="Test OP")
public class ExtendoTestOP extends LinearOpMode {

    public ExtendoTest extendoTest = new ExtendoTest();

    public Gamepad gp1;
    public Gamepad gp2;

    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        extendoTest.init(hardwareMap);

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            extendoTest.Loop(gp1, gp2);
            telemetry.addData("extendo.pos", extendoTest.getePos());
            telemetry.update();
        }
    }
}