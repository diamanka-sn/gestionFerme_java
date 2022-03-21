/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sn.ferme.espace.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sn.ferme.model.ModelBovin;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceBovin;
import sn.ferme.service.ServiceTraite;
import sn.ferme.service.ServiceUser;

/**
 *
 * @author Mouhamadou DIAMANKA
 */
public class AcheterLait extends javax.swing.JPanel {

    /**
     * Creates new form AcheterLait
     */
    private ServiceUser service = new ServiceUser();
    private final Utilisateur user;
    private ServiceTraite serviceT = new ServiceTraite();
    private ServiceBovin serviceb = new ServiceBovin();
    int prod;
    int vend;
    int stock;
    SimpleDateFormat dchoix = new SimpleDateFormat("yyyy-MM-dd");
    Date dactuelle = new Date();
    String dA = dchoix.format(dactuelle);
    
    public AcheterLait(Utilisateur user) {
        initComponents();
        this.user = user;
        dataCommande();
        dataCommandeHistorique();
        try {
            prod = serviceT.recupererProdictionTotal();
            vend = serviceT.recupererVenduLait();
            stock = prod - vend;
            Niveau.setText(stock + " Litres");
        } catch (SQLException ex) {
            Logger.getLogger(AcheterLait.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtQuantite.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        int u = user.getIdUtilisateur();
        if (service.afficherCommandeNONValiderLAitU(u).size()>0) {
            labelInfo.setText("NB: Veuillez vous presenter à KoSsam ferme dans les 24 heures qui suivent  afin  de finaliser votre commande.");
        }
    }
    Object bodyCommande[][];
    Object bodyHistorique[][];
    
    private void dataCommande() {
        int us = user.getIdUtilisateur();
        bodyCommande = new Object[100][4];
        String[] header = {"ID commande", "Date Commande", "Quantite", "Cout"};
        int i = 0;
        for (Utilisateur m : service.afficherCommandeNONValiderLAitU(us)) {
            bodyCommande[i][0] = m.getIdCom();
            bodyCommande[i][1] = m.getDateCom();
            bodyCommande[i][2] = m.getCapacite();
            bodyCommande[i][3] = m.getCapacite() * 1000;
            
            i++;
            System.out.println(i + " " + m.getDateCom());
        }
        tableCommande.setModel(new DefaultTableModel(bodyCommande, header));
    }
    
    private void dataCommandeHistorique() {
        int us = user.getIdUtilisateur();
        
        bodyHistorique = new Object[100][4];
        String[] header = {"ID commande", "Date Commande", "Quantite", "Cout"};
        int i = 0;
        for (Utilisateur m : service.afficherCommandeValiderLAitU(us)) {
            bodyHistorique[i][0] = m.getIdCom();
            bodyHistorique[i][1] = m.getDateCom();
            bodyHistorique[i][2] = m.getCapacite();
            bodyHistorique[i][3] = m.getCapacite() * 1000;
            
            i++;
            System.out.println(i + " " + m.getDateCom());
        }
        tableHistorique.setModel(new DefaultTableModel(bodyHistorique, header));
    }
    
    private void commanderLait() throws SQLException {
        String q = txtQuantite.getText();
        int use = user.getIdUtilisateur();
        if (q.contains("quantité a acheter") || q.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez donner la quantite a commander");
        } else {
            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez valider cette commande?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                int quantite = Integer.parseInt(q);
                if (quantite > stock) {
                    JOptionPane.showMessageDialog(null, "Quantité de lait non disponible");
                } else {
                    ModelBovin commande = new ModelBovin(0, use, dA);
                    int idCom = serviceb.insererCommande(commande);
                    ModelBovin lait = new ModelBovin(idCom, quantite);
                    int res = serviceb.insererVenteLait(lait);
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "Lait commander avec success");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur commande lait");
                    }
                }
            }
        }
    }
    
    public void supprimerCommande() {
        int row = tableCommande.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {
            
            int sup = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous vraiment supprimer cette commande?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                int id = (int) bodyCommande[row][0];
                try {
                    serviceb.supprimerCommande(id);
                    serviceb.supprimerVenteLait(id);
                } catch (SQLException ex) {
                    Logger.getLogger(AcheterLait.class.getName()).log(Level.SEVERE, null, ex);
                }
                
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

        jLabel2 = new javax.swing.JLabel();
        Niveau = new javax.swing.JLabel();
        txtQuantite = new javax.swing.JTextField();
        btnPasserCommande = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHistorique = new sn.ferme.swing.TableColumn();
        jLabel4 = new javax.swing.JLabel();
        Supprimer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCommande = new sn.ferme.swing.TableColumn();
        labelInfo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Quantité de lait disponible en stock");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        Niveau.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        Niveau.setForeground(new java.awt.Color(0, 102, 0));
        Niveau.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        txtQuantite.setText("quantité a acheter");

        btnPasserCommande.setBackground(new java.awt.Color(0, 102, 51));
        btnPasserCommande.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPasserCommande.setForeground(new java.awt.Color(255, 255, 255));
        btnPasserCommande.setText("Passer commande");
        btnPasserCommande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasserCommandeActionPerformed(evt);
            }
        });

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

        Supprimer.setBackground(new java.awt.Color(102, 0, 0));
        Supprimer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Supprimer.setForeground(new java.awt.Color(255, 255, 255));
        Supprimer.setText("Supprimer");
        Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupprimerActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Liste commande");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane2.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane2.setBorder(null);

        tableCommande.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableCommande);

        labelInfo.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        labelInfo.setForeground(new java.awt.Color(153, 0, 0));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("1 L = 1 000 F cfa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176)
                        .addComponent(Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Niveau, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(txtQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPasserCommande, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(Niveau))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPasserCommande, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Supprimer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupprimerActionPerformed
        supprimerCommande();
    }//GEN-LAST:event_SupprimerActionPerformed

    private void btnPasserCommandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasserCommandeActionPerformed
        try {
            commanderLait();  // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(AcheterLait.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPasserCommandeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Niveau;
    private javax.swing.JButton Supprimer;
    private javax.swing.JButton btnPasserCommande;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelInfo;
    private sn.ferme.swing.TableColumn tableCommande;
    private sn.ferme.swing.TableColumn tableHistorique;
    private javax.swing.JTextField txtQuantite;
    // End of variables declaration//GEN-END:variables
}
