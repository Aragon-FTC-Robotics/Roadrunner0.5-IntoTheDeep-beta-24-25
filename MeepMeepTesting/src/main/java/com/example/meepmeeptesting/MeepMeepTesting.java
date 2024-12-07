package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(72, 72, Math.toRadians(180), Math.toRadians(90), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(23.5, -60, Math.toRadians(270)))
                        //clip1
                        .lineToConstantHeading(new Vector2d(5, -35)) // To clipping bar
                        //clip2
                        //open claw
                        .waitSeconds(0.1)
                        .setTangent(Math.toRadians(-65))
                        .splineToSplineHeading(new Pose2d(31, -41.41, Math.toRadians(30)),Math.toRadians(35)) //extendo to sample1
                        .waitSeconds(0.1) //extendo
                        .lineToLinearHeading(new Pose2d(31,-41.41, Math.toRadians(-30)))
                        .waitSeconds(0.1) //outtake
                        .setTangent(Math.toRadians(15))
                        .splineToSplineHeading(new Pose2d(43, -24, Math.toRadians(0)),Math.toRadians(80)) //extnedo to saple2
                        .waitSeconds(0.1) //extendo
                        .lineToLinearHeading(new Pose2d(39.01,-36.88, Math.toRadians(-50)))
                        .waitSeconds(0.1) //outtake
                        .setTangent(Math.toRadians(0))
                        .splineToSplineHeading(new Pose2d(54, -24, Math.toRadians(0)),Math.toRadians(80)) //extendo to sample3
                        .waitSeconds(0.1) //extendo
                        .lineToLinearHeading(new Pose2d(39.01,-36.88, Math.toRadians(-50)))
                        .waitSeconds(0.1) //outtake
                        .setTangent(Math.toRadians(-100))
                        .splineToSplineHeading(new Pose2d(35, -58, Math.toRadians(-90)),Math.toRadians(-90))//to walli ntake
                        .waitSeconds(0.1) //claw close
                        .lineToConstantHeading(new Vector2d(5, -35)) // To clipping bar
                        .waitSeconds(0.1) //clip1and2 SAMP#2
                        .lineToConstantHeading(new Vector2d(35,-58)) // to wall itnake
                        .waitSeconds(0.1)
                        .lineToConstantHeading(new Vector2d(5, -35)) // To clipping bar
                        .waitSeconds(0.1) //clip1and2 SAMP#3
                        .lineToConstantHeading(new Vector2d(35,-58)) // to wall itnake
                        .waitSeconds(0.1)
                        .lineToConstantHeading(new Vector2d(5, -35)) // To clipping bar
                        .waitSeconds(0.1) //clip1and2 SAMP#4
                        .lineToConstantHeading(new Vector2d(35,-58)) // to wall itnake
                        .waitSeconds(0.1)
                        .lineToConstantHeading(new Vector2d(5, -35)) // To clipping bar
                        .waitSeconds(0.1) //clip1and2 SAMP#5

                        .setTangent(-65)
                        .splineToSplineHeading(new Pose2d(30, -30, Math.toRadians(60)), Math.toRadians(60))
                        .setTangent(70)
                        .splineToSplineHeading(new Pose2d(30,-10,Math.toRadians(180)),Math.toRadians(180))
                        .build());

//30,-10
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_LIGHT)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}