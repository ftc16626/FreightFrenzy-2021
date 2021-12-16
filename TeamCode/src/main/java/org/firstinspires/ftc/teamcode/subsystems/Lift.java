package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;


public class Lift {

    //Hardware
    public Servo liftServo; //Port 1-CH
    private Servo tiltServoOne; //Port 0-CH
    private Servo tiltServoTwo; //Port 4-EH
    private CRServo gateServo;//Port 5-EH

//    private Stage stage = Stage.DEFAULT;

    //Positions the motor should be at to raise the lift to the corresponding stage of the tower
    private final int stageOne = 0;
    private final int stageTwo = 0;
    private final int stageThree = 0;
    private final int startPosition = 0;

    public double getPosition() {
        return position;
    }

    private double position;
    private int changed = 0;

    public void elevate(boolean a, boolean b, boolean y, double left_stick_y) {
        if (a && changed != 1) {
            if (position != 0.5) position = 0.5;
            else position = 0;
            changed = 1;
        } else if (b && changed != 2) {
            if (position != 0.7) position = 0.7;
            else position = 0;
            changed = 2;
        } else if (y && changed != 3) {
            if (position != 1.0) position = 1.0;
            else position = 0;
            changed = 3;
        }/*
            else if(-gamepad1.left_stick_y > 0 && changed != 4){
                position = -gamepad1.left_stick_y * 3000;
                changed = 0;
            }
            */ else if (!a && !b && !y) {
            changed = 0;

        }

        if (left_stick_y != 0) liftServo.setPosition(left_stick_y);
        else liftServo.setPosition(position);
    }

//    public enum Stage {
//        DEFAULT, STAGEZERO, STAGEONE, STAGETWO, STAGETHREE
//    }

//    public DcMotor getLiftMotor() {
//        return liftMotor;
//    }

    public void init(HardwareMap hardwareMap) {
        liftServo = hardwareMap.get(Servo.class, "liftServo");
        //liftServo.setDirection(Servo.Direction.REVERSE);
        //tiltServoOne = hardwareMap.get(Servo.class, "tiltServoOne");
        //tiltServoTwo = hardwareMap.get(Servo.class, "tiltServoTwo");
        //gateServo = hardwareMap.get(CRServo.class,"gateServo");
    }
}
