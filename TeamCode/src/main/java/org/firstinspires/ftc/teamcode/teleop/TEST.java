package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "TEST", group = "Teleop")
public class TEST extends LinearOpMode {
    private BNO055IMU imu;
    //SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    boolean blue = false;
    boolean changed = false; //Outside of loop()
    boolean changed2 = false; //Outside of loop()
    double power = .5;




    @Override
    public void runOpMode() throws InterruptedException {

        imu = hardwareMap.get(BNO055IMU.class, "imu 1");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        while (!isStarted()) {
            swapTeleopGpadOne();
            swapTeleopGpadTwo();
            telemetry.addData("POWER", power);
            telemetry.update();
        }

        //Code to run after START is pressed
        while (!isStopRequested()) {




            swapTeleopGpadOne();
            swapTeleopGpadTwo();

            telemetry.addData("POWER", power);
            telemetry.addData("calibration status", imu.getCalibrationStatus());
            telemetry.addData("heading", Math.toDegrees(imu.getAngularOrientation().firstAngle));

            telemetry.update();

        }
    }

    private void swapTeleopGpadOne() {
        if(gamepad1.guide && !changed) {
            if(power == .5) {
                power = -.5;
            }
            else{
                power = .5;
            }
            changed = true;
        } else if(!gamepad1.guide) changed = false;
    }
    private void swapTeleopGpadTwo() {
        if(gamepad2.guide && !changed2) {
            if(power == .5) {
                power = -.5;
            }
            else{
                power = .5;
            }
            changed2 = true;
        } else if(!gamepad2.guide) changed2 = false;
    }

}
