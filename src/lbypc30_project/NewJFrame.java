package lbypc30_project;
import static org.opencv.highgui.Highgui.*;
import static org.opencv.imgproc.Imgproc.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;


import org.opencv.highgui.Highgui;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Macexcel
 */
public class NewJFrame extends javax.swing.JFrame {
    /**
     * Creates new form NewJFrame
     */
    
    Calendar cal = new GregorianCalendar();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int second = cal.get(Calendar.SECOND);
    int minute = cal.get(Calendar.MINUTE);
    int hour = cal.get(Calendar.HOUR);
    
    private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;
    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    CascadeClassifier faceDetector = new CascadeClassifier(NewJFrame.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
    MatOfRect faceDetections = new MatOfRect();
    
    PlayMain main = new PlayMain();
    public ImgShow imgShowOrigin = new ImgShow();
    public ImgShow imgShowFace = new ImgShow();
    public Map<Integer, String> mapIdName = new HashMap<Integer, String>();
    public String tym;
    public String Attendance;       //@TODO: PASS TO DATABASE and collect attendance information
    
    public void AttendanceComputer(){
            int classHour = 9;
            int classMinute = 15;

            //hour = 9;
            //minute = 20;
    
            if ((hour == classHour)&&(minute < classMinute + 15)&&(minute > classMinute)){
                {
                System.out.println("LATE");
                Attendance = "Late";
                }
            }
            else if ((hour == classHour)&&(minute > classMinute + 30)){
                System.out.println("ABSENT");
                Attendance = "Absent";
            }
            else if (hour > classHour){
                System.out.println("ABSENT");
                Attendance = "Absent";
            }
            else {
                System.out.println("PRESENT");
                Attendance = "Present";
            }
}
    
class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Graphics g = jPanel1.getGraphics();
                            faceDetector.detectMultiScale(frame, faceDetections);
                            for (Rect rect : faceDetections.toArray()) {
                               // System.out.println("ttt");
                                Core.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                                        new Scalar(0, 255,0));
                            }
                            Highgui.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            BufferedImage buff = (BufferedImage) im;
                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight() -175 , 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Paused ..-... ");
                                    this.wait();
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Error!!");
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

public void CurrentTime(){
    
    System.out.println("Date: " + month + "/"+ day + "/" + year);   
    System.out.println("Time: " + hour + ":"+ minute + ":" + second); 
    timeLabel.setText(month + "/" + day +"/" + year + "   " + hour + ":" + minute + ":" + second);
    timeLabel.setVisible(true);
    tym = month + "/" + day +"/" + year + "  " + hour + ":" + minute + ":" + second;
}

    public NewJFrame() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        initComponents();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cameraPanel = new javax.swing.JPanel();
        trainButton = new javax.swing.JButton();
        recognizeButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numberField = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        cameraPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout cameraPanelLayout = new javax.swing.GroupLayout(cameraPanel);
        cameraPanel.setLayout(cameraPanelLayout);
        cameraPanelLayout.setHorizontalGroup(
            cameraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        cameraPanelLayout.setVerticalGroup(
            cameraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
        );

        trainButton.setBackground(new java.awt.Color(51, 51, 255));
        trainButton.setText("Train");
        trainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainButtonActionPerformed(evt);
            }
        });

        recognizeButton.setBackground(new java.awt.Color(51, 51, 255));
        recognizeButton.setLabel("Capture");
        recognizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recognizeButtonActionPerformed(evt);
            }
        });

        stopButton.setBackground(new java.awt.Color(51, 51, 255));
        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("NAME:");

        nameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameField.setText(".");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("ID NUMBER:");

        numberField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numberField.setText(".");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Time-in:");

        timeLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        timeLabel.setText(".");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(740, 740, 740))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addGap(778, 778, 778))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(recognizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(202, 202, 202)
                                .addComponent(trainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(cameraPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(cameraPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameField)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numberField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeLabel)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recognizeButton)
                    .addComponent(trainButton)
                    .addComponent(stopButton)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        stopButton.getAccessibleContext().setAccessibleName("Start");
        timeLabel.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainButtonActionPerformed
        main.cameraTrain();
    }//GEN-LAST:event_trainButtonActionPerformed

    private void recognizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recognizeButtonActionPerformed
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    VideoCapture camera = webSource;

    Mat frame = new Mat();
    camera.retrieve(frame);
    int count = 0;
    int index = 0;
    if (!camera.isOpened()) {
            System.out.println("Error");
    } 
    else {
        System.out.println("Writing image...");
        FaceModel faceModel = FaceUtils.getFace(frame.clone());
        if (faceModel != null) {
                //FaceUtils.getFace(frame.clone());
                imwrite("C:/Users/sesem/Desktop/Trainables/" + "1" + ".png", faceModel.cropedFace);
                //imgShowFace.show(faceModel.cropedFace);
                FisherFaceRecognition FFR = new FisherFaceRecognition();
                //FFR.printHello();
                FFR.Recognize();
                CurrentTime();
                nameField.setText(FFR.name);
                numberField.setText(FFR.number);
                AttendanceComputer();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("INSERT INTO log_table(idnum,login_time) VALUES(?,?)");
            pst.setString(1, FFR.number);
            pst.setString(2, tym);
            pst.executeUpdate();
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("EXCEPTION: "+e);
        }
        }
    }
    
		
		
	
    }//GEN-LAST:event_recognizeButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
            myThread.runnable = false;
            stopButton.setEnabled(false);   
            jButton1.setEnabled(true);
            
            webSource.release();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         webSource =new VideoCapture(0);
         myThread = new DaemonThread();
            Thread t = new Thread(myThread);
            t.setDaemon(true);
            myThread.runnable = true;
            t.start();
	    jButton1.setEnabled(false);  //start button
            stopButton.setEnabled(true);  // stop button
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                new NewJFrame().setVisible(true);    

                
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cameraPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel nameField;
    public javax.swing.JLabel numberField;
    private javax.swing.JButton recognizeButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JButton trainButton;
    // End of variables declaration//GEN-END:variables
}
