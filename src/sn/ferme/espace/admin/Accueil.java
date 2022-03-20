package sn.ferme.espace.admin;

import sn.ferme.chart.ModelChart;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import sn.ferme.model.ModelBovin;
import sn.ferme.model.ModelDiagramme;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceBovin;
import sn.ferme.service.ServiceUser;
import java.util.Map;

public class Accueil extends javax.swing.JPanel {

    private ServiceUser service = new ServiceUser();
    private ServiceBovin serviceB = new ServiceBovin();

    public Accueil() {
        initComponents();
        setOpaque(false);
        init();
        data();
    }

    private void init() {
        List<ModelDiagramme> list1 = new ArrayList<>();
        List<ModelDiagramme> list = new ArrayList<>();

        /*list1.add(new ModelDiagramme("Vache", 10, new Color(4, 174, 243)));
        list1.add(new ModelDiagramme("Genisse", 150, new Color(215, 39, 250)));
        list1.add(new ModelDiagramme("Taureau", 80, new Color(44, 88, 236)));
        list1.add(new ModelDiagramme("Velle", 100, new Color(238, 167, 35)));
        list1.add(new ModelDiagramme("Veau", 125, new Color(127, 63, 255)));
        list1.add(new ModelDiagramme("Saturday", 80, new Color(238, 167, 35)));
        list1.add(new ModelDiagramme("Sunday", 200, new Color(245, 79, 99)));*/
        Color[] couleurs;
        couleurs = new Color[5];
        couleurs[0] = new Color(4, 174, 243);
        couleurs[1] = new Color(215, 39, 250);
        couleurs[2] = new Color(238, 167, 35);
        couleurs[3] = new Color(127, 63, 255);
        couleurs[4] = new Color(245, 79, 99);
        int i = 0;
        int j= 0;
        try {
            for (Map.Entry<String, ModelBovin> v : serviceB.etatFerme().entrySet()) {
                ModelBovin model = v.getValue();
                list1.add(new ModelDiagramme(v.getKey(), model.getNombre(), couleurs[i]));
                i++;
            }

            for (Map.Entry<String, ModelBovin> e : serviceB.etatSanteFerme().entrySet()) {
                ModelBovin model = e.getValue();
                list.add(new ModelDiagramme(e.getKey(), model.getNombre(), couleurs[j]));
                j++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        chartDiagramme1.setModel(list1);
        chartEtat.setModel(list);
    }

    private void data() {

        Object body[][] = new Object[service.afficherClient().size()][5];
        String[] header = {"id", "Nom complet", "Telephone", "Adresse", "Email"};
        int i = 0;
        for (Utilisateur m : new ServiceUser().afficherClient()) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new sn.ferme.swing.TableColumn();
        jLabel2 = new javax.swing.JLabel();
        scrollBar1 = new sn.ferme.swing.ScrollBar();
        chartDiagramme1 = new sn.ferme.chart.ChartDiagramme();
        chartEtat = new sn.ferme.chart.ChartDiagramme();

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

            }
        ));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(79, 79, 79));
        jLabel2.setText("Liste client");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        scrollBar1.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chartDiagramme1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chartEtat, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chartDiagramme1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartEtat, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(scrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sn.ferme.chart.ChartDiagramme chartDiagramme1;
    private sn.ferme.chart.ChartDiagramme chartEtat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private sn.ferme.swing.ScrollBar scrollBar1;
    private sn.ferme.swing.TableColumn table;
    // End of variables declaration//GEN-END:variables
}
