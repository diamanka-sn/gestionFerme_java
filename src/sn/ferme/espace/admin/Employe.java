package sn.ferme.espace.admin;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
        txtTelephone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        txtSalaire.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        setOpaque(false);
        // txtPasse.setHint("Mot de passe");
    }

    private void init() {
        // formAjout();
    }

    private void data() {

        Object body[][] = new Object[service.afficherEmploye().size()][5];
        String[] header = {"id", "Nom complet", "Telephone", "Adresse", "Email"};
        int i = 0;
        for (Utilisateur m : new ServiceUser().afficherEmploye()) {

            body[i][0] = m.getIdUtilisateur();
            body[i][1] = m.getPrenom() + " " + m.getNom();
            body[i][2] = m.getTelephone();
            body[i][3] = m.getAdresse();
            body[i][4] = m.getEmail();
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
        // String prenom = ;
        String prenom = txtPrenom.getText();
        String telephone = txtTelephone.getText();
        String adresse = txtAdresse.getText();
        String email = txtEmail.getText();
        String password = txtPasse.getText();
        String confirme = txtConfirme.getText();
        String salaire = txtSalaire.getText();
        // String profile = comboProfile.getSelectedItem().toString();
        String profile = "fermier";
        boolean isAdmin = false;
        if (!confirme.equals(password)) {
            JOptionPane.showMessageDialog(null, "Mot de passe non conforme avec la la confirmation");
        } else if (salaire.contains("Salaire")) {
            JOptionPane.showMessageDialog(null, "Veuillez donner le salaire");
        } else if (profile.equalsIgnoreCase("fermier")) {
            isAdmin = true;
            u = new Utilisateur(0, nom, prenom, telephone, adresse, email, password, profile, isAdmin);

            try {
                int r = new ServiceUser().insertUser(u);
                if (r > 0) {
                    int s = Integer.parseInt(salaire);
                    int code = service.recupereridUser(email);
                    Utilisateur us = new Utilisateur(code, s);
                    int stat = service.insererFermier(us);
                    if (stat > 0) {
                        JOptionPane.showMessageDialog(null, "Fermier ajouté avec success");
                    }
                }
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
        txtSalaire = new sn.ferme.login.swing.MyTextField();

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
        txtPasse.setToolTipText("mot de passe");

        txtConfirme.setText("passe");
        txtConfirme.setToolTipText("Confirmer mot de passe");

        button1.setText("Sauvegarder");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        txtSalaire.setText("Salaire");

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
                        .addComponent(txtSalaire, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(txtSalaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

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
                    .addComponent(jScrollPane1)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        inscrireEmploye();        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sn.ferme.login.swing.Button button1;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private sn.ferme.swing.TableColumn table;
    private sn.ferme.login.swing.MyTextField txtAdresse;
    private sn.ferme.login.swing.MyPasswordField txtConfirme;
    private sn.ferme.login.swing.MyTextField txtEmail;
    private sn.ferme.login.swing.MyTextField txtNom;
    private sn.ferme.login.swing.MyPasswordField txtPasse;
    private sn.ferme.login.swing.MyTextField txtPrenom;
    private sn.ferme.login.swing.MyTextField txtSalaire;
    private sn.ferme.login.swing.MyTextField txtTelephone;
    // End of variables declaration//GEN-END:variables
}
