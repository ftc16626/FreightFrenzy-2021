package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;

@Autonomous(name = "Timebased", group = "Timebased")
public class OGTimeBased extends OpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    DriveTrain driveTrain = new DriveTrain();
    private double start = 0;

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


        if (runtime.seconds() > 0 && runtime.seconds() < 1) {
            driveTrain.strafeLeft(.8);
        }
        if (runtime.seconds() > 2 && runtime.seconds() < 5) {
            driveTrain.driveAll(.8);
        }
        driveTrain.driveAll(0);


    }

    @Override
    public void loop() {


    }


}


