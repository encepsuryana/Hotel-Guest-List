/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * data_pembayaran.java
 *
 * Created on Jul 22, 2016, 5:41:38 PM
 */
package daftartamuhotel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
//Fungsi import untuk SQL
import java.sql.*;
//Fungsi import untuk tanggal
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Firdam
 */
public class data_pembayaran extends javax.swing.JFrame {
    
    //deklarasi variable
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;

    /** Creates new form data_pembayaran */
    public data_pembayaran() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_pembayaran.setModel(tableModel);
        settableload();
        
    }
    
    private javax.swing.table.DefaultTableModel tableModel=getDefaultTableModel();
    private javax.swing.table.DefaultTableModel getDefaultTableModel()
    {
        //membuat judul header
        return new javax.swing.table.DefaultTableModel
        (
                new Object[][] {},
                new String [] {"No.Pembayaran",
                               "No.ID",
                               "No.Kamar",
                               "Tgl.Masuk",
                               "Tgl.Keluar",
                               "Total Hari",
                               "Harga Kamar",
                               "Harga Fasilitas Tambahan",
                               "Total Bayar"}
                               
                    
       )
       //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[]
            {
                false,false,false,false,false,false,false,false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };
    }
    String data[]=new String[9];
    private void settableload()
    {
        String stat = "";
        try
            {
               Class.forName(driver);
               Connection kon = DriverManager.getConnection(database,user,pass);
               
               Statement stt = kon.createStatement();
               String SQL = "select * from t_pembayaran";
               ResultSet res = stt.executeQuery(SQL);
               while(res.next())
               {
                   data[0] = res.getString(1);
                   data[1] = res.getString(2);
                   data[2] = res.getString(3);
                   data[3] = res.getString(4);
                   data[4] = res.getString(5);
                   data[5] = res.getString(6);
                   data[6] = res.getString(7);
                   data[7] = res.getString(8);
                   data[8] = res.getString(9);
                   tableModel.addRow(data);
               }
               res.close();
               stt.close();
               kon.close();
            }
            catch(Exception ex)
            {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
                
                System.exit(0);
            }
    }
    
    public void aktif_teks()
    {
       no_pembayaran.setEnabled(true);
       no_id.setEnabled(true);
       no_kamar.setEnabled(true);
       tgl_masuk.setEnabled(true);
       tgl_keluar.setEnabled(true);
       total_hari.setEnabled(true);
       harga_kamar.setEnabled(true);
       harga_fasilitas_tambahan.setEnabled(true);
       total_bayar.setEnabled(true);  
    }
    
    int row = 0;
    public void tampil_field()
    {
        row = tabel_pembayaran.getSelectedRow();
        no_pembayaran.setText(tableModel.getValueAt(row, 0).toString());
        no_id.setText(tableModel.getValueAt(row, 1).toString());
        no_kamar.setText(tableModel.getValueAt(row, 2).toString());
        tgl_masuk.setText(tableModel.getValueAt(row, 3).toString());
        tgl_keluar.setText(tableModel.getValueAt(row, 4).toString());
        total_hari.setText(tableModel.getValueAt(row, 5).toString());
        harga_kamar.setText(tableModel.getValueAt(row, 6).toString());
        harga_fasilitas_tambahan.setText(tableModel.getValueAt(row, 7).toString());
        total_bayar.setText(tableModel.getValueAt(row, 8).toString());
        
        aktif_teks();
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_tampil = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pembayaran = new javax.swing.JTable();
        btn_keluar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        no_id1 = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        no_kamar1 = new javax.swing.JLabel();
        tipe_kamar = new javax.swing.JLabel();
        lantai = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        no_pembayaran = new javax.swing.JLabel();
        no_id = new javax.swing.JLabel();
        no_kamar = new javax.swing.JLabel();
        tgl_masuk = new javax.swing.JLabel();
        tgl_keluar = new javax.swing.JLabel();
        total_hari = new javax.swing.JLabel();
        harga_kamar = new javax.swing.JLabel();
        harga_fasilitas_tambahan = new javax.swing.JLabel();
        total_bayar = new javax.swing.JLabel();
        btn_edit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_keluar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149262_resolutions-16.png"))); // NOI18N
        jLabel1.setText("Pembayaran");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel14.setText("Masukkan No.ID/NO.Pembayaran/No.Kamar");

        btn_cari.setFont(new java.awt.Font("Times New Roman", 2, 14));
        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        btn_tampil.setFont(new java.awt.Font("Times New Roman", 2, 12));
        btn_tampil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tampil.png"))); // NOI18N
        btn_tampil.setText("Tampilkan Keseluruhan Data");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_tampil)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_cari)))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cari)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_tampil))
                    .addComponent(jLabel1))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tabel_pembayaran.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_pembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pembayaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pembayaran);

        btn_keluar.setFont(new java.awt.Font("Times New Roman", 1, 14));
        btn_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/kembali.png"))); // NOI18N
        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Data Pembayaran", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 0, 18), java.awt.Color.white)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("No.Pembayaran");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("No.ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("No.Kamar");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Tgl.Masuk");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Tgl.Keluar");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Total Hari");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel10.setText("Harga Kamar/Malam");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel11.setText("Single room Rp 150.000,00");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel12.setText("Twin room Rp 250.000,00");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel13.setText("Family room Rp 450.000,00");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Harga Fasilitas Tambahan");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setText("Total Bayar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(no_id1)
                                    .addComponent(nama)
                                    .addComponent(no_kamar1)
                                    .addComponent(tipe_kamar)
                                    .addComponent(lantai)))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(63, 63, 63)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(no_id)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(no_kamar)
                    .addComponent(tgl_masuk)
                    .addComponent(tgl_keluar)
                    .addComponent(total_hari)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(harga_kamar))
                    .addComponent(harga_fasilitas_tambahan)
                    .addComponent(no_pembayaran)
                    .addComponent(total_bayar))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(harga_kamar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(no_id)
                                .addGap(18, 18, 18)
                                .addComponent(no_kamar)
                                .addGap(18, 18, 18)
                                .addComponent(tgl_masuk)
                                .addGap(18, 18, 18)
                                .addComponent(tgl_keluar)
                                .addGap(18, 18, 18)
                                .addComponent(total_hari)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(harga_fasilitas_tambahan))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(no_id1)
                                .addGap(18, 18, 18)
                                .addComponent(nama))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(no_pembayaran))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(no_kamar1)
                                .addGap(18, 18, 18)
                                .addComponent(tipe_kamar)
                                .addGap(101, 101, 101)
                                .addComponent(lantai)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(99, 99, 99)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(total_bayar))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        btn_edit.setFont(new java.awt.Font("Times New Roman", 1, 14));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N
        btn_edit.setText("Edit Data");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        menu_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1468182599_Close_Box_Red.png"))); // NOI18N
        menu_keluar.setText("Keluar");
        menu_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_keluarMouseClicked(evt);
            }
        });
        jMenu1.add(menu_keluar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(btn_edit)
                        .addGap(37, 37, 37)
                        .addComponent(btn_keluar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(271, 271, 271))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_edit)
                            .addComponent(btn_keluar)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void menu_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_keluarMouseClicked
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_menu_keluarMouseClicked

private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
// TODO add your handling code here:
   
    dispose();
}//GEN-LAST:event_btn_keluarActionPerformed

private void tabel_pembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pembayaranMouseClicked
// TODO add your handling code here:
    if(evt.getClickCount()==1)
    {
        tampil_field();
    }
}//GEN-LAST:event_tabel_pembayaranMouseClicked

private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
// TODO add your handling code here:
    tableModel.setRowCount(0);
    //menggunakan queri untuk mencari
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "SELECT * FROM t_pembayaran WHERE no_id LIKE '%"+txt_cari.getText()+"%'OR no_pembayaran LIKE '%"+txt_cari.getText()+"%'OR no_kamar LIKE '%"+txt_cari.getText()+"%'";
        ResultSet res = stt.executeQuery(SQL);
        while(res.next())
        {
             data[0] = res.getString(1);
             data[1] = res.getString(2);
             data[2] = res.getString(3);
             data[3] = res.getString(4);
             data[4] = res.getString(5);
             data[5] = res.getString(6);
             data[6] = res.getString(7);
             data[7] = res.getString(8);
             data[8] = res.getString(9);
             tableModel.addRow(data);
        }
    res.close();
    stt.close();
    kon.close();
    }
    catch (Exception ex)
{
    System.err.println(ex.getMessage());
    JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
    System.exit(0);
}
}//GEN-LAST:event_btn_cariActionPerformed

private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
// TODO add your handling code here:
    tableModel.setRowCount(0);
    settableload();
}//GEN-LAST:event_btn_tampilActionPerformed

private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
// TODO add your handling code here:
        frm_pembayaran e = null;
        try {
            e = new frm_pembayaran();
        } catch (SQLException ex) {
            Logger.getLogger(data_pembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        e.setLocationRelativeTo(null);
        e.setVisible(true);
        this.setVisible(false);
}//GEN-LAST:event_btn_editActionPerformed

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
            java.util.logging.Logger.getLogger(data_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new data_pembayaran().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JLabel harga_fasilitas_tambahan;
    private javax.swing.JLabel harga_kamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lantai;
    private javax.swing.JMenu menu_keluar;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel no_id;
    private javax.swing.JLabel no_id1;
    private javax.swing.JLabel no_kamar;
    private javax.swing.JLabel no_kamar1;
    private javax.swing.JLabel no_pembayaran;
    private javax.swing.JTable tabel_pembayaran;
    private javax.swing.JLabel tgl_keluar;
    private javax.swing.JLabel tgl_masuk;
    private javax.swing.JLabel tipe_kamar;
    private javax.swing.JLabel total_bayar;
    private javax.swing.JLabel total_hari;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}