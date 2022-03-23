package sn.ferme.espace.fermier;

import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sn.ferme.espace.admin.*;
import sn.ferme.model.ModelBovin;
import sn.ferme.model.ModelCard;
import sn.ferme.model.ModelDepense;
import sn.ferme.model.ModelTraite;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceBovin;
import sn.ferme.service.ServiceTraite;

public class ProductionF extends javax.swing.JPanel {

    private ServiceTraite serviceT = new ServiceTraite();
    private ServiceBovin serviceB = new ServiceBovin();
    SimpleDateFormat dchoix = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateTraite = new SimpleDateFormat("yyyy-MM-dd");

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
        try {
            dataLactation();
            dataTarissemnt();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionF.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnAjouterTraite.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChangerEnLactation.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChangerEnTarissement.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChangerPhase2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChangerPhaseT.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtTraiteMatin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        txtTraiteSoir.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
    }

    private void init() {
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

        Object body[][] = new Object[serviceT.afficherPodParVache().size()][3];
        int matin = 0, soir = 0;
        String[] header = {"Nom vache", "Matin", "Soir"};
        int i = 0;

        for (Map.Entry<String, ModelTraite> v : serviceT.afficherPodParVache().entrySet()) {
            ModelTraite model = v.getValue();

            body[i][0] = v.getKey();
            body[i][1] = model.getTraiteMatin();
            body[i][2] = model.getTraiteSoir();
            matin = matin + model.getTraiteMatin();
            soir = soir + model.getTraiteSoir();
            i++;
        }

        int t = matin + soir;
        cardMatin.setData(new ModelCard("Traite Matin(L)", matin, matin * 100 / t));
        cardSoir.setData(new ModelCard("Traite Soir(L)", soir, soir * 100 / t));
        lbProductionT.setText("" + t + " L");
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
            date = dateTraite.format(datechooser.getDate());
            int m = Integer.parseInt(matin);
            int s = Integer.parseInt(soir);
            int idUser = user.getIdUtilisateur();
            ModelTraite traite = new ModelTraite(0, idUser, id, m, s, date);
            serviceT.insererTraite(traite);

        }
    }
    Object bodyL[][] = new Object[serviceB.listeBovinLactation().size()][4];

    private void dataLactation() throws SQLException {

        String[] header = {"Code", "Nom", "Race", "Phase"};
        int i = 0;
        for (ModelBovin m : new ServiceBovin().listeBovinLactation()) {
            bodyL[i][0] = m.getCodeBovin();
            bodyL[i][1] = m.getNom();
            bodyL[i][2] = m.getNomRace();
            bodyL[i][3] = m.getPhase();
            i++;
        }
        tableLactation.setModel(new DefaultTableModel(bodyL, header));
    }
    Object bodyT[][] = new Object[serviceB.listeBovinTarissement().size()][4];

    private void dataTarissemnt() throws SQLException {

        String[] header = {"Code", "Nom", "Race", "Phase"};
        int i = 0;
        for (ModelBovin m : new ServiceBovin().listeBovinTarissement()) {
            bodyT[i][0] = m.getCodeBovin();
            bodyT[i][1] = m.getNom();
            bodyT[i][2] = m.getNomRace();
            bodyT[i][3] = m.getPhase();
            // body[i][4] = m.getPeriode();
            // body[i][5] = m.getNomRace();
            i++;
        }
        tableTarissement.setModel(new DefaultTableModel(bodyT, header));
    }

    public void updatePeriodeLactationEnTarissement() throws SQLException {
        int row = tableLactation.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {

            // int code = Integer.parseInt(id);
            //System.out.println(id);
            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez confirmer le changement de periode ?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {

                String id = (String) bodyL[row][1];
                int code = serviceB.recupererCodeBovin(id);
                System.out.println(code);
                serviceB.updatePeriode(code, "tarissement");

            }
        }
    }

    public void changerPhaseLactation() throws SQLException {
        int row = tableLactation.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {

            // int code = Integer.parseInt(id);
            //System.out.println(id);
            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez confirmer le changement de phase ?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {

                String id = (String) bodyL[row][1];
                int code = serviceB.recupererCodeBovin(id);
                String phase = serviceB.recupererPhase(code);
                if (phase.equals("gestant")) {
                    serviceB.updatePhase(code, "non gestant");
                } else {
                    serviceB.updatePhase(code, "gestant");
                }

            }
        }
    }

    public void changerPhaseTarissement() throws SQLException {
        int row = tableTarissement.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {

            // int code = Integer.parseInt(id);
            //System.out.println(id);
            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez confirmer le changement de phase ?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {

                String id = (String) bodyT[row][1];
                int code = serviceB.recupererCodeBovin(id);
                String phase = serviceB.recupererPhase(code);
                if (phase.equals("gestant")) {
                    serviceB.updatePhase(code, "non gestant");
                } else {
                    serviceB.updatePhase(code, "gestant");
                }

            }
        }
    }

    public void updatePeriodeTarissementEnLactationM() throws SQLException {
        int row = tableTarissement.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne!!!");
        } else {

            int sup = JOptionPane.showConfirmDialog(null,
                    "Veuillez confirmer le changement de periode ?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (sup == 0) {

                String id = (String) bodyT[row][1];
                int code = serviceB.recupererCodeBovin(id);
                System.out.println(code);
                serviceB.updatePeriode(code, "lactation");

            }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tableLactation = new sn.ferme.swing.TableColumn();
        jLabel3 = new javax.swing.JLabel();
        btnChangerEnTarissement = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnChangerEnLactation = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTarissement = new sn.ferme.swing.TableColumn();
        btnChangerPhaseT = new javax.swing.JButton();
        btnChangerPhase2 = new javax.swing.JButton();

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

        jScrollPane2.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane2.setBorder(null);

        tableLactation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableLactation);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Liste Bovin en Lactation");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        btnChangerEnTarissement.setBackground(new java.awt.Color(0, 153, 0));
        btnChangerEnTarissement.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnChangerEnTarissement.setForeground(new java.awt.Color(255, 255, 255));
        btnChangerEnTarissement.setText("Mettre en tarissement");
        btnChangerEnTarissement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangerEnTarissementActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 79, 79));
        jLabel4.setText("Liste Bovin en Tarissement");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        btnChangerEnLactation.setBackground(new java.awt.Color(0, 153, 0));
        btnChangerEnLactation.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnChangerEnLactation.setForeground(new java.awt.Color(255, 255, 255));
        btnChangerEnLactation.setText("Mettre en lactation");
        btnChangerEnLactation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangerEnLactationActionPerformed(evt);
            }
        });

        jScrollPane3.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane3.setBorder(null);

        tableTarissement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableTarissement);

        btnChangerPhaseT.setBackground(new java.awt.Color(51, 51, 255));
        btnChangerPhaseT.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnChangerPhaseT.setForeground(new java.awt.Color(255, 255, 255));
        btnChangerPhaseT.setText("Changer phase");
        btnChangerPhaseT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangerPhaseTActionPerformed(evt);
            }
        });

        btnChangerPhase2.setBackground(new java.awt.Color(51, 51, 255));
        btnChangerPhase2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnChangerPhase2.setForeground(new java.awt.Color(255, 255, 255));
        btnChangerPhase2.setText("Changer phase");
        btnChangerPhase2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangerPhase2ActionPerformed(evt);
            }
        });

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
                        .addGap(93, 93, 93)
                        .addComponent(lbProductionT, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(cardMatin, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardSoir, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChangerPhase2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChangerEnTarissement))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(102, 102, 102)
                        .addComponent(btnChangerPhaseT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChangerEnLactation))
                    .addComponent(jScrollPane3))
                .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lbProductionT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cardMatin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cardSoir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChangerEnLactation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnChangerPhaseT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnChangerEnTarissement, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnChangerPhase2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
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

    private void btnChangerEnTarissementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangerEnTarissementActionPerformed
        try {
            updatePeriodeLactationEnTarissement();
            JOptionPane.showMessageDialog(null, "Periode modifier!!!");// TODO add your handling code here:
// TODO add your handling code here:
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnChangerEnTarissementActionPerformed

    private void btnChangerEnLactationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangerEnLactationActionPerformed
        try {
            updatePeriodeTarissementEnLactationM();
            JOptionPane.showMessageDialog(null, "Periode modifier!!!");// TODO add your handling code here:
// TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(ProductionF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnChangerEnLactationActionPerformed

    private void btnChangerPhaseTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangerPhaseTActionPerformed
        try {
            changerPhaseTarissement();
            JOptionPane.showMessageDialog(null, "Phase modifier!!!");// TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(ProductionF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnChangerPhaseTActionPerformed

    private void btnChangerPhase2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangerPhase2ActionPerformed
        try {
            changerPhaseLactation();
            JOptionPane.showMessageDialog(null, "Phase modifier!!!");// TODO add your handling code here:
// TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(ProductionF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnChangerPhase2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterTraite;
    private javax.swing.JButton btnChangerEnLactation;
    private javax.swing.JButton btnChangerEnTarissement;
    private javax.swing.JButton btnChangerPhase2;
    private javax.swing.JButton btnChangerPhaseT;
    private sn.ferme.component.Card cardMatin;
    private sn.ferme.component.Card cardSoir;
    private javax.swing.JComboBox<String> comboVache;
    private com.toedter.calendar.JDateChooser datechooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbProductionT;
    private sn.ferme.swing.TableColumn table;
    private sn.ferme.swing.TableColumn tableLactation;
    private sn.ferme.swing.TableColumn tableTarissement;
    private javax.swing.JTextField txtTraiteMatin;
    private javax.swing.JTextField txtTraiteSoir;
    // End of variables declaration//GEN-END:variables
}
