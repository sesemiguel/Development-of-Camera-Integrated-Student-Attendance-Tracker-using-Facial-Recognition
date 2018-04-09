/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lbypc30_project;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author sesem
 */
public class AdminFrame extends javax.swing.JFrame {

    DefaultTableModel model;
    ArrayList<String> users = new ArrayList<String>();
    /**
     * Creates new form AdminFrame
     */
    public AdminFrame() {
        initComponents();
        model = (DefaultTableModel) accounts_table.getModel();
        disp_info_student();
        disp_info_teacher();
        disp_pending();
        disp_info_dl();
        disp_info_delete_student();
        disp_info_delete_teacher();
    }
    
    public void disp_info_delete_student(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM student_personal_info");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                deletestudentComboBox.addItem(rs.getString(3));
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, "Database Error");
        }
    }
    
    public void disp_info_delete_teacher(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst2 = conn.prepareStatement("SELECT * FROM teacher_personal_info");
            ResultSet rs2 = pst2.executeQuery();
            while(rs2.next()){
                deleteteacherComboBox.addItem(rs2.getString(3));
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, "Database Error");
        }
    }
    
    public void disp_info_dl(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM student_personal_info");
            ResultSet rs = pst.executeQuery();
            String fn, ln, id;
            while(rs.next()){
                fn = rs.getString(1);
                ln = rs.getString(2);
                id = rs.getString(3);
                String str = id;
                dl_comboBox.addItem(str);
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, "Database Error");
        }
    }
    
    public void disp_info_student(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM student_personal_info");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                users.add(rs.getString(1));
                users.add(rs.getString(2));
                users.add(rs.getString(3));
                users.add(rs.getString(4));
                users.add(rs.getString(5));
                users.add(rs.getString(6));
                users.add(rs.getString(7));
                users.add(rs.getString(8));
                users.add(rs.getString(9));
                users.add(rs.getString(10));
                users.add(rs.getString(11));
                users.add(rs.getString(12));
                users.add("Student");
                model.addRow(users.toArray());
                users.clear();
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, "Database Error");
        }
    }
    
    public void disp_info_teacher(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM teacher_personal_info");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                users.add(rs.getString(1));
                users.add(rs.getString(2));
                users.add(rs.getString(3));
                users.add("N/A");
                users.add("N/A");
                users.add(rs.getString(4));
                users.add(rs.getString(5));
                users.add(rs.getString(6));
                users.add(rs.getString(7));
                users.add("N/A");
                users.add(rs.getString(8));
                users.add(rs.getString(9));
                users.add("Teacher");
                model.addRow(users.toArray());
                users.clear();
            }
            //accounts_table.setModel(model); 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database Error");
        }
    }
    
    public void disp_pending(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM pending");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String str = rs.getString(2)+", "+rs.getString(1)+":"+rs.getString(3)+":" +rs.getString(13) ;
                pending_accounts_combo.addItem(str);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database Error");
        }
    }
    
 
    public void transfer_teacher(String idnumber){
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            
            PreparedStatement pst = conn.prepareStatement("INSERT INTO teacher_personal_info(first_name,last_name,id_number,birthday,email,contact_number,address,username,password) "
                      +"SELECT first_name,last_name,id_num,birthday,email,contact_number,address,username,password "
                       +"FROM pending WHERE id_num = '"+idnumber+"';");
            
            pst.executeUpdate();
            
            PreparedStatement pst2 = conn.prepareStatement("INSERT INTO login_credentials(username, password, type)"
                    + " SELECT username, password, type "
                    + "FROM pending WHERE id_num = '"+idnumber+"';");
            pst2.executeUpdate();
            
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database Error");
            System.out.println(e);
        }
    }
    public void transfer_student(String idnumber){
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            
            PreparedStatement pst = conn.prepareStatement("INSERT INTO student_personal_info(first_name,last_name,id_num,course,college,birthday,email,contact_number,address,deans_lister,username,password) "
                      +"SELECT first_name,last_name,id_num,course,college,birthday,email,contact_number,address,deans_lister,username,password "
                       +"FROM pending WHERE id_num = '"+idnumber+"';");
            
            pst.executeUpdate(); 
            
            PreparedStatement pst2 = conn.prepareStatement("INSERT INTO login_credentials(username, password, type)"
                    + " SELECT username, password, type "
                    + "FROM pending WHERE id_num = '"+idnumber+"';");
            pst2.executeUpdate();
            
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database Error");
            
        }
    }
        public void delete_data(String idnumber){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
                 System.out.println(idnumber);
            PreparedStatement pst = conn.prepareStatement("DELETE FROM pending WHERE id_num = '" + idnumber+"';");
            pst.executeUpdate();
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database Error");
        }
    }
        
    public void delete_student(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("DELETE FROM student_personal_info WHERE id_num = '"+deletestudentComboBox.getSelectedItem().toString()+"';");
            pst.executeUpdate();
            
            refresh();
            deletestudentComboBox.removeItem(deletestudentComboBox.getSelectedItem().toString());
            dl_comboBox.removeItem(deletestudentComboBox.getSelectedItem().toString());
            
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database Error");
        }
    }
        
    public void delete_teacher(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("DELETE FROM teacher_personal_info WHERE id_number = '"+deleteteacherComboBox.getSelectedItem().toString()+"';");
            pst.executeUpdate();
            
            refresh();
            deleteteacherComboBox.removeItem(deleteteacherComboBox.getSelectedItem().toString());
            
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.print(e);
        }
    }
    
    public void refresh(){
        model.setRowCount(0);
        disp_info_student();
        disp_info_teacher();
        disp_info_delete_student();
        disp_info_delete_teacher();
        disp_pending();
        disp_info_dl();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        accounts_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        pending_accounts_combo = new javax.swing.JComboBox<>();
        approve_button = new javax.swing.JButton();
        decline_button = new javax.swing.JButton();
        viewLogin_button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        dl_comboBox = new javax.swing.JComboBox<>();
        noDLButton = new javax.swing.JButton();
        yesDLButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        deletestudentComboBox = new javax.swing.JComboBox<>();
        deleteButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        deleteteacherComboBox = new javax.swing.JComboBox<>();
        deleteButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrator");
        setResizable(false);

        accounts_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "ID Number", "Course", "College", "Birthday", "Email", "Contact Number", "Address", "Dean's Lister", "Username", "Password", "Type"
            }
        ));
        jScrollPane1.setViewportView(accounts_table);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pending Accounts"));

        pending_accounts_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pending_accounts_comboActionPerformed(evt);
            }
        });

        approve_button.setText("Approve");
        approve_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approve_buttonActionPerformed(evt);
            }
        });

        decline_button.setText("Decline");
        decline_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decline_buttonActionPerformed(evt);
            }
        });

        viewLogin_button.setText("View Logins");
        viewLogin_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewLogin_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pending_accounts_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(decline_button, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(approve_button)
                                .addGap(18, 18, 18)
                                .addComponent(viewLogin_button)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pending_accounts_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approve_button)
                    .addComponent(viewLogin_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(decline_button)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dean's Lister"));

        dl_comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dl_comboBoxActionPerformed(evt);
            }
        });

        noDLButton.setText("No");
        noDLButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noDLButtonActionPerformed(evt);
            }
        });

        yesDLButton.setText("Yes");
        yesDLButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesDLButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(yesDLButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noDLButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 95, Short.MAX_VALUE))
                    .addComponent(dl_comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dl_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(yesDLButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noDLButton)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Delete Student"));

        deletestudentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletestudentComboBoxActionPerformed(evt);
            }
        });

        deleteButton1.setText("Delete");
        deleteButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deletestudentComboBox, 0, 168, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(deleteButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deletestudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Delete Teacher"));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 99));

        deleteteacherComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteteacherComboBoxActionPerformed(evt);
            }
        });

        deleteButton2.setText("Delete");
        deleteButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deleteteacherComboBox, 0, 178, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton2)
                .addGap(59, 59, 59))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deleteteacherComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void yesDLButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesDLButtonActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("UPDATE student_personal_info SET deans_lister = ? WHERE id_num = '"+dl_comboBox.getSelectedItem().toString()+"';");

            pst.setString(1, "YES");
            pst.executeUpdate();
            
            model.setRowCount(0);
            disp_info_student();
            disp_info_teacher();
            //dl_comboBox.removeItem(dl_comboBox.getSelectedItem().toString());
            
        }
        catch(Exception e){
            System.out.print(e);
        }
    }//GEN-LAST:event_yesDLButtonActionPerformed

    private void deleteButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton1ActionPerformed
       delete_student();
       
    }//GEN-LAST:event_deleteButton1ActionPerformed

    private void approve_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approve_buttonActionPerformed
        String info = pending_accounts_combo.getSelectedItem().toString();
        //System.out.println(info);
        String[] parts = info.split(":");
        String idnumber = parts[1];
        //System.out.println(idnumber);
        if(info.endsWith("Teacher")){
            transfer_teacher(idnumber);
            delete_data(idnumber);
        }
        else if(info.endsWith("Student")){
            transfer_student(idnumber);
            delete_data(idnumber);
        }
        model.setRowCount(0);
        pending_accounts_combo.removeAllItems();
        disp_pending();
        disp_info_student();
        disp_info_teacher();
        disp_info_delete_student();
        disp_info_delete_teacher();
        disp_info_dl();
        //Put import account to database here
    }//GEN-LAST:event_approve_buttonActionPerformed

    private void decline_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decline_buttonActionPerformed
        //Put delete row here
        String info = pending_accounts_combo.getSelectedItem().toString();
        System.out.println(info);
        String[] parts = info.split(":");
        String idnumber = parts[1];
        delete_data(idnumber);
        pending_accounts_combo.removeItem(info);
        pending_accounts_combo.removeAllItems();
        disp_pending();
    }//GEN-LAST:event_decline_buttonActionPerformed

    private void pending_accounts_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pending_accounts_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pending_accounts_comboActionPerformed

    private void noDLButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noDLButtonActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lbycp30_tracking_system?"+"user=root&password=");
            PreparedStatement pst = conn.prepareStatement("UPDATE student_personal_info SET deans_lister = ? WHERE id_num = '"+dl_comboBox.getSelectedItem().toString()+"';");
            

            pst.setString(1, "NO");
            pst.executeUpdate();
            
            model.setRowCount(0);
            disp_info_student();
            disp_info_teacher();
            //dl_comboBox.removeItem(dl_comboBox.getSelectedItem().toString());
            
        }
        catch(Exception e){
            System.out.print(e);
        }
    }//GEN-LAST:event_noDLButtonActionPerformed

    private void deletestudentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletestudentComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deletestudentComboBoxActionPerformed

    private void dl_comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dl_comboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dl_comboBoxActionPerformed

    private void deleteteacherComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteteacherComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteteacherComboBoxActionPerformed

    private void deleteButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton2ActionPerformed
        // TODO add your handling code here:
        delete_teacher();
    }//GEN-LAST:event_deleteButton2ActionPerformed

    private void viewLogin_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewLogin_buttonActionPerformed
            LogFrame f = new LogFrame();
            f.setVisible(true);
    }//GEN-LAST:event_viewLogin_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accounts_table;
    private javax.swing.JButton approve_button;
    private javax.swing.JButton decline_button;
    private javax.swing.JButton deleteButton1;
    private javax.swing.JButton deleteButton2;
    private javax.swing.JComboBox<String> deletestudentComboBox;
    private javax.swing.JComboBox<String> deleteteacherComboBox;
    private javax.swing.JComboBox<String> dl_comboBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton noDLButton;
    private javax.swing.JComboBox<String> pending_accounts_combo;
    private javax.swing.JButton viewLogin_button;
    private javax.swing.JButton yesDLButton;
    // End of variables declaration//GEN-END:variables
}
