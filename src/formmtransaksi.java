/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wildan Abdul Rahman
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksidb;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
public class formmtransaksi extends javax.swing.JFrame {
    private Statement st;
    private Connection Con;
    private ResultSet Rs;
    private String Sql="";
    private String host;

     /**
     * Creates new formmtransaksi
     */
    String no_transaksi, Tanggal_sewa, No_pelanggan, Nama_pembeli, Alamat_pembeli, No_telp, Kode_tenda, Nama_tenda, Jenis_tenda, Kapasitas, Harga, QTY,Total,Kode_perlengkapan, Nama_perlengkapan, Jenis_perlengkapan,Harga_perlengkapan, Jumlah, Total_perlengkapan, Total_harga,Lama_sewa, Subtotal ; 
    int bulanTransaksi;
    int tagihanTransaksi, totalTransaksi, bayar, kembalian;
    
        DefaultTableModel model;
    private String total;
    public formmtransaksi() {
        initComponents();
            String[] judul={"no_transaksi","Tanggal_sewa", "No_pelanggan","Nama_pembeli", "Alamat_pembeli", "No_telp", "Kode_tenda", "Nama_tenda", "Jenis_tenda","Kapasitas", "Harga", "QTY","Total","Kode_perlengkapan", "Nama_perlengkapan", "Jenis_perlengkapan","Harga_perlengkapan", "Jumlah", "Total_perlengkapan", "Total_harga","Lama_sewa", "Subtotal"};
        model =new DefaultTableModel(judul,0);
        tblTransaksi.setModel(model);
        autonumber();
        loadDataTransaksi();
        jpelanggan();
        jkodetenda();
        jkodeper();
     
       }
    
    /**
     * Creates new form formmtransaksi
     */
    public void formmtransaksi() {
        initComponents();
        
         //membuat obyek
        model = new DefaultTableModel();
        
        //memberi nama header pada tabel
        tblTransaksi.setModel(model);
        model.addColumn("No_Transaksi");
        model.addColumn("Tanggal_sewa");
        model.addColumn("No_pelanggan");
        model.addColumn("Nama_pembeli");
        model.addColumn("Alamat_pembeli");
        model.addColumn("No_telp");
        model.addColumn("Kode_tenda");
        model.addColumn("Nama_tenda");
        model.addColumn("Jenis_tenda");
        model.addColumn("Kapasitas");
        model.addColumn("Harga");
        model.addColumn("QTY");
        model.addColumn("Total");
        model.addColumn("Kode_perlengkapan");
        model.addColumn("Nama_perlengkapan");
        model.addColumn("Jenis_perlengkapan");
        model.addColumn("Harga_perlengkapan");
        model.addColumn("Jumlah");
        model.addColumn("Total_perlengkapan");
        model.addColumn("Total_harga");
        model.addColumn("Lama_sewa");
        model.addColumn("Subtotal");
        
        getDataTransaksi();
    }
    //fungsi untuk menampilkan data pada textbox
    public void getDataTransaksi(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            //tes koneksi
            Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
           
            //perintah sql untuk membaca data dari tabel produk
            String sql = "SELECT * FROM transaksi ";
            ResultSet res = stat.executeQuery(sql);
                        
            //baca data dan tampilkan pada text produk dan harga
            while(res.next()){
                //membuat obyek berjenis array
               Object[] obj = new Object [23];
        obj[0]=res.getString("no_transaksi");
        obj[1]=res.getString("Tanggal_sewa");
        obj[2]=res.getString("No_pelanggan");
        obj[3]=res.getString("Nama_pembeli");
        obj[4]=res.getString("Alamat_pembeli");
        obj[5]=res.getString("No_telp");
        obj[6]=res.getString("Kode_tenda");
        obj[7]=res.getString("Nama_tenda");
        obj[8]=res.getString("Jenis_tenda");
        obj[9]=res.getString("Kapasitas");
        obj[10]=res.getString("Harga");
        obj[11]=res.getString("QTY");
        obj[12]=res.getString("Total");
        obj[13]=res.getString("Kode_perlengkapan");
        obj[14]=res.getString("Nama_perlengkapan");
        obj[15]=res.getString("Jenis_perlengkapan");
        obj[16]=res.getString("Harga_perlengkapan");
        obj[17]=res.getString("Jumlah");
        obj[18]=res.getString("Total_perlengkapan");
        obj[19]=res.getString("Total_harga");
        obj[20]=res.getString("Lama_sewa");
        obj[21]=res.getString("Subtotal");
               model.addRow(obj);
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    public void loadDataTransaksi(){
        autonumber(); 
        // noTransaksi =autonumber(); //txtNomor.getText();
         no_transaksi = (String) jtransaksi.getText();
         Tanggal_sewa = jtanggal.getText();
         No_pelanggan = (String) jpelanggan.getSelectedItem();
         Nama_pembeli = (String) jnama.getText();
         Alamat_pembeli = jalamat.getText();
         No_telp = (String)jnotelp.getText();
         Kode_tenda = (String)jkodetenda.getSelectedItem();
         Nama_tenda = (String)jtenda.getText();
         Jenis_tenda = (String) jhargatenda.getText();
         Kapasitas = (String)jkapasitas.getText();
         Harga = jhargatenda.getText();
         QTY = (String) jqty.getSelectedItem();
         Total = (String) jtotal.getText();
         Kode_perlengkapan = (String) jkodeper.getSelectedItem();
         Nama_perlengkapan = (String) jnamaper.getText();
         Jenis_perlengkapan = (String) jjenisper.getText();
         Harga_perlengkapan = (String) jhargaper.getText();
         Jumlah = (String) jqtyper.getSelectedItem();
         Total_perlengkapan = (String) jtotalper.getText();
         Total_harga = (String) jtotalbay.getText();
         Lama_sewa = (String) jlamasewa.getText();
         Subtotal = (String)  jsubtotal.getText();     
    }
    
    
    public void tampil()
       {
        try {
        Connection con = koneksidb.getKoneksi();
        Statement stt = con.createStatement();
        String sql = "select Tanggal_sewa from transaksi where no_transaksi='"+jtransaksi.getText()+"'";  
        ResultSet res = stt.executeQuery(sql);
        
        while(res.next()){
            Object[] ob = new Object[1];
            ob[0]=  res.getString(1);
            
            jnama.setText((String) ob[0]);
        }
        res.close(); stt.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }              
    }
     
     public void dataSelect(){
        //deklarasi variabel
        int i = tblTransaksi.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        jtransaksi.setText(""+model.getValueAt(i,0));
        jtanggal.setText(""+model.getValueAt(i,1));
        jpelanggan.setSelectedItem(""+model.getValueAt(i,2));
        jnama.setText(""+model.getValueAt(i,3));
        jalamat.setText(""+model.getValueAt(i,4));
        jnotelp.setText(""+model.getValueAt(i,5));
        jkodetenda.setSelectedItem(""+model.getValueAt(i,6));
        jtenda.setText(""+model.getValueAt(i,7));
        jjenis.setText(""+model.getValueAt(i,8));
        jkapasitas.setText(""+model.getValueAt(i,9));
        jhargatenda.setText(""+model.getValueAt(i,10));
        jqty.setSelectedItem(""+model.getValueAt(i,11));
        jtotal.setText(""+model.getValueAt(i,12));
        jkodeper.setSelectedItem(""+model.getValueAt(i,13));
        jnamaper.setText(""+model.getValueAt(i,14));
        jjenisper.setText(""+model.getValueAt(i,15));
        jhargaper.setText(""+model.getValueAt(i,16));
        jqtyper.setSelectedItem(""+model.getValueAt(i,17));
        jtotalper.setText(""+model.getValueAt(i,18));
        jtotalbay.setText(""+model.getValueAt(i,19));
        jlamasewa.setText(""+model.getValueAt(i,20));
        jsubtotal.setText(""+model.getValueAt(i,21));     
    }
     
     public void reset(){
        no_transaksi = "";
        Tanggal_sewa = "";
        No_pelanggan = "";
        Nama_pembeli = "";
        Alamat_pembeli= "";
        No_telp = "";
        Kode_tenda = "";
        Nama_tenda = "";
        Jenis_tenda ="";
        Kapasitas = "";
        Harga = "";
        QTY = "";
        Total = "";
        Kode_perlengkapan="";
        Nama_perlengkapan="";
        Jenis_perlengkapan="";
        Harga_perlengkapan="";
        Jumlah="";
        Total_perlengkapan="";
        Total_harga="";
        Lama_sewa="";
        Subtotal="";
        
        jtransaksi.setText(no_transaksi);
        jtanggal.setText(Tanggal_sewa);
        jpelanggan.setSelectedItem(No_pelanggan);
        jnama.setText(Nama_pembeli);
        jalamat.setText(Alamat_pembeli);
        jnotelp.setText(No_telp);
        jkodetenda.setSelectedItem(Kode_tenda);
        jtenda.setText(Nama_tenda);
        jjenis.setText(Jenis_tenda);
        jkapasitas.setText(Kapasitas);
        jhargatenda.setText(Harga);
        jqty.setSelectedItem(QTY);
        jtotal.setText(Total);
        jkodeper.setSelectedItem(Kode_perlengkapan);
        jnamaper.setText(Nama_perlengkapan);
        jjenisper.setText(Jenis_perlengkapan);
        jhargaper.setText(Harga_perlengkapan);
        jqtyper.setSelectedItem(Jumlah);
        jtotalper.setText(Total_perlengkapan);
        jtotalbay.setText(Total_harga);
        jlamasewa.setText(Lama_sewa);
        jsubtotal.setText(Subtotal);     

     }
     public void simpanDataTransaksi(){
         //panggil fungsi load data
        loadDataTransaksi();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String  sql =   "INSERT INTO transaksi(no_transaksi, Tanggal_sewa, No_pelanggan, Nama_pembeli, Alamat_pembeli, No_telp, Kode_tenda, Nama_tenda, Jenis_tenda, Kapasitas,Harga,QTY,Total,Kode_perlengkapan,Nama_perlengkapan,Jenis_perlengkapan,Harga_perlengkapan,Jumlah,Total_perlengkapan,Total_harga,Lama_sewa,Subtotal)"
                            + "VALUES('"+ jtransaksi.getText() +"','"+ jtanggal.getText()+"','"+ jpelanggan.getSelectedItem()+"','"+ jnama.getText()+"','"+ jalamat.getText()+"','"+ jnotelp.getText()+"','"+ jkodetenda.getSelectedItem()+"','"+ jtenda.getText()+"','"+ jjenis.getText()+"','"+ jkapasitas.getText()+"','"+ jhargatenda.getText()+"','"+ jqty.getSelectedItem()+"','"+ jtotal.getText()+"','"+ jkodeper.getSelectedItem()+"','"+ jnamaper.getText()+"','"+ jjenisper.getText()+"','"+ jhargaper.getText()+"','"+ jqtyper.getSelectedItem()+"','"+ jtotalper.getText()+"','"+ jtotalbay.getText()+"','"+ jlamasewa.getText()+"','"+ jsubtotal.getText()+"')";
            PreparedStatement p = (PreparedStatement) koneksidb.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataTransaksi();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
     
     public void rubahDataTransaksi(){
          //panggil fungsi load data
        loadDataTransaksi();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String sql  =   "UPDATE transaksi SET no_transaksi= '"+ jtransaksi +"',"
                            + "Tanggal_sewa  = '"+ jtanggal+"',"
                            + "No_pelanggan  = '"+ jpelanggan+"',"
                            + "Nama_pembeli  = '"+ jnama+"',"
                            + "Alamat_pembeli  = '"+ jalamat+"',"
                            + "No_telp  = '"+ jnotelp+"',"
                            + "Kode_tenda  = '"+ jkodetenda+"',"
                            + "Nama_tenda  = '"+ jtenda+"',"
                            + "Jenis_tenda  = '"+ jjenis+"',"
                            + "Kapasitas  = '"+ jkapasitas+"',"
                            + "Harga  = '"+ jhargatenda+"',"
                            + "QTY  = '"+ jqty+"',"
                            + "Total  = '"+ jtotal+"',"
                            + "Kode_perlengkapan  = '"+ jkodeper+"',"
                            + "Nama_perlengkapan  = '"+ jnamaper+"',"
                            + "Jenis_perlengkapan  = '"+ jjenisper+"',"
                            + "Harga_perlengkapan  = '"+ jhargaper+"',"
                            + "Jumlah  = '"+ jqtyper+"',"
                            + "Total_perlengkapan  = '"+ jtotalper+"',"
                            + "Total_harga  = '"+ jtotalbay+"',"
                            + "Lama_sewa  = '"+ jlamasewa+"',"
                            + "Subtotal  = '"+ jsubtotal+"',"
                            + "' WHERE no_transaksi = '" + jtransaksi +"'";
            PreparedStatement p = (PreparedStatement) koneksidb.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataTransaksi();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
     
      public void hapusDataTransaksi(){
        //panggil fungsi ambil data
        loadDataTransaksi(); 
        
        //Beri peringatan sebelum melakukan penghapusan data
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA"+ no_transaksi +"?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        //jika pengguna memilih OK lanjutkan proses hapus data
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM transaksi WHERE no_transaksi='"+ no_transaksi +"'";
                PreparedStatement p =(PreparedStatement)koneksidb.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
                getDataTransaksi();
                
                //fungsi reset data
                reset();
                JOptionPane.showMessageDialog(null, "BERHASIL DIHAPUS");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jnama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jlamasewa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtenda = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jhargatenda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jtanggal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jalamat = new javax.swing.JTextField();
        jnotelp = new javax.swing.JTextField();
        jpelanggan = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jjenis = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jtotal = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jqty = new javax.swing.JComboBox<>();
        jkapasitas = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jkodetenda = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jnamaper = new javax.swing.JTextField();
        jjenisper = new javax.swing.JTextField();
        jhargaper = new javax.swing.JTextField();
        jtotalper = new javax.swing.JTextField();
        jqtyper = new javax.swing.JComboBox<>();
        jkodeper = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jtotalbay = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jsubtotal = new javax.swing.JTextField();
        ttenda = new javax.swing.JLabel();
        tperlengkapan = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jtransaksi = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jnama.setEditable(false);
        getContentPane().add(jnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 247, -1));

        jLabel8.setText("KODE TENDA");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jLabel7.setText("TOTAL");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, -1, -1));

        jLabel2.setText("NOMOR PELANGGAN");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 750, 108));

        jLabel4.setText("Nomor telp");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 85, -1));

        jlamasewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlamasewaActionPerformed(evt);
            }
        });
        jlamasewa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jlamasewaKeyReleased(evt);
            }
        });
        getContentPane().add(jlamasewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 50, -1));

        jLabel10.setText("NAMA TENDA");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, 20));

        jtenda.setEditable(false);
        getContentPane().add(jtenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 247, -1));

        jLabel11.setText("JENIS TENDA");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        jhargatenda.setEditable(false);
        jhargatenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jhargatendaActionPerformed(evt);
            }
        });
        getContentPane().add(jhargatenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 110, -1));

        jButton1.setText("SIMPAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 124, -1));

        jButton2.setText("UBAH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, 124, -1));

        jButton3.setText("HAPUS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 500, 124, -1));

        jButton4.setText("RESET");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 500, 124, -1));

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setText("X");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 0, -1, -1));

        jButton7.setText("HITUNG");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, 80, 20));

        jLabel3.setText("HARI/TANGGAL");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jtanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtanggalActionPerformed(evt);
            }
        });
        getContentPane().add(jtanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 130, -1));

        jLabel5.setText("NAMA ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 85, -1));

        jLabel6.setText("Alamat");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 85, -1));

        jalamat.setEditable(false);
        jalamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jalamatActionPerformed(evt);
            }
        });
        getContentPane().add(jalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 250, -1));

        jnotelp.setEditable(false);
        jnotelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jnotelpActionPerformed(evt);
            }
        });
        getContentPane().add(jnotelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 130, -1));

        jpelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jpelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpelangganActionPerformed(evt);
            }
        });
        getContentPane().add(jpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 140, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("IDENTITAS PENYEWA");
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 400, 150));

        jLabel13.setText("HARGA");
        jLabel13.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                jLabel13ComponentMoved(evt);
            }
        });
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jjenis.setEditable(false);
        jjenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jjenisActionPerformed(evt);
            }
        });
        getContentPane().add(jjenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 110, -1));

        jLabel15.setText("TOTAL HARGA");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));
        getContentPane().add(jtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 110, -1));

        jLabel16.setText("QTY");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 30, 20));

        jqty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jqty.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jqtyItemStateChanged(evt);
            }
        });
        jqty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jqtyMouseClicked(evt);
            }
        });
        getContentPane().add(jqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, -1, -1));

        jkapasitas.setEditable(false);
        getContentPane().add(jkapasitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 110, -1));

        jLabel24.setText("KAPASITAS");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        jkodetenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jkodetenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkodetendaActionPerformed(evt);
            }
        });
        getContentPane().add(jkodetenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 140, -1));

        jScrollPane3.setToolTipText("");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("TENDA");
        jScrollPane3.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 400, 240));

        jLabel12.setText("Jenis Tenda");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, -1));

        jLabel17.setText("KODE PERLENGKAPAN");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, -1, -1));

        jLabel18.setText("NAMA PERLENGKAPAN");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));

        jLabel20.setText("HARGA");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, -1, -1));

        jLabel19.setText("JENIS PERLENGKAPAN");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, -1, -1));

        jLabel21.setText("QTY");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, -1, -1));

        jLabel22.setText("TOTAL HARGA");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, -1, -1));

        jLabel27.setText("SUBTOTAL");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, -1, -1));

        jnamaper.setEditable(false);
        getContentPane().add(jnamaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 180, -1));

        jjenisper.setEditable(false);
        getContentPane().add(jjenisper, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 180, -1));

        jhargaper.setEditable(false);
        getContentPane().add(jhargaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 180, -1));
        getContentPane().add(jtotalper, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 180, -1));

        jqtyper.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jqtyper.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jqtyperItemStateChanged(evt);
            }
        });
        getContentPane().add(jqtyper, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, -1, -1));

        jkodeper.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jkodeper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkodeperActionPerformed(evt);
            }
        });
        getContentPane().add(jkodeper, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 130, -1));

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText("PERLENGKAPAN");
        jScrollPane4.setViewportView(jTextArea3);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 370, 220));
        getContentPane().add(jtotalbay, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 120, -1));

        jLabel25.setText("LAMA SEWA");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, -1, -1));

        jLabel26.setText("HARI");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, -1, -1));
        getContentPane().add(jsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 120, -1));
        getContentPane().add(ttenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 370, 30, 20));
        getContentPane().add(tperlengkapan, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, 30, 20));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setText("BAYAR");
        jScrollPane5.setViewportView(jTextArea4);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 370, 150));
        getContentPane().add(jtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 130, -1));

        jLabel23.setText("NO TRANSAKSI");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel14.setText("jLabel9");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 370, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("YYYY-MM-DD");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("TRANSAKSI");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jButton6.setText("CETAK");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 500, 110, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jhargatendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jhargatendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jhargatendaActionPerformed

    private void jlamasewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlamasewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jlamasewaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        simpanDataTransaksi();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        rubahDataTransaksi();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        hapusDataTransaksi();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        reset();
        autonumber();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.dispose();{
        new formutamaa().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed
    }
    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        // TODO add your handling code here:
        loadDataTransaksi();
        dataSelect();
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void jtanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtanggalActionPerformed

    private void jnotelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jnotelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jnotelpActionPerformed

    private void jalamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jalamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jalamatActionPerformed

    private void jLabel13ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel13ComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13ComponentMoved

    private void jjenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jjenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jjenisActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
     // if(jbutton.get()!= null){
                    int total1,total2 ;
                    total1= Integer.parseInt(jtotal.getText());
                    total2= Integer.parseInt(jtotalper.getText());
                  int total= total1 + total2 ;
                  jtotalbay.setText(Integer.toString(total));
               
      
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jpelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpelangganActionPerformed
        // TODO add your handling code here:
        thn_pelanggan();
        jnama.enable(true);
         jalamat.enable(true);
         jnotelp.enable(true);
    }//GEN-LAST:event_jpelangganActionPerformed

    private void jkodetendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkodetendaActionPerformed
        // TODO add your handling code here:
        thn_tenda();
        jtenda.enable(true);
         jjenis.enable(true);
         jkapasitas.enable(true);
         jhargatenda.enable(true);
        
    }//GEN-LAST:event_jkodetendaActionPerformed

    private void jkodeperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkodeperActionPerformed
        // TODO add your handling code here:
        thn_perlengkapan();
    }//GEN-LAST:event_jkodeperActionPerformed

    private void jqtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jqtyMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jqtyMouseClicked

    private void jqtyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jqtyItemStateChanged
        // TODO add your handling code here:
            if(jqty.getSelectedItem()!= null){
                    int harga,qty ;
                    harga= Integer.parseInt(jhargatenda.getText());
                    qty= Integer.parseInt((String) jqty.getSelectedItem());
                  int total= harga * qty ;
                  jtotal.setText(Integer.toString(total));
               
        }

    }//GEN-LAST:event_jqtyItemStateChanged

    private void jqtyperItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jqtyperItemStateChanged
        // TODO add your handling code here:
        if(jqtyper.getSelectedItem()!= null){
                    int harga,qty ;
                    harga= Integer.parseInt(jhargaper.getText());
                    qty= Integer.parseInt((String) jqtyper.getSelectedItem());
                  int total= harga * qty ;
                  jtotalper.setText(Integer.toString(total));
        }  
    }//GEN-LAST:event_jqtyperItemStateChanged

    private void jlamasewaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlamasewaKeyReleased
        // TODO add your handling code here:
         if(jlamasewa.getText()!= null){
                  int total1,total2,lamasewa ;
                    total1= Integer.parseInt(jtotal.getText());
                    total2= Integer.parseInt(jtotalper.getText());
                    lamasewa= Integer.parseInt(jlamasewa.getText());
                  int total= (total1 + total2)*lamasewa ;
                  jsubtotal.setText(Integer.toString(total));
         }
    }//GEN-LAST:event_jlamasewaKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
          try {
        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("reporttransaksi.jasper"), null, koneksidb.getKoneksi());
        JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed
private void autonumber(){
    String kd_nomor= "TST000";
    int i = 0;
    try{
    //Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/sewa_tenda","root","");
    //Statement st =cn.createStatement();
    Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
                
    String sql= "select no_transaksi from transaksi";
    ResultSet rs = stat.executeQuery(sql);
    while(rs.next()){
	kd_nomor = rs.getString("no_transaksi");
    }
    kd_nomor  = kd_nomor.substring(4);
    i = Integer.parseInt(kd_nomor)+1;
    kd_nomor= "00"+i;
    kd_nomor = "TST"+ kd_nomor.substring(kd_nomor.length()-3);
    jtransaksi.setText(kd_nomor);
    } catch (SQLException e) {
	System.out.println(e.getMessage());
    }
}

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
            java.util.logging.Logger.getLogger(formmtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formmtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formmtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formmtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formmtransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jalamat;
    private javax.swing.JTextField jhargaper;
    private javax.swing.JTextField jhargatenda;
    private javax.swing.JTextField jjenis;
    private javax.swing.JTextField jjenisper;
    private javax.swing.JTextField jkapasitas;
    private javax.swing.JComboBox<String> jkodeper;
    private javax.swing.JComboBox<String> jkodetenda;
    private javax.swing.JTextField jlamasewa;
    private javax.swing.JTextField jnama;
    private javax.swing.JTextField jnamaper;
    private javax.swing.JTextField jnotelp;
    private javax.swing.JComboBox<String> jpelanggan;
    private javax.swing.JComboBox<String> jqty;
    private javax.swing.JComboBox<String> jqtyper;
    private javax.swing.JTextField jsubtotal;
    private javax.swing.JTextField jtanggal;
    private javax.swing.JTextField jtenda;
    private javax.swing.JTextField jtotal;
    private javax.swing.JTextField jtotalbay;
    private javax.swing.JTextField jtotalper;
    private javax.swing.JTextField jtransaksi;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JLabel tperlengkapan;
    private javax.swing.JLabel ttenda;
    // End of variables declaration//GEN-END:variables

    private void jpelanggan() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    jpelanggan.removeAllItems();
        try{
      Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/sewa_tenda","root","");
      ResultSet rs=cn.createStatement().executeQuery("select No_sewa FROM Penyewa");
      while(rs.next()){
         jpelanggan.addItem(rs.getString("No_sewa"));
      }
    } catch (SQLException ex){
        Logger.getLogger(formmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void jkodetenda() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       jkodetenda.removeAllItems();
        try{
      Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/sewa_tenda","root","");
      ResultSet rs=cn.createStatement().executeQuery("select Kode_tenda FROM tenda");
      while(rs.next()){
         jkodetenda.addItem(rs.getString("Kode_tenda"));
      }
    } catch (SQLException ex){
        Logger.getLogger(formmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private void jkodeper() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    jkodeper.removeAllItems();
        try{
      Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/sewa_tenda","root","");
      ResultSet rs=cn.createStatement().executeQuery("select Kode_perlengkapan FROM perlengkapan");
      while(rs.next()){
         jkodeper.addItem(rs.getString("Kode_perlengkapan"));
      }
    } catch (SQLException ex){
        Logger.getLogger(formmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
  public void thn_perlengkapan(){
        try{
      Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/sewa_tenda","root","");
      ResultSet rs=cn.createStatement().executeQuery("select * FROM perlengkapan where Kode_perlengkapan='"+jkodeper.getSelectedItem()+"'");
      while(rs.next()){
         jnamaper.setText(rs.getString("Nama_perlengkapan"));
         jjenisper.setText(rs.getString("Jenis_perlengkapan"));
         jhargaper.setText(rs.getString("Harga"));
         
      }
    } catch (SQLException ex){
        Logger.getLogger(formmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void thn_pelanggan() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     try{
      Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/sewa_tenda","root","");
      ResultSet rs=cn.createStatement().executeQuery("select * FROM Penyewa where No_sewa='"+jpelanggan.getSelectedItem()+"'");
      while(rs.next()){
         jnama.setText(rs.getString("Nama"));
         jalamat.setText(rs.getString("Alamat"));
         jnotelp.setText(rs.getString("No_telp"));
       
      }
    } catch (SQLException ex){
        Logger.getLogger(formmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private void thn_tenda() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
      Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/sewa_tenda","root","");
      ResultSet rs=cn.createStatement().executeQuery("select * FROM Tenda where Kode_tenda='"+jkodetenda.getSelectedItem()+"'");
      while(rs.next()){
         jtenda.setText(rs.getString("Nama_tenda"));
         jjenis.setText(rs.getString("Jenis_tenda"));
         jkapasitas.setText(rs.getString("Kapasitas"));
         jhargatenda.setText(rs.getString("harga"));
      }
    } catch (SQLException ex){
        Logger.getLogger(formmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
 




}