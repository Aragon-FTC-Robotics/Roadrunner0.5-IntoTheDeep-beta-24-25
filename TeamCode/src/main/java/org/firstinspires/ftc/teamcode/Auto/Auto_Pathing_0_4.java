package org.firstinspires.ftc.teamcode.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

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

@Autonomous(name="4+0 Pathing")
public class Auto_Pathing_0_4 extends LinearOpMode{
    SampleMecanumDrive drive;

    public void runOpMode() {

        drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(23.5, -60, Math.toRadians(90));
        drive.setPoseEstimate(startPose);


        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {})

                //got to clip
                .lineToConstantHeading(new Vector2d(-5, -34))
                .waitSeconds(1)

                //clip #1

                .setTangent(Math.toRadians(-65))
                .splineToSplineHeading(new Pose2d(31, -41.4, Math.toRadians(45)),Math.toRadians(50)) //extendo to sample1

                //extendo to sample #1

                .waitSeconds(1)
                //dump in observaiton
                .lineToLinearHeading(new Pose2d(31,-41.41, Math.toRadians(-30)))
                .waitSeconds(1)

                //extendo to sample #2
                .setTangent(Math.toRadians(15))
                .splineToSplineHeading(new Pose2d(38.8, -36.8, Math.toRadians(40)),Math.toRadians(30)) //extnedo to saple2
                .waitSeconds(1)
                //dump in Observation
                .lineToLinearHeading(new Pose2d(39.01,-36.88, Math.toRadians(-50)))
                .waitSeconds(1)

                //extendo to sample #3
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(38.8, -36.8, Math.toRadians(30)),Math.toRadians(25))
                .waitSeconds(1)

                //dump in Observation
                .lineToLinearHeading(new Pose2d(39.01,-36.88, Math.toRadians(-50)))
                .waitSeconds(1)

                //go to wall to pick up clip #2
                .setTangent(Math.toRadians(-100))
                .lineToLinearHeading(new Pose2d(35, -57, Math.toRadians(-90)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(5,-39, Math.toRadians(90)))
                //clipping
                .waitSeconds(1)
//                //go to wall to pick up clip #3
//                .lineToConstantHeading(new Vector2d(35,-57))
//
//                //move to clip
//                .lineToConstantHeading(new Vector2d(5, -39))
//                //clipping
//                .waitSeconds(1)
//
//
//                //go to wall to pick up clip #3
//                .lineToConstantHeading(new Vector2d(35,-57))
//                .waitSeconds(1)
//                .lineToConstantHeading(new Vector2d(5, -39))
//                //clipping
//                .waitSeconds(1)
//
//                //go to wall to pick up clip #4
//                .lineToConstantHeading(new Vector2d(35,-57))
//                .waitSeconds(1)
//                .lineToConstantHeading(new Vector2d(5, -39))
//                //clipping
//                .waitSeconds(1)
//                //go to wall to pick up clip #5
//                .lineToConstantHeading(new Vector2d(35,-57))
//                .waitSeconds(1)
//                .lineToConstantHeading(new Vector2d(5, -39))
//                //clipping
//                .waitSeconds(1)

                //park @ observation
                .lineToLinearHeading(new Pose2d(35, -60, Math.toRadians(90)))
                .build();
        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(myTrajectory);
    }

}
