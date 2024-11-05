package org.firstinspires.ftc.teamcode.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Bar {
    Servo barServo;
    enum BarState {TRANSFER, WALL, BUCKET, CLIPUP, CLIPDOWN}
    double pos;
    double transferpos = 0;
    double wallpos = 0;
    double bucketpos = 0;
    double clipuppos = 0;
    double clipdownpos = 0;

    BarState currentBarState;
    public void init(HardwareMap hm) {
        barServo = hm.get(Servo.class, "bar");
//        barServo.setDirection(Servo.Direction.REVERSE);
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.a) { //ALL OF THESE ARE PLACEHOLDERS type baet
            pos = transferpos;
        } else if (gp2.dpad_up || gp2.dpad_right) {
            pos = bucketpos;
        } else if (gp2.b) {
            pos = wallpos;
        } else if (gp2.a) {
            pos = clipuppos;
        } else if (gp2.y) {
            pos = clipdownpos;
        } else if (gp2.x) {
            pos = transferpos;
        }
        barServo.setPosition(pos);
    }
    public double getPos() {return pos;}
}
