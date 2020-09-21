/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frm_fasilitas_tambahan.java
 *
 * Created on Jun 18, 2016, 8:55:57 AM
 */
package daftartamuhotel;
import com.sun.rowset.internal.InsertRow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
//Fungsi Import untuk SQL
import java.sql.*;




/**
 *
 * @author User
 */
public class frm_fasilitas_tambahan extends javax.swing.JFrame {
    //deklarasi variable
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    /** Creates new form frm_fasilitas_tambahan */
    public frm_fasilitas_tambahan() throws SQLException {
        initComponents();
        
         this.setLocationRelativeTo(null);
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_fasilitas_tambahan.setModel(tableModel);
        settableload();
        auto_fasilitastambahan();
        auto_idkamar();
    }
    
    private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel()
    {
        //membuat judul header
        return new javax.swing.table.DefaultTableModel
        (
           new Object[][] {},
           new String [] {"No.FT",
                           "No.Kamar",
                          "Extra Bed",
                          "Bantal",
                          "Laundry(/Kg)",
                          "Harga"}
               
           
      )
      //disable prubahan grid
      {
            boolean[] canEdit = new boolean[]
            {
                false,false,false,false,false,false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
      };
    }
    
    String data[]=new String[6];
    private void settableload()
    {
        String stat = "";
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            
            Statement stt=kon.createStatement();
            String SQL = "select * from t_fasilitas_tambahan";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
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
    
    private void auto_idkamar() throws SQLException {
            Connection kon = DriverManager.getConnection(database,user,pass);  
            java.sql.Statement stmt;
            stmt = kon.createStatement();
            String sql = "Select * From t_kamar order by no_kamar asc";
            java.sql.ResultSet rslt = stmt.executeQuery(sql);
            while (rslt.next()) {
                String noid = rslt.getString("no_kamar");
                combo_kamar.addItem(noid);
            }
        } 
     public void auto_fasilitastambahan() {
    try {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database, user, pass);
        Statement stt = kon.createStatement();
        String sql = "SELECT * FROM t_fasilitas_tambahan ORDER BY no_ft DESC";
        ResultSet rs = stt.executeQuery(sql);
        if (rs.next()) {
            String ft = rs.getString("no_ft").substring(1);
            String AN = ""+(Integer.parseInt(ft)+1);
            String Nol = "";
            if(AN.length()==1)
                 {Nol = "000";}
            else if(AN.length()==2)
                 {Nol = "00";}
            else if(AN.length()==3)
                 {Nol = "0";}
            else if(AN.length()==4)
                 {Nol = "";}
            
            txt_ft.setText("F"+Nol+AN);
            } else {
                txt_ft.setText("F0001");
        } 
            rs.close();
            kon.close();
        } catch (Exception ex) {
              System.err.println(ex.getMessage());
        }
    }
    
    public void membersihkan_teks()
    {
        txt_ft.setText("");
        combo_kamar.setSelectedItem("");
        txt_extrabed.setText("");
        txt_bantal.setText("");
        txt_laundry.setText("");
        txt_harga.setText("");
    }
    public void nonaktif_teks()
    {
        txt_ft.setEnabled(false);
        combo_kamar.setEnabled(false);
        txt_extrabed.setEnabled(false);
        txt_bantal.setEnabled(false);
        txt_laundry.setEnabled(false);
        txt_harga.setEnabled(false);
                
    }
    public void aktif_teks()
    {
        txt_ft.setEnabled(true);
        combo_kamar.setEnabled(true);
        txt_extrabed.setEnabled(true);
        txt_bantal.setEnabled(true);
        txt_laundry.setEnabled(true);
        txt_harga.setEnabled(true);
        
    }
    
    int row = 0;
    public void tampil_field()
    {
        row = tabel_fasilitas_tambahan.getSelectedRow();
        txt_ft.setText(tableModel.getValueAt(row, 0).toString());
        combo_kamar.setSelectedItem(tableModel.getValueAt(row, 1).toString());
        txt_extrabed.setText(tableModel.getValueAt(row, 2).toString());
        txt_bantal.setText(tableModel.getValueAt(row, 3).toString());
        txt_laundry.setText(tableModel.getValueAt(row, 4).toString());
        txt_harga.setText(tableModel.getValueAt(row, 5).toString());
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(true);
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
        txt_cari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btn_tampil = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_extrabed = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_bantal = new javax.swing.JTextField();
        txt_laundry = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_fasilitas_tambahan = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_pembayaran = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_harga = new javax.swing.JTextField();
        btn_hitung = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_ft = new javax.swing.JTextField();
        btn_bayar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        combo_kamar = new javax.swing.JComboBox();
        keluar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_keluar = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 153));
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1469361376_pillows.png"))); // NOI18N
        jLabel1.setText("Fasilitas Tambahan");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel10.setText("Masukkan No.FT/No.Kamar");

        btn_tampil.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_tampil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tampil.png"))); // NOI18N
        btn_tampil.setText("Tampilkan Keseluruhan Data");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_tampil)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cari)
                        .addGap(196, 196, 196))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_tampil)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("Extra Bed");

        txt_extrabed.setFont(new java.awt.Font("Tahoma", 0, 14));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setText("Bantal");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel4.setText("Laundry");

        txt_bantal.setFont(new java.awt.Font("Tahoma", 0, 14));

        txt_laundry.setFont(new java.awt.Font("Tahoma", 0, 14));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel5.setText("(Rp 5000/Kg)");

        tabel_fasilitas_tambahan.setBackground(new java.awt.Color(255, 102, 153));
        tabel_fasilitas_tambahan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_fasilitas_tambahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_fasilitas_tambahanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_fasilitas_tambahan);

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

        btn_pembayaran.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_pembayaran.setText("Pembayaran");
        btn_pembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pembayaranActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel6.setText("Harga");

        txt_harga.setFont(new java.awt.Font("Tahoma", 0, 14));

        btn_hitung.setFont(new java.awt.Font("Tahoma", 0, 12));
        btn_hitung.setText("Hitung");
        btn_hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hitungActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel7.setText("(Rp 5000/Bantal)");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel8.setText("(Rp 50000/Bed)");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel9.setText("No.FT");

        btn_bayar.setText("Pembayaran");
        btn_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bayarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel12.setText("No.Kamar");

        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/kembali.png"))); // NOI18N
        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        keluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keluarKeyPressed(evt);
            }
        });

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

        jMenu2.setText("View");

        jMenu3.setBackground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("DataFasilitasTambahan");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12))
                        .addGap(19, 19, 19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_bantal, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(txt_laundry, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(txt_extrabed, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(txt_ft, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(combo_kamar, 0, 90, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_hitung))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btn_ubah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_batal, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(btn_simpan))))
                .addGap(119, 119, 119)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(621, 621, 621)
                .addComponent(keluar)
                .addGap(157, 157, 157)
                .addComponent(btn_bayar)
                .addGap(54, 54, 54)
                .addComponent(btn_pembayaran)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(txt_ft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(combo_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_extrabed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_bantal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_laundry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hitung)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_tambah)
                                    .addComponent(btn_simpan))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_batal)
                                    .addComponent(btn_hapus)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_ubah)
                                .addGap(38, 38, 38))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btn_pembayaran))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_bayar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(keluar)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1012)/2, (screenSize.height-681)/2, 1012, 681);
    }// </editor-fold>//GEN-END:initComponents

private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
// TODO add your handling code here:
    membersihkan_teks();
    txt_extrabed.requestFocus();
    btn_simpan.setEnabled(true);
    btn_ubah.setEnabled(false);
    btn_hapus.setEnabled(false);
    btn_pembayaran.setEnabled(false);
    auto_fasilitastambahan();
    aktif_teks();
    
}//GEN-LAST:event_btn_tambahActionPerformed

private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
// TODO add your handling code here:
    String data[] = new String[6];
    try
    {
    Class.forName(driver);
    Connection kon = DriverManager.getConnection(database,user,pass);
    Statement stt = kon.createStatement();
    String SQL = "INSERT INTO t_fasilitas_tambahan(no_ft,"
                     +"no_kamar,"
                    +"extra_bed,"
                    +"bantal,"
                    +"laundry,"
                    +"harga_fasilitas_tambahan) "
                        +"VALUES "
                    +"( '"+txt_ft.getText()+"',"
                    +"(select no_kamar from t_kamar where no_kamar='"+combo_kamar.getSelectedItem().toString()+"'),"
                    +" '"+txt_extrabed.getText()+"',"
                    +" '"+txt_bantal.getText()+"',"
                    +" '"+txt_laundry.getText()+"',"
                    +"' "+txt_harga.getText()+" ' )";
     int a = JOptionPane.showConfirmDialog(null,
                        "Apakah anda ingin menyimpan data fasilitas tambahan?");
     if(a== JOptionPane.YES_OPTION){
    stt.executeUpdate(SQL);
    data[0] = txt_ft.getText();
    data[1] = combo_kamar.getSelectedItem().toString();
    data[2] = txt_extrabed.getText();
    data[3] = txt_bantal.getText();
    data[4] = txt_laundry.getText();
    data[5] = txt_harga.getText();
    tableModel.insertRow(0, data);
    stt.close();
    kon.close();
    membersihkan_teks();
    tabel_fasilitas_tambahan.clearSelection();
             tableModel.setRowCount(0);
               settableload();
    auto_fasilitastambahan();
    btn_simpan.setEnabled(false);
    nonaktif_teks();
     }
      else if(a==JOptionPane.NO_OPTION){
                  txt_ft.requestFocus();
        }
    }
    catch(Exception ex)
    {
        JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
    }                   
}//GEN-LAST:event_btn_simpanActionPerformed

private void btn_hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hitungActionPerformed
// TODO add your handling code here:
    //referensi http://www.academia.edu/8932952/APLIKASI_PERHITUNGAN_SEDERHANA_DENGAN_MENGGUNAKAN_NETBEANS
    
    int extrabed = Integer.parseInt(txt_extrabed.getText());
    int bantal   = Integer.parseInt(txt_bantal.getText());
    int laundry  = Integer.parseInt(txt_laundry.getText());
    
    int harga = (50000*extrabed) + (5000*bantal) + (5000*laundry);
    txt_harga.setText(harga+"");
   
}//GEN-LAST:event_btn_hitungActionPerformed

private void tabel_fasilitas_tambahanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_fasilitas_tambahanMouseClicked
// TODO add your handling code here:
    if(evt.getClickCount()==1)
    {
        tampil_field();
    }
}//GEN-LAST:event_tabel_fasilitas_tambahanMouseClicked

private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
// TODO add your handling code here:
    int  ubah = JOptionPane.showConfirmDialog(null,"Apakah anda ingin Mengubah data ini?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(ubah==JOptionPane.YES_OPTION) {
        
    String no_ft=txt_ft.getText();
    String nokamar = combo_kamar.getSelectedItem().toString(); 
    String extrabed=txt_extrabed.getText();
    String bantal=txt_bantal.getText();
    String laundry=txt_laundry.getText();
    String harga=txt_harga.getText();
    
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "UPDATE `t_fasilitas_tambahan` "
                                    + "SET `no_ft`= '"+no_ft+"',"
                                    + "`no_kamar`= '"+nokamar+"',"
                                    + "`extra_bed`= '"+extrabed+"',"
                                    + "`bantal`= '"+bantal+"',"
                                    + "`laundry`= '"+laundry+"',"
                                    + "`harga_fasilitas_tambahan`= '"+harga+"'"
                            + "WHERE "
                            + "`no_ft`='"+tableModel.getValueAt(row, 0).toString()+"';";
                    stt.executeUpdate(SQL);
                    data[0]  = no_ft;
                    data[1]  = nokamar;
                    data[2]  = extrabed;
                    data[3]  = bantal;
                    data[4]  = laundry;
                    data[5]  = harga;
                    tableModel.removeRow(row);
        tableModel.insertRow(row,data);
        stt.close();
        kon.close();
        membersihkan_teks();
        btn_simpan.setEnabled(false);
        auto_fasilitastambahan();
        nonaktif_teks(); 
        
    }
    catch (Exception ex)
    {
        System.err.println(ex.getMessage());
    }
         
    } else if(ubah==JOptionPane.NO_OPTION) {
            membersihkan_teks();
        }
}//GEN-LAST:event_btn_ubahActionPerformed

private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
// TODO add your handling code here:
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "Delete From `t_fasilitas_tambahan`"
                        +"where "
                     +"`no_ft`='"+tableModel.getValueAt(row, 0).toString()+"'";
        int a = JOptionPane.showConfirmDialog(null,
                        "Apakah anda ingin menghapus data identitas tamu?");
                       if(a== JOptionPane.YES_OPTION){
                
    stt.executeUpdate(SQL);
    tableModel.removeRow(row);
    stt.close();
    kon.close();
    auto_fasilitastambahan();
    membersihkan_teks();
    }
     else if(a==JOptionPane.NO_OPTION){
                        txt_ft.requestFocus();
                       }        
    }
    catch (Exception ex)
    {
        System.err.println(ex.getMessage());
    }
    
}//GEN-LAST:event_btn_hapusActionPerformed

private void btn_pembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pembayaranActionPerformed
// TODO add your handling code here:
    frm_pembayaran bayar = null;
        try {
            bayar = new frm_pembayaran();
        } catch (SQLException ex) {
            Logger.getLogger(frm_fasilitas_tambahan.class.getName()).log(Level.SEVERE, null, ex);
        }
    bayar.setVisible(true);
   dispose();
}//GEN-LAST:event_btn_pembayaranActionPerformed

private void menu_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_keluarMouseClicked
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_menu_keluarMouseClicked

private void btn_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bayarActionPerformed
// TODO add your handling code here:
    frm_pembayaran bayar = null;
        try {
            bayar = new frm_pembayaran();
        } catch (SQLException ex) {
            Logger.getLogger(frm_fasilitas_tambahan.class.getName()).log(Level.SEVERE, null, ex);
        }
    bayar.setVisible(true);
    dispose();
}//GEN-LAST:event_btn_bayarActionPerformed

private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
// TODO add your handling code here:
    data_fasilitastambahan data = new data_fasilitastambahan();
    data.setVisible(true);
}//GEN-LAST:event_jMenu3MouseClicked

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
        String SQL = "SELECT * FROM t_fasilitas_tambahan WHERE no_ft LIKE '%"+txt_cari.getText()+"%'OR no_kamar LIKE '%"+txt_cari.getText()+"%'";
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

private void keluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keluarKeyPressed
// TODO add your handling code here:
    
}//GEN-LAST:event_keluarKeyPressed

private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_keluarActionPerformed

private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
// TODO add your handling code here:
        txt_cari.setEnabled(true);
        membersihkan_teks();
        btn_tambah.setEnabled(true);
        keluar.setEnabled(true);
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        tabel_fasilitas_tambahan.clearSelection();
        //membersihkan bekas pencarian
        txt_cari.setText("");
        tableModel.setRowCount(0);
        settableload();
        auto_fasilitastambahan();
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
            java.util.logging.Logger.getLogger(frm_fasilitas_tambahan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_fasilitas_tambahan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_fasilitas_tambahan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_fasilitas_tambahan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new frm_fasilitas_tambahan().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(frm_fasilitas_tambahan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_bayar;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_hitung;
    private javax.swing.JButton btn_pembayaran;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox combo_kamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton keluar;
    private javax.swing.JMenu menu_keluar;
    private javax.swing.JTable tabel_fasilitas_tambahan;
    private javax.swing.JTextField txt_bantal;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_extrabed;
    private javax.swing.JTextField txt_ft;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_laundry;
    // End of variables declaration//GEN-END:variables
}
