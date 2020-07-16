/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wildan Abdul Rahman
 */
import koneksi.koneksidb;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Penyewa extends javax.swing.JFrame {
private final DefaultTableModel model;
    String No_sewa, No_KTP, Nama, Alamat, No_telp;

    
    /**
     * Creates new form Penyewa
     */
    public Penyewa() {
        initComponents();
        model = new DefaultTableModel();
        
        //memberi nama header pada tabel
        jtabel.setModel(model);
        model.addColumn("No_sewa");
        model.addColumn("No_KTP");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("No_telp");
        
        //fungsi ambil data
        getdataPenyewa();
    }
 public void getdataPenyewa(){
        //kosongkan tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        //eksekusi koneksi dan kirimkan query ke database
        try{
            //tes koneksi
            Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
            
            //perintah sql untuk membaca data dari tabel kategori        
            String sql = "SELECT * FROM Penyewa";
            ResultSet res = stat.executeQuery(sql);
            
            //baca data
            while(res.next()){
                //membuat obyek berjenis array
                Object[] obj = new Object[5];
                obj[0]=res.getString("No_sewa");
                obj[1]=res.getString("No_KTP");
                obj[2]=res.getString("Nama");
                obj[3]=res.getString("Alamat");
                obj[4]=res.getString("No_telp");
                model.addRow(obj);
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public void loaddataPenyewa(){
        No_sewa =  jnosewa.getText();
        No_KTP = jktp.getText();
        Nama = jnama.getText();
        Alamat = jalamat.getText();
        No_telp = jtelp.getText();
    }
     //memilih data saat baris pada tabel dipilih dengan mouse
   
    public void dataSelect(){
        //deklarasi variabel
        int i = jtabel.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        jnosewa.setText(""+model.getValueAt(i,0));
        jktp.setText(""+model.getValueAt(i,1));
        jnama.setText(""+model.getValueAt(i,2));
        jalamat.setText(""+model.getValueAt(i,3));
        jtelp.setText(""+model.getValueAt(i,4));
    }
    
     //mengosongkan form
    public void reset(){
        No_sewa = "";
        No_KTP = "";
        Nama = "";
        Alamat ="";
        No_telp = "";
        
        jnosewa.setText(No_sewa);
        jktp.setText(No_KTP);
        jnama.setText(Nama);
        jalamat.setText(Alamat);
        jtelp.setText(No_telp);
    }
    
     public void simpandataPenyewa(){
        //panggil fungsi load data
        loaddataPenyewa();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String  sql =   "INSERT INTO Penyewa(No_sewa, No_KTP, Nama,Alamat,No_telp)" + "VALUES('"+ jnosewa.getText() +"','"+ jktp.getText() +"','"+ jnama.getText() +"','"+ jalamat.getText()+"','"+ jtelp.getText()+"')";
            PreparedStatement p = (PreparedStatement) koneksidb.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
           getdataPenyewa();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
     
         public void perbaruidataPenyewa(){
        //fungsi load data kategori
        loaddataPenyewa();
        
        try{
            //uji koneksi
            Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
            
            //kirim perintah sql
            String sql = "UPDATE Penyewa SET No_KTP = '"+ jktp +"',Nama = '"+ jnama +"',Alamat = '"+ jalamat+"',No_telp = '"+ jtelp +"' WHERE No_sewa = '"+ jnosewa +"'";
            PreparedStatement p =(PreparedStatement)koneksidb.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
           getdataPenyewa();
            
            //kosongkan data
            reset();
            JOptionPane.showMessageDialog(null, "PERUBAHAN DATA BERHASIL");
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
         
         public void hapusdataPenyewa(){
        //panggil fungsi ambil data
        loaddataPenyewa(); 
        
        //Beri peringatan sebelum melakukan penghapusan data
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA"+ No_sewa +"?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        //jika pengguna memilih OK lanjutkan proses hapus data
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM Penyewa WHERE No_sewa='"+ jnosewa +"'";
                PreparedStatement p =(PreparedStatement)koneksidb.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
               getdataPenyewa();
                
                //fungsi reset data
                reset();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }               
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jktp = new javax.swing.JTextField();
        jnama = new javax.swing.JTextField();
        jalamat = new javax.swing.JTextField();
        jtelp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jnosewa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("TABEL PENYEWA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 140, -1));

        jLabel2.setText("NO KTP");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel3.setText("NAMA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel4.setText("ALAMAT");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel5.setText("NO TELP");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));
        getContentPane().add(jktp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 210, -1));
        getContentPane().add(jnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 210, -1));
        getContentPane().add(jalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 210, -1));
        getContentPane().add(jtelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 120, -1));

        jLabel6.setText("NO SEWA");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jnosewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jnosewaActionPerformed(evt);
            }
        });
        getContentPane().add(jnosewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 80, -1));

        jtabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtabel);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, 130));

        jButton1.setText("SIMPAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        jButton2.setText("RESET");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 70, -1));

        jButton3.setText("UBAH");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 70, -1));

        jButton4.setText("HAPUS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 70, -1));

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setText("X");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, -1, -1));

        jButton6.setText("SIMPAN");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        jButton7.setText("RESET");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 70, -1));

        jButton8.setText("UBAH");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 70, -1));

        jButton9.setText("HAPUS");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 70, -1));

        jButton10.setText("LANJUT");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 80, -1));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           //memanggil fungsi reset
        reset();
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new home().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
   //memanggil fungsi perbarui data kategori
        perbaruidataPenyewa();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       //memanggil fungsi hapus data kategori
        hapusdataPenyewa();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       //memanggil fungsi simpan data kategori
        simpandataPenyewa();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        new formmtransaksi().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jtabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabelMouseClicked
    dataSelect();
        // TODO add your handling code here:
    }//GEN-LAST:event_jtabelMouseClicked

    private void jnosewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jnosewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jnosewaActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Penyewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Penyewa().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jalamat;
    private javax.swing.JTextField jktp;
    private javax.swing.JTextField jnama;
    private javax.swing.JTextField jnosewa;
    private javax.swing.JTable jtabel;
    private javax.swing.JTextField jtelp;
    // End of variables declaration//GEN-END:variables
}
