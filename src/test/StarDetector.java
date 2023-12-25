//package test;
//
//import org.opencv.core.*;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//
//public class StarDetector {
//    public static void main(String[] args) {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        // 读取输入图片
//        Mat image = Imgcodecs.imread("your_image_path.jpg");
//
//        // 将图像转换为灰度图
//        Mat grayImage = new Mat();
//        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);
//
//        // 检测图像中的轮廓
//        Mat hierarchy = new Mat();
//        MatVector contours = new MatVector();
//        Imgproc.findContours(grayImage, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//
//        // 遍历轮廓并进行形状匹配
//        for (int i = 0; i < contours.size(); i++) {
//            MatOfPoint contour = contours.get(i);
//            MatOfPoint2f approxCurve = new MatOfPoint2f();
//            Imgproc.approxPolyDP(new MatOfPoint2f(contour.toArray()), approxCurve, Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) * 0.02, true);
//
//            // 判断是否为五角星形状
//            if (approxCurve.total() == 5) {
//                System.out.println("图片中存在五角星形状");
//                return;
//            }
//        }
//
//        System.out.println("图片中不存在五角星形状");
//    }
//}
