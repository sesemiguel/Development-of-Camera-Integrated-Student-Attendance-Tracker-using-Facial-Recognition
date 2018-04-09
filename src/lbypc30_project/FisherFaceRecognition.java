package lbypc30_project;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_core.CV_8UC1;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_face.FisherFaceRecognizer;
import org.bytedeco.javacpp.opencv_face.EigenFaceRecognizer;
import org.bytedeco.javacpp.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;



public class FisherFaceRecognition {
    public String a;
    public String b;
    
    public String name;
    public String number;

    
    public void Recognize() {
        NewJFrame jframe = new NewJFrame();
        
        String trainingDir = "C:/Users/sesem/Desktop/Trainables/Train/train";
        Mat testImage = imread("C:/Users/sesem/Desktop/Trainables/1.png", CV_LOAD_IMAGE_GRAYSCALE);

        File root = new File(trainingDir);

        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
            }
        };

        File[] imageFiles = root.listFiles(imgFilter);

        MatVector images = new MatVector(imageFiles.length);

        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.createBuffer();

        int counter = 0;

        for (File image : imageFiles) {
            Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);

            int label = Integer.parseInt(image.getName().split("\\-")[0]);

            images.put(counter, img);

            labelsBuf.put(counter, label);

            counter++;
        }

        //FaceRecognizer faceRecognizer = FisherFaceRecognizer.create();
        //FaceRecognizer faceRecognizer = EigenFaceRecognizer.create();
        FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();

        faceRecognizer.train(images, labels);

        IntPointer label = new IntPointer(1);
        DoublePointer confidence = new DoublePointer(1);
        faceRecognizer.predict(testImage, label, confidence);
        int predictedLabel = label.get(0);
        
        

        System.out.println("Predicted label: " + predictedLabel);
        
        if (predictedLabel == 1){
        	System.out.println("Detected: Mac Excel Fallar");
                name = "Mac Excel Fallar";
                number = "11340096" ;      
        }
        else if (predictedLabel == 2) {
        	System.out.println("Detected: Miguel Sese");
                name = "Miguel Sese";
                number= "11328215";
        }
        else if (predictedLabel == 3) {
        	System.out.println("Detected: Patrick Vani Ramos");
                name = "Patrick Vani Ramos";
                number = "11307927";
        }
        else if (predictedLabel == 4) {
        	System.out.println("Detected: Fae Serrano");
                name = "Fae Serrano";
                number = "11416548" ;     
        }
        else if (predictedLabel == 5) {
        	System.out.println("Detected: Janela Angeles");
                name = "Janela Angeles";
                number = "11327685";
        }
        else if (predictedLabel == 6) {
        	System.out.println("Detected: Aldwin Cabebe");
                name = "Aldwin Cabebe";
                number = "11338385";
        }
        else if (predictedLabel == 7) {
        	System.out.println("Detected: Jeremy Chan");
                name = "Jeremy Chan";
                number = "11413492";
        }
        else if (predictedLabel == 8) {
        	System.out.println("Detected: Laureen Garcia");
                name = "Laureen Garcia";
                number = "11334770";
        }
        else if (predictedLabel == 9) {
        	System.out.println("Detected: Jeremiah Jacinto");
                name = "Jeremiah Jacinto";
                number = "11331755";
        }
        else if (predictedLabel == 10) {
        	System.out.println("Detected: Isaiah Lecitona");
                name = "Isaiah Lecitona";
                number = "11343133";
        }
        else if (predictedLabel == 11) {
        	System.out.println("Detected: Ken Roxas");
                name = "Ken Roxas";
                number = "11318619";
        }
        else if (predictedLabel == 12) {
        	System.out.println("Detected: Rafael Talavera");
                name = "";
                number = "11342196";
        }
        else if (predictedLabel == 13) {
        	System.out.println("Detected: Janssen Co");
                name = "Janssen Co";
                number = "11312157";
        }
        else if (predictedLabel == 14) {
        	System.out.println("Detected: Kylie Licudine");
                name = "Kylie Licudine";
                number = "11347147";
        }
        else if (predictedLabel == 15) {
        	System.out.println("Detected: Miguel Magana");
                name = "Miguel Magana";
                number = "11302437";
        }
        else if (predictedLabel == 16) {
        	System.out.println("Detected: Renzt Racela");
                name = "Renzt Racela";
                number = "11416785";
        }
        else if (predictedLabel == 17) {
        	System.out.println("Detected: Rainiell Bacani");
                name = "Rainell Bacani";
                number = "11413034";
        }
        else if (predictedLabel == 18) {
        	System.out.println("Detected: Jacob Sindayen");
                name = "Jacob Sindayen";
                number = "11319844";
        }
        else if (predictedLabel == 19) {
        	System.out.println("Detected: Willard Songco");
                name = "Willard Songco";
                number = "11344253";
        }
        else if (predictedLabel == 20) {
        	System.out.println("Detected: Trisha de Vera");
                name = "Trisha de Vera";
                number = "11345284";
        }
        else if (predictedLabel == 21) {
        	System.out.println("Detected: Hannah Pituk");
                name = "Hannah Pituk";
                number = "11331615";
        }
        else if (predictedLabel == 22) {
        	System.out.println("Detected: Isabel del Castillo");
                name = "Isabel del Castillo";
                number = "11345306";
        }
        else if (predictedLabel == 23) {
        	System.out.println("Detected: Joseph Dominguez");
                name = "Joseph Dominguez";
                number = "11329750";
        }
        else if (predictedLabel == 24) {
        	System.out.println("Detected: Nico Jimenez");
                name = "Nico Jimenez";
                number = "11425032";
        }
        
        
    }
}