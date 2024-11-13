package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Bar;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Extendo;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Wrist;

public class ActionHandler {
    public Slides slides;
    public Drivetrain drivetrain;
    public Extendo extendo;
    public Bar bar;
    public Wrist wrist;
    public Intake intake;

    public void init(HardwareMap hwMap){
        slides.init(hwMap);
        drivetrain.init(hwMap);
        extendo.init(hwMap);
        bar.init(hwMap);
        wrist.init(hwMap);
        intake.init(hwMap);
    }

    public void loop(Gamepad gp1, Gamepad gp2) {
        //Intake action, runs intake + color sensor check
        if(gp1.right_bumper) {
            intake.setState(Intake.IntakeDirection.IN);
            //Color sensor :fire:
        }


    }
}
