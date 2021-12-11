package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;

@Autonomous(name = "RedCarouselAuto", group = "Red")
public class LocalizationBasedRedCarousel extends LinearOpMode {


    Carousel carousel = new Carousel();

    @Override
    public void runOpMode() {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        carousel.init(hardwareMap);

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-39.0, -63.0, Math.toRadians(180.0)))
                .strafeRight(10.0)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .forward(20.0)
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .strafeRight(18.0)
                .build();

        Trajectory traj4 = drive.trajectoryBuilder(traj3.end())
                .forward(42.0)
                .build();

        Trajectory traj5 = drive.trajectoryBuilder(traj4.end())
                .back(144.0)
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);

        //carousel.rotate(true, 0.5);
        carousel.rotate(true, false);
        sleep(2500);
        carousel.rotate(false, false);
        //sleep(5000);
        drive.followTrajectory(traj3);
        drive.followTrajectory(traj4);
        drive.followTrajectory(traj5);
    }
}