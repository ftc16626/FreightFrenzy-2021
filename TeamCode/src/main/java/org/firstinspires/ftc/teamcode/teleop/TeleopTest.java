package org.firstinspires.ftc.teamcode.teleop;

import static org.firstinspires.ftc.teamcode.subsystems.Lift.Stage.STAGEONE;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.Stage.STAGETHREE;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.Stage.STAGETWO;
import static org.firstinspires.ftc.teamcode.subsystems.Lift.Stage.STAGEZERO;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

@TeleOp(name = "Teleoptest", group = "Teleop")
public class TeleopTest extends LinearOpMode {

    double carouselPower =.5;
    private boolean change = false;
    private float position = 0;
    Intake intake = new Intake();
    Lift lift  = new Lift();
    int changed = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        lift.init(hardwareMap);
        lift.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        while (!isStopRequested()) {

            if(gamepad1.a && changed != 1) {
                if (position != 1000) position = 1000;
                else position = 0;
                changed = 1;
            }
            else if(gamepad1.b && changed != 2){
                if (position != 2000) position = 2000;
                else position = 0;
                changed = 2;
            }
            else if(gamepad1.x && changed != 3){
                if (position != 3000) position = 3000;
                else position = 0;
                changed = 3;
            }/*
            else if(-gamepad1.left_stick_y > 0 && changed != 4){
                position = -gamepad1.left_stick_y * 3000;
                changed = 0;
            }
            */
            else if(!gamepad1.a && !gamepad1.b && !gamepad1.x){
                changed = 0;

            }

            if (-gamepad1.left_stick_y > 0) {
                lift.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                lift.liftMotor.setPower(-gamepad1.left_stick_y);
            } else {
                lift.liftMotor.setTargetPosition((int) position);
                lift.liftMotor.setPower(1);
                lift.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }



            telemetry.addData("dfghj", lift.liftMotor.getCurrentPosition());
            telemetry.addData("dfghj", position);
            telemetry.update();
        }

    }

}