/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sn.ferme.espace.client;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sn.ferme.model.ModelBovin;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceBovin;

/**
 *
 * @author Mouhamadou DIAMANKA
 */
public class PanierClient extends javax.swing.JPanel {

    private final Utilisateur user;
    private ServiceBovin service = new ServiceBovin();

    /**
     * Creates new form PanierClient
     *
     * @param user
     */
    public PanierClient(Utilisateur user) {
        initComponents();
        this.user = user;
        try {
            data();
            dataCommande();
            dataHistorique();
        } catch (SQLException ex) {
            Logger.getLogger(PanierClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Object body[][];
    Object bodyCommande[][];
    Object bodyHistorique[][];

    private void data() throws SQLException {
        int r = service.nombrePanier(user.getIdUtilisateur(), "en cours");

        body = new Object[r][7];
        String[] header = {"ID bovin", "Code bovin", "Nom", "Description", "Race", "Date commande", "Prix"};
        int i = 0;
        int us = user.getIdUtilisateur();
        for (ModelBovin m : new ServiceBovin().BovinEnCoursCommande(us, "en cours")) {
            body[i][0] = m.getIdBovin();
            body[i][1] = m.getCodeBovin();
            body[i][2] = m.getNom();
            body[i][3] = m.getDescription();
            body[i][4] = m.getNomRace();
            body[i][5] = m.getDateCom();
            body[i][6] = m.getPrix();

            i++;
            System.out.println(i + " " + m.getDateCom());
        }
        table.setModel(new DefaultTableModel(body, header));
    }

    private void dataCommande() throws SQLException {
        int r = service.nombrePanier(user.getIdUtilisateur(), "commander");

        bodyCommande = new Object[r][7];
        String[] header = {"ID bovin", "Code bovin", "Nom", "Description", "Race", "Date commande", "Prix"};
        int i = 0;
        int us = user.getIdUtilisateur();
        for (ModelBovin m : new ServiceBovin().BovinEnCoursCommande(us, "commander")) {
            bodyCommande[i][0] = m.getIdBovin();
            bodyCommande[i][1] = m.getCodeBovin();
            bodyCommande[i][2] = m.getNom();
            bodyCommande[i][3] = m.getDescription();
            bodyCommande[i][4] = m.getNomRace();
            bodyCommande[i][5] = m.getDateCom();
            bodyCommande[i][6] = m.getPrix();

            i++;
            System.out.println(i + " " + m.getDateCom());
        }
        tableCommande.setModel(new DefaultTableModel(bodyCommande, header));
    }

    private void dataHistorique() throws SQLException {
        int r = service.nombrePanier(user.getIdUtilisateur(), "vendu");

        bodyHistorique = new Object[r][7];
        String[] header = {"ID bovin", "Code bovin", "Nom", "Description", "Race", "Date commande", "Prix"};
        int i = 0;
        int us = user.getIdUtilisateur();
        for (ModelBovin m : new ServiceBovin().BovinEnCoursCommande(us, "vendu")) {
            bodyHistorique[i][0] = m.getIdBovin();
            bodyHistorique[i][1] = m.getCodeBovin();
            bodyHistorique[i][2] = m.getNom();
            bodyHistorique[i][3] = m.getDescription();
            bodyHistorique[i][4] = m.getNomRace();
            bodyHistorique[i][5] = m.getDateCom();
            bodyHistorique[i][6] = m.getPrix();

            i++;
            System.out.println(i + " " + m.getDateCom());
        }
        tableHistorique.setModel(new DefaultTableModel(bodyHistorique, header));
    }

    private void valideCommande() throws SQLException {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {

            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez valider cette commande?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                int id = (int) body[row][0];

                service.updateSituationtBovin(id, "commander");
                JOptionPane.showMessageDialog(null, "Commande Valider");
            }
        }
    }

    private void supprimerCommandeEncours() throws SQLException {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {

            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez confirmer la suppression de cette commande ?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                int id = (int) body[row][0];
                service.supprimerVenteBovin(id);
                service.updateSituationtBovin(id, "En vente");
                JOptionPane.showMessageDialog(null, "Commande Supprimer!!!");
            }
        }
    }

    private void supprimerCommande() throws SQLException {
        int row = tableCommande.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {

            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez confirmer la suppression de cette commande ?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                int id = (int) bodyCommande[row][0];
                service.supprimerVenteBovin(id);
                service.updateSituationtBovin(id, "En vente");
                JOptionPane.showMessageDialog(null, "Commande Supprimer!!!");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCommande = new sn.ferme.swing.TableColumn();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHistorique = new sn.ferme.swing.TableColumn();
        jLabel4 = new javax.swing.JLabel();
        btnSupprimer = new javax.swing.JButton();
        btnValider = new javax.swing.JButton();
        Supprimer = new javax.swing.JButton();

        jScrollPane1.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane1.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Contenu du panier");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane2.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane2.setBorder(null);

        tableCommande.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableCommande);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Liste commande");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane3.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane3.setBorder(null);

        tableHistorique.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableHistorique);

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 79, 79));
        jLabel4.setText("Historique ");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        btnSupprimer.setBackground(new java.awt.Color(102, 0, 0));
        btnSupprimer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSupprimer.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        btnValider.setBackground(new java.awt.Color(0, 102, 0));
        btnValider.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnValider.setForeground(new java.awt.Color(255, 255, 255));
        btnValider.setText("Valider");
        btnValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderActionPerformed(evt);
            }
        });

        Supprimer.setBackground(new java.awt.Color(102, 0, 0));
        Supprimer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Supprimer.setForeground(new java.awt.Color(255, 255, 255));
        Supprimer.setText("Supprimer");
        Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupprimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(186, 186, 186)
                                .addComponent(Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 43, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnValider, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnValider, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        try {
            supprimerCommandeEncours();   // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(PanierClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValiderActionPerformed
        try {
            valideCommande();        // TODO add your handling code here:
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnValiderActionPerformed

    private void SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupprimerActionPerformed
        try {
            supprimerCommande();   // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(PanierClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SupprimerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Supprimer;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton btnValider;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private sn.ferme.swing.TableColumn table;
    private sn.ferme.swing.TableColumn tableCommande;
    private sn.ferme.swing.TableColumn tableHistorique;
    // End of variables declaration//GEN-END:variables
}
