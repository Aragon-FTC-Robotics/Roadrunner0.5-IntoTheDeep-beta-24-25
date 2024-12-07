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

@Autonomous(name="4+0 right\uD83D\uDEE5\uFE0F")
public class Auto_right_0_4 extends LinearOpMode {
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
    final int EXTENDOMED = Extendo.MED; //may need to be changed

    final double BARPOSWALL = Bar.wallpos;
    final double BARREG = Bar.neutralpos;
    final double BARCLIP1 = Bar.clip1;
    final double BARCLIP2 = Bar.clip2;

    final double CLAWCLOSE = Claw.CLOSEPOS;
    final double CLAWOPEN = Claw.OPENPOS;

    final double WRISTLEVEL = 0.5;
    final double WRISTCLIP = 0;
    final double WRISTCLIPUP =0;

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
        Pose2d startPose = new Pose2d(23.5, -60, Math.toRadians(90));
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
                .addTemporalMarker(() -> {})

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
                .setTangent(Math.toRadians(-65))
                .splineToSplineHeading(new Pose2d(31, -41.4, Math.toRadians(30)),Math.toRadians(35)) //extendo to sample1

                //extendo to sample #1
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOMED);
                    intakeWrist.setPosition(IWRISTGROUND);
                    intake.setPower(INTAKEIN);})
                .waitSeconds(1)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .waitSeconds(0.1)
                //dump in observaiton
                .lineToLinearHeading(new Pose2d(31,-41.41, Math.toRadians(-30)))
                .addTemporalMarker(() -> {intake.setPower(INTAKEOUT);})
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .waitSeconds(3)
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOIN);})

                //extendo to sample #2
                .setTangent(Math.toRadians(15))
                .splineToSplineHeading(new Pose2d(38.8, -36.8, Math.toRadians(25)),Math.toRadians(15)) //extnedo to saple2
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOMED);
                    intake.setPower(INTAKEIN);})
                .waitSeconds(1)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .waitSeconds(0.1)
                //dump in Observation
                .lineToLinearHeading(new Pose2d(39.01,-36.88, Math.toRadians(-50)))
                .addTemporalMarker(() -> {intake.setPower(INTAKEOUT);})
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .waitSeconds(3)
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOIN);})

                //extendo to sample #3
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(38.8, -36.8, Math.toRadians(25)),Math.toRadians(15))
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOMED);
                    intake.setPower(INTAKEIN);})
                .waitSeconds(1)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .waitSeconds(0.1)
                //dump in Observation
                .lineToLinearHeading(new Pose2d(39.01,-36.88, Math.toRadians(-50)))
                .addTemporalMarker(() -> {intake.setPower(INTAKEOUT);})
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {intake.setPower(INTAKESTOP);})
                .waitSeconds(3)
                .addTemporalMarker(() -> {extendo.setTargetPosition(EXTENDOIN);
                    intakeWrist.setPosition(IWRISTTRANSFER);
                    bar.setPosition(BARPOSWALL);
                    claw.setPosition(CLAWOPEN);})

                //go to wall to pick up clip #2
                .setTangent(Math.toRadians(-100))
                .splineToSplineHeading(new Pose2d(35, -58, Math.toRadians(-90)),Math.toRadians(-90))
                .addTemporalMarker(() -> {claw.setPosition(CLAWCLOSE);})
                //move to clip
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .lineToLinearHeading(new Pose2d(5, -35, Math.toRadians(90)))
                //clipping
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                .addTemporalMarker(() -> {bar.setPosition(BARREG);})

                //go to wall to pick up clip #3
                .lineToConstantHeading(new Vector2d(35,-58))
                .addTemporalMarker(() -> {claw.setPosition(CLAWCLOSE);})
                //move to clip
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .lineToLinearHeading(new Pose2d(5, -35, Math.toRadians(90)))
                //clipping
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                .addTemporalMarker(() -> {bar.setPosition(BARREG);})

                //go to wall to pick up clip #3
                .lineToConstantHeading(new Vector2d(35,-58))
                .addTemporalMarker(() -> {claw.setPosition(CLAWCLOSE);})
                //move to clip
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .lineToLinearHeading(new Pose2d(5, -35, Math.toRadians(90)))
                //clipping
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                .addTemporalMarker(() -> {bar.setPosition(BARREG);})

                //go to wall to pick up clip #4
                .lineToConstantHeading(new Vector2d(35,-58))
                .addTemporalMarker(() -> {claw.setPosition(CLAWCLOSE);})
                //move to clip
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .lineToLinearHeading(new Pose2d(5, -35, Math.toRadians(90)))
                //clipping
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                .addTemporalMarker(() -> {bar.setPosition(BARREG);})

                //go to wall to pick up clip #5
                .lineToConstantHeading(new Vector2d(35,-58))
                .addTemporalMarker(() -> {claw.setPosition(CLAWCLOSE);})
                //move to clip
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .lineToLinearHeading(new Pose2d(5, -35, Math.toRadians(90)))
                //clipping
                .addTemporalMarker(() -> {slides1.setTargetPosition(SLIDESCLIPDOWN);
                    slides2.setTargetPosition(SLIDESCLIPDOWN);
                    bar.setPosition(BARCLIP2);})
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {claw.setPosition(CLAWOPEN);})
                .addTemporalMarker(() -> {bar.setPosition(BARREG);})

                //park @ observation
                .lineToSplineHeading(new Pose2d(35, -60, Math.toRadians(90)))
                .build();
        waitForStart();

    }
}
