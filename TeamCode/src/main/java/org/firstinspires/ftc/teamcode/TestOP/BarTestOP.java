package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="BarTest", group="Test OP")
public class BarTestOP extends LinearOpMode {

    public BarTest barTest = new BarTest();
    public Gamepad gp1;
    public Gamepad gp2;

    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        barTest.init(hardwareMap);

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {

            barTest.Loop(gp1, gp2);
            telemetry.addData("rightBar.value", barTest.getrightPos());
            telemetry.addData("leftBar.value", barTest.getleftPos());
            telemetry.update();

        }
    }
}