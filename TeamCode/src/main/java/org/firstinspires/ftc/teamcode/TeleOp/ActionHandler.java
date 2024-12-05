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
        TRANSFER_STAGE_1,
        TRANSFER_STAGE_2,
        TRANSFER_STAGE_3,
        CLIP2_STAGE_1,
        CLIP2_STAGE_2
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

        // Bucket high
        if (gp2.dpad_up) {
            highBucket();
        }

        // Reset slides and arm
        if (gp2.dpad_down) {
            resetSlidesArm();
        }

        // Clip up
        if (gp2.b) {
            Clip1();
        }

        // Clip down
        if (gp2.y) {
            startClip2();
        }

        // Wall pickup
        if (gp2.a) {
            claw.setState(Claw.ClawState.OPEN);
            bar.setState(Bar.BarState.WALL);
            wrist.setState(Wrist.wristState.WALL);
        }

        // Transfer
        if (gp1.b) {
            startTransfer();
        }

        // Intake wrist down
        if (gp1.x) {
            intakeWrist.setState(IntakeWrist.iwristState.OUT);
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
                if (elapsedMs >= 250) {
                    flywheel.setState(Flywheel.FlywheelDirection.STOP);
                    bar.setState(Bar.BarState.TRANSFER);
                    wrist.setState(Wrist.wristState.TRANSFER);
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
            default:
                break;
        }
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
