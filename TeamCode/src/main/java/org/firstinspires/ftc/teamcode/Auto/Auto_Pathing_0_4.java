package org.firstinspires.ftc.teamcode.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleOp.ActionHandler;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Bar;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Colorsensor;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Extendo;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.IntakeWrist;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.SpecialExtendo;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.SpecialSlides;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="RIGHT SIDE CLIP 1  AND PUSH, FACING LEFT")
public class Auto_Pathing_0_4 extends LinearOpMode{
    SampleMecanumDrive drive;
    public void runOpMode() {

        drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(23.5, -60, Math.toRadians(180));
        drive.setPoseEstimate(startPose);
        Bar bar = new Bar();
        Claw claw = new Claw();
        SpecialSlides slides = new SpecialSlides();
        SpecialExtendo extendo = new SpecialExtendo();
        IntakeWrist intakeWrist = new IntakeWrist();
        Wrist wrist = new Wrist();
        bar.init(hardwareMap);
        claw.init(hardwareMap);
        slides.init(hardwareMap);
        extendo.init(hardwareMap);
        intakeWrist.init(hardwareMap);
        wrist.init(hardwareMap);
        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(()->{
                    //hello
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    //hello2
                })
                    .setTangent(Math.toRadians(45))
                        .splineToSplineHeading(new Pose2d(36, -20, Math.toRadians(0)), Math.toRadians(90))
                        .setTangent(Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(47, -2, Math.toRadians(-90)), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(47, -50))
                        .lineToConstantHeading(new Vector2d(47, -2))
                        .lineToConstantHeading(new Vector2d(60, -2))
                .lineToConstantHeading(new Vector2d(60, -50))
                .lineToConstantHeading(new Vector2d(60, -2))
                .lineToConstantHeading(new Vector2d(64, -2))
                .lineToConstantHeading(new Vector2d(62, -50))
                .lineToConstantHeading(new Vector2d(60, -40))
                .waitSeconds(1)
                .lineToConstantHeading(new Vector2d(60, -50))

                        .build();
        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(myTrajectory);
        int i = 0;
        while (!isStopRequested()) {
            slides.loop();
            extendo.Loop();
            i++;
            telemetry.addData("i", i);
            telemetry.update();
        }
    }

}
