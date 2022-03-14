package sn.ferme.component;

import sn.ferme.model.ModelCard;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;

public class Card extends javax.swing.JPanel {

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
    }

    private Color colorGradient;

    public Card() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(112, 69, 246));
        colorGradient = new Color(255, 255, 255);
        pro.setBackground(new Color(255, 255, 255, 100));
        pro.setForeground(Color.WHITE);
    }

    public void setData(ModelCard data) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        lbTitre.setText(data.getTitre());
        lbValeur.setText(df.format(data.getValeur()));
        lbIcone.setIcon(data.getIcone());
        pro.setValue(data.getPourcentage());
        lbPour.setText(df.format(data.getPourcentage()) + "%");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcone = new javax.swing.JLabel();
        lbTitre = new javax.swing.JLabel();
        lbValeur = new javax.swing.JLabel();
        lbPour = new javax.swing.JLabel();
        pro = new sn.ferme.swing.ProgressBarCustom();

        lbIcone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lbTitre.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbTitre.setForeground(new java.awt.Color(225, 225, 225));
        lbTitre.setText("Titre");

        lbValeur.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        lbValeur.setForeground(new java.awt.Color(225, 225, 225));
        lbValeur.setText("Valeur");

        lbPour.setForeground(new java.awt.Color(255, 255, 255));
        lbPour.setText("0%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbPour))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitre)
                            .addComponent(lbValeur))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbValeur)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbPour, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, getHeight(), getBackground(), getWidth(), 0, colorGradient);
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcone;
    private javax.swing.JLabel lbPour;
    private javax.swing.JLabel lbTitre;
    private javax.swing.JLabel lbValeur;
    private sn.ferme.swing.ProgressBarCustom pro;
    // End of variables declaration//GEN-END:variables
}
