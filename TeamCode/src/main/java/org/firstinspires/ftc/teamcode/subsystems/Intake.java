package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class Intake {

    private DcMotor intakeMotor; //Port



    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorConfigurationType motorConfigurationType = intakeMotor.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(1);
        intakeMotor.setMotorType(motorConfigurationType);

        intakeMotor.setPower(0);
    }

    //[button1]: forwards (intake) [button2]: backwards (outtake)
    public void rotateIntake(boolean button1, boolean button2, double power) {
        if (button1) {
            intakeMotor.setPower(1);
        }
        if (button2) {
            intakeMotor.setPower(-1);
        }
        else {
            intakeMotor.setPower(0);
        }
    }

    public DcMotor getIntakeMotor() {
        return intakeMotor;
    }
}
