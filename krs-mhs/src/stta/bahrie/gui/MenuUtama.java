/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuUtama.java
 *
 * Created on Oct 16, 2010, 1:35:23 AM
 */
package stta.bahrie.gui;

import api.stta.bahrie.entiy.DetailKrs;
import api.stta.bahrie.entiy.Dosen;
import api.stta.bahrie.entiy.Mahasiswa;
import api.stta.bahrie.entiy.Mk;
import api.stta.bahrie.entiy.WaktuKrs;
import api.stta.bahrie.inter.DetailKrsInterf;
import api.stta.bahrie.inter.DosenInterf;
import api.stta.bahrie.inter.MahasiswaInterf;
import api.stta.bahrie.inter.MkInterf;
import api.stta.bahrie.inter.WaktuKrsInterf;
import java.awt.Dimension;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bahrie
 */
public class MenuUtama extends javax.swing.JFrame {

    Mahasiswa mhsPerson = new Mahasiswa();
    Dosen dsnPerson = new Dosen();
    List<Mk> mkList = new ArrayList<Mk>();
    List<WaktuKrs> wktList = new ArrayList<WaktuKrs>();
    List<Mk> mk2List = new ArrayList<Mk>();
    List<DetailKrs> detailList = new ArrayList<DetailKrs>();
    MahasiswaInterf mhsService;
    DosenInterf dsnService;
    MkInterf mkService;
    WaktuKrsInterf wktService;
    DetailKrsInterf detailService;
    boolean simpan=false;

    /** Creates new form MenuUtama */
    public MenuUtama(MahasiswaInterf mhs, DosenInterf dsn, MkInterf mk, WaktuKrsInterf wktu, DetailKrsInterf detailService) {
        try {
            this.mhsService = mhs;
            this.dsnService = dsn;
            this.mkService = mk;
            this.wktService = wktu;
            this.detailService = detailService;
            initComponents();
            this.statusAwal();
            logoutMenu.setEnabled(false);
            //        lblWktu.setText("waktu : "+String.valueOf(skrg.getTime()));
            //        lblWktu.setText("waktu : "+String.valueOf(skrg.getTime()));
        } catch (RemoteException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void updateJmlhSks() {
        int total = 0;
        for (Mk mk : mk2List) {
            total = total + mk.getSks();
        }
        txtJumlahKrs.setText(String.valueOf(total));

    }

    void menuPanel(boolean status) {
        panelMhs.setVisible(status);
        panelKrs.setVisible(status);
        panelBotton.setVisible(status);
    }

    void loadPertama() throws RemoteException {
        mkList = mkService.getAll();
    }

    void loadDosen() throws RemoteException {
        dsnPerson = dsnService.getByKd(mhsPerson.getKd_dosen());
    }

    void isiteks() {
        txtNim.setText(mhsPerson.getNim());
        txtNama.setText(mhsPerson.getNama());
        TxtJur.setText(mhsPerson.getProdi());
        TxtDsn.setText(dsnPerson.getDosen());
        txtJumlahKrs.setText(String.valueOf(mhsPerson.getJumlah_krs()));
        txtTa.setText("2010/2011");
    }

    void kosongkanTeks() {
        txtNim.setText("");
        txtNama.setText("");
        TxtJur.setText("");
        TxtDsn.setText("");
        txtTa.setText("");
        txtJumlahKrs.setText("");
    }

    void statusAwal() throws RemoteException {
        this.menuPanel(false);
        this.logoutMenu.setEnabled(false);
        this.kosongkanTeks();

    }

    private boolean isValidUser(String nim, String password) throws RemoteException {

        Mahasiswa mhs = mhsService.getByNim(nim);

        if (mhs != null) {
            if (mhs.getNim().equals(nim) && mhs.getPwd().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    void isiTabelMk() {
        Object data[][] = new Object[mkList.size()][4];
        int x = 0;
        for (Mk mk : mkList) {
            data[x][0] = mk.getKd_mk();
            data[x][1] = mk.getMk();
            data[x][2] = mk.getSks();
            data[x][3] = mk.getJurusan();
            x++;
        }
        String[] judul = {"Kode", "Nama", "Sks", "Jurusan"};
        tableKrsJur.setModel(new DefaultTableModel(data, judul));
        jScrollPane2.setViewportView(tableKrsJur);
    }

    void isitabelKrs() {
        Object data[][] = new Object[mk2List.size()][4];
        int x = 0;
        for (Mk mk : mk2List) {
            data[x][0] = mk.getKd_mk();
            data[x][1] = mk.getMk();
            data[x][2] = mk.getSks();
            data[x][3] = mk.getJurusan();
            x++;
        }
        String[] judul = {"Kode", "Nama", "Sks", "Jurusan"};
        tableAmbil.setModel(new DefaultTableModel(data, judul));
        jScrollPane1.setViewportView(tableAmbil);
    }

    void loadKrs() throws RemoteException {
        detailList = detailService.getByNim(mhsPerson.getNim());
        for (int a = 0; a < detailList.size(); a++) {
            Mk mk = mkService.getByMk(detailList.get(a).getKd_mk());
            mk2List.add(mk);
        }
    }

    boolean bolehTidak(Mk mk) {
        boolean status = true;
        for (int a = 0; a < mk2List.size(); a++) {
            if (mk2List.get(a).getKd_mk().equals(mk.getKd_mk())) {
                status = false;
                break;
            } else {
                status = true;
            }
        }
        return status;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        dialogKrs = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKrsJur = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        ambilButton = new javax.swing.JButton();
        tutupButton = new javax.swing.JButton();
        dialogLogin = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TxtLoginNim = new javax.swing.JTextField();
        txtLoginPwd = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        panelMhs = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtJumlahKrs = new javax.swing.JTextField();
        TxtJur = new javax.swing.JTextField();
        TxtDsn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTa = new javax.swing.JTextField();
        panelKrs = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAmbil = new javax.swing.JTable();
        panelBotton = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tglFormat = new javax.swing.JFormattedTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        loginMenu = new javax.swing.JMenuItem();
        logoutMenu = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jToolBar1.setRollover(true);

        jPanel4.setLayout(new java.awt.BorderLayout());

        tableKrsJur.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableKrsJur);

        ambilButton.setText("Ambil");
        ambilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ambilButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ambilButton);

        tutupButton.setText("Tutup");
        tutupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutupButtonActionPerformed(evt);
            }
        });
        jPanel1.add(tutupButton);

        javax.swing.GroupLayout dialogKrsLayout = new javax.swing.GroupLayout(dialogKrs.getContentPane());
        dialogKrs.getContentPane().setLayout(dialogKrsLayout);
        dialogKrsLayout.setHorizontalGroup(
            dialogKrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogKrsLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(400, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        dialogKrsLayout.setVerticalGroup(
            dialogKrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogKrsLayout.createSequentialGroup()
                .addGroup(dialogKrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel7.setText("Nim");

        jLabel8.setText("Password");

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout dialogLoginLayout = new javax.swing.GroupLayout(dialogLogin.getContentPane());
        dialogLogin.getContentPane().setLayout(dialogLoginLayout);
        dialogLoginLayout.setHorizontalGroup(
            dialogLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dialogLoginLayout.createSequentialGroup()
                        .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtLoginPwd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(TxtLoginNim, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addContainerGap())
        );
        dialogLoginLayout.setVerticalGroup(
            dialogLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtLoginNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLoginPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText("Ambil");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMhs.setBorder(javax.swing.BorderFactory.createTitledBorder("Mahasiswa"));

        jLabel1.setText("Nim");

        jLabel2.setText("Nama");

        jLabel3.setText("Jurusan");

        jLabel4.setText("Pembimbing");

        jLabel5.setText("jumlah KRS :");

        TxtDsn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDsnActionPerformed(evt);
            }
        });

        jLabel6.setText("Tahun Ajaran");

        javax.swing.GroupLayout panelMhsLayout = new javax.swing.GroupLayout(panelMhs);
        panelMhs.setLayout(panelMhsLayout);
        panelMhsLayout.setHorizontalGroup(
            panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMhsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtJumlahKrs, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(txtNim, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtDsn, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(TxtJur, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(txtTa, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMhsLayout.setVerticalGroup(
            panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMhsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMhsLayout.createSequentialGroup()
                        .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtJur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TxtDsn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelMhsLayout.createSequentialGroup()
                        .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMhsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtJumlahKrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelKrs.setBorder(javax.swing.BorderFactory.createTitledBorder("Ambil KRS"));

        tableAmbil.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableAmbil);

        javax.swing.GroupLayout panelKrsLayout = new javax.swing.GroupLayout(panelKrs);
        panelKrs.setLayout(panelKrsLayout);
        panelKrsLayout.setHorizontalGroup(
            panelKrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKrsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelKrsLayout.setVerticalGroup(
            panelKrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKrsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Ambil");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelBotton.add(jButton1);

        jButton3.setText("Simpan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelBotton.add(jButton3);

        jButton2.setText("Hapus");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelBotton.add(jButton2);

        jLabel9.setText("Tanggal :");

        tglFormat.setEditable(false);
        tglFormat.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd MMMM yyyy"))));
        tglFormat.setValue(new java.util.Date());

        Menu.setText("Menu");

        loginMenu.setText("Login");
        loginMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginMenuActionPerformed(evt);
            }
        });
        Menu.add(loginMenu);

        logoutMenu.setText("Logout");
        logoutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuActionPerformed(evt);
            }
        });
        Menu.add(logoutMenu);

        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        Menu.add(exitMenu);

        jMenuBar1.add(Menu);

        jMenu1.setText("Help");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("About");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMhs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelKrs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBotton, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tglFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelKrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tglFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtDsnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDsnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDsnActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_exitMenuActionPerformed

    private void loginMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginMenuActionPerformed
        // TODO add your handling code here:
        dialogLogin.setSize(350, 120);
        dialogLogin.setVisible(true);
    }//GEN-LAST:event_loginMenuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dialogKrs.setSize(500, 500);
        dialogKrs.setVisible(true);
        this.isiTabelMk();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        try {
            // TODO add your handling code here:
            if (isValidUser(TxtLoginNim.getText(), String.valueOf(txtLoginPwd.getText()))) {
                this.mhsPerson = mhsService.getByNim(TxtLoginNim.getText());
                if (mhsPerson != null) {
                    this.setTitle("Login : " + mhsPerson.getNim() + " - " + mhsPerson.getNama());
                }
                this.menuPanel(true);
                this.loadDosen();
                this.isiteks();
                loginMenu.setEnabled(false);
                logoutMenu.setEnabled(true);
                this.mkList = mkService.getByProdi(mhsPerson.getProdi());
                this.loadKrs();
                this.isitabelKrs();
                if (detailList.isEmpty()) {
                    simpan=false;
                } else {
                    simpan=true;
                }
                dialogLogin.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(this, "kesalahan Login");
                TxtLoginNim.setText("");
                txtLoginPwd.setText("");
                TxtLoginNim.requestFocus();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void tutupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutupButtonActionPerformed
        // TODO add your handling code here:
        dialogKrs.setVisible(false);
    }//GEN-LAST:event_tutupButtonActionPerformed

    private void ambilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ambilButtonActionPerformed
        try {
            // TODO add your handling code here:


            Mk kd = mkList.get(tableKrsJur.getSelectedRow());
            int cadangan = kd.getSks();
            if (bolehTidak(kd)) {
                if ((Integer.valueOf(txtJumlahKrs.getText()) + cadangan) <= 24) {

                    mk2List.add(kd);
                    this.isitabelKrs();
                    this.updateJmlhSks();
                } else {
                    JOptionPane.showMessageDialog(this, "SKS melebihi kuwota");
                }
            } else {
                JOptionPane.showMessageDialog(this, "matakuliah sudah diambil");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ambilButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        mk2List.remove(tableAmbil.getSelectedRow());
        this.isitabelKrs();
        this.updateJmlhSks();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            System.out.println("jalan dulu brur");
            if (simpan==false) {
                WaktuKrs wk = new WaktuKrs();
                wk.setId_wktu("" + System.currentTimeMillis());
                wk.setNim(txtNim.getText());
                wk.setSemester("ganjil");
                wk.setTa(txtTa.getText());
                wk.setTanggal_pengisian((Date) tglFormat.getValue());
                wktService.insert(wk);
                for (int a = 0; a < mk2List.size(); a++) {
                    DetailKrs dt = new DetailKrs();
                    dt.setId_wktu(wk.getId_wktu());
                    dt.setNim(txtNim.getText());
                    dt.setKd_mk(mk2List.get(a).getKd_mk());
                    detailService.insert(dt);
                }
                System.out.println("sudah masuk/belum");
            } else {
               
                for (int a = 0; a < mk2List.size(); a++) {
                //    DetailKrs dt = mk2List.get(a);
                  //  dt.setId_wktu(wk.getId_wktu());
//                    dt.setNim(txtNim.getText());
//                    dt.setKd_mk(mk2List.get(a).getKd_mk());
//                    detailService.update(dt);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void logoutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMenuActionPerformed
        // TODO add your handling code here:
        menuPanel(false);
        loginMenu.setEnabled(true);
        logoutMenu.setEnabled(false);
    }//GEN-LAST:event_logoutMenuActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
        About ab=new About();

        Dimension screenSize=this.getSize();
        Dimension frameSize=ab.getSize();
        ab.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        ab.setVisible(true);
    }//GEN-LAST:event_jMenu1ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Menu;
    private javax.swing.JTextField TxtDsn;
    private javax.swing.JTextField TxtJur;
    private javax.swing.JTextField TxtLoginNim;
    private javax.swing.JButton ambilButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JDialog dialogKrs;
    private javax.swing.JDialog dialogLogin;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton loginButton;
    private javax.swing.JMenuItem loginMenu;
    private javax.swing.JMenuItem logoutMenu;
    private javax.swing.JPanel panelBotton;
    private javax.swing.JPanel panelKrs;
    private javax.swing.JPanel panelMhs;
    private javax.swing.JTable tableAmbil;
    private javax.swing.JTable tableKrsJur;
    private javax.swing.JFormattedTextField tglFormat;
    private javax.swing.JButton tutupButton;
    private javax.swing.JTextField txtJumlahKrs;
    private javax.swing.JPasswordField txtLoginPwd;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtTa;
    // End of variables declaration//GEN-END:variables
}
