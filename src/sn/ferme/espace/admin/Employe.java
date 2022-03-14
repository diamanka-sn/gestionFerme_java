package sn.ferme.espace.admin;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import sn.ferme.event.ActionEvenenement;
import sn.ferme.login.swing.MyTextField;
import sn.ferme.model.Action;
import sn.ferme.model.ModelAction;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceUser;

public class Employe extends javax.swing.JPanel {

    private ServiceUser service = new ServiceUser();
    private Utilisateur u;
    private Utilisateur utilisateur = new Utilisateur();

    int nb;

    public Employe() {
        initComponents();
        init();
        data();
        setOpaque(false);
        // txtPasse.setHint("Mot de passe");
    }

    public void init() {
        // formAjout();
    }

    public void data() {
        ModelAction data = null;
        ActionEvenenement evenement = new ActionEvenenement() {
            @Override
            public void delete(Utilisateur user) {
                System.out.println("Supprimer " + user.getNom());
            }

            @Override
            public void update(Utilisateur user) {
            }
        };
        Object body[][] = new Object[service.afficherEmploye().size()][8];
        String[] header = {"id", "Nom complet", "Telephone", "Adresse", "Email", "pofile", "Etat", "Action"};
        int i = 0;
        for (Utilisateur m : new ServiceUser().afficherEmploye()) {
            data = new ModelAction(m, evenement);
            body[i][0] = m.getIdUtilisateur();
            body[i][1] = m.getPrenom() + " " + m.getNom();
            body[i][2] = m.getTelephone();
            body[i][3] = m.getAdresse();
            body[i][4] = m.getEmail();
            body[i][5] = m.getProfile();
            if (m.isIsAdmin() && "fermier".equals(m.getProfile())) {
                body[i][6] = "Actif";
            } else if (!"fermier".equals(m.getProfile())) {
                body[i][6] = "Employé simple";
            } else {
                body[i][6] = "En congé";
            }
            // body[i][7] = new ModelAction(m, evenement);
            i++;
        }
        //DefaultTableModel model = (DefaultTableModel) table.getModel();
        //model.addRow(body);
        table.setModel(new DefaultTableModel(body, header));
    }

    private void formAjout() {
        formPanel.setLayout(new MigLayout("wrap", "push[][][]10[]10[]10[]10[]10[]25[]push"));
        MyTextField txtNomeE = new MyTextField();
        txtNomeE.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/user.png")));
        txtNomeE.setHint("Nomjhdjhdj");
        // txtNomeE.setBounds(2, 2, 2, 2);
        formPanel.add(txtNomeE, "w 40%");
    }

    public void inscrireEmploye() {
        String nom = txtNom.getText();
        // String prenom = txtPrenom.getText();
        String prenom = "";
        String telephone = txtTelephone.getText();
        String adresse = txtAdresse.getText();
        String email = txtEmail.getText();
        String password = txtPasse.getText();
        String confirme = txtConfirme.getText();
        // String profile = comboProfile.getSelectedItem().toString();
        String profile = "fermier";
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
        jLabel4 = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        txtPrenom = new sn.ferme.login.swing.MyTextField();
        txtNom = new sn.ferme.login.swing.MyTextField();
        txtEmail = new sn.ferme.login.swing.MyTextField();
        txtAdresse = new sn.ferme.login.swing.MyTextField();
        txtTelephone = new sn.ferme.login.swing.MyTextField();
        txtPasse = new sn.ferme.login.swing.MyPasswordField();
        txtConfirme = new sn.ferme.login.swing.MyPasswordField();
        button1 = new sn.ferme.login.swing.Button();
        txtEmail1 = new sn.ferme.login.swing.MyTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new sn.ferme.swing.TableColumn();
        jLabel3 = new javax.swing.JLabel();

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

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 79, 79));
        jLabel4.setText("Ajouter fermier");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        txtPrenom.setText("Prenom");

        txtNom.setText("Nom");

        txtEmail.setText("Email");

        txtAdresse.setText("Adresse");

        txtTelephone.setText("Telephone");

        txtPasse.setText("passe");

        txtConfirme.setText("passe");

        button1.setText("Sauvegarder");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        txtEmail1.setText("Salaire");

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtConfirme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPasse, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jScrollPane2.setBorder(null);

        table1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(table1);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Liste employé");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addContainerGap(387, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        inscrireEmploye();        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sn.ferme.login.swing.Button button1;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private sn.ferme.swing.TableColumn table;
    private sn.ferme.swing.TableColumn table1;
    private sn.ferme.login.swing.MyTextField txtAdresse;
    private sn.ferme.login.swing.MyPasswordField txtConfirme;
    private sn.ferme.login.swing.MyTextField txtEmail;
    private sn.ferme.login.swing.MyTextField txtEmail1;
    private sn.ferme.login.swing.MyTextField txtNom;
    private sn.ferme.login.swing.MyPasswordField txtPasse;
    private sn.ferme.login.swing.MyTextField txtPrenom;
    private sn.ferme.login.swing.MyTextField txtTelephone;
    // End of variables declaration//GEN-END:variables
}
