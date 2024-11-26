package org.firstinspires.ftc.teamcode.TestOP;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
@Config
@TeleOp
public class ExtendoPIDtuning extends OpMode {

    //horizontal slides, wrist (servo), flywheels
    // vert slides, bar (motor), claw (servo), wrist (servo)

    private PIDController controller;
    public static double p=0.006, i=0.1, d=0.0005; //find values
    public static double f=-0.11;
    double extendoPos;
    double power;
    public int target = 0;
    private final double ticks_in_degree = 2786.2 / 360;

    Gamepad gp;
    DcMotorEx extendo;
    int ePos;
    @Override
    public void init() {
        controller = new PIDController(p,i,d);
        extendo = hardwareMap.get(DcMotorEx.class, "extendo");
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        ePos = 0;
        extendo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    @Override
    public void loop() {
        controller.setPID(p,i,d);

        int extendoPos = extendo.getCurrentPosition();
        double pid = controller.calculate(extendoPos, target);
        double ff = 0;

        power = pid + ff;

        extendo.setPower(power);
        telemetry.addData("pos ", extendoPos);
        telemetry.addData("target ", target);
        telemetry.update();
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