package sn.ferme.espace.fermier;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sn.ferme.espace.admin.*;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceUser;

public class Passe extends javax.swing.JPanel {

    private Utilisateur user;
    private ServiceUser service = new ServiceUser();

    public Passe(Utilisateur user) {
        this.user = user;
        initComponents();
        setOpaque(false);
        btnChangez.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnModifier.setCursor(new Cursor(Cursor.HAND_CURSOR));

        LbEMail.setText(user.getEmail());
        LbNumero.setText(user.getTelephone());
        Lbprenom.setText(user.getPrenom() + " " + user.getNom());
        LbResidence.setText(user.getAdresse());
    }

    private void showMessage(Component com) {
        panelModifier.removeAll();
        panelModifier.setLayout(new FlowLayout());
        panelModifier.add(com);
        panelModifier.repaint();
        panelModifier.revalidate();
    }

    public void changerMotpasse() throws SQLException {
        String ancien = txtAncienPasse.getText();
        String nouveau = txtNouveauPasse.getText();
        String confirme = txtConfirmerPAsse.getText();

        if (ancien.isEmpty() || nouveau.isEmpty() || confirme.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "Veuillez remplir tous les champs");
        } else if (!confirme.equals(nouveau)) {
            JOptionPane.showMessageDialog(null, "Mot de passe non conforme");
        } else {
            service.updatePassword(user.getIdUtilisateur(), nouveau, ancien);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new sn.ferme.swing.PanelRound();
        imageAvatar1 = new sn.ferme.swing.ImageAvatar();
        Lbprenom = new javax.swing.JLabel();
        LbNumero = new javax.swing.JLabel();
        LbResidence = new javax.swing.JLabel();
        LbEMail = new javax.swing.JLabel();
        btnModifier = new javax.swing.JButton();
        panelRound2 = new sn.ferme.swing.PanelRound();
        txtAncienPasse = new javax.swing.JPasswordField();
        txtNouveauPasse = new javax.swing.JPasswordField();
        txtConfirmerPAsse = new javax.swing.JPasswordField();
        btnChangez = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        panelModifier = new sn.ferme.swing.PanelRound();

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));

        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sn/ferme/icon/kossamb_1_1.jpg"))); // NOI18N

        Lbprenom.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        Lbprenom.setForeground(new java.awt.Color(0, 102, 102));

        LbNumero.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        LbNumero.setForeground(new java.awt.Color(0, 102, 102));

        LbResidence.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        LbResidence.setForeground(new java.awt.Color(0, 102, 102));

        LbEMail.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        LbEMail.setForeground(new java.awt.Color(0, 102, 102));

        btnModifier.setBackground(new java.awt.Color(0, 255, 204));
        btnModifier.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModifier.setForeground(new java.awt.Color(102, 102, 102));
        btnModifier.setText("Modifier mes informations");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnModifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LbNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lbprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LbEMail, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LbResidence, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(Lbprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LbNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LbEMail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LbResidence, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addComponent(btnModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));

        txtAncienPasse.setText("Ancien mot de passe");
        txtAncienPasse.setToolTipText("Ancien mot de passe");
        txtAncienPasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAncienPasseActionPerformed(evt);
            }
        });

        txtNouveauPasse.setText("Mot de passe");
        txtNouveauPasse.setToolTipText("Nouveau mot de passe");

        txtConfirmerPAsse.setText("Mot de passe");
        txtConfirmerPAsse.setToolTipText("Confirmer mot de passe");

        btnChangez.setBackground(new java.awt.Color(204, 0, 0));
        btnChangez.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnChangez.setForeground(new java.awt.Color(255, 255, 255));
        btnChangez.setText("Changer mot de passe");
        btnChangez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangezActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("Modifier mot de passe");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(btnChangez, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmerPAsse, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNouveauPasse, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAncienPasse, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAncienPasse, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtNouveauPasse, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtConfirmerPAsse, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChangez, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout panelModifierLayout = new javax.swing.GroupLayout(panelModifier);
        panelModifier.setLayout(panelModifierLayout);
        panelModifierLayout.setHorizontalGroup(
            panelModifierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );
        panelModifierLayout.setVerticalGroup(
            panelModifierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(panelModifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelModifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(164, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        showMessage(new ModifierInfos());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnChangezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangezActionPerformed
        try {
            int sup = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous vraiment changer votre mot de passe ?", "Confirmation changement mot de passe",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {
                changerMotpasse();
                JOptionPane.showMessageDialog(null, "Mot de passe modifier");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Impossible de changer votre motre passe");
        }
    }//GEN-LAST:event_btnChangezActionPerformed

    private void txtAncienPasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAncienPasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAncienPasseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbEMail;
    private javax.swing.JLabel LbNumero;
    private javax.swing.JLabel LbResidence;
    private javax.swing.JLabel Lbprenom;
    private javax.swing.JButton btnChangez;
    private javax.swing.JButton btnModifier;
    private sn.ferme.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private sn.ferme.swing.PanelRound panelModifier;
    private sn.ferme.swing.PanelRound panelRound1;
    private sn.ferme.swing.PanelRound panelRound2;
    private javax.swing.JPasswordField txtAncienPasse;
    private javax.swing.JPasswordField txtConfirmerPAsse;
    private javax.swing.JPasswordField txtNouveauPasse;
    // End of variables declaration//GEN-END:variables
}
