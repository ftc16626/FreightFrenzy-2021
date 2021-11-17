package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
@Autonomous(name = "Localization", group = "Localization")
public class LocalizationBased extends LinearOpMode {

    Carousel carousel = new Carousel();
    Intake intake = new Intake();

    Pose2d startPose = new Pose2d(-37,-51 );

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(startPose);












        Trajectory Traj1 = drive.trajectoryBuilder(startPose)
                .strafeRight(3)
                .back(-5)
                .build();

        Trajectory Traj15 = drive.trajectoryBuilder(Traj1.end())
                .back(-5)
                .build();

        Trajectory Traj2 = drive.trajectoryBuilder(Traj15.end())
                .strafeRight(24)
                .build();

        Trajectory Traj3 = drive.trajectoryBuilder(Traj2.end())
                .lineToConstantHeading(new Vector2d(-6, -60))
                .forward(60)
                .build();

        Trajectory Traj35 = drive.trajectoryBuilder(Traj3.end())
                .forward(60)
                .build();

        Trajectory Traj4 = drive.trajectoryBuilder(Traj35.end())
                .back(60)

                .build();
        Trajectory Traj45 = drive.trajectoryBuilder(Traj4.end())
                .strafeRight(10)

                .build();


        Trajectory Traj5 = drive.trajectoryBuilder(Traj45.end())
                .lineToSplineHeading(new Pose2d(-47, -34, Math.toRadians(180)))

                .build();
        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(Traj1);
        drive.followTrajectory(Traj15);
        carousel.getCarouselMotor().setPower(.3);
        sleep(1000);
        carousel.getCarouselMotor().setPower(0);
        drive.followTrajectory(Traj2);
        intake.getIntakeMotor().setPower(-.3);
        sleep(1000);
        intake.getIntakeMotor().setPower(0);
        drive.followTrajectory(Traj3);
        drive.followTrajectory(Traj35);
        intake.getIntakeMotor().setPower(-.3);
        sleep(1000);
        intake.getIntakeMotor().setPower(0);
        drive.followTrajectory(Traj4);
        drive.followTrajectory(Traj45);
        drive.followTrajectory(Traj5);
        intake.getIntakeMotor().setPower(-.3);
        sleep(1000);
        intake.getIntakeMotor().setPower(0);
    }
}
