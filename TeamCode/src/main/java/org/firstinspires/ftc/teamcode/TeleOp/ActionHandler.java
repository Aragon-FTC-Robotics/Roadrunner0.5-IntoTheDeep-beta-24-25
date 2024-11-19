package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Bar;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Extendo;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Claw;


public class ActionHandler {
    public Slides slides;
    public Drivetrain drivetrain;
    public Extendo extendo;
    public Bar bar;
    public Wrist wrist;
    public Flywheel flywheel;
    public Claw claw;

    public void init(HardwareMap hwMap){
        slides.init(hwMap);
        drivetrain.init(hwMap);
        extendo.init(hwMap);
        bar.init(hwMap);
        wrist.init(hwMap);
        flywheel.init(hwMap);
        claw.init(hwMap);
    }

    public void loop(Gamepad gp1, Gamepad gp2) throws InterruptedException {
        //Intake action, runs intake + color sensor check
        if(gp1.right_bumper) {
            flywheel.setState(Flywheel.FlywheelDirection.IN);
            //Color sensor :fire:
        }

        //Transfer: extendo min, wait, intake wrist in, , wait, claw open
        //High Bucket: slides up, bar back, wrist mid, claw close
        // ??? clip: slides down, wait, claw open
        //Wall pick-up: bar mid, wrist mid, claw open
        //

    }
}
