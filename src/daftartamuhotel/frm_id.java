/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frm_id.java
 *
 * Created on Jun 13, 2016, 8:43:33 PM
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
public class frm_id extends javax.swing.JFrame {

    //deklarasi variable
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    /** Creates new form frm_id */
    public frm_id() {
        initComponents();
        
         this.setLocationRelativeTo(null);
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_identitas.setModel(tableModel);
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
                                    "Kontak",
                                    "Alamat"
                                    }
                    
                )
                //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[]
            {
                false,false,false,false
            };
            public boolean isCellEditable(int rowIndex,int columnIndex )
            {
                return canEdit[columnIndex];
                
            }
        };
    }
    
    String data[]=new String [4];
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
            String SQL = "select * from t_identitas";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
               
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
    public void membersihkan_teks()
    {
        txt_noid.setText("");
        txt_nama.setText("");
        txt_kontak.setText("");
        txt_alamat.setText("");
       
    }
    public void nonaktif_teks()
    {
        txt_noid.setEnabled(false);
        txt_nama.setEnabled(false);
        txt_kontak.setEnabled(false);
        txt_alamat.setEnabled(false);
        
        
    }
    public void aktif_teks()
    {
        txt_noid.setEnabled(true);
        txt_nama.setEnabled(true);
        txt_kontak.setEnabled(true);
        txt_alamat.setEnabled(true);
       
    }
    
    int row = 0;
    public void tampil_field()
    {
        row = tabel_identitas.getSelectedRow();
        txt_noid.setText(tableModel.getValueAt(row, 0).toString());
        txt_nama.setText(tableModel.getValueAt(row, 1).toString());
        txt_kontak.setText(tableModel.getValueAt(row, 2).toString());
        txt_alamat.setText(tableModel.getValueAt(row, 3).toString());
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
        btn_tampil = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_identitas = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        btn_selanjutnya = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_noid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_kontak = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_keluar = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        menu_dataidentitas = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Identitas");
        setMinimumSize(new java.awt.Dimension(122, 34));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149959_Icon_Business_Set_00011_A.png"))); // NOI18N
        jLabel1.setText("Identitas Tamu");

        btn_tampil.setFont(new java.awt.Font("Times New Roman", 2, 12));
        btn_tampil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tampil.png"))); // NOI18N
        btn_tampil.setText("Tampilkan Keseluruhan Data");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel10.setText("Masukkan No.ID");

        btn_cari.setFont(new java.awt.Font("Times New Roman", 2, 14));
        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cari))
                    .addComponent(btn_tampil))
                .addGap(105, 105, 105))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_tampil)
                .addGap(18, 18, 18))
        );

        tabel_identitas.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_identitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_identitasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_identitas);

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

        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        btn_selanjutnya.setText("Pilih Kamar");
        btn_selanjutnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selanjutnyaActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Identitas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 0, 18), java.awt.Color.white)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("No.ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Kontak");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Alamat");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_noid, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_alamat, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_nama, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_kontak, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                        .addGap(40, 40, 40))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_noid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_kontak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14));

        menu_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1468182599_Close_Box_Red.png"))); // NOI18N
        menu_keluar.setText("Keluar");
        menu_keluar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        menu_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_keluarMouseClicked(evt);
            }
        });
        jMenu1.add(menu_keluar);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("View");

        menu_dataidentitas.setText("DataIdentitasTamu");
        menu_dataidentitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_dataidentitasMouseClicked(evt);
            }
        });
        jMenu4.add(menu_dataidentitas);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(btn_tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_simpan)
                        .addGap(171, 171, 171)
                        .addComponent(btn_selanjutnya))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(149, 149, 149))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(509, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(btn_selanjutnya, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tambah)
                            .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_simpan))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_keluar)
                .addGap(55, 55, 55))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-891)/2, (screenSize.height-640)/2, 891, 640);
    }// </editor-fold>//GEN-END:initComponents

private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
// TODO add your handling code here:
    membersihkan_teks();
    txt_noid.requestFocus();
    btn_simpan.setEnabled(true);
    btn_ubah.setEnabled(false);
    btn_hapus.setEnabled(false);
    btn_keluar.setEnabled(false);
    aktif_teks();
}//GEN-LAST:event_btn_tambahActionPerformed

private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
// TODO add your handling code here:
    String data[]=new String [7];
    
    if ((txt_noid.getText().isEmpty()) || (txt_nama.getText().isEmpty()) )
    {
        JOptionPane.showMessageDialog(null,
                        "data tidak boleh kosong, harap dilengkapi");
        txt_noid.requestFocus();
    }
    else
    {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String    SQL = "INSERT INTO t_identitas(no_id,"
                            + "nama,"
                            + "kontak,"
                            + "alamat) "
                                +"VALUES "
                    
                            + "( '"+txt_noid.getText()+"',"
                            + " ' "+txt_nama.getText()+" ' ,"
                            + " ' "+txt_kontak.getText()+" ' ,"
                            + " ' "+txt_alamat.getText()+" ')";                 
             int a = JOptionPane.showConfirmDialog(null,
                        "Apakah anda ingin menyimpan data identitas tamu?");
             if(a== JOptionPane.YES_OPTION){
            stt.executeUpdate(SQL);
            data[0] = txt_noid.getText();
            data[1] = txt_nama.getText();
            data[2] = txt_kontak.getText();
            data[3] = txt_alamat.getText();
            tableModel.insertRow(0, data);
            stt.close();
            kon.close();
            membersihkan_teks();
            btn_simpan.setEnabled(false);
          
            nonaktif_teks();
        }
             else if(a==JOptionPane.NO_OPTION){
                  txt_noid.requestFocus();
        }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),"Error! No.ID Sudah Terdaftar",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}//GEN-LAST:event_btn_simpanActionPerformed

private void tabel_identitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_identitasMouseClicked
// TODO add your handling code here:
    if(evt.getClickCount()==1)
    {
        tampil_field();
    }
}//GEN-LAST:event_tabel_identitasMouseClicked

private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
// TODO add your handling code here:
    String no_id=txt_noid.getText();
    String nama=txt_nama.getText();
    String kontak=txt_kontak.getText();
    String alamat=txt_alamat.getText();
  
    
    if ((no_id.isEmpty()) | (kontak.isEmpty()))
    {
        JOptionPane.showMessageDialog(null,"data tidak boleh kosong, harap dilengkapi");
        txt_noid.requestFocus();
    }
    else
    {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "UPDATE `t_identitas` "
                         + "SET `no_id`='"+no_id+"',"
                         + "`nama`='"+nama+"',"
                         + "`kontak`='"+kontak+"',"
                         + "`alamat`='"+alamat+"' "
                      + "WHERE "
                      +"`no_id`='"+tableModel.getValueAt(row, 0).toString()+"';";
             int a = JOptionPane.showConfirmDialog(null,
                        "Apakah anda ingin mengubah data identitas tamu?");
             if(a== JOptionPane.YES_OPTION){
           stt.executeUpdate(SQL);
           data[0] = no_id;
           data[1] = nama;
           data[2] = kontak;
           data[3] = alamat;
           tableModel.removeRow(row);
           tableModel.insertRow(row, data);
           stt.close();
           kon.close();
           membersihkan_teks();
           btn_simpan.setEnabled(false);
           nonaktif_teks();
             }
           else if(a==JOptionPane.NO_OPTION){
                  txt_noid.requestFocus();
        }               
                         
        }
        catch (Exception ex)
        {
           JOptionPane.showMessageDialog(null,
                    ex.getMessage(),"Error! No.ID Sudah Terdaftar",JOptionPane.INFORMATION_MESSAGE);
            
        }
    }
}//GEN-LAST:event_btn_ubahActionPerformed

private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
// TODO add your handling code here:
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "Delete From `t_identitas`"
                      + "where "
                      +"`no_id`='"+tableModel.getValueAt(row, 0).toString()+"'";
                      int a = JOptionPane.showConfirmDialog(null,
                        "Apakah anda ingin menghapus data identitas tamu?");
                       if(a== JOptionPane.YES_OPTION){
                      stt.executeUpdate(SQL);
                      tableModel.removeRow(row);
                      stt.close();
                      kon.close();
                      membersihkan_teks();
                      }
                       else if(a==JOptionPane.NO_OPTION){
                        txt_noid.requestFocus();
                       }        
            
       }
    catch (Exception ex)
    {
        System.err.println(ex.getMessage());
    }
}//GEN-LAST:event_btn_hapusActionPerformed

private void btn_selanjutnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selanjutnyaActionPerformed
    //memanggil form kamar
    frm_kamar kamar = null;
        try {
            kamar = new frm_kamar();
        } catch (SQLException ex) {
            Logger.getLogger(frm_id.class.getName()).log(Level.SEVERE, null, ex);
        }
    kamar.setVisible(true);
    
    dispose();
    
    
    // TODO add your handling code here:
}//GEN-LAST:event_btn_selanjutnyaActionPerformed

private void menu_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_keluarMouseClicked
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_menu_keluarMouseClicked

private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_btn_keluarActionPerformed

private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
// TODO add your handling code here:
    tableModel.setRowCount(0);
    settableload();
}//GEN-LAST:event_btn_tampilActionPerformed

private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
// TODO add your handling code here:
    tableModel.setRowCount(0);
    //menggunakan queri untuk mencari
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "SELECT * FROM t_identitas WHERE no_id LIKE '%"+txt_cari.getText()+"%'"; 
        ResultSet res = stt.executeQuery(SQL);
        while(res.next())
        {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
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

private void menu_dataidentitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_dataidentitasMouseClicked
// TODO add your handling code here:
    data_identitas data_identitas = new data_identitas();
    data_identitas.setVisible(true);
}//GEN-LAST:event_menu_dataidentitasMouseClicked

private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
// TODO add your handling code here:
        txt_cari.setEnabled(true);
        membersihkan_teks();
        btn_tambah.setEnabled(true);
        btn_keluar.setEnabled(true);
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        tabel_identitas.clearSelection();
        //membersihkan bekas pencarian
        txt_cari.setText("");
        tableModel.setRowCount(0);
        settableload();
        aktif_teks();
}//GEN-LAST:event_btn_batalActionPerformed

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
            java.util.logging.Logger.getLogger(frm_id.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_id.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_id.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_id.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frm_id().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_selanjutnya;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menu_dataidentitas;
    private javax.swing.JMenu menu_keluar;
    private javax.swing.JTable tabel_identitas;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_kontak;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_noid;
    // End of variables declaration//GEN-END:variables
}