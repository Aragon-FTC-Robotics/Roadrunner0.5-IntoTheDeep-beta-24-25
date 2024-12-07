package org.firstinspires.ftc.teamcode.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "AutoPathing 3_1")
public class Auto_Pathing_3_1 extends LinearOpMode {
    SampleMecanumDrive drive;

    public void runOpMode() {
        drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(23.5, -60, Math.toRadians(90));
        drive.setPoseEstimate(startPose);

        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(startPose)
                //got to clip
                .lineToConstantHeading(new Vector2d(-5, -35))
                .waitSeconds(1)

                //clip #1

                //move to intake yellow #1
                .lineToLinearHeading(new Pose2d(-37, -34, Math.toRadians(180))) //
                .lineToConstantHeading(new Vector2d(-34, -24))
                //collect sample#1
                .waitSeconds(1)

                //move to bucket
                .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))
                .waitSeconds(1)

                //move to yellow #2
                .lineToLinearHeading(new Pose2d(-44, -24, Math.toRadians(180)))
                .waitSeconds(1)
                //collect sample #2

                //move to bucket
                .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))
                //bucket sample
                .waitSeconds(1)

                //move to yellow #3
                .lineToLinearHeading(new Pose2d(-55, -24, Math.toRadians(180)))
                .waitSeconds(1)
                //collect sample #3

                //move to bucket
                .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))
                .waitSeconds(1)

                //park on observation
                .lineToSplineHeading(new Pose2d(35, -60, Math.toRadians(90)))
                .build();
        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(myTrajectory);
    }
}
