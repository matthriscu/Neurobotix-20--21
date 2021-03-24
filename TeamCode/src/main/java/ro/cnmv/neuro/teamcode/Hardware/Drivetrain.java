package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    public DcMotor leftFront, rightFront, leftRear, rightRear;
    static final double speedLimit = 0.7, wheelCircumference = 12.368475, ticksPerMotorTurn = 767.2, robotDiagonal = 27;

    Drivetrain(HardwareMap ahwMap) {
        leftFront = ahwMap.get(DcMotor.class, "leftFront"); rightFront = ahwMap.get(DcMotor.class, "rightFront");
        leftRear = ahwMap.get(DcMotor.class, "leftRear");   rightRear = ahwMap.get(DcMotor.class, "rightRear");
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);    rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);     rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void Drive(double angle, double rotation, double multiplier) {
        double y = Math.sin(angle), x = Math.cos(angle),
        leftFrontPower = y + x + rotation,  rightFrontPower = y - x - rotation,
        leftRearPower = y - x + rotation,   rightRearPower = y + x - rotation,
        temp = Math.max(Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower)), Math.max(Math.abs(leftRearPower), Math.abs(rightRearPower)));
        leftFrontPower /= temp; rightFrontPower /= temp;
        leftRearPower /= temp;  rightRearPower /= temp;
        leftFront.setPower(leftFrontPower * multiplier * speedLimit); rightFront.setPower(rightFrontPower * multiplier * speedLimit);
        leftRear.setPower(leftRearPower * multiplier * speedLimit);   rightRear.setPower(rightRearPower * multiplier * speedLimit);
    }

    public void TurnInPlace(double multiplier, double direction) {
        if(direction > 0) {
            leftFront.setPower(multiplier * speedLimit);  rightFront.setPower(-multiplier * speedLimit);
            leftRear.setPower(multiplier * speedLimit);   rightRear.setPower(-multiplier * speedLimit);
        }
        else {
            leftFront.setPower(-multiplier * speedLimit);  rightFront.setPower(multiplier * speedLimit);
            leftRear.setPower(-multiplier * speedLimit);   rightRear.setPower(multiplier * speedLimit);
        }
    }

    public void Move(double distance, int angle, double power) {
        int ticksNeeded = (int)(ticksPerMotorTurn * distance / wheelCircumference), mainDiagonalSign = 1, secondaryDiagonalSign = 1;
        switch(angle) {
            case 0:
                secondaryDiagonalSign = -1;
                break;
            case 180:
                mainDiagonalSign = -1;
                break;
            case 270:
                mainDiagonalSign = secondaryDiagonalSign = -1;
                break;
        }

        leftFront.setTargetPosition(leftFront.getCurrentPosition() + mainDiagonalSign * ticksNeeded);       rightFront.setTargetPosition(rightFront.getCurrentPosition() + secondaryDiagonalSign * ticksNeeded);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() + secondaryDiagonalSign * ticksNeeded);    rightRear.setTargetPosition(rightRear.getCurrentPosition() + mainDiagonalSign * ticksNeeded);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION); rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);  rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(power);  rightFront.setPower(power);
        leftRear.setPower(power);   rightRear.setPower(power);

        while(leftFront.isBusy() && rightFront.isBusy() && leftRear.isBusy() && rightRear.isBusy());

        leftFront.setPower(0);  rightFront.setPower(0);
        leftRear.setPower(0);   rightRear.setPower(0);
    }

    public void Turn(double angle, double power) {
        angle *= 0.01745329251994329576923690768489;
        int ticksNeeded = (int)(ticksPerMotorTurn * (robotDiagonal / 2 * angle) / wheelCircumference);
        if(angle > 0) {
            leftFront.setTargetPosition(leftFront.getCurrentPosition() + ticksNeeded);  rightFront.setTargetPosition(rightFront.getCurrentPosition() - ticksNeeded);
            leftRear.setTargetPosition(leftRear.getCurrentPosition() + ticksNeeded);    rightRear.setTargetPosition(rightRear.getCurrentPosition() - ticksNeeded);
        }
        else {
            leftFront.setTargetPosition(leftFront.getCurrentPosition() - ticksNeeded);  rightFront.setTargetPosition(rightFront.getCurrentPosition() + ticksNeeded);
            leftRear.setTargetPosition(leftRear.getCurrentPosition() - ticksNeeded);    rightRear.setTargetPosition(rightRear.getCurrentPosition() + ticksNeeded);
        }

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION); rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);  rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(power);  rightFront.setPower(power);
        leftRear.setPower(power);   rightRear.setPower(power);

        while(leftFront.isBusy() && rightFront.isBusy() && leftRear.isBusy() && rightRear.isBusy());

        leftFront.setPower(0);  rightFront.setPower(0);
        leftRear.setPower(0);   rightRear.setPower(0);
    }
}
