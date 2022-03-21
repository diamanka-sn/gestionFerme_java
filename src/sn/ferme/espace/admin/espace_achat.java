/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sn.ferme.espace.admin;

import sn.ferme.espace.client.*;
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
public class espace_achat extends javax.swing.JPanel {

    private final Utilisateur user;
    private ServiceBovin service = new ServiceBovin();

    /**
     * Creates new form PanierClient
     *
     * @param user
     */
    public espace_achat(Utilisateur user) {
        initComponents();
        this.user = user;
        try {
            data();

        } catch (SQLException ex) {
            Logger.getLogger(espace_achat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Object body[][];

    private void data() throws SQLException {
        int r = service.ClientEnCoursCommande("en cours").size();

        body = new Object[r][10];
        String[] header = {"ID bovin", "Code bovin", "Nom", "Description", "Race", "Date commande", "Prix", "Client", "Telephone", "Email"};
        int i = 0;
        int us = user.getIdUtilisateur();
        for (ModelBovin m : new ServiceBovin().ClientEnCoursCommande("en cours")) {
            body[i][0] = m.getIdBovin();
            body[i][1] = m.getCodeBovin();
            body[i][2] = m.getNom();
            body[i][3] = m.getDescription();
            body[i][4] = m.getNomRace();
            body[i][5] = m.getDateCom();
            body[i][6] = m.getPrix();
            body[i][7] = m.getPrenom() + " " + m.getNomClient();
            body[i][8] = m.getTelephone();
            body[i][9] = m.getEmail();

            i++;
            System.out.println(i + " " + m.getDateCom());
        }
        table.setModel(new DefaultTableModel(body, header));
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
        btnSupprimer = new javax.swing.JButton();

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
        jLabel2.setText("Liste commande non valider");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        btnSupprimer.setBackground(new java.awt.Color(102, 0, 0));
        btnSupprimer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSupprimer.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1181, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        try {
            supprimerCommandeEncours();
        } catch (SQLException ex) {
            Logger.getLogger(espace_achat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSupprimerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private sn.ferme.swing.TableColumn table;
    // End of variables declaration//GEN-END:variables
}
