package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

@TeleOp(name="Field Centric Drive", group="Linear Opmode")
public class FieldCentricDrive extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);
        double joystickAngle, gamepad1LeftStickX, gamepad1LeftStickY, gamepad1RightStickX;

        waitForStart();

        while(opModeIsActive()) {
            gamepad1LeftStickX = gamepad1.left_stick_x;
            gamepad1LeftStickY = -gamepad1.left_stick_y;
            gamepad1RightStickX = gamepad1.right_stick_x;
            if(gamepad1LeftStickX != 0 || gamepad1LeftStickY != 0) {
                robot.imu.currentOrientation = robot.imu.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
                joystickAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x);
                if(gamepad1.left_bumper)
                    robot.drivetrain.Drive(joystickAngle + robot.imu.getCorrection(), gamepad1RightStickX, 0.66);
                else if(gamepad1.right_bumper)
                    robot.drivetrain.Drive(joystickAngle + robot.imu.getCorrection(), gamepad1RightStickX, 0.33);
                else
                    robot.drivetrain.Drive(joystickAngle + robot.imu.getCorrection(), gamepad1RightStickX, 1);
            }
            else if(gamepad1RightStickX != 0) {
                if(gamepad1.left_bumper)
                    robot.drivetrain.TurnInPlace(0.66, gamepad1RightStickX);
                else if(gamepad1.right_bumper)
                    robot.drivetrain.TurnInPlace(0.33, gamepad1RightStickX);
                else
                    robot.drivetrain.TurnInPlace(1, gamepad1RightStickX);
            }
            else {
                robot.drivetrain.leftFront.setPower(0); robot.drivetrain.rightFront.setPower(0);
                robot.drivetrain.leftRear.setPower(0);  robot.drivetrain.rightRear.setPower(0);
            }

            if(gamepad1.x)
                robot.intake.toggleState();
            if(gamepad1.b)
                robot.outtake.toggleState();
        }
    }
}

