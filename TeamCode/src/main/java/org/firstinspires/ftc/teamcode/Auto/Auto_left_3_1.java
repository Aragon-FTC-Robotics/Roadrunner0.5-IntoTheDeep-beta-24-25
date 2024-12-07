package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Bar;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Extendo;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.IntakeWrist;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="1+3 LEFt\uD83D\uDEE5\uFE0F")
public class Auto_left_3_1 extends LinearOpMode {
    IntakeWrist IntakeWrist = new IntakeWrist();
    Extendo Extendo = new Extendo();
    Bar Bar = new Bar();
    Claw Claw = new Claw();
    Wrist Wrist = new Wrist();
    Slides Slides = new Slides();

    final double INTAKEIN = 0.5;
    final double INTAKESTOP = 0;
    final double INTAKEOUT = -0.5;

    final double IWRISTGROUND = IntakeWrist.INPOS;
    final double IWRISTTRANSFER = IntakeWrist.OUTPOS;

    final int EXTENDOIN = Extendo.min;
    final int EXTENDOMED = Extendo.MED;

    final double BARPOSWALL = Bar.wallpos;
    final double BARREG = Bar.neutralpos;
    final double BARCLIP1 = Bar.clip1;
    final double BARCLIP2 = Bar.clip2;
    final double BARTRANSFER = Bar.transferpos;

    final double CLAWCLOSE = Claw.CLOSEPOS;
    final double CLAWOPEN = Claw.OPENPOS;

    final double WRISTLEVEL = 0.5;
    final double WRISTCLIP = 0;
    final double WRISTCLIPUP =0;
    final double WRISTTRANSFER = Wrist.transfer;

    final int SLIDEHIGH = 0;
    final int SLIDESLOW = 0;
    final int SLIDESCLIPUP = Slides.lowPos;
    final int SLIDESCLIPDOWN = Slides.groundPos;

    DcMotor intake;
    Servo intakeWrist;
    Servo wrist;
    Servo bar;
    Servo claw;
    DcMotor slides1;
    DcMotor slides2;
    DcMotor extendo;
    SampleMecanumDrive drive;
    public void runOpMode() {
        drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-23.5, -60, Math.toRadians(90));
        drive.setPoseEstimate(startPose);
        intake = hardwareMap.get(DcMotor.class, "intake name thihgn");
        intakeWrist = hardwareMap.get(Servo.class, "intake wrist namer ");
        wrist = hardwareMap.get(Servo.class, " wrist namer ");
        bar = hardwareMap.get(Servo.class, "ball 1 namer ");
        //bar2.setDirection(Servo.Direction.REVERSE);
        claw = hardwareMap.get(Servo.class, "clawname");
        slides1 = hardwareMap.get(DcMotor.class, "slide1namer");
        slides2 = hardwareMap.get(DcMotor.class, "slide2namer");
//        slides2.setDirection(DcMotorSimple.Direction.REVERSE);
        extendo = hardwareMap.get(DcMotor.class, "eckstendoNameguy");
        claw.setPosition(CLAWCLOSE);

        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(startPose)
                //got to clip
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPUP);
                    slides2.setTargetPosition(SLIDESCLIPUP);
                    bar.setPosition(BARCLIP1);})
                .lineToConstantHeading(new Vector2d(-5, -35))
                .waitSeconds(0.4)

                //clip #1
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                .addTemporalMarker(() -> {bar.setPosition(BARREG);})

                //move to intake yellow #1
                .lineToLinearHeading(new Pose2d(-37, -34, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(-34, -24))
                //collect sample#1
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOMED);
                    intakeWrist.setPosition(IWRISTGROUND);})
                .addTemporalMarker(() -> {intake.setPower(INTAKEIN);})
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .addTemporalMarker(() -> {intakeWrist.setPosition(INTAKEIN);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {intake.setPower(INTAKEOUT);})
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .addTemporalMarker(() -> {bar.setPosition(BARTRANSFER);
                    wrist.setPosition(WRISTTRANSFER);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                //move to bucket
                .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDEHIGH);
                    slides2.setTargetPosition(SLIDEHIGH);
                    bar.setPosition(BARTRANSFER);
                    wrist.setPosition(WRISTTRANSFER);})
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {claw.setPosition(CLAWCLOSE);})
                .addTemporalMarker(() -> {bar.setPosition(BARREG);
                    wrist.setPosition(WRISTTRANSFER);
                    slides1.setTargetPosition(SLIDESLOW);
                    slides2.setTargetPosition(SLIDESLOW);})

                //move to yellow #2
                .lineToLinearHeading(new Pose2d(-44, -24, Math.toRadians(180)))
                .waitSeconds(0.2)
                //collect sample #2
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOMED);
                    intakeWrist.setPosition(IWRISTGROUND);})
                .addTemporalMarker(() -> {intake.setPower(INTAKEIN);})
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .addTemporalMarker(() -> {intakeWrist.setPosition(INTAKEIN);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {intake.setPower(INTAKEOUT);})
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .addTemporalMarker(() -> {bar.setPosition(BARTRANSFER);
                    wrist.setPosition(WRISTTRANSFER);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                //move to bucket
                .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))
                //bucket sample
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDEHIGH);
                    slides2.setTargetPosition(SLIDEHIGH);
                    bar.setPosition(BARTRANSFER);
                    wrist.setPosition(WRISTTRANSFER);})
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {claw.setPosition(CLAWCLOSE);})
                .addTemporalMarker(() -> {bar.setPosition(BARREG);
                    wrist.setPosition(WRISTTRANSFER);
                    slides1.setTargetPosition(SLIDESLOW);
                    slides2.setTargetPosition(SLIDESLOW);})

                //move to yellow #3
                .lineToLinearHeading(new Pose2d(-55, -24, Math.toRadians(180)))
                .waitSeconds(0.2)
                //collect sample #3
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOMED);
                    intakeWrist.setPosition(IWRISTGROUND);})
                .addTemporalMarker(() -> {intake.setPower(INTAKEIN);})
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .addTemporalMarker(() -> {intakeWrist.setPosition(INTAKEIN);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {intake.setPower(INTAKEOUT);})
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .addTemporalMarker(() -> {bar.setPosition(BARTRANSFER);
                    wrist.setPosition(WRISTTRANSFER);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                //move to bucket
                .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDEHIGH);
                    slides2.setTargetPosition(SLIDEHIGH);
                    bar.setPosition(BARTRANSFER);
                    wrist.setPosition(WRISTTRANSFER);})
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {claw.setPosition(CLAWCLOSE);})
                .addTemporalMarker(() -> {bar.setPosition(BARREG);
                    wrist.setPosition(WRISTTRANSFER);
                    slides1.setTargetPosition(SLIDESLOW);
                    slides2.setTargetPosition(SLIDESLOW);})

                //park on observation
                .lineToSplineHeading(new Pose2d(35, -60, Math.toRadians(90)))
                .build();
        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(myTrajectory);

    }
    private void transfer() {
        //transfer, ciopy pasted from when we have a robot.  guh
    }
}
