package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;


public class Lift {

    //Hardware
    public DcMotor liftMotor; //Port 1-CH
    private Servo tiltServoOne; //Port 0-CH
    private Servo tiltServoTwo; //Port 4-EH
    private CRServo gateServo;//Port 5-EH

    private Stage stage = Stage.DEFAULT;

    //Positions the motor should be at to raise the lift to the corresponding stage of the tower
    private final int stageOne = 0;
    private final int stageTwo = 0;
    private final int stageThree = 0;
    private final int startPosition = 0;





    public enum Stage {
        DEFAULT, STAGEZERO, STAGEONE, STAGETWO, STAGETHREE
    }

    public DcMotor getLiftMotor() {
        return liftMotor;
    }

    public void init(HardwareMap hardwareMap) {


        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
       //tiltServoOne = hardwareMap.get(Servo.class, "tiltServoOne");
        //tiltServoTwo = hardwareMap.get(Servo.class, "tiltServoTwo");
        //gateServo = hardwareMap.get(CRServo.class,"gateServo");

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }



    /*
    // Add lift values and lift parameter to prevent line breakage
    /*public void elevate(double power, double joyStickControl) {

        switch (stage) {

            case DEFAULT:
                //liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftMotor.setTargetPosition(startPosition);
                liftMotor.setPower(power);
                break;

            case STAGEZERO:
                //liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                liftMotor.setPower(joyStickControl);
                break;

            case STAGEONE:
                //liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftMotor.setTargetPosition(stageOne);
                liftMotor.setPower(power);
                break;

            case STAGETWO:
                //liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftMotor.setTargetPosition(stageTwo);
                liftMotor.setPower(power);

            case STAGETHREE:
                //liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftMotor.setTargetPosition(stageThree);
                liftMotor.setPower(power);
                break;

        }

    }
    */

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

    /*public void toggleTilt(boolean button, boolean changed, double position, double targetPosition) {
        if(button && !changed) {
            if(position == 0) {
                position = targetPosition;
            }
            else{
                position = 0;
            }
            tiltServoOne.setPosition(-position);
            tiltServoTwo.setPosition(position);
            changed = true;
        } else if(!button) changed = false;
    }
    */
/*
    public void toggleGate(double rightsticky){
        gateServo.setPower(rightsticky);

    }

*/

/*
    public Servo getTiltServoOne() {
        return tiltServoOne;
    }
*/
}
