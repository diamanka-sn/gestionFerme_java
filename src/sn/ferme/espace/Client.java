package sn.ferme.espace;

import sn.ferme.component.Menu;
import sn.ferme.event.EventMenuSelected;
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
import sn.ferme.espace.client.AccueilClient;
import sn.ferme.espace.client.AcheterLait;
import sn.ferme.espace.client.PanierClient;

import sn.ferme.main.Main;
import sn.ferme.model.Utilisateur;

public class Client extends javax.swing.JFrame {

    private Menu menu = new Menu();
    private JPanel main = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;
    Utilisateur user;

    public Client(Utilisateur user) {
        this.user = user;
        initComponents();
        menu.bottom.setLabelNom(user.getPrenom() + " " + user.getNom().toUpperCase());
        menu.bottom.setProfile(user.getProfile());
        init();
    }

    private void init() {
        System.out.println(user.getIdUtilisateur());
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
                    showForm(new AccueilClient(user));
                } else if(index == 1){
                    showForm(new PanierClient(user));
                } else if(index==2){
                    showForm(new AcheterLait(user));
                }
            }
        });
        menu.addMenu(new ModelMenu("Accueil", new ImageIcon(getClass().getResource("/sn/ferme/icon/home.png"))));
        menu.addMenu(new ModelMenu("Panier", new ImageIcon(getClass().getResource("/sn/ferme/icon/cartnonvide.png"))));
        menu.addMenu(new ModelMenu("Achater lait", new ImageIcon(getClass().getResource("/sn/ferme/icon/lait.png"))));
        menu.addMenu(new ModelMenu("Historique achats", new ImageIcon(getClass().getResource("/sn/ferme/icon/history.png"))));

        // menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        showForm(new AccueilClient(user));
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
                Client main = new Client(user);
                Image ic = Toolkit.getDefaultToolkit()
                        .getImage(getClass().getResource("/sn/ferme/icon/icons8_cow_breed_100px_4.png"));
                main.setIconImage(ic);
                main.setTitle("Espace client");
                main.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables
}
