package org.firstinspires.ftc.teamcode.TestOP;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
@Config
@TeleOp
public class SlidesPIDtuning extends OpMode {

    //horizontal slides, wrist (servo), flywheels
    // vert slides, bar (motor), claw (servo), wrist (servo)

    private PIDController controller;
    public static double p=0.006, i=0.1, d=0.0005; //find values
    public static double f=-0.11;
    double slidesPos;
    double power;
    public int target = 0;
    private final double ticks_in_degree = 2786.2 / 360;

    Gamepad gp;
    DcMotorEx leftSlide;
    DcMotorEx rightSlide;
    int sPos;
    @Override
    public void init() {
        controller = new PIDController(p,i,d);
        leftSlide = hardwareMap.get(DcMotorEx.class, "leftSlide");
        rightSlide = hardwareMap.get(DcMotorEx.class, "rightSlide");

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        sPos = 0;

        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    @Override
    public void loop() {
        controller.setPID(p,i,d);

        int leftslidesPos = leftSlide.getCurrentPosition();
        double pid = controller.calculate(leftslidesPos, target);
        double ff = 0;

        power = pid + ff;

        leftSlide.setPower(power);
        rightSlide.setPower(power);

        telemetry.addData("pos ", leftslidesPos);
        telemetry.addData("target ", target);
        telemetry.update();
    }

    private void moveToPos(int position) {
        leftSlide.setTargetPosition(position);
        rightSlide.setTargetPosition(position);

        leftSlide.setPower(power); // change if its too high or low
        rightSlide.setPower(power);
    }

    public int getePos() {
        return sPos;
    }
}