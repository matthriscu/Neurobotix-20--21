package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor left, right;
    boolean state = false;

    Intake(HardwareMap ahwMap) {
        left = ahwMap.get(DcMotor.class, "intakeLeft");
        right = ahwMap.get(DcMotor.class, "intakeRight");
        right.setDirection(DcMotor.Direction.REVERSE);
    }

    public void toggleState() {
        if(state) {
            state = false;
            left.setPower(1);
            right.setPower(1);
        }
        else {
            state = true;
            left.setPower(0);
            right.setPower(0);
        }
    }
}
