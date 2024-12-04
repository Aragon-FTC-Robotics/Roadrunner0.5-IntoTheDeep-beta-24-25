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
    private boolean intook = false;
    private boolean intaking = false;
    private String alliance;
    public void init(Slides s, Extendo e, Bar b, Wrist w, Flywheel f, Claw c, IntakeWrist iw, Colorsensor cs, String alliance){
        slides=s;extendo=e;bar=b;wrist=w;flywheel=f;claw=c;intakeWrist=iw;colorSensor=cs;
        this.alliance = alliance;
    }
    public void Loop(Gamepad gp1, Gamepad gp2) throws InterruptedException {
        //Intake action, runs intake + color sensor check
        if (gp1.y && !intaking) {
            intaking = true;
            flywheel.setState(Flywheel.FlywheelDirection.IN);
        }
        intakecheck();
        //LATER: MAKE IT RUMBLE OR SOMETHING
        if(gp1.left_bumper) {
            Transfer();
        }
        if(gp1.right_bumper) {
            Thread.sleep(500);
            intakeWrist.setState(IntakeWrist.iwristState.OUT);
        }
        //Bucket high
        if(gp2.dpad_up) {
            highBucket();
        }
        if(gp2.dpad_down) {
            resetSlidesArm();
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
        claw.setState(Claw.ClawState.CLOSE);
        Thread.sleep(500);
        intakeWrist.setState(IntakeWrist.iwristState.IN);
        Thread.sleep(500);
        flywheel.setState(Flywheel.FlywheelDirection.OUT);
        Thread.sleep(250);
        flywheel.setState(Flywheel.FlywheelDirection.STOP);
        bar.setState(Bar.BarState.TRANSFER);
        wrist.setState(Wrist.wristState.TRANSFER);
        Thread.sleep(2000);
        claw.setState(Claw.ClawState.OPEN);
        Thread.sleep(200);
        bar.setState(Bar.BarState.WALL);
    }
    public void Clip1() {
        bar.setState(Bar.BarState.CLIP1);
        wrist.setState(Wrist.wristState.CLIP1);
    }
    public void Clip2() throws InterruptedException{
        bar.setState(Bar.BarState.CLIP2);
        wrist.setState(Wrist.wristState.CLIP2);
        Thread.sleep(200);
        claw.setState(Claw.ClawState.OPEN);
    }
    public void intakecheck() throws InterruptedException{
        if (intaking) {

            if (
                    ((alliance == "red") && (colorSensor.sensorIsRed() || colorSensor.sensorIsYellow()))
                            ||  ((alliance == "blue") && (colorSensor.sensorIsBlue() || colorSensor.sensorIsYellow()))
            ) { //Intakes correct color
                Thread.sleep(200);
                if (
                        ((alliance == "red") && (colorSensor.sensorIsRed() || colorSensor.sensorIsYellow()))
                                ||  ((alliance == "blue") && (colorSensor.sensorIsBlue() || colorSensor.sensorIsYellow()))
                ) {
                    flywheel.setState(Flywheel.FlywheelDirection.STOP);
                    intaking = false;
                }
            }
            if (
                    ((alliance == "red") && colorSensor.sensorIsBlue())
                            ||  ((alliance == "blue") && colorSensor.sensorIsRed())
            ) {
                Thread.sleep(200);
                if (colorSensor.sensorIsBlue()) {
                    flywheel.setState(Flywheel.FlywheelDirection.OUT);
                    Thread.sleep(1000);
                    flywheel.setState(Flywheel.FlywheelDirection.IN);
                }
            }
        }
    }
    public void highBucket() {
        slides.setState(Slides.slideState.HIGH);
        bar.setState(Bar.BarState.BUCKET);
        wrist.setState(Wrist.wristState.BUCKET);
    }
    public void resetSlidesArm() {
        slides.setState(Slides.slideState.GROUND);
        bar.setState(Bar.BarState.WALL);
        wrist.setState(Wrist.wristState.TRANSFER);
    }
}