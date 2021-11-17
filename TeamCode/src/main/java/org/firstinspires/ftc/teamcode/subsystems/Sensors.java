package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

public class Sensors {

    private DistanceSensor distanceSensorX;
    private DistanceSensor distanceSensorY;
    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

    public void init(HardwareMap hardwareMap) {
        distanceSensorX = hardwareMap.get(DistanceSensor.class, "name");
        distanceSensorY = hardwareMap.get(DistanceSensor.class, "name");
    }

    public double getRealXDistance() {
        return distanceSensorX.getDistance(DistanceUnit.INCH) * Math.cos(drive.getExternalHeading());
    }

    public double getRealYDistance() {
        return distanceSensorY.getDistance(DistanceUnit.INCH) * Math.cos(Math.toDegrees(drive.getExternalHeading()));
    }
}


