package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

@Autonomous(name="Move/Turn Test", group="Autonomous")
public class MoveTurnTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);

        waitForStart();

        robot.drivetrain.Turn(45, 1);
        robot.drivetrain.Turn(-90, 1);
        robot.drivetrain.Turn(135, 1);
        robot.drivetrain.Turn(180, 1);
    }
}
