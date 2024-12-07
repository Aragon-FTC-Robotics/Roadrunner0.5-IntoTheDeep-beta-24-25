package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Bar;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Extendo;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.IntakeWrist;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
@Disabled
@Autonomous(name="0+1 LEFt, FACING UP ???")
public class Auto_1_0 extends LinearOpMode {
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
    final double BARBucket = Bar.bucketpos;

    final double CLAWCLOSE = Claw.CLOSEPOS;
    final double CLAWOPEN = Claw.OPENPOS;

    final double WRISTLEVEL = 0.5;
        final double WRISTCLIP = 0;
        final double WRISTCLIPUP = 0;
        final double WRISTTRANSFER = Wrist.transfer;
        final double WRISTBUCKET = Wrist.bucket;
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
        public void runOpMode () {
            drive = new SampleMecanumDrive(hardwareMap);
            Pose2d startPose = new Pose2d(-23.5, -60, Math.toRadians(90));
            drive.setPoseEstimate(startPose);
            intake = hardwareMap.get(DcMotor.class, "flywheel");
            intakeWrist = hardwareMap.get(Servo.class, "iWrist");
            wrist = hardwareMap.get(Servo.class, " wrist");
            bar = hardwareMap.get(Servo.class, "barRight");
            //bar2.setDirection(Servo.Direction.REVERSE);
            claw = hardwareMap.get(Servo.class, "claw1");
            slides1 = hardwareMap.get(DcMotor.class, "slideLeft");
            slides2 = hardwareMap.get(DcMotor.class, "slideRight");
            slides2.setDirection(DcMotorSimple.Direction.REVERSE);
            slides1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            slides2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            slides2.setDirection(DcMotorSimple.Direction.REVERSE);
            extendo = hardwareMap.get(DcMotor.class, "extendo");
            claw.setPosition(CLAWOPEN);

            TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(startPose)
                    //move to bucket
                    .UNSTABLE_addTemporalMarkerOffset(2, () -> {
                        slides1.setTargetPosition(SLIDEHIGH);
                        slides2.setTargetPosition(SLIDEHIGH);
                        slides1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        slides2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        bar.setPosition(BARBucket);
                        wrist.setPosition(WRISTBUCKET);
                        intakeWrist.setPosition(IWRISTGROUND);
                    })
                    .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))

                    .waitSeconds(0.2)
                    .addTemporalMarker(() -> {
                        claw.setPosition(CLAWCLOSE);
                    })
                    .waitSeconds(1)
                    .addTemporalMarker( () -> {
                        bar.setPosition(BARREG);
                        wrist.setPosition(WRISTTRANSFER);
                        slides1.setTargetPosition(SLIDESLOW);
                        slides2.setTargetPosition(SLIDESLOW);
                        slides1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        slides2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    })


                    //park on observation
                    .lineToSplineHeading(new Pose2d(35, -60, Math.toRadians(90)))
                    .build();
            waitForStart();

            if (isStopRequested()) return;

            drive.followTrajectorySequence(myTrajectory);
        }
}
