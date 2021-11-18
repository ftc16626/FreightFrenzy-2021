package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;

public class LocalizationBasedRedCarousel extends LinearOpMode {

    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    Carousel carousel = new Carousel();

    @Override
    public void runOpMode() {

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-39.0, -63.0, Math.toRadians(90.0)))
                .forward(3.0)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .strafeLeft(14.0)
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .lineToLinearHeading(new Pose2d(-12.0,-42.0,Math.toRadians(-90.0)))
                .build();

        Trajectory traj4 = drive.trajectoryBuilder(traj3.end())
                .lineToLinearHeading(new Pose2d(12.0,-42.0,Math.toRadians(0.0)))
                .build();

        Trajectory traj5 = drive.trajectoryBuilder(traj4.end())
                .forward(36.0)
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);
        carousel.rotate(true, 0.5);

        drive.followTrajectory(traj3);


        drive.followTrajectory(traj4);
        drive.followTrajectory(traj5);
    }
}