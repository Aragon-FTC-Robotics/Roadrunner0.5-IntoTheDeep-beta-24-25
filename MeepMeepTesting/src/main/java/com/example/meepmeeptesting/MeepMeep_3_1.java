package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeep_3_1 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(72, 72, Math.toRadians(180), Math.toRadians(90), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(23.5, -60, Math.toRadians(90)))
                        //To Clip
                        .lineToConstantHeading(new Vector2d(5, -35))
                        .waitSeconds(0.2)//clipping
                        .lineToLinearHeading(new Pose2d(-37, -34, Math.toRadians(180)))
                        .lineToConstantHeading(new Vector2d(-34, -24))
                        .waitSeconds(0.2)
                        //extendo small, iwrist out, intake in, intake stop, iwrist in, intake out, intake stop, bar transfer pos, wrist transfer pos, claw open
                        .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))
                        .waitSeconds(0.2)
                        //slides up, bar bucket, wrist bucket, claw close, bar norm, wrist transfer, slides down
                        .lineToLinearHeading(new Pose2d(-44, -24, Math.toRadians(180)))
                        .waitSeconds(0.2)
                        //extendo small, intake in, intake stop, iwrist in, intake out, intake stop, bar transfer pos, wrist transfer pos, claw open
                        .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))
                        .waitSeconds(0.2)
                        //slides up, bar bucket, wrist bucket, claw close, bar norm, wrist transfer, slides down
                        .lineToLinearHeading(new Pose2d(-55, -24, Math.toRadians(180)))
                        .waitSeconds(0.2)
                        //extendo small, intake in, intake stop, iwrist in, intake out, intake stop, bar transfer pos, wrist transfer pos, claw open
                        .lineToLinearHeading(new Pose2d(-51, -51, Math.toRadians(45)))
                        .waitSeconds(0.2)
                        //slides up, bar bucket, wrist bucket, claw close, bar norm, wrist transfer, slides down
                        .lineToSplineHeading(new Pose2d(35, -60, Math.toRadians(90)))
                        .build());
//-40,-30, 90;
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_LIGHT)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
