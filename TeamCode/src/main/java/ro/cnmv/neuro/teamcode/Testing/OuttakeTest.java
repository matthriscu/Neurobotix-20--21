package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Hardware.Outtake;

@Autonomous
public class OuttakeTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Outtake outtake = new Outtake(hardwareMap);

        waitForStart();

        while(opModeIsActive()) {
            outtake.left.setPower(1);
            outtake.right.setPower(1);
        }
    }
}
