package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Sensors;

@Autonomous(name = "Gyro-Distance Test", group = "Tests")
public class GyroTest extends LinearOpMode {

    Sensors sensors = new Sensors();

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        sensors.init(hardwareMap);
        waitForStart();

        while (!isStopRequested()) {
            telemetry.addData("Heading", Math.toDegrees(drive.getExternalHeading()));
            telemetry.addData("X-Coordinate", sensors.getRealXDistance());
            telemetry.addData("Y-Coordinate", sensors.getRealYDistance());
            telemetry.update();

        }

    }
}
