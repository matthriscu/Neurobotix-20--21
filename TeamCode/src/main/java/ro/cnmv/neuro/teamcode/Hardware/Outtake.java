package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    public DcMotor left, right;
    boolean state = false;

    public Outtake(HardwareMap ahwMap) {
        left = ahwMap.get(DcMotor.class, "outtakeLeft");
        right = ahwMap.get(DcMotor.class, "outtakeRight");
        right.setDirection(DcMotor.Direction.REVERSE);
    }

    public void toggleState() {
        if(state) {
            state = false;
            left.setPower(0.9);
            right.setPower(0.9);
        }
        else {
            state = true;
            left.setPower(0);
            right.setPower(0);
        }
    }
}