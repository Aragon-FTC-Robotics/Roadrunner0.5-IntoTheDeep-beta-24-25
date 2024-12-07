package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(72, 72, Math.toRadians(180), Math.toRadians(90), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(23.5, -60, Math.toRadians(270)))
                        .setTangent(Math.toRadians(135))
                        .splineToSplineHeading(new Pose2d(-10, -29, Math.toRadians(90)), Math.toRadians(90)) // To clipping bar
                        //clip2
                        //open claw
                        .waitSeconds(0.1)
                        .setTangent(Math.toRadians(-90))
                        .splineToSplineHeading(new Pose2d(36, -24, Math.toRadians(0)), Math.toRadians(90))
                        .setTangent(Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(47, -12, Math.toRadians(-90)), Math.toRadians(0))
                        .lineToConstantHeading(new Vector2d(47, -50))
                        .lineToConstantHeading(new Vector2d(47, -12))
                        .lineToConstantHeading(new Vector2d(57, -12))
                        .lineToConstantHeading(new Vector2d(57, -50))
                        .lineToConstantHeading(new Vector2d(57, -12))
                        .lineToConstantHeading(new Vector2d(64.25, -12))
                        .lineToConstantHeading(new Vector2d(64.25, -50))
                        .lineToConstantHeading(new Vector2d(23.5, -60))

                        .build());

//30,-10
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_LIGHT)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}