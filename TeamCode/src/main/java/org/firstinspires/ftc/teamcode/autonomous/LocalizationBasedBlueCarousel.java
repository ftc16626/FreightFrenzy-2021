package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;


import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "BlueCarouselAuto", group = "Blue")
public class LocalizationBasedBlueCarousel extends LinearOpMode {

    Carousel carousel = new Carousel();


    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        carousel.init(hardwareMap);

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-39.0, 66.0, Math.toRadians(180.0)))
                .strafeLeft(10.0)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .strafeLeft(20.0)
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .back(18.0)
                .build();

        Trajectory traj4 = drive.trajectoryBuilder(traj3.end())
                .back(120.0)
                .build();

        /*Trajectory traj6 = drive.trajectoryBuilder(traj5.end())
                .lineToLinearHeading(new Pose2d())
                .build();
        */

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(traj1);
        drive.turn(Math.toRadians(-90.0));
        drive.followTrajectory(traj2);

        //carousel.rotate(true, 0.5);
        carousel.rotate(true, false);
        sleep(2000);
        carousel.rotate(false, false);

        drive.followTrajectory(traj3);
        drive.turn(Math.toRadians(90));
        drive.followTrajectory(traj4);
        //drive.followTrajectory(traj5);
        //drive.followTrajectory(traj6);


    }


}
