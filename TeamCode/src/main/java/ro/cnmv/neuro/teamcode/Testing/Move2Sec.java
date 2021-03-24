package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@Autonomous(name="Move 2 Sec", group="Autonomous")
public class Move2Sec extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);
        robot.drivetrain.leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.drivetrain.rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.drivetrain.leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.drivetrain.rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.drivetrain.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        robot.drivetrain.leftFront.setPower(1);
        robot.drivetrain.rightFront.setPower(1);
        robot.drivetrain.leftRear.setPower(1);
        robot.drivetrain.rightRear.setPower(1);
        sleep(2000);
        robot.drivetrain.leftFront.setPower(0);
        robot.drivetrain.rightFront.setPower(0);
        robot.drivetrain.leftRear.setPower(0);
        robot.drivetrain.rightRear.setPower(0);

        telemetry.addData("Left Front: ", robot.drivetrain.leftFront.getCurrentPosition());
        telemetry.addData("Right Front: ", robot.drivetrain.rightFront.getCurrentPosition());
        telemetry.addData("Left Rear: ", robot.drivetrain.leftRear.getCurrentPosition());
        telemetry.addData("Right Rear: ", robot.drivetrain.rightRear.getCurrentPosition());
        telemetry.update();

        sleep(50000);
    }
}
