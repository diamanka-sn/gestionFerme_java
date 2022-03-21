package sn.ferme.espace.fermier;

import sn.ferme.espace.admin.*;
import sn.ferme.chart.ModelChart;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sn.ferme.model.ModelBovin;
import sn.ferme.model.ModelDiagramme;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceBovin;
import sn.ferme.service.ServiceTraite;
import sn.ferme.service.ServiceUser;

public class AccueilF extends javax.swing.JPanel {

    private ServiceUser service = new ServiceUser();
    private ServiceBovin serv = new ServiceBovin();

    public AccueilF() {
        initComponents();
        setOpaque(false);
        init();
    }

    private void init() {
        List<ModelDiagramme> list1 = new ArrayList<>();
        List<ModelDiagramme> listV = new ArrayList<>();
        List<ModelDiagramme> listP = new ArrayList<>();

        Color[] couleurs;
        couleurs = new Color[5];
        couleurs[0] = new Color(4, 174, 243);
        couleurs[1] = new Color(215, 39, 250);
        couleurs[2] = new Color(238, 167, 35);
        couleurs[3] = new Color(127, 63, 255);
        couleurs[4] = new Color(245, 79, 99);
        try {
            int i = 0;
            int Production = new ServiceTraite().recupererProdictionTotal();
            int Vendu = new ServiceTraite().recupererVenduLait();
            int stock = Production - Vendu;
            list1.add(new ModelDiagramme("Stock Lait", stock, couleurs[1]));
            list1.add(new ModelDiagramme("Lait Vendu", Vendu, couleurs[2]));
            chartDiagramme2.setModel(list1);
            for (Map.Entry<String, Integer> v : serv.vachePeriode().entrySet()) {

                listV.add(new ModelDiagramme(v.getKey(), v.getValue(), couleurs[i]));
                i++;
            }
            int j = 2;
            for (Map.Entry<String, Integer> p : serv.vachePhase().entrySet()) {
                listP.add(new ModelDiagramme(p.getKey(), p.getValue(), couleurs[j]));
                j++;

            }
            chartPeriode.setModel(listV);
           chartPhase.setModel(listP);
        } catch (SQLException ex) {
            Logger.getLogger(AccueilF.class.getName()).log(Level.SEVERE, null, ex);
        }
        data();
    }
    Object body[][] = new Object[100][6];

    public void data() {

        String[] header = {"Id commande", "Date commande", "Capacite", "Prenom ", "Nom", "Email"};
        int i = 0;
        for (Utilisateur m : service.afficherCommandeNONValiderLAit()) {
            body[i][0] = m.getIdCom();
            body[i][1] = m.getDateCom();
            body[i][2] = m.getCapacite();
            body[i][3] = m.getPrenom();
            body[i][4] = m.getNom();
            body[i][5] = m.getEmail();
            i++;
        }
        table.setModel(new DefaultTableModel(body, header));
    }

    public void comfirmerVenteLait() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {
            int id = (int) body[row][0];
            String date = (String) body[row][1];
            // int code = Integer.parseInt(id);
            System.out.println(id);
            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez confirmer la vente de lait ?", "Confirmation de vente",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                try {
                    service.UpdateCommandeLAit(id);
                    JOptionPane.showMessageDialog(null, "Lait vendu!!!");
                } catch (SQLException ex) {
                    Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void supprimerCommande() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {
            int id = (int) body[row][0];
            // String date = (String) body[row][1];
            // int code = Integer.parseInt(id);
            System.out.println(id);
            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez confirmer la suppression de lait ?", "Confirmation de vente",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {

                try {
                    serv.supprimerCommande(id);
                    serv.supprimerVenteLait(id);
                    JOptionPane.showMessageDialog(null, "Lait vendu!!!");
                } catch (SQLException ex) {
                    Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        jLabel2 = new javax.swing.JLabel();
        scrollBar1 = new sn.ferme.swing.ScrollBar();
        btnValiderCommande = new javax.swing.JButton();
        chartDiagramme2 = new sn.ferme.chart.ChartDiagramme();
        chartPeriode = new sn.ferme.chart.ChartDiagramme();
        btnSupprimer = new javax.swing.JButton();
        chartPhase = new sn.ferme.chart.ChartDiagramme();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(79, 79, 79));
        jLabel1.setText("Tableau de bord");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane1.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBar(scrollBar1);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nom complet", "Sexe", "Email", "Numero", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Commande Lait");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        scrollBar1.setBackground(new java.awt.Color(245, 245, 245));

        btnValiderCommande.setBackground(new java.awt.Color(0, 153, 0));
        btnValiderCommande.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnValiderCommande.setForeground(new java.awt.Color(255, 255, 255));
        btnValiderCommande.setText("Vendre");
        btnValiderCommande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderCommandeActionPerformed(evt);
            }
        });

        btnSupprimer.setBackground(new java.awt.Color(204, 0, 0));
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(chartDiagramme2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(chartPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chartPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSupprimer)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnValiderCommande)))
                                .addGap(14, 14, 14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chartPhase, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(chartPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chartDiagramme2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(scrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnValiderCommande, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnValiderCommandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValiderCommandeActionPerformed
        comfirmerVenteLait();  // TODO add your handling code here:
    }//GEN-LAST:event_btnValiderCommandeActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        supprimerCommande();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprimerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton btnValiderCommande;
    private sn.ferme.chart.ChartDiagramme chartDiagramme2;
    private sn.ferme.chart.ChartDiagramme chartPeriode;
    private sn.ferme.chart.ChartDiagramme chartPhase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private sn.ferme.swing.ScrollBar scrollBar1;
    private sn.ferme.swing.TableColumn table;
    // End of variables declaration//GEN-END:variables
}
