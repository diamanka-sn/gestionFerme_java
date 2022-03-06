package sn.ferme.espace.admin;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceUser;

public class Employe extends javax.swing.JPanel {

    private ServiceUser service;
    private Utilisateur u;
    int nb;

    public Employe() {
        initComponents();
        // init();
        data();
        setOpaque(false);
    }

    public void init() {

    }

    public void data() {
        try {
            // Membre membre = new Membre();
            nb = new ServiceUser().ligne();
        } catch (SQLException ex) {
            Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object body[][] = new Object[nb][6];
        String[] header = {"id", "Nom complet", "Telephone", "Adresse", "Email", "Etat"};
        int i = 0;
        for (Utilisateur m : new ServiceUser().afficher()) {
            body[i][0] = m.getIdUtilisateur();
            body[i][1] = m.getPrenom() + " " + m.getNom();
            body[i][2] = m.getTelephone();
            body[i][3] = m.getAdresse();
            body[i][4] = m.getEmail();
            if (m.isIsAdmin() && "fermier".equals(m.getProfile())) {
                body[i][5] = "Actif";
            } else if (!"fermier".equals(m.getProfile())) {
                body[i][5] = "Employé simple";
            } else {
                body[i][5] = "En congé";
            }
            i++;
        }
        //DefaultTableModel model = (DefaultTableModel) table.getModel();
        //model.addRow(body);
        table.setModel(new DefaultTableModel(body, header));
    }

    public void inscrireEmploye() {
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String telephone = txtTelephone.getText();
        String adresse = txtAdresse.getText();
        String email = txtEmail.getText();
        String password = txtPasse.getText();
        String confirme = txtConfirme.getText();
        String profile = comboProfile.getSelectedItem().toString();
        boolean isAdmin = false;
        if (profile.equalsIgnoreCase("fermier")) {
            isAdmin = true;
            u = new Utilisateur(0, nom, prenom, telephone, adresse, email, password, profile, isAdmin);
            try {
                new ServiceUser().insertUser(u);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            //isAdmin = true;
            u = new Utilisateur(0, nom, prenom, telephone, adresse, email, password, profile, isAdmin);
            try {
                new ServiceUser().insertUser(u);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrenom = new sn.ferme.login.swing.MyTextField();
        button1 = new sn.ferme.login.swing.Button();
        txtAdresse = new sn.ferme.login.swing.MyTextField();
        txtNom = new sn.ferme.login.swing.MyTextField();
        txtTelephone = new sn.ferme.login.swing.MyTextField();
        txtEmail = new sn.ferme.login.swing.MyTextField();
        txtConfirme = new sn.ferme.login.swing.MyPasswordField();
        txtPasse = new sn.ferme.login.swing.MyPasswordField();
        comboProfile = new javax.swing.JComboBox<>();

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Liste fermier");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jScrollPane1.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nom complet", "Telephone", "Adresse", "Email", "Etat", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Liste fermier");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 79, 79));
        jLabel4.setText("Ajouter employé");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        txtPrenom.setText("Prenom");

        button1.setText("Sauvegarder");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        txtAdresse.setText("Adresse");

        txtNom.setText("Nom");

        txtTelephone.setText("Telephone");

        txtEmail.setText("Email");

        txtConfirme.setText("passe");

        txtPasse.setText("passe");

        comboProfile.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "fermier", "employe simple" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtConfirme, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(txtPasse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(comboProfile, 0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jLabel3)
                    .addContainerGap(582, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboProfile))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(278, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(228, 228, 228)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

        inscrireEmploye();        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sn.ferme.login.swing.Button button1;
    private javax.swing.JComboBox<String> comboProfile;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private sn.ferme.swing.TableColumn table;
    private sn.ferme.login.swing.MyTextField txtAdresse;
    private sn.ferme.login.swing.MyPasswordField txtConfirme;
    private sn.ferme.login.swing.MyTextField txtEmail;
    private sn.ferme.login.swing.MyTextField txtNom;
    private sn.ferme.login.swing.MyPasswordField txtPasse;
    private sn.ferme.login.swing.MyTextField txtPrenom;
    private sn.ferme.login.swing.MyTextField txtTelephone;
    // End of variables declaration//GEN-END:variables
}
