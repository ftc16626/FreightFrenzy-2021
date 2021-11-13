package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private DcMotor intakeMotor; //Port



    public void init(HardwareMap hardwareMap) {

        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        intakeMotor.setPower(0);
    }

    //[button1]: forwards (intake) [button2]: backwards (outtake)
    public void rotateIntake(boolean button1, boolean button2, double power) {
        if (button1) {
            intakeMotor.setPower(power);
        }
        if (button2) {
            intakeMotor.setPower(-power);
        }
            else {
            intakeMotor.setPower(0);
        }
    }

    public DcMotor getIntakeMotor() {
        return intakeMotor;
    }
}
