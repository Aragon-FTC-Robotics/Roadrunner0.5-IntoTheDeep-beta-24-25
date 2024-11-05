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


    }


}
