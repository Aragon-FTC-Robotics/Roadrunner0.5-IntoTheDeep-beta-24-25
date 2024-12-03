package org.firstinspires.ftc.teamcode.TeleOp;

import android.graphics.Color;

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
    private Slides slides;
    private Extendo extendo;
    private Bar bar;
    private Wrist wrist;
    private Flywheel flywheel;
    private Claw claw;
    private IntakeWrist intakeWrist;
    private Colorsensor colorSensor;
    public void init(Slides s, Extendo e, Bar b, Wrist w, Flywheel f, Claw c, IntakeWrist iw, Colorsensor cs){
        slides=s;extendo=e;bar=b;wrist=w;flywheel=f;claw=c;intakeWrist=iw;colorSensor=cs;
    }
    public void Loop(Gamepad gp1, Gamepad gp2) throws InterruptedException {
        //Intake action, runs intake + color sensor check
        if (gp1.y) {
            flywheel.setState(Flywheel.FlywheelDirection.IN);
        } else if (gp1.a){
            flywheel.setState(Flywheel.FlywheelDirection.OUT);
        } else {
            flywheel.setState(Flywheel.FlywheelDirection.STOP);
        }
        //LATER: MAKE IT RUMBLE OR SOMETHING
        //
        if (gp1.left_bumper) {
            Transfer();
        }
        if (gp1.right_bumper) {
            Thread.sleep(300);
        }
        //Bucket high
        if(gp2.dpad_up) {
            slides.setState(Slides.slideState.HIGH);
            bar.setState(Bar.BarState.BUCKET);
            wrist.setState(Wrist.wristState.BUCKET);
        }
        if(gp2.dpad_down) {
            slides.setState(Slides.slideState.GROUND);
            bar.setState(Bar.BarState.WALL);
            wrist.setState(Wrist.wristState.WALL);
        }

        //clip Up
        if(gp2.b) {
            Clip1();
        }

        //Clip down
        if(gp2.y) {
            Clip2();
        }
        //Wall pickup
        if(gp2.a) {
            claw.setState(Claw.ClawState.OPEN);
            bar.setState(Bar.BarState.WALL);
            wrist.setState(Wrist.wristState.WALL);
        }
        //transfer
        if(gp1.b) {
            Transfer();
        }
        //intakewristdown
        if(gp1.x) {
            intakeWrist.setState(IntakeWrist.iwristState.OUT);
        }
    }

    public void Transfer() throws InterruptedException {
        intakeWrist.setState(IntakeWrist.iwristState.IN);
        Thread.sleep(500);
        flywheel.setState(Flywheel.FlywheelDirection.OUT);
        Thread.sleep(250);
        flywheel.setState(Flywheel.FlywheelDirection.STOP);
        claw.setState(Claw.ClawState.CLOSE);
        bar.setState(Bar.BarState.TRANSFER);
        wrist.setState(Wrist.wristState.TRANSFER);
        Thread.sleep(2000);
        claw.setState(Claw.ClawState.OPEN);
        bar.setState(Bar.BarState.WALL);
    }
    public void Clip1() {
        bar.setState(Bar.BarState.CLIP1);
        wrist.setState(Wrist.wristState.CLIP1);
    }
    public void Clip2() {
        bar.setState(Bar.BarState.CLIP2);
        wrist.setState(Wrist.wristState.CLIP2);
    }
}