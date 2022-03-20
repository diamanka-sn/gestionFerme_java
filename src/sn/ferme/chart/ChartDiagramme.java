package sn.ferme.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import sn.ferme.model.ModelDiagramme;

public class ChartDiagramme extends javax.swing.JPanel {

    public List<ModelDiagramme> getModel() {
        return model;
    }

    public void setModel(List<ModelDiagramme> model) {
        this.model = model;
        initData();
    }

    private List<ModelDiagramme> model;

    public ChartDiagramme() {
        initComponents();
        setOpaque(false);
        setBackground(Color.WHITE);
    }

    private void initData() {
        panelDiagramme1.removeAllData();
        if (model != null) {
            for (ModelDiagramme data : model) {
                panelDiagramme1.addItem(data);
                panelData.add(new ItemDiagramme(data));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelData = new javax.swing.JPanel();
        panelDiagramme1 = new sn.ferme.chart.PanelDiagramme();

        panelData.setOpaque(false);
        panelData.setLayout(new javax.swing.BoxLayout(panelData, javax.swing.BoxLayout.Y_AXIS));

        panelDiagramme1.setPreferredSize(new java.awt.Dimension(250, 188));

        javax.swing.GroupLayout panelDiagramme1Layout = new javax.swing.GroupLayout(panelDiagramme1);
        panelDiagramme1.setLayout(panelDiagramme1Layout);
        panelDiagramme1Layout.setHorizontalGroup(
            panelDiagramme1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        panelDiagramme1Layout.setVerticalGroup(
            panelDiagramme1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDiagramme1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelDiagramme1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(panelData, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelData;
    private sn.ferme.chart.PanelDiagramme panelDiagramme1;
    // End of variables declaration//GEN-END:variables
}
