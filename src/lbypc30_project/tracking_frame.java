/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lbypc30_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author sesem
 */
public class tracking_frame extends javax.swing.JFrame {
    public String user;
    public static String lastname, firstname,idnum, course, college, bday,email,cpnum,addr,dl,lates,absence;
   // public static String 
    public static Boolean deans;
    DefaultListModel lm = new DefaultListModel();
    JList list = new JList(lm);
    
    
    /**
     * Creates new form tracking_frame
     */
    
    public static void disp_info(String user){
       // String try1;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM student_personal_info");
            ResultSet rs = pst.executeQuery();
            String finduser = "";
          
            int i=1;
            int j=0;
            //System.out.println(user);
            while(j==0){
               rs.absolute(i);
               finduser = rs.getString("username");
               //System.out.println(finduser);
               if(user.contentEquals(finduser))
               {
                   j=1;
               }
               i++;
            } 

            //selects rows
            if(i-2 == 0){
                rs.beforeFirst();
            }
            else{
                rs.absolute(i-2);
            }
            
            //get data
            if(rs.next()){
                lastname = rs.getString("last_name");
                firstname = rs.getString("first_name");
                idnum = rs.getString("id_num");
                course = rs.getString("course");
                college = rs.getString("college");
                bday = rs.getString("birthday");
                email = rs.getString("email");
                cpnum = rs.getString("contact_number");
                addr = rs.getString("address");
                //deans = rs.getBoolean("deans_lister");
            }
        }
        catch(Exception e){
        }              
    }
    public void disp_late(){
       // String try1;
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM log_table");
            ResultSet rs = pst.executeQuery();
            
            PreparedStatement pst1 = conn.prepareStatement("SELECT COUNT(*)FROM log_table");
            ResultSet resultSet = pst1.executeQuery();
              resultSet.next();
                int rowcount = resultSet.getInt(1);
            
            
            //System.out.print(rowcount);
    
            String finduser = "";
            //rs.getRow();
            int i=1;
            int j=0;
            int ind=0;
            int k[] = new int[20];
            int s=0;
            //get row number
            
            while(i<=rowcount){
                //System.out.print(idnum);
               rs.absolute(i);
               finduser = rs.getString("idnum");
               //System.out.println(finduser+"!");
               if(idnum.contentEquals(finduser))
               {
                   k[ind]=i;
                   //System.out.print("hh");
                   ind++;
               }
               i++;
            } 
            
            while(s<rowcount){
                //System.out.println(k[s]);
                if(k[s] == 0){
                    rs.beforeFirst();
                    }
                else{
                    rs.absolute(k[s]);
                    }
                
                //System.out.println("Late: "+rs.getString("login_time"));
                
                lm.add(s, rs.getString("login_time"));
                
                s++;
            }
           
          
          
        }
        catch(Exception e){
        }              
    }
    public tracking_frame(String usr) {
        this.user = usr;
        initComponents();
        disp_info(user);
        disp_late();
        last_name_label.setText(lastname);
        first_middle_name_label.setText(firstname);
        jLabel12.setText(idnum);
        birthday_label.setText(bday);
        email_label.setText(email);
        course_label.setText(course);
        college_label.setText(college);
        contact_label.setText(cpnum);
        address_label.setText(addr);
        login_list.setModel(lm);
        
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
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        login_list = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        last_name_label = new javax.swing.JLabel();
        first_middle_name_label = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        birthday_label = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        email_label = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        contact_label = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        deanslist_label = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        address_label = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        course_label = new javax.swing.JLabel();
        college_label = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        logout_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Interface");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Login Times");

        jScrollPane3.setViewportView(login_list);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        last_name_label.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        last_name_label.setText("DELA CRUZ");

        first_middle_name_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        first_middle_name_label.setText("Mac Excel Patrick O.");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("11307927");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setText("Birthday:");

        birthday_label.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        birthday_label.setText("02/20/96");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel17.setText("Email:");

        email_label.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        email_label.setText("miguel_sese@dlsu.edu.ph");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText("Contact No:");

        contact_label.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        contact_label.setText("09171234567");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setText("Dean's List:");

        deanslist_label.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        deanslist_label.setText("YES");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel23.setText("Address:");

        address_label.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        address_label.setText("2353 Taft Avenue, Malate, Manila City");
        address_label.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        address_label.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel25.setText("Course:");

        course_label.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        course_label.setText("BS Computer Engineering");

        college_label.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        college_label.setText("Engineering");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel28.setText("College:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(last_name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(first_middle_name_label))
                        .addGap(107, 107, 107)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(birthday_label)
                            .addComponent(email_label, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contact_label, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(deanslist_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(course_label, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(college_label, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(address_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(last_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(first_middle_name_label)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(course_label)
                                    .addComponent(jLabel23))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(college_label)))
                            .addComponent(address_label, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(birthday_label)
                            .addComponent(jLabel19)
                            .addComponent(contact_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(email_label)
                            .addComponent(jLabel21)
                            .addComponent(deanslist_label))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        logout_button.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logout_button.setText("LOGOUT");
        logout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logout_button, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logout_button, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(364, 364, 364)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logout_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_buttonActionPerformed
        Object[] options = {"Yes", "No"};
        int y = JOptionPane.showOptionDialog(null, "Continue Exit?", "Logging-Out",
             JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
             null, options, null);
        if(y==0){
            login_frame f = new login_frame();
            f.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logout_buttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address_label;
    private javax.swing.JLabel birthday_label;
    private javax.swing.JLabel college_label;
    private javax.swing.JLabel contact_label;
    private javax.swing.JLabel course_label;
    private javax.swing.JLabel deanslist_label;
    private javax.swing.JLabel email_label;
    private javax.swing.JLabel first_middle_name_label;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel last_name_label;
    private javax.swing.JList<String> login_list;
    private javax.swing.JButton logout_button;
    // End of variables declaration//GEN-END:variables
}
