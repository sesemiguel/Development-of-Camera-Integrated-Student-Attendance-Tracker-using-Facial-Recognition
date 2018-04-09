package lbypc30_project;
import static org.opencv.highgui.Highgui.*;
import static org.opencv.imgproc.Imgproc.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.bytedeco.javacpp.opencv_face.FisherFaceRecognizer;



import org.opencv.core.Core;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;


public class PlayMain {

	public ImgShow imgShowOrigin = new ImgShow();
	public ImgShow imgShowFace = new ImgShow();
	public Map<Integer, String> mapIdName = new HashMap<Integer, String>();
        
        Calendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+ 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);

//	public void main(String[] args) {
//		System.out.println("========= Start Face recognize =========");
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		//System.loadLibrary("MyOpencvDLL");
//		String id = "test";
//		cameraTrain(id);
//		//FisherFaceRecognition();	
//		System.out.println("Done");
//	}

	/**
	 * Capture face and set ID
	 */
	
	public void cameraTrain() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		VideoCapture camera = new VideoCapture(0);

		Mat frame = new Mat();
		camera.read(frame);
		int count = 0;
		int index = 0;
                int x = 0;
		if (!camera.isOpened()) {
			System.out.println("Error");
		} else {
			while (true) {
				if (camera.read(frame)) {

					count++;                                    
					if (count % 5 == 0) {
						System.out.println("Write image  " + count);
						FaceModel faceModel = FaceUtils.getFace(frame.clone());

						if (faceModel != null) {
							index++;
							//FaceUtils.getFace(frame.clone());
							imwrite("C:/Users/sesem/Desktop/Trainables/Train/" + index + ".png", faceModel.cropedFace);
							
						}
					}
					
				}				
			}
		}
                
		camera.release();
	}

	
	/**
	 * Recognize
	 */
        
	
	
	public void FisherFaceRecognition() {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		VideoCapture camera = new VideoCapture(0);

		Mat frame = new Mat();
		camera.read(frame);
		int count = 0;
		int index = 0;
		if (!camera.isOpened()) {
			System.out.println("Error");
		} else {
			while(true) {
				//if (camera.read(frame)) {
					count++;
					if (count % 20 == 0) {
						System.out.println("Writing image...");
						
						FaceModel faceModel = FaceUtils.getFace(frame.clone());
							
						if (faceModel != null) {
							//FaceUtils.getFace(frame.clone());
							imwrite("C:/Users/sesem/Desktop/Trainables/" + "1" + ".png", faceModel.cropedFace);
							//imgShowFace.show(faceModel.cropedFace);
							
							FisherFaceRecognition FFR = new FisherFaceRecognition();
							//FFR.printHello();
							FFR.Recognize();
                                                        
						}
					}
					//imgShowOrigin.show(frame);
				//}	
				}
		}
		camera.release();
		
	}

}


