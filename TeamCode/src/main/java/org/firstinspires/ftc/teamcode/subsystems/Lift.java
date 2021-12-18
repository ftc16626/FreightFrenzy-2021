package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;


public class Lift {

    //Hardware
    public Servo liftServo; //Port 1-CH
    public Servo bucketServo; //Port 1-CH
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

    private double position = .8;
    public double bucketPosition = .1;
    private int changed = 0;

    public void elevate(boolean a, boolean b, boolean y, boolean x, double left_stick_y, float intake) {
        if (a && changed != 1) {
            if (position != .5) {
                position = .5;

                bucketPosition = .4;
            }
            else {
                position = .8;
                bucketPosition = .1;
            }
            changed = 1;
        } else if (b && changed != 2) {
            if (position != 0.3) {
                position = 0.3;
                bucketPosition = .6;
            }
            else {
                position = .8;
                bucketPosition = .1;
            }
            changed = 2;
        } else if (y && changed != 3) {
            if (position != .15) {
                position = .15;
                bucketPosition = .8;
            }
            else {
                position = .8;
                bucketPosition = .1;
            }
            changed = 3;
        }else if (x && changed != 4) {
            if (bucketPosition != 1) {
                bucketPosition = 1;
            } else {
                if (position == .5) {
                    bucketPosition = .4;
                }

                if (position == .3) {
                    bucketPosition = .6;
                }

                if (position == .15) {
                    bucketPosition = .8;
                }
                if (position == .8) {
                    bucketPosition = .1;
                }

            }
            changed = 4;
        } else if (!a && !b && !y && !x) {

            changed = 0;

        }



            liftServo.setPosition(position);
            if (intake < 0) {
                bucketPosition = 0;

            } else if (!x) {
                if (position == .5) {
                    bucketPosition = .4;
                }

                if (position == .3) {
                    bucketPosition = .6;
                }

                if (position == .15) {
                    bucketPosition = .8;
                }
                if (position == .8) {
                    bucketPosition = .1;
                }
            }
            bucketServo.setPosition(bucketPosition);
        }
    

//    public enum Stage {
//        DEFAULT, STAGEZERO, STAGEONE, STAGETWO, STAGETHREE
//    }

//    public DcMotor getLiftMotor() {
//        return liftMotor;
//    }

    public void init(HardwareMap hardwareMap) {
        liftServo = hardwareMap.get(Servo.class, "liftServo");
        bucketServo = hardwareMap.get(Servo.class, "bucketServo");
       // bucketServo.setDirection(DcMotorSimple.Direction.REVERSE);

        //liftServo.setDirection(Servo.Direction.REVERSE);
        //tiltServoOne = hardwareMap.get(Servo.class, "tiltServoOne");
        //tiltServoTwo = hardwareMap.get(Servo.class, "tiltServoTwo");
        //gateServo = hardwareMap.get(CRServo.class,"gateServo");
    }
}
