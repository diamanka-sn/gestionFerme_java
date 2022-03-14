package sn.ferme.espace.admin;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import sn.ferme.model.ModelCard;
import sn.ferme.model.ModelDepense;
import sn.ferme.service.ServiceDepense;
import sn.ferme.service.ValiderChamp;

public class Finance extends javax.swing.JPanel {

    private ServiceDepense serviced = new ServiceDepense();
    SimpleDateFormat dchoix = new SimpleDateFormat("dd/MM/yyyy");
    Date dactuelle = new Date();
    String dA = dchoix.format(dactuelle);
    String date = null;
    Date dateJour = null, dateChoix = null;
    int nb;

    public Finance() {
        try {
            initComponents();
            data();
            comboType.setModel(new DefaultComboBoxModel(serviced.listProfession().toArray()));
            lbMontant.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                        e.consume();
                    }
                }
            });
            initFinanceCard();
            setOpaque(false);
        } catch (SQLException ex) {
            Logger.getLogger(Finance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void data() {
        try {
            nb = serviced.ligne();
        } catch (SQLException ex) {
            Logger.getLogger(Finance.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  Object body[][] = new Object[serviced.afficherDepense().size()][5];
        Object body[][] = new Object[nb][5];
        String[] header = {"id", "Libellé", "Montant", "Date dépense", "Type de dépense"};
        int i = 0;
        for (ModelDepense m : serviced.afficherDepense()) {
            body[i][0] = m.getIdDepense();
            body[i][1] = m.getLibelle();
            body[i][2] = m.getMontant();
            body[i][3] = m.getDateDepense();
            body[i][4] = m.getNomType();
            i++;
        }
        table.setModel(new DefaultTableModel(body, header));
    }

    public int verifDate() {
        date = dchoix.format(jDateChooser1.getDate());
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

    public void initFinanceCard() {
        cardCA.setData(new ModelCard("Chiffre d'affaire(F cfa)", 200000, 20));
        cardCommande.setData(new ModelCard("Commande", 300000, 20));
        cardBenefice.setData(new ModelCard("Benefice(F cfa)", 200000, 3));
        cardDepenseMensuelle.setData(new ModelCard("Total depense(F cfa)", 200000, 20));
    }

    private void ajouterType() throws SQLException {
        ValiderChamp v = new ValiderChamp();
        ModelDepense t = null;
        String type = txtTypeDepense.getText();
        if (type.isEmpty() || type.contains("Ajouter type de depense")) {
            JOptionPane.showMessageDialog(null, "Veuillez renseigner un type");
        } else {
            t = new ModelDepense(0, type);
            serviced.inserType(t);
        }
    }

    private void ajouterDepense() throws SQLException {
        String libelle = txtLibele.getText();
        String type = comboType.getSelectedItem().toString();
        int t = serviced.recupererCodeType(type);
        String montant = lbMontant.getText();
        date = dchoix.format(jDateChooser1.getDate());
        if (libelle.isEmpty() || montant.isEmpty() || montant.contains("Montant depense")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Tous les champs sont obligatoires", JOptionPane.ERROR_MESSAGE);
        } else if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une date", "Choisir", JOptionPane.ERROR_MESSAGE);
        } else if (verifDate() == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une date correcte", "Choix date incorrect", JOptionPane.ERROR_MESSAGE);
        } else {
            int m = Integer.parseInt(montant);
            ModelDepense depense = new ModelDepense(0, t, date, libelle, m);
            serviced.insertDepense(depense);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardCA = new sn.ferme.component.Card();
        cardDepenseMensuelle = new sn.ferme.component.Card();
        cardCommande = new sn.ferme.component.Card();
        cardBenefice = new sn.ferme.component.Card();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        comboType = new javax.swing.JComboBox<>();
        lbMontant = new javax.swing.JTextField();
        txtLibele = new javax.swing.JTextField();
        btnAjouterDepense = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        txtTypeDepense = new javax.swing.JTextField();
        btnAjouterType = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new sn.ferme.swing.TableColumn();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        cardCA.setColorGradient(new java.awt.Color(0, 0, 102));

        cardDepenseMensuelle.setBackground(new java.awt.Color(255, 0, 0));
        cardDepenseMensuelle.setColorGradient(new java.awt.Color(153, 0, 0));

        cardCommande.setBackground(new java.awt.Color(51, 153, 0));
        cardCommande.setColorGradient(new java.awt.Color(0, 102, 51));

        cardBenefice.setBackground(new java.awt.Color(0, 153, 153));
        cardBenefice.setColorGradient(new java.awt.Color(0, 102, 102));

        jScrollPane1.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Libellé", "Montant", "Date depense", "Type"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Liste dépense");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        lbMontant.setText("Montant depense");

        txtLibele.setText("Libellé");

        btnAjouterDepense.setText("Ajouter dépense");
        btnAjouterDepense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterDepenseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLibele)
                    .addComponent(lbMontant)
                    .addComponent(comboType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAjouterDepense, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtLibele, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAjouterDepense, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        txtTypeDepense.setText("Ajouter type de depense");

        btnAjouterType.setText("Ajouter type");
        btnAjouterType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTypeDepense, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(btnAjouterType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTypeDepense, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAjouterType, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setBorder(null);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type de dépense", "Montant dépensé"
            }
        ));
        jScrollPane2.setViewportView(table1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Ajout dépense");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 79, 79));
        jLabel4.setText("Ajout type de dépense");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cardCA, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardDepenseMensuelle, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardCommande, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardBenefice, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardBenefice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardCommande, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardDepenseMensuelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(333, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjouterDepenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterDepenseActionPerformed
        try {
            ajouterDepense(); // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Finance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAjouterDepenseActionPerformed

    private void btnAjouterTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterTypeActionPerformed
        try {
            ajouterType();        // TODO add your handling code here:
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Type de dépense existe déja", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAjouterTypeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterDepense;
    private javax.swing.JButton btnAjouterType;
    private sn.ferme.component.Card cardBenefice;
    private sn.ferme.component.Card cardCA;
    private sn.ferme.component.Card cardCommande;
    private sn.ferme.component.Card cardDepenseMensuelle;
    private javax.swing.JComboBox<String> comboType;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lbMontant;
    private sn.ferme.swing.TableColumn table;
    private sn.ferme.swing.TableColumn table1;
    private javax.swing.JTextField txtLibele;
    private javax.swing.JTextField txtTypeDepense;
    // End of variables declaration//GEN-END:variables
}
