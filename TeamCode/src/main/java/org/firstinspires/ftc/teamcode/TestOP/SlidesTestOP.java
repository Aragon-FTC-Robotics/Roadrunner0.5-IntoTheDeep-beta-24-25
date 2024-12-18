package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="VertSlidesTestOp", group="Test OP")
public class SlidesTestOP extends LinearOpMode {

    public SlidesTest slidesTest = new SlidesTest();

    public Gamepad gp1;
    public Gamepad gp2;

    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        slidesTest.init(hardwareMap);

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            slidesTest.Loop(gp1, gp2);
            telemetry.addData("slideLeft.pos", slidesTest.getleftPos());
            telemetry.addData("slideRight.pos", slidesTest.getrightPos());
            telemetry.addData("spos", slidesTest.getSpos());
            telemetry.update();
        }
    }
}