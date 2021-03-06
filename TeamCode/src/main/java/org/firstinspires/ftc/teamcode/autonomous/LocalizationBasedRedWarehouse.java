package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;

@Autonomous(name = "RedWarehouseAuto", group = "Red")
public class LocalizationBasedRedWarehouse extends LinearOpMode {

Pose2d pose = new Pose2d(0.0, -63.0, Math.toRadians(180.0));

    @Override
    public void runOpMode() {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(0.0, -63.0, Math.toRadians(180.0)))
                .back(50.0)
                .build();

        waitForStart();


        if(isStopRequested()) return;


        drive.followTrajectory(traj1);
        //drive.followTrajectory(traj2);
    }
}