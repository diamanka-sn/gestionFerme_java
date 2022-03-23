package sn.ferme.espace.fermier;

import com.toedter.calendar.JDateChooser;
import java.awt.Cursor;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.Severity;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sn.ferme.model.ModelBovin;
import sn.ferme.model.ModelDiagnostic;

import sn.ferme.service.ServiceBovin;
import sn.ferme.service.ServiceDiagnostic;

public class Sante extends javax.swing.JPanel {

    private ServiceBovin service = new ServiceBovin();
    private ServiceDiagnostic serviceD = new ServiceDiagnostic();
    SimpleDateFormat dchoix = new SimpleDateFormat("yyyy-MM-dd");
    Date dactuelle = new Date();
    String dA = dchoix.format(dactuelle);
    String date = null;
    String dateN = null;
    String dateA = null;
    Date dateJour = null, dateChoix = null;

    public Sante() {
        initComponents();

        setOpaque(false);
        try {
            comboMaladie.setModel(new DefaultComboBoxModel(serviceD.listeMaladie().toArray()));
            comboBovinSain.setModel(new DefaultComboBoxModel(service.listeBovinSain().toArray()));
            listeBovinMalade();
        } catch (SQLException ex) {
            Logger.getLogger(Sante.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnAjouterbovinMalade.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuerir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bntAjouterMaladie.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public int verifDate(JDateChooser j) {
        date = dchoix.format(j.getDate());
        int retour = 0;
        try {
            dateJour = dchoix.parse(dA);
            dateChoix = dchoix.parse(date);
            if (dateChoix.after(dateJour)) {
                retour = -1;
            }
        } catch (ParseException e2) {
        }

        return retour;
    }

    public void inserMaladie() throws SQLException {
        String maladie = txtMaladie.getText();
        if (maladie.isEmpty() || maladie.contains("Nom maladie")) {
            JOptionPane.showMessageDialog(null, "Veuillez renseigner la maladie");
        } else {
            ModelDiagnostic maladies = new ModelDiagnostic(0, maladie);
            int r = serviceD.insererMaladie(maladies);

            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Maladie enregistré avec success");
                txtMaladie.setText("Nom maladie");
            } else {
                JOptionPane.showMessageDialog(null, "Erreur d'ajout");
            }
        }
    }
    Object body[][] = new Object[service.listeBovinMalade().size()][5];

    public void listeBovinMalade() {
        String[] header = {"Code", "Nom", "Race", "Maladie", "Date maladie"};
        int i = 0;
        for (ModelBovin m : new ServiceBovin().listeBovinMalade()) {
            body[i][0] = m.getCodeBovin();
            body[i][1] = m.getNom();
            body[i][2] = m.getNomRace();
            body[i][3] = m.getMaladie();
            body[i][4] = m.getDateMaladie();
            i++;
        }
        table.setModel(new DefaultTableModel(body, header));
    }

    public void ajouterBovinMalade() throws SQLException {
        String bovin = comboBovinSain.getSelectedItem().toString();
        String maladie = comboMaladie.getSelectedItem().toString();
        if (dateMaladie.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une date de maladie");
        } else if (verifDate(dateMaladie) == -1) {
            JOptionPane.showConfirmDialog(null, "Veuillez choisir une date correcte");
        } else {
            date = dchoix.format(dateMaladie.getDate());

            int idBovin = service.recupererCodeBovin(bovin);
            int idMaladie = serviceD.recupererIdMaladie(maladie);

            ModelDiagnostic diagnostic = new ModelDiagnostic(0, idBovin, idMaladie, date, "non");
            int res = serviceD.insererDiagnostic(diagnostic);
            if (res > 0) {
                service.updateEtatBovin(idBovin, "Malade");
                JOptionPane.showMessageDialog(null, "Bovin enregistrer avec succes");
            } else {
                JOptionPane.showMessageDialog(null, "Erreur d'ajout");
            }

        }
    }

    public void guerirBovin() throws SQLException {
        int i = table.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir un bovin a guerir");
        } else {
            int sup = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous vraiment Guerir ce bovin ?", "Confirmation changement guerison",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                String nom = (String) body[i][1];
                int idBovin = service.recupererCodeBovin(nom);
                String datMaladie = (String) body[i][4];

                System.out.println(idBovin);
                service.updateEtatBovin(idBovin, "Gueri");
                serviceD.updateDateGuerison(idBovin, datMaladie, dA);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        comboBovinSain = new javax.swing.JComboBox<>();
        btnAjouterbovinMalade = new sn.ferme.login.swing.ButtonOutLine();
        comboMaladie = new javax.swing.JComboBox<>();
        dateMaladie = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtMaladie = new sn.ferme.login.swing.MyTextField();
        bntAjouterMaladie = new sn.ferme.login.swing.ButtonOutLine();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        jLabel2 = new javax.swing.JLabel();
        btnGuerir = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        comboBovinSain.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sain", "Malade" }));

        btnAjouterbovinMalade.setBackground(new java.awt.Color(204, 0, 0));
        btnAjouterbovinMalade.setText("Ajouter bovin malade");
        btnAjouterbovinMalade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterbovinMaladeActionPerformed(evt);
            }
        });

        dateMaladie.setToolTipText("Date de maladie");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Date maladie bovin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnAjouterbovinMalade, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboBovinSain, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboMaladie, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateMaladie, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboBovinSain, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(comboMaladie)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateMaladie, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAjouterbovinMalade, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Ajout Bovin malade");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtMaladie.setText("Nom maladie");

        bntAjouterMaladie.setBackground(new java.awt.Color(204, 0, 0));
        bntAjouterMaladie.setText("Ajouter");
        bntAjouterMaladie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAjouterMaladieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntAjouterMaladie, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(txtMaladie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMaladie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntAjouterMaladie, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 79, 79));
        jLabel4.setText("Ajouter maladie");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane1.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane1.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Nom", "Naissance", "Etat santé", "Geniteur", "Genitrice", "Situation", "Prix", "Description", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Liste Bovin Malade");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        btnGuerir.setBackground(new java.awt.Color(0, 153, 0));
        btnGuerir.setText("Guérir");
        btnGuerir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuerirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuerir, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuerir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjouterbovinMaladeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterbovinMaladeActionPerformed
        try {
            ajouterBovinMalade();
        } catch (SQLException ex) {
            Logger.getLogger(Sante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAjouterbovinMaladeActionPerformed

    private void bntAjouterMaladieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAjouterMaladieActionPerformed
        try {
            inserMaladie();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nom maladie existe deja");
        }
    }//GEN-LAST:event_bntAjouterMaladieActionPerformed

    private void btnGuerirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuerirActionPerformed
        try {
            guerirBovin();
        } catch (SQLException ex) {
            Logger.getLogger(Sante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuerirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sn.ferme.login.swing.ButtonOutLine bntAjouterMaladie;
    private sn.ferme.login.swing.ButtonOutLine btnAjouterbovinMalade;
    private javax.swing.JButton btnGuerir;
    private javax.swing.JComboBox<String> comboBovinSain;
    private javax.swing.JComboBox<String> comboMaladie;
    private com.toedter.calendar.JDateChooser dateMaladie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private sn.ferme.swing.TableColumn table;
    private sn.ferme.login.swing.MyTextField txtMaladie;
    // End of variables declaration//GEN-END:variables
}
