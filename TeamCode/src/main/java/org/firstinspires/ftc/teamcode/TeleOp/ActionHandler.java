package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.TeleOp.Mechanisms.*;

import java.util.concurrent.TimeUnit;

public class ActionHandler {
    private Slides slides;
    private Extendo extendo;
    private Bar bar;
    private Wrist wrist;
    private Flywheel flywheel;
    private Claw claw;
    private IntakeWrist intakeWrist;
    private Colorsensor colorSensor;
    private boolean intaking = false;
    private String alliance;

    private ElapsedTime timer = new ElapsedTime();
    private ActionState currentActionState = ActionState.IDLE;

    enum ActionState {
        IDLE,
        TRANSFER_STAGE_1, //intakewrist in BEFORE, stage1: claw close when wait is done
        TRANSFER_STAGE_2, //flywheel out
        TRANSFER_STAGE_3, //barwrist transfer, flywheel stop
        TRANSFER_STAGE_4, //clawopen
        CLIP2_STAGE_1, //delay to claw open
        HIGHBUCKET_STAGE_1, //slides up BEFORE
        HIGHBUCKET_STAGE_2, //barwrist bucket
//        HIGHBUCKET_STAGE_3 //claw open and extendo medium
        //slides up -> stage 1 -> barwrist -> stage2 ... etc
        //each case statement has a delay and then runs an action after....
        SLIDESDOWN_STAGE_1, //extendo in
        EJECT_STAGE_1,
        TUAH_STAGE_1, //flywehl out
        TUAH_STAGE_2 //wheel STOP
    }

    public void init(Slides s, Extendo e, Bar b, Wrist w, Flywheel f, Claw c, IntakeWrist iw, Colorsensor cs, String alliance) {
        slides = s;
        extendo = e;
        bar = b;
        wrist = w;
        flywheel = f;
        claw = c;
        intakeWrist = iw;
        colorSensor = cs;
        this.alliance = alliance;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        handleTimedActions();

        // Intake action
        if (gp1.y && !intaking) {
            intaking = true;
            flywheel.setState(Flywheel.FlywheelDirection.IN);
        }
        intakecheck();

        // Initiate transfer
        if (gp1.left_bumper) {
            startTransfer();
        }

        // Intake wrist out
        if (gp1.right_bumper) {
            intakeWrist.setState(IntakeWrist.iwristState.OUT);
        }
        if (gp1.left_trigger > 0.5) {
            Hawk();
        }
        if (gp1.right_trigger < 0.5) {
            startTuah();
        }

        // Bucket high
        if (gp2.dpad_up) {
            startHighBucket();
        }

        // Reset slides and arm
        if (gp2.dpad_down) {
            startSlidesDown();
        }

        if (gp2.x) {
            Clip1();
        }

        if (gp2.y) {
            startClip2();
        }

        // Wall pickup
        if (gp2.b) {
            claw.setState(Claw.ClawState.OPEN);
            bar.setState(Bar.BarState.WALL);
            wrist.setState(Wrist.wristState.WALL);
        }
        if (gp2.a) {
            claw.setState(Claw.ClawState.OPEN);
            bar.setState(Bar.BarState.TRANSFER);
            wrist.setState(Wrist.wristState.TRANSFER);
        }
    }

    private void handleTimedActions() {
        long elapsedMs = timer.time(TimeUnit.MILLISECONDS);

        switch (currentActionState) {
            case TRANSFER_STAGE_1:
                if (elapsedMs >= 500) {
                    intakeWrist.setState(IntakeWrist.iwristState.IN);
                    currentActionState = ActionState.TRANSFER_STAGE_2;
                    timer.reset();
                }
                break;
            case TRANSFER_STAGE_2:
                if (elapsedMs >= 500) {
                    flywheel.setState(Flywheel.FlywheelDirection.OUT);
                    currentActionState = ActionState.TRANSFER_STAGE_3;
                    timer.reset();
                }
                break;
            case TRANSFER_STAGE_3:
                if (elapsedMs >= 500) {
                    flywheel.setState(Flywheel.FlywheelDirection.STOP);
                    bar.setState(Bar.BarState.TRANSFER);
                    wrist.setState(Wrist.wristState.TRANSFER);
                    currentActionState = ActionState.TRANSFER_STAGE_4;
                    timer.reset();
                }
                break;
            case TRANSFER_STAGE_4:
                if (elapsedMs >= 250) {
                    claw.setState(Claw.ClawState.OPEN);
                    currentActionState = ActionState.IDLE;
                }
                break;
            case CLIP2_STAGE_1:
                if (elapsedMs >= 200) {
                    claw.setState(Claw.ClawState.OPEN);
                    currentActionState = ActionState.IDLE;
                }
                break;
            case HIGHBUCKET_STAGE_1:
                if (elapsedMs >= 700) {
                    bar.setState(Bar.BarState.BUCKET);
                    wrist.setState(Wrist.wristState.BUCKET);
                    currentActionState = ActionState.IDLE;
                }
                break;
            case SLIDESDOWN_STAGE_1:
                if (elapsedMs >= 2000) {
                    extendo.setPos(extendo.min);
                    currentActionState = ActionState.IDLE;
                }
                break;
            case EJECT_STAGE_1:
                if (elapsedMs >= 500) {
                    claw.setState(Claw.ClawState.CLOSE);
                    currentActionState = ActionState.IDLE;
                }
                break;
            case TUAH_STAGE_1:
                if (elapsedMs >= 500) {
                    flywheel.setState(Flywheel.FlywheelDirection.OUT);
                    currentActionState = ActionState.TUAH_STAGE_2;
                    timer.reset();
                }
                break;
            case TUAH_STAGE_2:
                if (elapsedMs >= 1000) {
                    flywheel.setState(Flywheel.FlywheelDirection.STOP);
                    currentActionState = ActionState.IDLE;
                }
                break;
            default:
                currentActionState = ActionState.IDLE;
                break;
        }
    }
    private void startEject() {
        bar.setState(Bar.BarState.EJECT);
        wrist.setState(Wrist.wristState.EJECT);
        currentActionState = ActionState.EJECT_STAGE_1;
        timer.reset();
    }
    private void Hawk() {
        intakeWrist.setState(IntakeWrist.iwristState.IN);
    }
    private void startTuah() {
        intakeWrist.setState(IntakeWrist.iwristState.OUT);
        currentActionState = ActionState.TUAH_STAGE_1;
        timer.reset();
    }
    private void startHighBucket() {
        slides.setState(Slides.slideState.HIGH);
        currentActionState = ActionState.HIGHBUCKET_STAGE_1;
        timer.reset();
    }
    private void startSlidesDown() {
        extendo.setPos(extendo.MED);
        bar.setState(Bar.BarState.NEUTRAL);
        wrist.setState(Wrist.wristState.TRANSFER);
        slides.setState(Slides.slideState.GROUND);
        currentActionState = ActionState.SLIDESDOWN_STAGE_1;
        timer.reset();
    }
    private void startTransfer() {
        claw.setState(Claw.ClawState.CLOSE);
        currentActionState = ActionState.TRANSFER_STAGE_1;
        timer.reset();
    }

    private void startClip2() {
        bar.setState(Bar.BarState.CLIP2);
        wrist.setState(Wrist.wristState.CLIP2);
        currentActionState = ActionState.CLIP2_STAGE_1;
        timer.reset();
    }

    public void Clip1() {
        bar.setState(Bar.BarState.CLIP1);
        wrist.setState(Wrist.wristState.CLIP1);
    }

    public void intakecheck() {
        if (intaking) {
            boolean correctColor = (alliance.equals("red") && (colorSensor.sensorIsRed() || colorSensor.sensorIsYellow()))
                    || (alliance.equals("blue") && (colorSensor.sensorIsBlue() || colorSensor.sensorIsYellow()));

            if (correctColor) {
                flywheel.setState(Flywheel.FlywheelDirection.STOP);
                intaking = false;
            } else {
                boolean wrongColor = (alliance.equals("red") && colorSensor.sensorIsBlue())
                        || (alliance.equals("blue") && colorSensor.sensorIsRed());

                if (wrongColor) {
                    flywheel.setState(Flywheel.FlywheelDirection.OUT);
                    intaking = false; // Stops after correcting
                }
            }
        }
    }




}
