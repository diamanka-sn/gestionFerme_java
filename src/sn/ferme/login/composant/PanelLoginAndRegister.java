package sn.ferme.login.composant;

import sn.ferme.model.ModelLogin;
import sn.ferme.model.ModelUser;
import sn.ferme.login.swing.Button;
import sn.ferme.login.swing.MyPasswordField;
import sn.ferme.login.swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import sn.ferme.model.Utilisateur;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public ModelLogin getDataLogin() {
        return dataLogin;
    }

    public Utilisateur getUser() {
        return user;
    }

    private Utilisateur user;
    private ModelLogin dataLogin;

    public PanelLoginAndRegister(ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventLogin);
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister(ActionListener eventRegister) {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("Creer un compte");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);
        MyTextField txtNom = new MyTextField();
        txtNom.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/user.png")));
        txtNom.setHint("Nom");
        register.add(txtNom, "w 60%");

        MyTextField txtPrenom = new MyTextField();
        txtPrenom.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/user.png")));
        txtPrenom.setHint("Prenom");
        register.add(txtPrenom, "w 60%");

        MyTextField txtTelephone = new MyTextField();
        txtTelephone.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/user.png")));
        txtTelephone.setHint("Numero télephone");
        txtTelephone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        register.add(txtTelephone, "w 60%");

        MyTextField txtAdresse = new MyTextField();
        txtAdresse.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/user.png")));
        txtAdresse.setHint("Adresse");
        register.add(txtAdresse, "w 60%");

        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");

        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/pass.png")));
        txtPass.setHint("Mot de passe");
        register.add(txtPass, "w 60%");

        MyPasswordField txtConfirme = new MyPasswordField();
        txtConfirme.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/pass.png")));
        txtConfirme.setHint("Confirmer mot de passe");
        register.add(txtConfirme, "w 60%");

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventRegister);
        cmd.setText("INSCRIPTION");
        register.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nom = txtNom.getText();
                String prenom = txtPrenom.getText();
                String telephone = txtTelephone.getText();
                String adresse = txtAdresse.getText();
                String email = txtEmail.getText();
                String password = txtPass.getText();
                String confirme = txtConfirme.getText();

                user = new Utilisateur(0, nom, prenom, telephone, adresse, email, password, "client", false);
            }
        });
    }

    private void initLogin(ActionListener eventLogin) {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("CONNEXION");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/login/icon/pass.png")));
        txtPass.setHint("Mot de passe");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Mot de passe oublié ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventLogin);
        cmd.setText("CONNEXION");
        login.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                dataLogin = new ModelLogin(email, password);
            }
        });
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
