package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class Intake {

    private DcMotor intakeMotor; //Port
    private double intakePower = 1;


    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        //Runs intake using encoder and the percentage of its max rpm it can reach
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorConfigurationType motorConfigurationType = intakeMotor.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(1);
        intakeMotor.setMotorType(motorConfigurationType);

        intakeMotor.setPower(0);
    }

    //[button1]: forwards (intake) [button2]: backwards (outtake)
    public void rotateIntake(boolean button1, boolean button2, double power) {
        if (button1) {
            intakeMotor.setPower(intakePower);
        }
        if (button2) {
            intakeMotor.setPower(-intakePower);
        }
        else {
            intakeMotor.setPower(0);
        }
    }

    public DcMotor getIntakeMotor() {
        return intakeMotor;
    }
}
