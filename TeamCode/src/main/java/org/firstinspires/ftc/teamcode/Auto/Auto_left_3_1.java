package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="1+3 LEFt\uD83D\uDEE5\uFE0F")
public class Auto_left_3_1 extends LinearOpMode {
    final double INTAKEPOWER = 0.5; //
    final double IWRISTGROUND = 0;
    final double IWRISTTRANSFER = 0;
    final int EXTENDOIN = 0;
    final int EXTENDOMED = 400;
    final double BARPOSREGULAR = 0;
    final double BARPOSCLIP = 0;
    final int SLIDESLOW = 0;
    final int SLIDESHIGH = 0;
    final int SLIDESCLIPUP = 0;
    final int SLIDESCLIPDOWN = 0;
    final double CLAWCLOSE = 0;
    final double CLAWOPEN = 1;
    final double WRISTLEVEL = 0.5;
    final double WRISTBUCKET = 1;

    DcMotor intake;
    Servo intakeWrist;
    Servo wrist;
    Servo bar1;
    Servo bar2;
    Servo claw;
    DcMotor slides1;
    DcMotor slides2;
    DcMotor extendo;
    SampleMecanumDrive drive;
    public void runOpMode() {
        drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-23.5, -60, Math.toRadians(-90));
        drive.setPoseEstimate(startPose);
        intake = hardwareMap.get(DcMotor.class, "intake name thihgn");
        intakeWrist = hardwareMap.get(Servo.class, "intake wrist namer ");
        wrist = hardwareMap.get(Servo.class, " wrist namer ");
        bar1 = hardwareMap.get(Servo.class, "ball 1 namer ");
        bar2 = hardwareMap.get(Servo.class, "ball 2 namer ");
        //bar2.setDirection(Servo.Direction.REVERSE);
        claw = hardwareMap.get(Servo.class, "clawname");
        slides1 = hardwareMap.get(DcMotor.class, "slide1namer");
        slides2 = hardwareMap.get(DcMotor.class, "slide2namer");
//        slides2.setDirection(DcMotorSimple.Direction.REVERSE);
        extendo = hardwareMap.get(DcMotor.class, "eckstendoNameguy");
        claw.setPosition(CLAWCLOSE);

        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {})
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPUP);slides2.setTargetPosition(SLIDESCLIPUP);}) //slidesup
                .lineToConstantHeading(new Vector2d(-5, -39))
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {bar1.setPosition(BARPOSCLIP);})
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);slides2.setTargetPosition(SLIDESCLIPDOWN);}) //slidesdown
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                .waitSeconds(0.1)
                .setTangent(Math.toRadians(220))
                .splineToSplineHeading(new Pose2d(-58, -44, Math.toRadians(66)), Math.toRadians(170)) //to bucket facing r neutral
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOMED);intake.setPower(INTAKEPOWER);})
                .waitSeconds(1)
                .addTemporalMarker(() -> {transfer();})
                .waitSeconds(3)
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESHIGH);slides2.setTargetPosition(SLIDESHIGH);})
                .waitSeconds(1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {wrist.setPosition(WRISTLEVEL);slides1.setTargetPosition(SLIDESLOW);slides2.setTargetPosition(SLIDESLOW);bar1.setPosition(BARPOSREGULAR);})
                .waitSeconds(1.5)
                .addTemporalMarker(() -> {})
                .build();
        waitForStart();

    }
    private void transfer() {
        //transfer, ciopy pasted from when we have a robot.  guh
    }
}
