package org.firstinspires.ftc.teamcode.vision;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "BlueCarouselVisionAutonomous", group = "Vision")
public class BlueCarouselVisionAutonomous extends LinearOpMode {
    private double liftPos;
    private double bucketPos;
    private double distanceToHub;
    private double bucketDumpPos;

    Carousel carousel = new Carousel();
    Lift lift = new Lift();
    DriveTrain driveTrain = new DriveTrain();

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        carousel.init(hardwareMap);
        lift.init(hardwareMap);
        driveTrain.init(hardwareMap);

        final int width = 320;
        final int height = 240;
        boolean test = false;

        ElementDetector detector = new ElementDetector(width);
        OpenCvCamera camera;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(width, height, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
        camera.setPipeline(detector);
        //camera.startStreaming(width, height, OpenCvCameraRotation.UPRIGHT);
        while (!isStarted()) {

            if (detector.getPosition() == 1) {
                telemetry.addData("Position:", "LEFT");
                liftPos = .2;
                bucketPos = .7;
                distanceToHub = 11;
                bucketDumpPos = 1.0;
            }
            if (detector.getPosition() == 2) {
                telemetry.addData("Position:", "CENTER");
                liftPos = .3;
                bucketPos = .7;
                distanceToHub = 13;
                bucketDumpPos = .9;
            }
            if (detector.getPosition() == 3) {
                telemetry.addData("Position:", "RIGHT");
                liftPos = .5;
                bucketPos = .7;
                distanceToHub = 15;
                bucketDumpPos = .8;
            }

            lift.liftServo.setPosition(.8);
            lift.bucketServo.setPosition(.1);

            telemetry.addData("Lift Position:", liftPos);
            telemetry.addData("Bucket Position:", bucketPos);
            telemetry.update();
        }

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-39.0, -63.0, Math.toRadians(180.0)))
                .strafeRight(13.0)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .strafeLeft(23.0)
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .strafeRight(23.0)
                .build();

        Trajectory traj34 = drive.trajectoryBuilder(traj3.end())
                .back(10.0)
                .build();

        Trajectory traj4 = drive.trajectoryBuilder(traj34.end())
                .strafeRight(40.0)
                .build();

        Trajectory traj45 = drive.trajectoryBuilder(traj4.end())
                .back(distanceToHub)
                .build();

        Trajectory traj46 = drive.trajectoryBuilder(traj45.end())
                .forward(3)
                .build();

        Trajectory traj6 = drive.trajectoryBuilder(traj46.end())
                .strafeLeft(40.0)
                .build();

        Trajectory traj5 = drive.trajectoryBuilder(traj6.end())
                .forward(98.0)
                .build();

        waitForStart();

        //lift.liftServo.setPosition(liftPos);
        //lift.bucketServo.setPosition(bucketPos);

        sleep(2000);

        if (isStopRequested()) return;

        drive.followTrajectory(traj1);
        drive.turn(Math.toRadians(127.5));
        drive.followTrajectory(traj2);

        //carousel.rotate(true, 0.5);
        carousel.rotate(false, true);
        driveTrain.driveAll(.05);
        lift.liftServo.setPosition(liftPos);
        lift.bucketServo.setPosition(bucketPos);
        sleep(2500);
        carousel.rotate(false, false);
        //sleep(5000);
        drive.followTrajectory(traj3);
        drive.followTrajectory(traj34);

        //drive.turn(Math.toRadians(135.0));
        sleep(1000);
        drive.followTrajectory(traj4);

        drive.followTrajectory(traj45);
        lift.bucketServo.setPosition(bucketDumpPos);
        sleep(1000);
        drive.followTrajectory(traj46);
        drive.turn(Math.toRadians(-135.0));
        lift.liftServo.setPosition(.8);
        lift.bucketServo.setPosition(.1);
        sleep(1000);
        //drive.followTrajectory(traj6);
        drive.followTrajectory(traj5);
    }
}
