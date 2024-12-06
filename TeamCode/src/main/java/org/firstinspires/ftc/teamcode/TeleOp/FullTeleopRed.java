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


@TeleOp(name="what if the joker was red and yellow", group="Linear OpMode")
public class FullTeleopRed extends LinearOpMode {

    public Bar bar = new Bar();
    public Claw claw = new Claw();
    public Colorsensor colorsensor = new Colorsensor();
    public Slides slides = new Slides();
    public Drivetrain drivetrain = new Drivetrain();
    public Extendo extendo = new Extendo();
    public Flywheel flywheel = new Flywheel();
    public IntakeWrist intakeWrist = new IntakeWrist();
    public Wrist wrist = new Wrist();
    public ActionHandler actionHandler = new ActionHandler();
    public ElapsedTime timer = new ElapsedTime();
    public Gamepad gp1;
    public Gamepad gp2;
    double looptime = 0.000;
    double highestTime = 0.000;
    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        bar.init(hardwareMap);
        claw.init(hardwareMap);
        colorsensor.init(hardwareMap);
        drivetrain.init(hardwareMap);
        extendo.init(hardwareMap);
        flywheel.init(hardwareMap);
        intakeWrist.init(hardwareMap);
        slides.init(hardwareMap);
        wrist.init(hardwareMap);
        actionHandler.init(slides,extendo,bar,wrist,flywheel,claw,intakeWrist,colorsensor, "red");
        timer.reset();

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            looptime = timer.milliseconds();
            bar.Loop(gp1, gp2);
            colorsensor.Loop(gp1, gp2);
            claw.Loop(gp1, gp2);
            drivetrain.Loop(gp1, gp2);
            extendo.Loop(gp1, gp2);
            flywheel.Loop(gp1, gp2);
            intakeWrist.Loop(gp1, gp2);
            slides.Loop(gp1, gp2);
            wrist.Loop(gp1, gp2);
            actionHandler.Loop(gp1, gp2);
            telemetry.addData("Last Commanded Bar Pos", bar.getPos());
            telemetry.addData("BARSTATE:    ", bar.getBarState());
            telemetry.addData("Extendo intpos", extendo.getPos());
            telemetry.addData("Extendo encoder pos", extendo.getExtendopos());
            telemetry.addData("slideLpos", slides.getLPos());
            telemetry.addData("slideRpos", slides.getRPos());
            telemetry.addData("is REd?", colorsensor.sensorIsRed());
            telemetry.addData("is brue?", colorsensor.sensorIsBlue());
            telemetry.addData("is yelo?", colorsensor.sensorIsYellow());
            telemetry.addData("Looptime", looptime);
            telemetry.addData("Highest loop time", highestTime);
            if (looptime > highestTime) {
                highestTime = looptime;
            }
            telemetry.update();
            timer.reset();
        }
    }
}