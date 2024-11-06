package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="FullTestOP", group="Test OP")

public class FullTestTeleOP extends LinearOpMode {

    public BarTest barTest = new BarTest();
    public ClawTest clawTest = new ClawTest();
    public ExtendoTest extendoTest = new ExtendoTest();
    public IntakeTest intakeTest = new IntakeTest();
    public SlidesTest slidesTest = new SlidesTest();
    public WristTest wristTest = new WristTest();

    public Gamepad gp1;
    public Gamepad gp2;

    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        barTest.init(hardwareMap);
        clawTest.init(hardwareMap);
        extendoTest.init(hardwareMap);
        intakeTest.init(hardwareMap);
        slidesTest.init(hardwareMap);
        wristTest.init(hardwareMap);

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {

            barTest.Loop(gp1, gp2);
            telemetry.addData("rightBar.value", barTest.getrightPos());
            telemetry.addData("leftBar.value", barTest.getleftPos());
            telemetry.addData("claw.value", clawTest.getcPos());
            telemetry.addData("extendo.value", extendoTest.getePos());
            telemetry.addData("intake.value", intakeTest.getiPos());
            telemetry.addData("leftslides.value", slidesTest.getleftPos());
            telemetry.addData("rightslides.value", slidesTest.getrightPos());
            telemetry.addData("wrist.value", wristTest.getwPos());
            telemetry.addLine();

        }

    }
}
