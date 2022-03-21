/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sn.ferme.espace.admin;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sn.ferme.chart.ModelChart;
import sn.ferme.espace.Admin;
import sn.ferme.model.ModelBovin;
import sn.ferme.model.ModelTraite;
import sn.ferme.model.Utilisateur;
import sn.ferme.service.ServiceBovin;
import sn.ferme.service.ServiceTraite;

/**
 *
 * @author hp
 */
public class DetailBovin extends javax.swing.JPanel {

    /**
     * Creates new form DetailBovin
     */
    // String bovin;
    private ServiceBovin service = new ServiceBovin();

    public DetailBovin(String bovin) {
        //this.bovin = bovin;

        int id = 0;
        initComponents();
        ArrayList<ModelBovin> m = new ServiceBovin().DetailsBovin(bovin);
        for (ModelBovin b : m) {
            id = b.getIdBovin();
            Lb_situation.setText(b.getSituation());
            Lb_race.setText(b.getNomRace());
            Lb_nom.setText(b.getNom());
            Lb_naissance.setText(b.getDateNaissance());
            Lb_code.setText(b.getCodeBovin());
            Lb_etat.setText(b.getEtat());
            Lb_etat_sante.setText(b.getEtatSante());
            Lb_prix.setText("" + b.getPrix() + " F");
            Lb_description.setText(b.getDescription());
            Lb_geniteur.setText(b.getGeniteur());
            Lb_genitrice.setText(b.getGenetrice());
            System.out.println(id);
            if ("Vache".equals(b.getDescription())) {
                //  label_production.setText("Evolution Production et poids");
                LbPhase.setText("Phase");
                LbPeriode.setText("Periode");
                Lbproduction.setText("Production");
                ArrayList<ModelBovin> V = new ServiceBovin().afficherBovinPeriode(id);
                for (ModelBovin d : V) {
                    txtPeriode.setText("" + d.getPeriode());
                    txtPhase.setText("" + d.getPhase());
                }
                int i = 0;
                int prod = 0;
                for (Map.Entry<String, ModelTraite> v : new ServiceTraite().afficherPodParVache().entrySet()) {
                    ModelTraite model = v.getValue();
                    if (v.getKey().equals(b.getNom())) {
                        prod = model.getTraiteMatin() + model.getTraiteSoir();
                        break;
                    }
                    i++;
                }
                
                txtProduction.setText(""+prod+" litres");
            }

            label_production.setText("Evolution du Poids");
            chart.addLegend("Poids", new Color(245, 189, 135));
            for (Map.Entry<Integer, ModelBovin> v : service.pesageParBovin(id).entrySet()) {
                ModelBovin model = v.getValue();
                if (null != v.getKey()) {
                    switch (v.getKey()) {
                        case 1:
                            chart.addData(new ModelChart("Janvier", new double[]{model.getPoids()}));
                            break;
                        //   chart.addData(new ModelChart("Fev", new double[]{600}));
                        case 2:
                            chart.addData(new ModelChart("Fevrier", new double[]{model.getPoids()}));
                            break;
                        case 3:
                            chart.addData(new ModelChart("Mars", new double[]{model.getPoids()}));
                            break;
                        default:
                            break;
                    }
                }

            }

        }
        try {
            ArrayList<ModelBovin> achat = new ServiceBovin().bovinAcheter(id);
            for (ModelBovin a : achat) {
                if (a.getDateAchatBovin() != null) {
                    LbDateAchat.setText("Date achat");
                    Lb_achat.setText(a.getDateAchatBovin());
                    LbMontantAchat.setText("Montant achat");
                    LbMontant.setText(a.getMontantAchat() + " F");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailBovin.class.getName()).log(Level.SEVERE, null, ex);
        }
        //   init();
    }

    public void init() {
        chart.addLegend("Poids", new Color(245, 189, 135));
        chart.addLegend("Production", new Color(135, 189, 245));

        chart.addData(new ModelChart("January", new double[]{100, 150}));
        chart.addData(new ModelChart("February", new double[]{600, 750}));
        chart.addData(new ModelChart("March", new double[]{200, 350}));
        chart.addData(new ModelChart("April", new double[]{480, 150}));
        chart.addData(new ModelChart("May", new double[]{350, 540}));
        chart.addData(new ModelChart("June", new double[]{190, 500}));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Lb_code = new javax.swing.JLabel();
        Lb_nom = new javax.swing.JLabel();
        Lb_race = new javax.swing.JLabel();
        Lb_geniteur = new javax.swing.JLabel();
        Lb_genitrice = new javax.swing.JLabel();
        Lb_naissance = new javax.swing.JLabel();
        Lb_situation = new javax.swing.JLabel();
        Lb_etat = new javax.swing.JLabel();
        Lb_etat_sante = new javax.swing.JLabel();
        Lb_description = new javax.swing.JLabel();
        Lb_prix = new javax.swing.JLabel();
        label_production = new javax.swing.JLabel();
        LbDateAchat = new javax.swing.JLabel();
        Lb_achat = new javax.swing.JLabel();
        LbMontantAchat = new javax.swing.JLabel();
        LbMontant = new javax.swing.JLabel();
        chart = new sn.ferme.chart.Chart();
        LbPeriode = new javax.swing.JLabel();
        txtPeriode = new javax.swing.JLabel();
        LbPhase = new javax.swing.JLabel();
        txtPhase = new javax.swing.JLabel();
        Lbproduction = new javax.swing.JLabel();
        txtProduction = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(79, 79, 79));
        jLabel3.setText("Detail Bovin ");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Code bovin");

        jLabel2.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Race");

        jLabel4.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Nom bovin");

        jLabel5.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Date naissance");

        jLabel6.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Genitrice");

        jLabel7.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Geniteur");

        jLabel8.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Etat de santé");

        jLabel9.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Etat");

        jLabel10.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Situation");

        jLabel12.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Prix");

        jLabel13.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Description");

        Lb_code.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_code.setForeground(new java.awt.Color(0, 102, 102));

        Lb_nom.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_nom.setForeground(new java.awt.Color(0, 102, 102));

        Lb_race.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_race.setForeground(new java.awt.Color(0, 102, 102));

        Lb_geniteur.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_geniteur.setForeground(new java.awt.Color(0, 102, 102));

        Lb_genitrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_genitrice.setForeground(new java.awt.Color(0, 102, 102));

        Lb_naissance.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_naissance.setForeground(new java.awt.Color(0, 102, 102));

        Lb_situation.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_situation.setForeground(new java.awt.Color(0, 102, 102));

        Lb_etat.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_etat.setForeground(new java.awt.Color(0, 102, 102));

        Lb_etat_sante.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_etat_sante.setForeground(new java.awt.Color(0, 102, 102));

        Lb_description.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_description.setForeground(new java.awt.Color(0, 102, 102));

        Lb_prix.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_prix.setForeground(new java.awt.Color(0, 102, 102));

        label_production.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        label_production.setForeground(new java.awt.Color(79, 79, 79));
        label_production.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label_production.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));

        LbDateAchat.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        LbDateAchat.setForeground(new java.awt.Color(102, 102, 102));

        Lb_achat.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Lb_achat.setForeground(new java.awt.Color(0, 102, 102));

        LbMontantAchat.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        LbMontantAchat.setForeground(new java.awt.Color(102, 102, 102));

        LbMontant.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        LbMontant.setForeground(new java.awt.Color(0, 102, 102));

        LbPeriode.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        LbPeriode.setForeground(new java.awt.Color(102, 102, 102));

        txtPeriode.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txtPeriode.setForeground(new java.awt.Color(0, 102, 102));

        LbPhase.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        LbPhase.setForeground(new java.awt.Color(102, 102, 102));

        txtPhase.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txtPhase.setForeground(new java.awt.Color(0, 102, 102));

        Lbproduction.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        Lbproduction.setForeground(new java.awt.Color(102, 102, 102));

        txtProduction.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txtProduction.setForeground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel3)
                        .addGap(0, 182, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Lb_code, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(Lb_nom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Lb_race, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Lb_genitrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Lb_geniteur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Lb_naissance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(7, 7, 7))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(LbPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(txtPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(LbPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Lbproduction, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtProduction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Lb_situation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Lb_etat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Lb_etat_sante, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(Lb_description, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Lb_prix, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LbDateAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Lb_achat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LbMontantAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LbMontant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(label_production, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lb_code, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Lb_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_race, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(Lb_geniteur, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33))
                                            .addComponent(Lb_genitrice, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Lb_naissance, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LbPeriode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPeriode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LbDateAchat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lb_achat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LbMontantAchat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LbMontant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Lb_situation, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33))
                                    .addComponent(Lb_etat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Lb_etat_sante, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Lb_description, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Lb_prix, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LbPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lbproduction, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProduction, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(label_production, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbDateAchat;
    private javax.swing.JLabel LbMontant;
    private javax.swing.JLabel LbMontantAchat;
    private javax.swing.JLabel LbPeriode;
    private javax.swing.JLabel LbPhase;
    private javax.swing.JLabel Lb_achat;
    private javax.swing.JLabel Lb_code;
    private javax.swing.JLabel Lb_description;
    private javax.swing.JLabel Lb_etat;
    private javax.swing.JLabel Lb_etat_sante;
    private javax.swing.JLabel Lb_geniteur;
    private javax.swing.JLabel Lb_genitrice;
    private javax.swing.JLabel Lb_naissance;
    private javax.swing.JLabel Lb_nom;
    private javax.swing.JLabel Lb_prix;
    private javax.swing.JLabel Lb_race;
    private javax.swing.JLabel Lb_situation;
    private javax.swing.JLabel Lbproduction;
    private sn.ferme.chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel label_production;
    private javax.swing.JLabel txtPeriode;
    private javax.swing.JLabel txtPhase;
    private javax.swing.JLabel txtProduction;
    // End of variables declaration//GEN-END:variables
}
