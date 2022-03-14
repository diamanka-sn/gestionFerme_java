package sn.ferme.espace.fermier;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sn.ferme.espace.admin.*;
import sn.ferme.model.ModelCard;
import sn.ferme.model.ModelTraite;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceTraite;

public class ProductionF extends javax.swing.JPanel {

    private ServiceTraite serviceT = new ServiceTraite();
    SimpleDateFormat dchoix = new SimpleDateFormat("dd/MM/yyyy");
    Date dactuelle = new Date();
    String dA = dchoix.format(dactuelle);
    String date = null;
    Date dateJour = null, dateChoix = null;
    Utilisateur user = new Utilisateur();

    public ProductionF(Utilisateur user) {
        this.user = user;
        initComponents();
        init();
        System.out.println(dA);
        try {
            comboVache.setModel(new DefaultComboBoxModel(serviceT.listeVacheNonTraite().toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(ProductionF.class.getName()).log(Level.SEVERE, null, ex);
        }
        setOpaque(false);
    }

    public void init() {
        txtTraiteMatin.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        txtTraiteSoir.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        try {
            data();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void data() throws SQLException {
        Object body[][] = new Object[10][3];
        int matin = 0, soir = 0;
        String[] header = {"Nom vache", "Matin", "Soir"};
        int i = 0;
        for (ModelTraite m : serviceT.afficherPodParVache()) {
            body[i][0] = m.getNom();
            body[i][1] = m.getTraiteMatin();
            body[i][2] = m.getTraiteSoir();
            matin = matin + m.getTraiteMatin();
            soir = soir + m.getTraiteSoir();
            i++;
        }
        int t = matin + soir;
        cardMatin.setData(new ModelCard("Traite Matin(L)", matin, matin * 100 / t));
        cardSoir.setData(new ModelCard("Traite Soir(L)", soir, soir * 100 / t));
        lbProductionT.setText(""+t+" L");
        table.setModel(new DefaultTableModel(body, header));
    }

    public int verifDate() {
        date = dchoix.format(datechooser.getDate());
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

    public void ajouterTraite() throws SQLException {
        String matin = txtTraiteMatin.getText();
        String soir = txtTraiteSoir.getText();
        String vache = (String) comboVache.getSelectedItem();
        int id = serviceT.recupererIdBovin(vache);
        if (matin.isEmpty() || soir.isEmpty() || matin.contains("Traite matin") || soir.contains("Traite matin")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les traites matin et soir!!!");
        } else if (datechooser.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une date!!!");
        } else if (verifDate() == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une date valide!!!");
        } else {
            date = dchoix.format(datechooser.getDate());
            int m = Integer.parseInt(matin);
            int s = Integer.parseInt(soir);
            int idUser = user.getIdUtilisateur();
            ModelTraite traite = new ModelTraite(0, idUser, id, m, s, date);
            serviceT.insererTraite(traite);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        comboVache = new javax.swing.JComboBox<>();
        datechooser = new com.toedter.calendar.JDateChooser();
        txtTraiteMatin = new javax.swing.JTextField();
        txtTraiteSoir = new javax.swing.JTextField();
        btnAjouterTraite = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        cardMatin = new sn.ferme.component.Card();
        cardSoir = new sn.ferme.component.Card();
        lbProductionT = new javax.swing.JLabel();

        txtTraiteMatin.setText("Traite matin");

        txtTraiteSoir.setText("Traite Soir");

        btnAjouterTraite.setText("Ajouter traite du jour");
        btnAjouterTraite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterTraiteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAjouterTraite, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(datechooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTraiteSoir, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTraiteMatin, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboVache, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboVache, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTraiteMatin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTraiteSoir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datechooser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAjouterTraite, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(79, 79, 79));
        jLabel1.setText("Ajouter traite du jour");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Production vache");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane1.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane1.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom vache", "Matin", "Soir"
            }
        ));
        jScrollPane1.setViewportView(table);

        cardMatin.setColorGradient(new java.awt.Color(0, 51, 153));

        cardSoir.setBackground(new java.awt.Color(153, 0, 0));
        cardSoir.setColorGradient(new java.awt.Color(255, 0, 0));

        lbProductionT.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        lbProductionT.setForeground(new java.awt.Color(79, 79, 79));
        lbProductionT.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(lbProductionT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cardMatin, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardSoir, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(lbProductionT))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(cardMatin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardSoir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(249, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjouterTraiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterTraiteActionPerformed
        try {
            ajouterTraite();
            JOptionPane.showMessageDialog(null, "Traite ajouter!!!");// TODO add your handling code here:
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Traite n'est ajouté que une fois par jour pour chaque vache!!!");
        }
    }//GEN-LAST:event_btnAjouterTraiteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterTraite;
    private sn.ferme.component.Card cardMatin;
    private sn.ferme.component.Card cardSoir;
    private javax.swing.JComboBox<String> comboVache;
    private com.toedter.calendar.JDateChooser datechooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbProductionT;
    private sn.ferme.swing.TableColumn table;
    private javax.swing.JTextField txtTraiteMatin;
    private javax.swing.JTextField txtTraiteSoir;
    // End of variables declaration//GEN-END:variables
}
