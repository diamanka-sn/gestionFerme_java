package sn.ferme.espace;

import sn.ferme.component.Menu;
import sn.ferme.event.EventMenuSelected;
import sn.ferme.espace.admin.Accueil;
import sn.ferme.espace.admin.Finance;
import sn.ferme.model.ModelMenu;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import sn.ferme.espace.admin.Bovin;
import sn.ferme.espace.admin.Employe;
import sn.ferme.espace.admin.Production;
import sn.ferme.espace.admin.espace_achat;
import sn.ferme.main.Main;
import sn.ferme.model.Utilisateur;

public class Admin extends javax.swing.JFrame {
    
    private Menu menu = new Menu();
    private JPanel main = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;
    
    public Admin(Utilisateur user) {
        initComponents();
        menu.bottom.setLabelNom(user.getPrenom() + " " + user.getNom().toUpperCase());
        menu.bottom.setProfile(user.getProfile().toUpperCase());
        init(user);
    }
    
    private void init(Utilisateur user) {
        layout = new MigLayout("fill", "0[]10[]5", "0[fill]0");
        body.setLayout(layout);
        main.setOpaque(false);
        main.setLayout(new BorderLayout());
        menu.addEventLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int confirme = JOptionPane.showConfirmDialog(null, "Voulez vous vous deconnecter", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (confirme == 0) {
                    logout();
                }
                
            }
        });
        menu.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        menu.setEvent(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    showForm(new Accueil());
                } else if (index == 1) {
                    showForm(new Finance(user));
                } else if (index == 2) {
                    showForm(new Production());
                } else if (index == 3) {
                    showForm(new Employe());
                } else if (index == 5) {
                    showForm(new espace_achat(user));
                } else {
                    showForm(new Bovin(user));
                }
            }
        });
        menu.addMenu(new ModelMenu("Tableau de bord", new ImageIcon(getClass().getResource("/sn/ferme/icon/t.png"))));
        menu.addMenu(new ModelMenu("Finance", new ImageIcon(getClass().getResource("/sn/ferme/icon/bn.png"))));
        menu.addMenu(new ModelMenu("Production", new ImageIcon(getClass().getResource("/sn/ferme/icon/v.png"))));
        menu.addMenu(new ModelMenu("Employé", new ImageIcon(getClass().getResource("/sn/ferme/icon/em.png"))));
        menu.addMenu(new ModelMenu("Bovin", new ImageIcon(getClass().getResource("/sn/ferme/icon/bv.png"))));
        menu.addMenu(new ModelMenu("Commande", new ImageIcon(getClass().getResource("/sn/ferme/espace/client/a.png"))));

        //menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        body.add(menu, "w 50!");
        body.add(main, "w 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menuShow) {
                    width = 50 + (150 * (1f - fraction));
                    menu.setAlpha(1f - fraction);
                } else {
                    width = 50 + (150 * fraction);
                    menu.setAlpha(fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!");
                body.revalidate();
            }
            
            @Override
            public void end() {
                menuShow = !menuShow;
            }
        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        showForm(new Accueil());
    }
    
    private void showForm(Component com) {
        main.removeAll();
        main.add(com);
        main.repaint();
        main.revalidate();
    }
    
    private void logout() {
        this.dispose();
        new Main().setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        body.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1131, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(Utilisateur user) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Admin main = new Admin(user);
                Image ic = Toolkit.getDefaultToolkit()
                        .getImage(getClass().getResource("/sn/ferme/icon/icons8_cow_breed_100px_4.png"));
                main.setIconImage(ic);
                main.setTitle("Espace administration");
                main.setVisible(true);
                //  new Admin(user).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables
}
