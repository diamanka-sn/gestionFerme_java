package sn.ferme.espace.fermier;

import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sn.ferme.espace.admin.*;
import sn.ferme.model.ModelDepense;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceDepense;

public class Alimentation extends javax.swing.JPanel {

    SimpleDateFormat dchoix = new SimpleDateFormat("yyyy-MM-dd");
    Date dactuelle = new Date();
    String dA = dchoix.format(dactuelle);
    String date = null;
    String dateN = null;
    String dateA = null;
    Date dateJour = null, dateChoix = null;

    private ServiceDepense service = new ServiceDepense();
    Utilisateur user;

    public Alimentation(Utilisateur user) {
        this.user = user;
        initComponents();
        setOpaque(false);
        try {
            ComboAlimentation.setModel(new DefaultComboBoxModel(service.listeAliment().toArray()));
            dataNiveau();
        } catch (SQLException ex) {
            Logger.getLogger(Alimentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        Lb_quantite.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
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

    public void dataNiveau() throws SQLException {
        int nb = service.niveauStock().size();
        nb++;
        Object body[][] = new Object[service.niveauStock().size()][3];
        String[] header = {"Nom aliment", "Stock", "Consomes"};
        int i = 0;
        for (Map.Entry<String, ModelDepense> v : service.niveauStock().entrySet()) {
            ModelDepense model = v.getValue();
            body[i][0] = v.getKey();
            body[i][1] = model.getStock() - model.getConsomes();
            body[i][2] = model.getConsomes();

            i++;
        }
        table.setModel(new DefaultTableModel(body, header));

    }

    public void ajouterAlimentationduJour() {
        String qu = Lb_quantite.getText();
        String Aliment = ComboAlimentation.getSelectedItem().toString();
        if (qu.isEmpty() || qu.contains("Quantité")) {
            JOptionPane.showMessageDialog(null, "Veillez donner la quantité a consommée");
        } else if (datechooser.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une date");
        } else if (verifDate(datechooser) == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une date valide");
        } else {
            int quantite = Integer.parseInt(qu);
            int stock;
            String dateAlimentation = dchoix.format(datechooser.getDate());
            try {
                for (Map.Entry<String, ModelDepense> v : service.niveauStock().entrySet()) {
                    ModelDepense model = v.getValue();
                    stock = model.getStock() - model.getConsomes();
                    if (Aliment.equals(v.getKey()) && quantite <= stock) {
                        ModelDepense depense = new ModelDepense(0, user.getIdUtilisateur(), Aliment, quantite, dateAlimentation);
                        int rs = service.insertAliment(depense);
                        if (rs > 0) {
                            JOptionPane.showMessageDialog(null, "Alimentation du jour ajouter avec succes");
                        } else {
                            JOptionPane.showMessageDialog(null, "Erreur d(ajout");
                        }
                    } else if (Aliment.equals(v.getKey()) && quantite > stock) {
                        JOptionPane.showMessageDialog(null, "La quantité voulu n'est pas disponible en stock");
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(Alimentation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ComboAlimentation = new javax.swing.JComboBox<>();
        datechooser = new com.toedter.calendar.JDateChooser();
        Lb_quantite = new javax.swing.JTextField();
        btnAjouterAliment = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(79, 79, 79));
        jLabel1.setText("Ajouter Alimentation du jour");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        Lb_quantite.setText("Quantité");

        btnAjouterAliment.setText("Ajouter alimentation du jour");
        btnAjouterAliment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterAlimentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAjouterAliment, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(datechooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_quantite, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboAlimentation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboAlimentation, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Lb_quantite, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datechooser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnAjouterAliment, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Niveau Stock");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 249, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjouterAlimentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterAlimentActionPerformed
        ajouterAlimentationduJour();
    }//GEN-LAST:event_btnAjouterAlimentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboAlimentation;
    private javax.swing.JTextField Lb_quantite;
    private javax.swing.JButton btnAjouterAliment;
    private com.toedter.calendar.JDateChooser datechooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private sn.ferme.swing.TableColumn table;
    // End of variables declaration//GEN-END:variables
}
