package org.firstinspires.ftc.teamcode.TestOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
//TRANSFER -- bar 0.933, claw 0.785, wrist 0.040
//HIGH BUCKET -- bar 0.318, claw 0.733, wrist 0.979
@TeleOp(name="bwc \uD83D\uDE1B\uD83D\uDE1B\uD83D\uDE1B\uD83D\uDE1B", group="Test OP")
public class barwristclaw extends LinearOpMode {

    public BarTest bar = new BarTest();
    public ClawTest claw = new ClawTest();
    public WristTest wrist = new WristTest();
    public Gamepad gp1;
    public Gamepad gp2;

    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        bar.init(hardwareMap);
        claw.init(hardwareMap);
        wrist.init(hardwareMap);

        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {

            bar.Loop(gp1, gp2);
            claw.Loop(gp1, gp2);
            wrist.Loop(gp1, gp2);
            telemetry.addData("rightBar.value", bar.getrightPos());
            telemetry.addData("leftBar.value", bar.getleftPos());
            telemetry.addData("claw.getCpos","%.5f", claw.getcPos());
            telemetry.addData("claw encoder pos","%.5f", claw.getClawgetPos());
            telemetry.update();
            telemetry.addData("wrist.pos", "%.3f", wrist.getwPos());
            telemetry.addData("port #", wrist.portNumber());
            telemetry.addData("dir", wrist.getDirection());

        }
    }
}