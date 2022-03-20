package sn.ferme.espace.admin;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sn.ferme.model.ModelBovin;
import sn.ferme.model.ModelDiagramme;
import sn.ferme.service.ServiceBovin;

public class Production extends javax.swing.JPanel {

    private ServiceBovin service = new ServiceBovin();

    public Production() {
        initComponents();
        setOpaque(false);
        data();
        dataEnVente();
        dataVendu();
        dataCommander();
    }
    Object body[][] = new Object[service.afficherBovinNonEnLigne().size()][6];
    Object bodyc[][] = new Object[service.afficherBovinCommander().size()][6];

    public void dataCommander() {

        String[] header = {"Code", "Nom", "Naissance", "prix", "Description", "Race","Client"};
        int i = 0;
        for (ModelBovin m : new ServiceBovin().afficherBovinCommander()) {
            bodyc[i][0] = m.getCodeBovin();
            bodyc[i][1] = m.getNom();
            bodyc[i][2] = m.getDateNaissance();
            bodyc[i][3] = m.getPrix();
            bodyc[i][4] = m.getDescription();
            bodyc[i][5] = m.getNomRace();
            i++;
        }
        tableCommander.setModel(new DefaultTableModel(bodyc, header));
    }

    public void data() {

        int nb = service.afficherBovinNonEnLigne().size();
        lbNombre.setText("" + nb / 2);
        String[] header = {"Code", "Nom", "Naissance", "prix", "Description", "Race"};
        int i = 0;
        for (ModelBovin m : new ServiceBovin().afficherBovinNonEnLigne()) {
            body[i][0] = m.getCodeBovin();
            body[i][1] = m.getNom();
            body[i][2] = m.getDateNaissance();
            body[i][3] = m.getPrix();
            body[i][4] = m.getDescription();
            body[i][5] = m.getNomRace();
            i++;
        }
        table.setModel(new DefaultTableModel(body, header));
    }
    Object body1[][] = new Object[service.afficherBovinEnLigne().size()][6];
    Object body3[][] = new Object[service.listeBovinVendu().size()][6];

    public void dataEnVente() {

        lbNombre1.setText("" + service.afficherBovinEnLigne().size() / 2);
        String[] header = {"Code", "Nom", "Naissance", "prix", "Description", "Race"};
        int i = 0;
        for (ModelBovin m : new ServiceBovin().afficherBovinEnLigne()) {
            body1[i][0] = m.getCodeBovin();
            body1[i][1] = m.getNom();
            body1[i][2] = m.getDateNaissance();
            body1[i][3] = m.getPrix();
            body1[i][4] = m.getDescription();
            body1[i][5] = m.getNomRace();
            i++;
        }
        table1.setModel(new DefaultTableModel(body1, header));
    }

    public void dataVendu() {
        String[] header = {"Code", "Nom", "Naissance", "prix", "Description", "Race"};
        int i = 0;
        for (ModelBovin m : new ServiceBovin().listeBovinVendu()) {
            body3[i][0] = m.getCodeBovin();
            body3[i][1] = m.getNom();
            body3[i][2] = m.getDateNaissance();
            body3[i][3] = m.getPrix();
            body3[i][4] = m.getDescription();
            body3[i][5] = m.getNomRace();
            i++;
        }
        tableVendu.setModel(new DefaultTableModel(body3, header));
    }

    public void mettreEnligne() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {
            String id = (String) body[row][0];
            System.out.println(id);
            int sup = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous mettre en ligne ce bovin ?", "Confirmation de la mise en ligne",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                try {
                    service.enleverOuMettre(id, "En vente");
                    JOptionPane.showMessageDialog(null, "Bovin mis en ligne!!!");
                } catch (SQLException ex) {
                    Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void enleverEnligne() {
        int row = table1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {
            String id = (String) body1[row][0];
            System.out.println(id);
            int sup = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous vraiment retirer ce bovin ?", "Confirmation retrait",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                try {
                    service.enleverOuMettre(id, "Pas en vente");
                    JOptionPane.showMessageDialog(null, "Bovin retiré!!!");
                } catch (SQLException ex) {
                    Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void validerCommande() {
        int row = tableCommander.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {
            String id = (String) bodyc[row][1];
            System.out.println(id);
            int sup = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous vraiment Valider cette commande ?", "Confirmation commande",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                try {
                    int ids = service.recupererCodeBovin(id);
                    service.updateSituationtBovin(ids, "vendu");
                    JOptionPane.showMessageDialog(null, "Bovin vendu!!!");
                } catch (SQLException ex) {
                    Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new sn.ferme.swing.TableColumn();
        jLabel3 = new javax.swing.JLabel();
        btnLigne = new javax.swing.JButton();
        btnEnlever = new javax.swing.JButton();
        lbNombre = new javax.swing.JLabel();
        lbNombre1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableVendu = new sn.ferme.swing.TableColumn();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableCommander = new sn.ferme.swing.TableColumn();
        jLabel5 = new javax.swing.JLabel();
        btnValiderCommande = new javax.swing.JButton();

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
        jLabel2.setText("Liste Bovin non en ligne");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane2.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane2.setBorder(null);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(table1);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Liste Bovin en ligne");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        btnLigne.setBackground(new java.awt.Color(0, 153, 0));
        btnLigne.setText("Mettre en ligne");
        btnLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLigneActionPerformed(evt);
            }
        });

        btnEnlever.setBackground(new java.awt.Color(153, 0, 0));
        btnEnlever.setText("Retirer");
        btnEnlever.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnleverActionPerformed(evt);
            }
        });

        lbNombre.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        lbNombre.setForeground(new java.awt.Color(79, 79, 79));
        lbNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        lbNombre1.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        lbNombre1.setForeground(new java.awt.Color(79, 79, 79));
        lbNombre1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane3.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane3.setBorder(null);

        tableVendu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableVendu);

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 79, 79));
        jLabel4.setText("Liste Bovin Vendu");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane4.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane4.setBorder(null);

        tableCommander.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tableCommander);

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(79, 79, 79));
        jLabel5.setText("Liste Bovin Commander");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        btnValiderCommande.setBackground(new java.awt.Color(0, 153, 0));
        btnValiderCommande.setText("Valider Commande");
        btnValiderCommande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderCommandeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                        .addComponent(btnLigne, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(204, 204, 204)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEnlever, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnValiderCommande)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLigne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEnlever, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbNombre1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNombre)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnValiderCommande, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLigneActionPerformed
        mettreEnligne(); // TODO add your handling code here:
    }//GEN-LAST:event_btnLigneActionPerformed

    private void btnEnleverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnleverActionPerformed
        enleverEnligne();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnleverActionPerformed

    private void btnValiderCommandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValiderCommandeActionPerformed
        validerCommande();    // TODO add your handling code here:
    }//GEN-LAST:event_btnValiderCommandeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnlever;
    private javax.swing.JButton btnLigne;
    private javax.swing.JButton btnValiderCommande;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNombre1;
    private sn.ferme.swing.TableColumn table;
    private sn.ferme.swing.TableColumn table1;
    private sn.ferme.swing.TableColumn tableCommander;
    private sn.ferme.swing.TableColumn tableVendu;
    // End of variables declaration//GEN-END:variables
}
