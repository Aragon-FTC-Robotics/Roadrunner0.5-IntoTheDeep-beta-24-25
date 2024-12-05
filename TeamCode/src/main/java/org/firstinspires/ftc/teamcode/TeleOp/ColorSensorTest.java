package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Bar;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Colorsensor;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Extendo;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.IntakeWrist;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Wrist;


@TeleOp(name="Test Color sensor color recog itio", group="Linear OpMode")
public class ColorSensorTest extends LinearOpMode {


    public Colorsensor colorsensor = new Colorsensor();
    public ElapsedTime timer = new ElapsedTime();
    public Gamepad gp1;
    public Gamepad gp2;

    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        colorsensor.init(hardwareMap);
        timer.reset();

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            colorsensor.Loop(gp1, gp2);
            telemetry.addData("colrosenser R", colorsensor.getColor()[0]);
            telemetry.addData("colrosenser G", colorsensor.getColor()[1]);
            telemetry.addData("colrosenser B", colorsensor.getColor()[2]);
            telemetry.addData("is REd?", colorsensor.sensorIsRed());
            telemetry.addData("is brue?", colorsensor.sensorIsBlue());
            telemetry.addData("is yelo?", colorsensor.sensorIsYellow());
            telemetry.addData("time", timer.time());
            telemetry.update();
        }
    }
}