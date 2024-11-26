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
    public static double p=0.00, i=0., d=0.000; //find values
    public static double f=0;
    double slidesPos;
    double power;
    public final int target = 0;
    private final double ticks_in_degree = 2786.2 / 360;

    Gamepad gp;
    DcMotorEx slideLeft;
    DcMotorEx slideRight;
    int sPos;
    @Override
    public void init() {
        controller = new PIDController(p,i,d);
        slideLeft = hardwareMap.get(DcMotorEx.class, "slideLeft");
        slideRight = hardwareMap.get(DcMotorEx.class, "slideRight");

        slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        sPos = 0;

        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    @Override
    public void loop() {
        controller.setPID(p,i,d);

        int leftslidesPos = slideLeft.getCurrentPosition();
        double pid = controller.calculate(leftslidesPos, target);
        double ff = 0;

        power = pid + ff;

        slideLeft.setPower(power);
        slideRight.setPower(power);

        telemetry.addData("pos ", leftslidesPos);
        telemetry.addData("target ", target);
        telemetry.update();
    }



    public int getePos() {
        return sPos;
    }
}