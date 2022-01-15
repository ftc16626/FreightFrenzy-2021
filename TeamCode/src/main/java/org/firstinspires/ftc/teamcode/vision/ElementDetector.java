package org.firstinspires.ftc.teamcode.vision;

//import org.firstinspires.ftc.teamcode.autonomous.VisionTest;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class ElementDetector extends OpenCvPipeline {

    public int lastResult = 0;
    boolean test = false;

    public boolean left;
    public boolean right;
    //VisionTest vt = new VisionTest();
    //public Rect[] boundRect = new Rect[0];
    private int width;

    public ElementDetector(int width) {
        this.width = width;
    }

    @Override
    public Mat processFrame(Mat input) {
        Mat mat = new Mat();
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        if (mat.empty()) return input;

        Scalar lowHSV = new Scalar(20, 100, 100);
        Scalar highHSV = new Scalar(30, 255, 255);
        Mat thresh = new Mat();

        Core.inRange(mat, lowHSV, highHSV, thresh);

        Mat edges = new Mat();
        Imgproc.Canny(thresh, edges, 100, 300);
        thresh.release();


        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        edges.release();
        hierarchy.release();

        MatOfPoint2f[] contoursPoly = new MatOfPoint2f[contours.size()];
       Rect[] boundRect = new Rect[contours.size()];
        for (int i = 0; i < contours.size(); i++) {
            contoursPoly[i] = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly[i], 3, true);
            boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
        }

        double left_x = 0.25 * width;
        double right_x = 0.75 * width;
        left = false;
        right = false;
        for (int i = 0; i != boundRect.length; i++) {
            if (boundRect[i].x < left_x) left = true;
            if (boundRect[i].x + boundRect[i].width > right_x) right = true;

            //if(boundRect[i].x > 0) test = true;

            Imgproc.rectangle(mat, boundRect[i], new Scalar(0.5, 76.9, 89.8));
        }

        lastResult = 1;
        return mat;
    }


    public int getLastResult() {
        return lastResult;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return right;
    }

public boolean getTest() {
        return test;
}
}
