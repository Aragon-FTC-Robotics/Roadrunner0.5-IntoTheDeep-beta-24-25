package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Bar;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Colorsensor;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Extendo;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.IntakeWrist;
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
    public IntakeWrist intakeWrist;
    public Colorsensor colorSensor;

    public void init(HardwareMap hwMap){
        slides.init(hwMap);
        drivetrain.init(hwMap);
        extendo.init(hwMap);
        bar.init(hwMap);
        wrist.init(hwMap);
        flywheel.init(hwMap);
        claw.init(hwMap);
        intakeWrist.init(hwMap);
        colorSensor.init(hwMap);
    }

    public void Loop(Gamepad gp1, Gamepad gp2) throws InterruptedException {
        //Intake action, runs intake + color sensor check
        if(gp1.right_bumper) {
            flywheel.setState(Flywheel.FlywheelDirection.IN);
            //wait
            if (colorSensor.sensorIsBlue()) {
                flywheel.setState(Flywheel.FlywheelDirection.OUT);
            }
        }

        //Bucket
        if(gp2.dpad_down) {
            Transfer();
            //Wait?
            slides.setState(Slides.slideState.HIGH);
            bar.setState(Bar.BarState.BUCKET);
            wrist.setState(Wrist.wristState.BUCKET);
        }

        //clip Up
        if(gp2.dpad_right) {
            slides.setState(Slides.slideState.MED);
            bar.setState(Bar.BarState.CLIPUP);
            wrist.setState(Wrist.wristState.CLIP);
        }

        //Clip down
        if(gp1.dpad_left) {
            slides.setState(Slides.slideState.GROUND);
            //Wait
            claw.setState(Claw.ClawState.OPEN);
            //Should be a pos slightly above transfer
            bar.setState(Bar.BarState.TRANSFER);
            wrist.setState(Wrist.wristState.TRANSFER);
            claw.setState(Claw.ClawState.CLOSE);
        }

        //Wall pickup
        if(gp2.b) {
            claw.setState(Claw.ClawState.OPEN);
            bar.setState(Bar.BarState.WALL);
            wrist.setState(Wrist.wristState.WALL);
        }

    }

    public void Transfer() {
        
        bar.setState(Bar.BarState.TRANSFER);
        claw.setState(Claw.ClawState.OPEN);
    }
}
