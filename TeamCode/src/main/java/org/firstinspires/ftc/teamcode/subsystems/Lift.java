package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Lift {

    //Hardware
    private DcMotor liftMotor; //Port
    private Servo tiltServoOne; //Port
    private Servo tiltServoTwo; //Port

    private Stage stage;

    //Positions the motor should be at to raise the lift to the corresponding stage of the tower
    private final int stageOne = 0;
    private final int stageTwo = 0;
    private final int stageThree = 0;

    public enum Stage {
        DEFAULT, STAGEZERO, STAGEONE, STAGETWO, STAGETHREE
    }

    public void init(HardwareMap hardwareMap) {


        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        tiltServoOne = hardwareMap.get(Servo.class, "tiltServoOne");
        tiltServoTwo = hardwareMap.get(Servo.class, "tiltServoTwo");

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void elevate(double power, double joyStickControl) {

        switch (stage) {

            case DEFAULT:
                liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                liftMotor.setPower(0);
                break;

            case STAGEZERO:
                liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                liftMotor.setPower(joyStickControl);
                break;

            case STAGEONE:
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftMotor.setTargetPosition(stageOne);
                liftMotor.setPower(power);
                break;

            case STAGETWO:
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftMotor.setTargetPosition(stageTwo);
                liftMotor.setPower(power);

            case STAGETHREE:
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftMotor.setTargetPosition(stageThree);
                liftMotor.setPower(power);
                break;

        }

    }

    public void toggleStage(boolean button, boolean changed, Stage stageToElevateTo) {
        if(button && !changed) {
            if(stage != stageToElevateTo) {
                stage = stageToElevateTo;
            }
            else{
                stage = Stage.DEFAULT;
            }
            changed = true;
        } else if(!button) changed = false;
    }

    public void toggleTilt(boolean button, boolean changed, double position, double targetPosition) {
        if(button && !changed) {
            if(position == 0) {
                position = targetPosition;
            }
            else{
                position = 0;
            }
            tiltServoOne.setPosition(position);
            tiltServoTwo.setPosition(position);
            changed = true;
        } else if(!button) changed = false;
    }

    public Servo getTiltServoOne() {
        return tiltServoOne;
    }

}
