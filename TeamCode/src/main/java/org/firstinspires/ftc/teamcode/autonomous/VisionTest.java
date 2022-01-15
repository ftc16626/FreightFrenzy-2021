package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.vision.ElementDetector;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "VisionTest", group = "Vision")
public class VisionTest extends LinearOpMode {

    public boolean test = false;
    @Override
    public void runOpMode() throws InterruptedException {

        final int width = 320;
        final int height = 240;

        ElementDetector detector = new ElementDetector(width);
        OpenCvCamera camera;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(width, height, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
        camera.setPipeline(detector);
        //camera.startStreaming(width, height, OpenCvCameraRotation.UPRIGHT);
while (!isStarted()) {
    telemetry.addData("Left", test);
    telemetry.addData("Right", test);

    telemetry.addData("lastResult", detector.getLastResult());
    telemetry.addData("Test", detector.getTest());

    //telemetry.addData("BoundRect", detector.boundRect.length);
    telemetry.update();
}
        waitForStart();
    }


}
