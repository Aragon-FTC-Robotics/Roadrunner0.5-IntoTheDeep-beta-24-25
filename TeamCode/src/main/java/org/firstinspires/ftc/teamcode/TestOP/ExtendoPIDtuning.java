package org.firstinspires.ftc.teamcode.TestOP;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ExtendoPIDtuning {

    //horizontal slides, wrist (servo), flywheels
    // vert slides, bar (motor), claw (servo), wrist (servo)

    private PIDController controller;
    public static double p=0.006, i=0.1, d=0.0005; //find values
    public static double f=-0.11;
    double extendoPos;
    double power;
    double pid;
    public int target = 0;
    private final double ticks_in_degree = 2786.2 / 360;

    Gamepad gp;
    DcMotorEx extendo;
    int ePos;

    public void init(HardwareMap hm) {
        controller = new PIDController(p,i,d);
        extendo = hm.get(DcMotorEx.class, "extendo");
        ePos = 0;
        extendo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        controller.setPID(p,i,d);

        extendoPos = extendo.getCurrentPosition();
        pid = controller.calculate(extendoPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        power = pid + ff;

        if (gp2.dpad_left) {
            ePos = 600;
        } else if (gp2.dpad_right) {
            ePos = 0;
        }
        moveToPos(ePos);
    }

    private void moveToPos(int position) {
        extendo.setTargetPosition(position);
        extendo.setPower(power); // change if its too high or low
        extendo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public int getePos() {
        return ePos;
    }
}