package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.Stage.STAGEZERO;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.Stage.STAGEONE;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.Stage.STAGETWO;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.Stage.STAGETHREE;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {

    //Class instantiation
    

    DriveTrain driveTrain = new DriveTrain();

    Intake intake = new Intake();
    Carousel carousel = new Carousel();
    Lift lift = new Lift();


    //Button changed variables
    private boolean gPadOneGuide = false;
    private boolean gPadTwoGuide = false;
    private boolean dPadLeftChanged = false;
    private boolean dPadRightChanged = false;
    private boolean dPadUpChanged = false;
    private boolean dPadDownChanged = false;
    private boolean rightBumperChanged = false;

    //Power variables
    private double carouselPower = .5;
    private final double intakePower = 1;

    //Position variables
    private final double targetTiltPosition = 1;

    //Misc
    private final double slowPercentage = .3; //How much the wheels should be slowed by when the driver holds the right trigger


    @Override
    public void runOpMode() throws InterruptedException {

        //--------------------------- CODE THAT WILL BE RUN DURING INITIALIZATION BELOW ---------------------------\\
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
       driveTrain.init(hardwareMap);
        intake.init(hardwareMap);
        carousel.init(hardwareMap);
        lift.init(hardwareMap);

        //--------------------------- CODE THAT WILL BE LOOPED DURING INITIALIZATION BELOW ---------------------------\\

        while (!isStarted()) {

            //Check if the guide button on either controller has been pressed
            toggleGuide(gamepad1.guide, gPadOneGuide);
            toggleGuide(gamepad2.guide, gPadTwoGuide);

            //Telemetry
            if (gPadOneGuide || gPadTwoGuide) {
                telemetry.addData("Teleop", "BLUE");
            }

            telemetry.update();
        }

        //========================== CODE THAT WILL BE RUN AFTER INITIALIZATION BELOW ==========================\\

        //--------------------------- TELEOP LOOP ---------------------------\\

        while (!isStopRequested()) {


            //Gamepad 1 Controls
           // gamePadOneControls();

            //Gamepad 2 Controls
            gamePadTwoControls();
            lift.getLiftMotor().setPower(-gamepad2.right_stick_y*.65);

            //liftMotor.setMode.(DcMotor.runMode.RUN_WITHOUT_ENCODER);
            //liftMotor.setPower(-gamepad2.left_stick_y);

            //Any button input that needs to be checked (toggle buttons)
            getButtons();
          

            //Gamepad 1 Controls
            //driveTrain.setDrivePower(gamepad1.right_trigger, .3, -gamepad1.left_stick_y, gamepad1.left_stick_x * 1.5, gamepad1.right_stick_x);


            //Telemetry
           // telemetry.addData("heading", drive.getExternalHeading());
            telemetry.addData("Lift Position", lift.getLiftMotor().getCurrentPosition());

            if (gamepad1.dpad_down) {
                driveTrain.setDriveUsingEncoders();
                driveTrain.driveAll(.5);
                telemetry.addData("frontleft", driveTrain.frontLeft.getCurrentPosition());
            }

            telemetry.update();
        }
    }


    //--------------------------- METHODS  ---------------------------\\

    private void gamePadOneControls() {
        if(!gamepad1.left_bumper) driveTrain.setDrivePower(gamepad1.right_trigger, slowPercentage, -gamepad1.left_stick_y, gamepad1.left_stick_x * 1.5, gamepad1.right_stick_x);
        else driveTrain.setDrivePowerReversed(gamepad1.right_trigger, slowPercentage, -gamepad1.left_stick_y, gamepad1.left_stick_x * 1.5, gamepad1.right_stick_x);
    }

    private void gamePadTwoControls() {
        intake.rotateIntake(gamepad2.a, gamepad2.x, intakePower);
        carousel.rotate(gamepad1.b, gamepad1.y);

        //lift.elevate(.5, -gamepad2.left_stick_y);

    }

    private void getButtons() {
        toggleGuide(gamepad1.guide, gPadOneGuide);
        toggleGuide(gamepad2.guide, gPadTwoGuide);


        //lift.toggleStage(gamepad2.dpad_down, dPadDownChanged,STAGEZERO);
        //lift.toggleStage(gamepad2.dpad_left, dPadLeftChanged,STAGEONE);
        //lift.toggleStage(gamepad2.dpad_up, dPadUpChanged,STAGETWO);
        //lift.toggleStage(gamepad2.dpad_right, dPadRightChanged,STAGETHREE);
        //lift.toggleTilt(gamepad2.right_bumper, rightBumperChanged, lift.getTiltServoOne().getPosition(), targetTiltPosition);
       // lift.toggleGate(gamepad2.left_stick_y);

    }

    //Toggles carouselPower via the guide button on the controller
    private void toggleGuide(boolean button, boolean changed) {
        if(button && !changed) {
            if(carouselPower == .5) {
                carouselPower = -.5;
            }
            else{
                carouselPower = .5;
            }
            changed = true;
        } else if(!button) changed = false;
    }






}
