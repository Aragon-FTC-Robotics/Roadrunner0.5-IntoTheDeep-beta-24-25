package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Bar;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.IntakeWrist;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.SpecialExtendo;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.SpecialSlides;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="PARK PARK PARK AKOR, FACING LEFT")
public class parkatuo extends LinearOpMode{
    SampleMecanumDrive drive;
    public void runOpMode() {

        drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(23.5, -60, Math.toRadians(180));
        drive.setPoseEstimate(startPose);
        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(startPose)
                .setTangent(45)
                        .splineToConstantHeading(new Vector2d(60, -58), Math.toRadians(180))

                        .build();
        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(myTrajectory);
        int i = 0;
    }

}
