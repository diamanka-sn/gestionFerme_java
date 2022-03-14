package sn.ferme.espace.admin;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
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
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import sn.ferme.espace.Admin;
import sn.ferme.login.composant.Message;
import sn.ferme.model.ModelBovin;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ModelRace;
import sn.ferme.service.ServiceBovin;

public class Bovin extends javax.swing.JPanel {

    private ServiceBovin service = new ServiceBovin();
    SimpleDateFormat dchoix = new SimpleDateFormat("dd/MM/yyyy");
    Date dactuelle = new Date();
    String dA = dchoix.format(dactuelle);
    String date = null;
    String dateN = null;
    String dateA = null;
    Date dateJour = null, dateChoix = null;
    Utilisateur user = new Utilisateur();

    public Bovin(Utilisateur user) {
        this.user = user;
        initComponents();
        data();
        setOpaque(false);

        btnDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnModifier.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSupprimer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        try {
            race_choix.setModel(new DefaultComboBoxModel(service.listeRaceExistant().toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Bovin.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtPrix.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
    }

    public Bovin() {
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

    public void enregistrerBovin() {
        String code = null;
        String nom = txtNom.getText();
        String description = txtDescription.getSelectedItem().toString();
        String situation = txtSituation.getSelectedItem().toString();
        String race = race_choix.getSelectedItem().toString();
        String prix = txtPrix.getText();
        String etatSante = txtEtatSante.getSelectedItem().toString();
        switch (description) {
            case "Veau":
                code = "VEA-";
                break;
            case "Velle":
                code = "VEL-";
                break;
            case "Vache":
                code = "VAC-";
                break;
            case "Genisse":
                code = "GEN-";
                break;
            case "Taureau":
                code = "TAU-";
                break;
        }
        if (nom.isEmpty() || nom.contains("Nom bovin") || prix.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!!");
        } else if (jDateAchat.getDate() == null || jDateNaissance.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les dates");
        } else if (verifDate(jDateAchat) == -1 || verifDate(jDateNaissance) == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une date valide");
        } else {
            dateN = dchoix.format(jDateNaissance.getDate());
            dateA = dchoix.format(jDateAchat.getDate());
            int p = Integer.parseInt(prix);
            try {
                int idRace = service.recupererCodeRace(race);
                ModelBovin b = new ModelBovin(0, idRace, "Defaut05550", nom, null, dateN, etatSante, "-", "-", "Vivant", situation, p, description);
                int c = service.insererBovin(b);

                if (c > 0) {
                    int idBovin = service.recupererCodeBovin(nom);
                    code = code + "" + idBovin;
                    System.out.println(code);
                    service.updatecodeBovin(idBovin, code);
                    ModelBovin achats = new ModelBovin(idBovin, user.getIdUtilisateur(), p, dateA);
                    System.out.println(idBovin);
                    service.inserAchatsBovin(achats);
                    if ("Vache".equals(description)) {
                        String phase = comPhase.getSelectedItem().toString();
                        String periode = comPeriode.getSelectedItem().toString();
                        ModelBovin vache = new ModelBovin(idBovin, periode, phase);

                        service.inserVache(vache);
                    }

                    JOptionPane.showMessageDialog(null, "Enregistre avec success! " + user.getIdUtilisateur());
                } else {
                    JOptionPane.showMessageDialog(null, "erreur d'insertion");
                }
                System.out.print(c);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
    Object body[][] = new Object[service.afficherBovin().size()][9];

    public void data() {

        String[] header = {"Code", "Nom", "Naissance", "Etat santé", "Etat", "Situation", "prix", "Description", "Race"};
        int i = 0;
        lbNombre.setText(service.afficherBovin().size() / 2 + " ");
        for (ModelBovin m : new ServiceBovin().afficherBovin()) {
            body[i][0] = m.getCodeBovin();
            body[i][1] = m.getNom();
            body[i][2] = m.getDateNaissance();
            body[i][3] = m.getEtatSante();

            body[i][4] = m.getEtat();
            body[i][5] = m.getSituation();
            body[i][6] = m.getPrix();
            body[i][7] = m.getDescription();
            body[i][8] = m.getNomRace();
            i++;
        }
        table.setModel(new DefaultTableModel(body, header));
    }

    public void ajouterRace() throws SQLException {
        ModelRace r = null;
        String race = txtRace.getText();
        if (race.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs");
        } else {
            r = new ModelRace(race);
            int i = service.insererRace(r);
            System.out.println(i);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Enregistre avec success! ");
                txtRace.setText("Ajoutez race");
            } else {
                JOptionPane.showMessageDialog(null, "Race existe déja! ");
                txtRace.setText("Ajoutez race");

            }
        }
    }

    private void showMessage(Component com) {
        detailsBovin.removeAll();
        detailsBovin.setLayout(new FlowLayout()); 
        detailsBovin.add(com);
        detailsBovin.repaint();
        detailsBovin.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNom = new sn.ferme.login.swing.MyTextField();
        txtDescription = new javax.swing.JComboBox<>();
        txtEtatSante = new javax.swing.JComboBox<>();
        txtSituation = new javax.swing.JComboBox<>();
        txtPrix = new sn.ferme.login.swing.MyTextField();
        sauvegarder = new sn.ferme.login.swing.ButtonOutLine();
        race_choix = new javax.swing.JComboBox<>();
        jDateNaissance = new com.toedter.calendar.JDateChooser();
        jDateAchat = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comPeriode = new javax.swing.JComboBox<>();
        comPhase = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtRace = new sn.ferme.login.swing.MyTextField();
        ajouterRace = new sn.ferme.login.swing.ButtonOutLine();
        detailsBovin = new javax.swing.JPanel();
        btnSupprimer = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        lbNombre = new javax.swing.JLabel();

        jScrollPane1.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane1.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Nom", "Naissance", "Etat santé", "Geniteur", "Genitrice", "Etat", "Situation", "Prix", "Description", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Liste Bovin Vivant");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Ajout Bovin");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 79, 79));
        jLabel4.setText("Ajout Race");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        txtNom.setText("Nom bovin");

        txtDescription.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Veau", "Velle", "Taureau", "Genisse", "Vache" }));

        txtEtatSante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sain", "Malade" }));

        txtSituation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En vente", "Pas en vente" }));

        txtPrix.setText("Prix");

        sauvegarder.setText("Ajouter bovin");
        sauvegarder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauvegarderActionPerformed(evt);
            }
        });

        jDateNaissance.setToolTipText("Date de naissance");

        jDateAchat.setToolTipText("Date achat bovin");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Date naissance bovin");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Date achat bovin");

        comPeriode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lactation", "Tarrissement" }));

        comPhase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gestante", "Non gestante" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateNaissance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(txtPrix, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(5, 5, 5)
                                .addComponent(sauvegarder, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(race_choix, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEtatSante, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSituation, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(40, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEtatSante, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSituation, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comPeriode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPrix, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jDateAchat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(jDateNaissance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(race_choix, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(comPhase))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sauvegarder, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        txtRace.setText("Nom race");

        ajouterRace.setText("Ajouter race");
        ajouterRace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterRaceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRace, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouterRace, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ajouterRace, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout detailsBovinLayout = new javax.swing.GroupLayout(detailsBovin);
        detailsBovin.setLayout(detailsBovinLayout);
        detailsBovinLayout.setHorizontalGroup(
            detailsBovinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        detailsBovinLayout.setVerticalGroup(
            detailsBovinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnSupprimer.setBackground(new java.awt.Color(204, 0, 0));
        btnSupprimer.setText("Supprimer");
        btnSupprimer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        btnModifier.setBackground(new java.awt.Color(0, 153, 0));
        btnModifier.setText("Modifier");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });

        btnDetails.setBackground(new java.awt.Color(255, 255, 204));
        btnDetails.setText("Détails");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        lbNombre.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        lbNombre.setForeground(new java.awt.Color(79, 79, 79));
        lbNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailsBovin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(btnSupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                    .addComponent(detailsBovin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ajouterRaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterRaceActionPerformed
        try {
            ajouterRace(); // TODO add your handling code here:
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Race existe déja! ");
            txtRace.setText("Ajoutez race");
        }
    }//GEN-LAST:event_ajouterRaceActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {
            String id = (String) body[row][0];
            System.out.println(id);
            showMessage(new DetailBovin(id));
        }
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {
            String id = (String) body[row][0];
            String description = (String) body[row][7];
            System.out.println(id);
            showMessage(new ModifierBovin(id,description));// TODO add your handling code here:
        }
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void sauvegarderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sauvegarderActionPerformed
        enregistrerBovin();// TODO add your handling code here:
    }//GEN-LAST:event_sauvegarderActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sn.ferme.login.swing.ButtonOutLine ajouterRace;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JComboBox<String> comPeriode;
    private javax.swing.JComboBox<String> comPhase;
    private javax.swing.JPanel detailsBovin;
    private com.toedter.calendar.JDateChooser jDateAchat;
    private com.toedter.calendar.JDateChooser jDateNaissance;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JComboBox<String> race_choix;
    private sn.ferme.login.swing.ButtonOutLine sauvegarder;
    private sn.ferme.swing.TableColumn table;
    private javax.swing.JComboBox<String> txtDescription;
    private javax.swing.JComboBox<String> txtEtatSante;
    private sn.ferme.login.swing.MyTextField txtNom;
    private sn.ferme.login.swing.MyTextField txtPrix;
    private sn.ferme.login.swing.MyTextField txtRace;
    private javax.swing.JComboBox<String> txtSituation;
    // End of variables declaration//GEN-END:variables
}
