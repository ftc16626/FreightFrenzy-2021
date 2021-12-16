package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Carousel {


    private final double carouselPower = .5;
    private DcMotor carouselMotor; //Port

    public void init(HardwareMap hardwareMap) {

        carouselMotor = hardwareMap.get(DcMotor.class, "carouselMotor");

        carouselMotor.setPower(0);
    }

    //[button1]: rotate counterclockwise [button2]: rotate clockwise
    public void rotate(boolean button1, boolean button2) {
        if (button1) {
            carouselMotor.setPower(-carouselPower);
        } else if (button2) {
            carouselMotor.setPower(carouselPower);
        } else {
            carouselMotor.setPower(0);
        }
    }

    public DcMotor getCarouselMotor() {
        return carouselMotor;
    }
}
