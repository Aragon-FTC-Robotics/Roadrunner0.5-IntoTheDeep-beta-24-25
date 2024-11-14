package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class merpmerp1plus2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(90), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-23.5, -60, Math.toRadians(90)))
                        //slides to upclippos
                        .lineToConstantHeading(new Vector2d(-5, -39)) // To clipping bar
                        //slides to downclippos
                        //open claw
                        //
                        .waitSeconds(1)
//                        .splineToConstantHeading(new Vector2d(-48, -50),Math.PI/2)
//                        .splineToConstantHeading(new Vector2d(-58, -44),180)
//                        .strafeTo(new Vector2d(-58, -44))
                        .setTangent(Math.toRadians(220))
                        .splineToSplineHeading(new Pose2d(-58, -44, Math.toRadians(66)), Math.toRadians(170)) //to bucket facing r neutral
                        //extendo
                        //intake and extendo back
                        //claw pickup from intake
                        //slides up and drop bucket
                        //slides down and bar reset pos
                        .waitSeconds(1)
                        .lineToLinearHeading(new Pose2d(-59.5, -44, Math.toRadians(87.5))) //facing c neutral
                        //do it again
                        .waitSeconds(1)
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_LIGHT)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}