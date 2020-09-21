/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frm_kamar.java
 *
 * Created on Jul 22, 2016, 4:47:04 PM
 */
package daftartamuhotel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
//Fungsi Import yang digunakan untuk SQL
import java.sql.*;
//fungsi import yang digunakan untuk tanggal
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class frm_kamar extends javax.swing.JFrame {
    //deklarasi variable
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    /** Creates new form frm_kamar */
    public frm_kamar() throws SQLException   {
        initComponents();
        
          this.setLocationRelativeTo(null);
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_kamar.setModel(tableModel);
        comboid_auto();
        settableload();
    }
     private javax.swing.table.DefaultTableModel tableModel=getDefaultTableModel();
     private javax.swing.table.DefaultTableModel getDefaultTableModel()
     {
        //membuat judul header
        return new javax.swing.table.DefaultTableModel
                (
                    new Object[][] {},
                    new String [] {"No.ID",
                                    "Nama",
                                    "No.Kamar",
                                    "Tipe Kamar",
                                    "Lantai"}
                    
                )
         //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[]
            {
                false,false,false,false,false,false,false
            };
            public boolean isCellEditable(int rowIndex,int columnIndex )
            {
                return canEdit[columnIndex];
                
            }
        };
    }
     
    String data[]=new String [5];
    private void settableload()
    {
        String stat ="";
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(
                                database,
                                user,
                                pass);
            Statement stt=kon.createStatement();
            String SQL = "select * from t_kamar";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
                    
                    System.exit(0);
                }   
    } 
    
    private void comboid_auto() throws SQLException {
            Connection kon = DriverManager.getConnection(database,user,pass);  
            java.sql.Statement stmt;
            stmt = kon.createStatement();
            String sql = "Select * From t_identitas order by no_id asc";
            java.sql.ResultSet rslt = stmt.executeQuery(sql);
            while (rslt.next()) {
                String noid = rslt.getString("no_id");
                combo_id.addItem(noid);
            }
        } 
      
    public void membersihkan_teks()
    {
        combo_id.setSelectedItem("");
        txt_nama.setText("");
        txt_nokamar.setText("");
        combo_tipe.setSelectedItem("");
        txt_lantaikamar.setText("");
    }
    public void nonaktif_teks()
    {
        combo_id.setSelectedItem(false);
        txt_nama.setEnabled(false);
        txt_nokamar.setEnabled(false);
        combo_tipe.setEditable(false);
        txt_lantaikamar.setEnabled(false);
        
    }
    public void aktif_teks()
    {
        combo_id.setEnabled(true);
        txt_nama.setEnabled(true);
        txt_nokamar.setEnabled(true);
        combo_tipe.setEnabled(true);
        txt_lantaikamar.setEnabled(true);
    }
     int row = 0;
    public void tampil_field()
    {
        row = tabel_kamar.getSelectedRow();
        combo_id.setSelectedItem(tableModel.getValueAt(row, 0).toString());
        txt_nama.setText(tableModel.getValueAt(row, 1).toString());
        txt_nokamar.setText(tableModel.getValueAt(row, 2).toString());
        combo_tipe.setSelectedItem(tableModel.getValueAt(row,3).toString());
        txt_lantaikamar.setText(tableModel.getValueAt(row, 4).toString());
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_batal.setEnabled(false);
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
        jLabel10 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_tampilkan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_nokamar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        combo_tipe = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txt_lantaikamar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_kamar = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_fasilitas = new javax.swing.JButton();
        combo_id = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_keluar = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menu_datakamar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36));
        jLabel1.setForeground(new java.awt.Color(51, 0, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469147293_bedroom.png"))); // NOI18N
        jLabel1.setText("Kamar");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel10.setText("Masukkan No.ID/No.Kamar");

        btn_cari.setFont(new java.awt.Font("Times New Roman", 2, 14));
        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        btn_tampilkan.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        btn_tampilkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tampil.png"))); // NOI18N
        btn_tampilkan.setText("Tampilkan keseluruhan data");
        btn_tampilkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilkanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_tampilkan, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cari))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_tampilkan))
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("No.ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Nama");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("No.Kamar");

        txt_nokamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nokamarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Tipe Kamar");

        combo_tipe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Tipe Kamar", "Single Room", "Twin Room", "Family Room", " " }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Lantai ");

        tabel_kamar.setBackground(new java.awt.Color(204, 153, 255));
        tabel_kamar.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_kamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_kamarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_kamar);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel11.setText("Single room Rp 150.000,00");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel12.setText("Twin room Rp 250.000,00");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel13.setText("Family room Rp 450.000,00");

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149763_icon-105-folder-add.png"))); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149880_system-software-update.png"))); // NOI18N
        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149352_Save.png"))); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.setToolTipText("");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149501_1-04.png"))); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149813_stop.png"))); // NOI18N
        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_fasilitas.setText("Fasilitas Tambahan");
        btn_fasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fasilitasActionPerformed(evt);
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

        jMenu2.setText("View");

        menu_datakamar.setText("DataKamar");
        menu_datakamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_datakamarMouseClicked(evt);
            }
        });
        jMenu2.add(menu_datakamar);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(combo_id, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_nokamar)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_lantaikamar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(combo_tipe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addComponent(jLabel13)
                                .addComponent(jLabel12)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btn_tambah)
                        .addGap(32, 32, 32)
                        .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_simpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(btn_fasilitas)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(275, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(combo_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_nokamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(combo_tipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(101, 101, 101)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_lantaikamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(248, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(90, 90, 90)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tambah)
                            .addComponent(btn_simpan)
                            .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btn_fasilitas)))
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void menu_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_keluarMouseClicked
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_menu_keluarMouseClicked

private void txt_nokamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nokamarActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txt_nokamarActionPerformed

private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
// TODO add your handling code here:
    membersihkan_teks();
    txt_nama.requestFocus();
    btn_simpan.setEnabled(true);
    btn_ubah.setEnabled(false);
    btn_hapus.setEnabled(false);
   
    aktif_teks();
}//GEN-LAST:event_btn_tambahActionPerformed

private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
// TODO add your handling code here:
    String no_id = combo_id.getSelectedItem().toString();
    String nama=txt_nama.getText();
    String no_kamar=txt_nokamar.getText();
    String tipe_kamar=combo_tipe.getSelectedItem().toString();
    String lantai_kamar=txt_lantaikamar.getText();
    
    if ((no_id.isEmpty()) | (no_kamar.isEmpty()))
    {
        JOptionPane.showMessageDialog(null,"data tidak boleh kosong, harap dilengkapi");
        txt_nama.requestFocus();
    }
    else
    {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "UPDATE `t_kamar` "
                         + "SET `no_id`='"+no_id+"',"
                         + "`nama`='"+nama+"',"
                         +"`no_kamar`='"+no_kamar+"',"
                         + "`tipe_kamar`='"+tipe_kamar+"',"
                         + "`lantai`='"+lantai_kamar+"' "
                      + "WHERE "
                      +"`no_id`='"+tableModel.getValueAt(row, 0).toString()+"';";
             int a = JOptionPane.showConfirmDialog(null,
                        "Apakah anda ingin mengubah data kamar?");
             if(a == JOptionPane.YES_OPTION){
           stt.executeUpdate(SQL);
           data[0] = no_id;
           data[1] = nama;
           data[2] = no_kamar;
           data[3] = tipe_kamar;
           data[4] = lantai_kamar;
           tableModel.removeRow(row);
           tableModel.insertRow(row, data);
           stt.close();
           kon.close();
           membersihkan_teks();
           btn_simpan.setEnabled(false);
           nonaktif_teks();
             }
             else if(a==JOptionPane.NO_OPTION){
                  txt_nama.requestFocus();
                         
        }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),"Error! No.Kamar Sudah Terdaftar",JOptionPane.INFORMATION_MESSAGE);    
        }
    }
}//GEN-LAST:event_btn_ubahActionPerformed

private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
// TODO add your handling code here:
    String data[]=new String [7];
    
    if ((txt_nama.getText().isEmpty()) || (txt_nokamar.getText().isEmpty()))
    {
        JOptionPane.showMessageDialog(null,
                        "data tidak boleh kosong, harap dilengkapi");
        txt_nama.requestFocus();
    }
    else
    {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String    SQL = "INSERT INTO t_kamar(no_id,"
                            + "nama,"
                            +"no_kamar,"
                            + "tipe_kamar,"
                            + "lantai) "
                                +"VALUES "
                            + "( '"+combo_id.getSelectedItem().toString()+"',"
                            + " ' "+txt_nama.getText()+" ' ,"
                            + " ' "+txt_nokamar.getText()+"',"
                            + " ' "+combo_tipe.getSelectedItem().toString()+"',"
                            + " ' "+txt_lantaikamar.getText()+" ')";
             int a = JOptionPane.showConfirmDialog(null,
                        "Apakah anda ingin menyimpan data kamar?");
             if(a== JOptionPane.YES_OPTION){
            stt.executeUpdate(SQL);
            data[0] = combo_id.getSelectedItem().toString();
            data[1] = txt_nama.getText();
            data[2] = txt_nokamar.getText();
            data[3] = combo_tipe.getSelectedItem().toString();
            data[4] = txt_lantaikamar.getText();
            tableModel.insertRow(0, data);
            stt.close();
            kon.close();
            membersihkan_teks();
            btn_simpan.setEnabled(false);
            nonaktif_teks();
             }
               else if(a==JOptionPane.NO_OPTION){
                  txt_nama.requestFocus();
        }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),"Error! No.Kamar Sudah Terdaftar",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}//GEN-LAST:event_btn_simpanActionPerformed

private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
// TODO add your handling code here:
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "Delete From `t_kamar`"
                      + "where "
                      +"`no_id`='"+tableModel.getValueAt(row, 0).toString()+"'";
                       int a = JOptionPane.showConfirmDialog(null,
                        "Apakah anda ingin menghapus data kamar?");
                       if(a== JOptionPane.YES_OPTION){
                      stt.executeUpdate(SQL);
                      tableModel.removeRow(row);
                      stt.close();
                      kon.close();
                      membersihkan_teks();
                       }
                       
                      else if(a==JOptionPane.NO_OPTION){
                        txt_nama.requestFocus();
                      }                       
    }
    catch (Exception ex)
    {
        System.err.println(ex.getMessage());
    }
}//GEN-LAST:event_btn_hapusActionPerformed

private void btn_fasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fasilitasActionPerformed
// TODO add your handling code here:
    frm_fasilitas_tambahan fasilitas = null;
        try {
            fasilitas = new frm_fasilitas_tambahan();
        } catch (SQLException ex) {
            Logger.getLogger(frm_kamar.class.getName()).log(Level.SEVERE, null, ex);
        }
    fasilitas.setVisible(true);
    dispose();
}//GEN-LAST:event_btn_fasilitasActionPerformed

private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
// TODO add your handling code here:
    tableModel.setRowCount(0);
    //menggunakan queri untuk mencari
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "SELECT * FROM t_kamar WHERE no_kamar LIKE '%"+txt_cari.getText()+"%' OR no_id LIKE '%"+txt_cari.getText()+"%' "; 
        ResultSet res = stt.executeQuery(SQL);
        while(res.next())
        {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
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

private void btn_tampilkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilkanActionPerformed
// TODO add your handling code here:
    tableModel.setRowCount(0);
    settableload();
}//GEN-LAST:event_btn_tampilkanActionPerformed

private void menu_datakamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_datakamarMouseClicked
// TODO add your handling code here:
    data_kamar datakamar = new data_kamar();
    datakamar.setVisible(true);
}//GEN-LAST:event_menu_datakamarMouseClicked

private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
// TODO add your handling code here:
        txt_cari.setEnabled(true);
        membersihkan_teks();
        btn_tambah.setEnabled(true);
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        tabel_kamar.clearSelection();
        //membersihkan bekas pencarian
        txt_cari.setText("");
        tableModel.setRowCount(0);
        settableload();
        aktif_teks();
}//GEN-LAST:event_btn_batalActionPerformed

private void tabel_kamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_kamarMouseClicked
// TODO add your handling code here:
     if(evt.getClickCount()==1)
    {
        tampil_field();
    }
}//GEN-LAST:event_tabel_kamarMouseClicked

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
            java.util.logging.Logger.getLogger(frm_kamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_kamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_kamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_kamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new frm_kamar().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(frm_kamar.class.getName()).log(Level.SEVERE, null, ex);
                }   
                }    
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_fasilitas;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tampilkan;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox combo_id;
    private javax.swing.JComboBox combo_tipe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menu_datakamar;
    private javax.swing.JMenu menu_keluar;
    private javax.swing.JTable tabel_kamar;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_lantaikamar;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nokamar;
    // End of variables declaration//GEN-END:variables
}
