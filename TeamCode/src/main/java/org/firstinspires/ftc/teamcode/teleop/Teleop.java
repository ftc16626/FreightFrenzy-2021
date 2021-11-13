package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;


@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {

    DriveTrain driveTrain = new DriveTrain();
    Intake intake = new Intake();
    Carousel carousel = new Carousel();
    Lift lift = new Lift();
    boolean blue = false;

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialization Phase
        driveTrain.init(hardwareMap);
        intake.init(hardwareMap);
        carousel.init(hardwareMap);

        waitForStart();


        //Code to run after START is pressed
        while (!isStopRequested()) {


            //Gamepad 1 Controls
            driveTrain.setDrivePower(gamepad1.right_trigger, .3, -gamepad1.left_stick_y, gamepad1.left_stick_x * 1.5, gamepad1.right_stick_x);


            //Gamepad 2 Controls
            if(gamepad2.y && blue) blue = false;
            else if (gamepad2.y) blue = true;
            intake.rotate(gamepad2.a, gamepad2.x, .5);
            if(blue) carousel.rotate(gamepad2.b, -.5);
            else carousel.rotate(gamepad2.b, -.5);




        }
    }
}
