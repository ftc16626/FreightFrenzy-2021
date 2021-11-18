package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;

@Autonomous(name = "Timebased", group = "Timebased")
public class OGTimeBased extends OpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private double start = 0;

    DriveTrain driveTrain = new DriveTrain();






    public double duration(double duration) {
        start += duration;
        return start;
    }

    @Override
    public void init() {
        driveTrain.init(hardwareMap);
    }

    @Override
    public void start() {
        runtime.reset();








    }

    @Override
    public void loop() {
        if (runtime.seconds() > start && runtime.seconds() < duration(.5)) {
            driveTrain.driveAll(.3);
        }

        else if (runtime.seconds() > start && runtime.seconds() < duration(.5)) {
            driveTrain.driveAll(0);
        }

        if (runtime.seconds() > start && runtime.seconds() < duration(.5)) {
            driveTrain.strafeLeft(.3);
        }

        else if (runtime.seconds() > start && runtime.seconds() < duration(.5)) {
            driveTrain.strafeLeft(0);
        }

        if (runtime.seconds() > start && runtime.seconds() < duration(.5)) {
            driveTrain.rotateRight(.3);
        }

        else if (runtime.seconds() > start && runtime.seconds() < duration(.5)) {
            driveTrain.rotateRight(0);
        }

        if (runtime.seconds() > start && runtime.seconds() < duration(.5)) {
            driveTrain.driveAll(.3);
        }

        else if (runtime.seconds() > start && runtime.seconds() < duration(.5)) {
            driveTrain.rotateRight(0);
        }








    }

}
