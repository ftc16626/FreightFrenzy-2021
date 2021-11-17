package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;


@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {

    DriveTrain driveTrain = new DriveTrain();
    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    Intake intake = new Intake();
    Carousel carousel = new Carousel();
    Lift lift = new Lift();


    private boolean changed = false;
    private boolean changed2 = false;
    private double power = .5;


    @Override
    public void runOpMode() throws InterruptedException {

        //Initialization Phase
        driveTrain.init(hardwareMap);
        intake.init(hardwareMap);
        carousel.init(hardwareMap);

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


            //Gamepad 1 Controls
            driveTrain.setDrivePower(gamepad1.right_trigger, .3, -gamepad1.left_stick_y, gamepad1.left_stick_x * 1.5, gamepad1.right_stick_x);

            telemetry.addData("heading", drive.getExternalHeading());
            telemetry.update();

            //Gamepad 2 Controls

            intake.rotateIntake(gamepad2.a, gamepad1.x, 1);
            carousel.rotate(gamepad2.b, power);





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
