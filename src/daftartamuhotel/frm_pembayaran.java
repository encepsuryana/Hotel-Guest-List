/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frm_pembayaran.java
 *
 * Created on Jun 26, 2016, 12:50:08 PM
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
 * @author Hasna
 */
public final class frm_pembayaran extends javax.swing.JFrame {
    
    //deklarasi variable
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;

    /** Creates new form frm_pembayaran */
    public frm_pembayaran() throws SQLException {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_pembayaran.setModel(tableModel);
        settableload();
        auto_pembayaran();
        auto_idkamar();
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
    
    private void auto_idkamar() throws SQLException {
            Connection kon = DriverManager.getConnection(database,user,pass);  
            java.sql.Statement stmt;
            stmt = kon.createStatement();
            String sql = "Select * From t_kamar order by no_kamar asc";
            java.sql.ResultSet rslt = stmt.executeQuery(sql);
            while (rslt.next()) {
                String noid = rslt.getString("no_kamar");
                no_kamar.addItem(noid);
            }
        } 
    
    
    public void auto_pembayaran() {
    try {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database, user, pass);
        Statement stt = kon.createStatement();
        String sql = "SELECT * FROM t_pembayaran ORDER BY no_pembayaran DESC";
        ResultSet rs = stt.executeQuery(sql);
        if (rs.next()) {
            String kd_barang = rs.getString("no_pembayaran").substring(1);
            String AN = ""+(Integer.parseInt(kd_barang)+1);
            String Nol = "";
            if(AN.length()==1)
                 {Nol = "000";}
            else if(AN.length()==2)
                 {Nol = "00";}
            else if(AN.length()==3)
                 {Nol = "0";}
            else if(AN.length()==4)
                 {Nol = "";}
            txt_pembayaran.setText("H"+Nol+AN);
            } else {
                txt_pembayaran.setText("H0001");
        } 
            rs.close();
            kon.close();
        } catch (Exception ex) {
              System.err.println(ex.getMessage());
        }
    }
     public void tanggal(){
        Date tgl = new Date();    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String str_tanggal = (tableModel.getValueAt(row, 8).toString());
        try {
             Date date = sdf.parse(str_tanggal);
             tgl_masuk.setDate(date);
             tgl_keluar.setDate(date);
        } catch (Exception e) { 
      } 
    }
     
    public void membersihkan_teks()
    {
        txt_pembayaran.setText("");
        txt_id.setText("");
        no_kamar.setSelectedItem("");
        ((JTextField)tgl_masuk.getDateEditor().getUiComponent()).setText("");
        ((JTextField)tgl_keluar.getDateEditor().getUiComponent()).setText("");
        txt_totalhari.setText("");
        txt_hargakamar.setText("");
        txt_hargafasilitas.setText("");
        txt_totalbayar.setText("");
    }
    public void nonaktif_teks()
    {
      txt_pembayaran.setEnabled(false);
        txt_id.setEnabled(false);
        no_kamar.setEnabled(false);
        tgl_masuk.setDate(null);
        tgl_keluar.setEnabled(false);
        txt_totalhari.setEnabled(false);
        txt_hargakamar.setEnabled(false);
        txt_hargafasilitas.setEnabled(false);
        txt_totalbayar.setEnabled(false);  
    }
    public void aktif_teks()
    {
       txt_pembayaran.setEnabled(true);
        txt_id.setEnabled(true);
        no_kamar.setEnabled(true);
        tgl_masuk.setEnabled(true);
        tgl_keluar.setEnabled(true);
        tgl_keluar.setEnabled(true);
        txt_totalhari.setEnabled(true);
        txt_hargakamar.setEnabled(true);
        txt_hargafasilitas.setEnabled(true);
        txt_totalbayar.setEnabled(true);  
    }
    
    int row = 0;
    public void tampil_field()
    {
        row = tabel_pembayaran.getSelectedRow();
        txt_pembayaran.setText(tableModel.getValueAt(row, 0).toString());
        txt_id.setText(tableModel.getValueAt(row, 1).toString());
        no_kamar.setSelectedItem(tableModel.getValueAt(row, 2).toString());
        tanggal();
        tanggal();
        txt_totalhari.setText(tableModel.getValueAt(row, 5).toString());
        txt_hargakamar.setText(tableModel.getValueAt(row, 6).toString());
        txt_hargafasilitas.setText(tableModel.getValueAt(row,7).toString());
        txt_totalbayar.setText(tableModel.getValueAt(row, 8).toString());
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
        jLabel14 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_tampil = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pembayaran = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_pembayaran = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_totalhari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_hargafasilitas = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_totalbayar = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_selesai = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_hargakamar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btn_total = new javax.swing.JButton();
        tgl_keluar = new com.toedter.calendar.JDateChooser();
        tgl_masuk = new com.toedter.calendar.JDateChooser();
        no_kamar = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_keluar = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menu_bayar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cari))
                    .addComponent(btn_tampil))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cari))
                        .addGap(18, 18, 18)
                        .addComponent(btn_tampil)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabel_pembayaran.setBackground(new java.awt.Color(153, 255, 153));
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("No.ID");

        txt_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_idMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_idMousePressed(evt);
            }
        });
        txt_id.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txt_idComponentAdded(evt);
            }
        });
        txt_id.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_idInputMethodTextChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("No.Kamar");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("No.Pembayaran");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Tgl.Masuk");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Tgl.Keluar");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Total Hari");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Harga Fasilitas Tambahan");

        txt_hargafasilitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_hargafasilitasMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setText("Total Bayar");

        btn_tambah.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149763_icon-105-folder-add.png"))); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149880_system-software-update.png"))); // NOI18N
        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_simpan.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149352_Save.png"))); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_hapus.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149501_1-04.png"))); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_batal.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469149813_stop.png"))); // NOI18N
        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_selesai.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_selesai.setText("Selesai");
        btn_selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selesaiActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel10.setText("Harga Kamar/Malam");

        txt_hargakamar.setFont(new java.awt.Font("Tahoma", 0, 12));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel11.setText("Single room Rp 150.000,00");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel12.setText("Twin room Rp 250.000,00");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel13.setText("Family room Rp 450.000,00");

        btn_total.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_total.setText("Total");
        btn_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_totalActionPerformed(evt);
            }
        });

        tgl_keluar.setDateFormatString("yyyy-MM-dd");

        tgl_masuk.setDateFormatString("yyyy-MM-dd");

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

        menu_bayar.setText("DataPembayaran");
        menu_bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_bayarMouseClicked(evt);
            }
        });
        jMenu2.add(menu_bayar);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_hargafasilitas)
                                    .addComponent(txt_totalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txt_hargakamar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btn_total)
                                        .addComponent(jLabel13))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(no_kamar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_totalhari, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tgl_keluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_id, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(txt_pembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(tgl_masuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(1304, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_tambah)
                .addGap(34, 34, 34)
                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(btn_simpan)
                .addGap(26, 26, 26)
                .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_hapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 486, Short.MAX_VALUE)
                .addComponent(btn_selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(no_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(tgl_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(tgl_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_totalhari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_hargakamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_hargafasilitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_totalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_total)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tambah)
                            .addComponent(btn_ubah)
                            .addComponent(btn_batal)
                            .addComponent(btn_simpan)
                            .addComponent(btn_hapus))
                        .addGap(76, 76, 76))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btn_selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
// TODO add your handling code here:
     
    membersihkan_teks();
    auto_pembayaran();
    txt_pembayaran.requestFocus();
    btn_simpan.setEnabled(true);
    btn_ubah.setEnabled(false);
    btn_hapus.setEnabled(false);
    btn_selesai.setEnabled(false);
   
    aktif_teks();
}//GEN-LAST:event_btn_tambahActionPerformed

private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
// TODO add your handling code here:
    String data[]=new String[9];
    
    if ((txt_pembayaran.getText().isEmpty()) || (txt_id.getText().isEmpty()))
    {
        JOptionPane.showMessageDialog(null, "data tidak boleh kosong, harap dilengkapi");
        
        txt_pembayaran.requestFocus();
    }
    else
    {
       try
       {
           Class.forName(driver);
           Connection kon = DriverManager.getConnection(database,user,pass);
           Statement stt = kon.createStatement();
           String SQL = "INSERT INTO t_pembayaran(no_pembayaran,"
                        +"no_id,"
                        +"no_kamar,"
                        +"tgl_masuk,"
                        +"tgl_keluar,"
                        +"total_hari,"
                        +"harga_kamar,"
                        +"harga_fasilitas_tambahan,"
                        +"total_bayar) "
                        +"VALUES "
                       +"('"+txt_pembayaran.getText()+"',"
                       +"'"+txt_id.getText()+"',"
                       +"(select no_kamar from t_kamar where no_kamar='"+no_kamar.getSelectedItem().toString()+"'),"
                       +"'"+((JTextField)tgl_masuk.getDateEditor().getUiComponent()).getText()+"',"
                       +"'"+((JTextField)tgl_keluar.getDateEditor().getUiComponent()).getText()+"',"
                       +"'"+txt_totalhari.getText()+"',"
                       +"'"+txt_hargakamar.getText()+"',"
                       +"'"+txt_hargafasilitas.getText()+"',"
                       +"'"+txt_totalbayar.getText()+"')";
           
           stt.executeUpdate(SQL);
           data[0] = txt_pembayaran.getText();
           data[1] = txt_id.getText();
           data[2] = no_kamar.getSelectedItem().toString();
           data[3] = ((JTextField)tgl_masuk.getDateEditor().getUiComponent()).getText();
           data[4] = ((JTextField)tgl_keluar.getDateEditor().getUiComponent()).getText();
           data[5] = txt_totalhari.getText();
           data[6] = txt_hargakamar.getText();
           data[7] = txt_hargafasilitas.getText();
           data[8] = txt_totalbayar.getText();
           tableModel.insertRow(0,data);
           stt.close();
           kon.close();
           membersihkan_teks();
           tabel_pembayaran.clearSelection();
             tableModel.setRowCount(0);
               settableload();
           auto_pembayaran();
           btn_simpan.setEnabled(false);
           nonaktif_teks();
           
           
       }
       catch(Exception ex)
       {
           JOptionPane.showMessageDialog(null,ex.getMessage(),"Error! No.Pembayaran Sudah Terdaftar",JOptionPane.INFORMATION_MESSAGE);
       }
    }
}//GEN-LAST:event_btn_simpanActionPerformed

private void btn_selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selesaiActionPerformed
// TODO add your handling code here:
    
    dispose();
}//GEN-LAST:event_btn_selesaiActionPerformed

private void txt_idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_idMouseClicked
// TODO add your handling code here:
     frm_id id = new frm_id();
    id.setVisible(true);
    
   
}//GEN-LAST:event_txt_idMouseClicked

private void txt_idInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_idInputMethodTextChanged
// TODO add your handling code here:
   
}//GEN-LAST:event_txt_idInputMethodTextChanged

private void txt_idComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txt_idComponentAdded
// TODO add your handling code here:
}//GEN-LAST:event_txt_idComponentAdded

private void txt_hargafasilitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_hargafasilitasMouseClicked
// TODO add your handling code here:
    frm_fasilitas_tambahan fasilitas = null;
        try {
            fasilitas = new frm_fasilitas_tambahan();
        } catch (SQLException ex) {
            Logger.getLogger(frm_pembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
    fasilitas.setVisible(true);
}//GEN-LAST:event_txt_hargafasilitasMouseClicked

private void tabel_pembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pembayaranMouseClicked
// TODO add your handling code here:
   if (evt.getClickCount() ==1)
   {
       tampil_field();
   }
}//GEN-LAST:event_tabel_pembayaranMouseClicked

private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
// TODO add your handling code here:
    String no_bayar=txt_pembayaran.getText();
    String no_id=txt_id.getText();
    String nokamar = no_kamar.getSelectedItem().toString(); 
    String masuk=((JTextField)tgl_masuk.getDateEditor().getUiComponent()).getText();
    String keluar=((JTextField)tgl_keluar.getDateEditor().getUiComponent()).getText();
    String total_hari = txt_totalhari.getText();
    String harga_kamar = txt_hargakamar.getText();
    String fasilitas = txt_hargafasilitas.getText();
    String total_bayar = txt_totalbayar.getText();
    
    if ((no_bayar.isEmpty())|(total_bayar.isEmpty())|(masuk.isEmpty())|(keluar.isEmpty()))
    {
        JOptionPane.showMessageDialog(null,"Data tidak boleh kosong, harap dilengkapi");
        txt_pembayaran.requestFocus();
    }
    else
    {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "UPDATE `t_pembayaran`"
                        +"SET `no_pembayaran`='"+no_bayar+"',"
                        +"`no_id` ='"+no_id+"',"
                        +"`no_kamar`='"+nokamar+"',"
                        +"`tgl_masuk`='"+masuk+"',"
                        +"`tgl_keluar`='"+keluar+"',"
                        +"`total_hari`='"+total_hari+"',"
                        +"`harga_kamar`='"+harga_kamar+"',"
                        +"`harga_fasilitas_tambahan`='"+fasilitas+"',"
                        +"`total_bayar`='"+total_bayar+"'"
                    +"WHERE"
                    +"`no_pembayaran`='"+tableModel.getValueAt(row, 0).toString()+"';";
            stt.executeUpdate(SQL);
            data[0] = no_bayar;
            data[1] = no_id;
            data[2] = nokamar;
            data[3] = masuk;
            data[4] = keluar;
            data[5] = total_hari;
            data[6] = harga_kamar;
            data[7] = fasilitas;
            data[8] = total_bayar;
            tableModel.removeRow(row);
            tableModel.insertRow(row,data);
            stt.close();
            kon.close();
            membersihkan_teks();
            btn_simpan.setEnabled(false);
            auto_pembayaran();
            nonaktif_teks();
        }
        catch (Exception ex)
        {
           JOptionPane.showMessageDialog(null,ex.getMessage(),"Error! No.Pembayaran Sudah Terdaftar",JOptionPane.INFORMATION_MESSAGE);
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
        String SQL = "Delete From `t_pembayaran`"
                     +"where"
                     +"`no_pembayaran`='"+tableModel.getValueAt(row, 0).toString()+"'";
        stt.executeUpdate(SQL);
        tableModel.removeRow(row);
        stt.close();
        kon.close();
        auto_pembayaran();
        membersihkan_teks();
        
    }
    catch (Exception ex)
    {
        System.err.println(ex.getMessage());
    }
}//GEN-LAST:event_btn_hapusActionPerformed

private void txt_idMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_idMousePressed
// TODO add your handling code here:
}//GEN-LAST:event_txt_idMousePressed

private void menu_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_keluarMouseClicked
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_menu_keluarMouseClicked

private void btn_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_totalActionPerformed
// TODO add your handling code here:
     int total_hari = Integer.parseInt(txt_totalhari.getText());
    int harga_kamar   = Integer.parseInt(txt_hargakamar.getText());
    int fasilitas  = Integer.parseInt(txt_hargafasilitas.getText());
    
    int total_bayar = (total_hari*harga_kamar) + fasilitas;
    txt_totalbayar.setText(total_bayar+"");
}//GEN-LAST:event_btn_totalActionPerformed

private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
// TODO add your handling code here:
        txt_cari.setEnabled(true);
        membersihkan_teks();
        btn_tambah.setEnabled(true);
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        tabel_pembayaran.clearSelection();
        //membersihkan bekas pencarian
        txt_cari.setText("");
        tableModel.setRowCount(0);
        settableload();
        auto_pembayaran();
        aktif_teks();
}//GEN-LAST:event_btn_batalActionPerformed

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

private void menu_bayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_bayarMouseClicked
// TODO add your handling code here:
    data_pembayaran bayar = new data_pembayaran();
    bayar.setVisible(true);
            
}//GEN-LAST:event_menu_bayarMouseClicked

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
            java.util.logging.Logger.getLogger(frm_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new frm_pembayaran().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(frm_pembayaran.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_selesai;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JButton btn_total;
    private javax.swing.JButton btn_ubah;
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
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menu_bayar;
    private javax.swing.JMenu menu_keluar;
    private javax.swing.JComboBox no_kamar;
    private javax.swing.JTable tabel_pembayaran;
    private com.toedter.calendar.JDateChooser tgl_keluar;
    private com.toedter.calendar.JDateChooser tgl_masuk;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_hargafasilitas;
    private javax.swing.JTextField txt_hargakamar;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_pembayaran;
    private javax.swing.JTextField txt_totalbayar;
    private javax.swing.JTextField txt_totalhari;
    // End of variables declaration//GEN-END:variables
}
