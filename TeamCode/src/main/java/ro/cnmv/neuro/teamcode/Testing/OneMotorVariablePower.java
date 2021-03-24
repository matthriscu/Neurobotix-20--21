package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="One Motor 800", group="Autonomous")
public class OneMotorVariablePower extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");
        double power = 1;

        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.x && power > 0) {
                power -= 0.1;
                sleep(300);
            }
            if(gamepad1.b && power < 1) {
                power += 0.1;
                sleep(300);
            }
            motor.setPower(power);
            telemetry.addData("Power", power);
            telemetry.update();
        }
    }
}