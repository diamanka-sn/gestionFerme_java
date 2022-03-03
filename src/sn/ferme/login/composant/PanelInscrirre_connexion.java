package sn.ferme.login.composant;

import sn.ferme.model.ModelUtilisateur;
import sn.ferme.login.swing.Button;
import sn.ferme.login.swing.MyPasswordField;
import sn.ferme.login.swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelInscrirre_connexion extends javax.swing.JLayeredPane {

    public ModelUtilisateur getUser() {
        return user;
    }

    private ModelUtilisateur user;

    public PanelInscrirre_connexion(ActionListener eventRegister) {
        initComponents();
        initRegister(eventRegister);
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister(ActionListener eventRegister) {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("CREER UN COMPTE");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);
        MyTextField txtNom = new MyTextField();
        txtNom.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/icon/user_1.png")));
        txtNom.setHint("Nom");
        register.add(txtNom, "w 60%");
        MyTextField txtPrenom = new MyTextField();
        txtPrenom.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/icon/user_1.png")));
        txtPrenom.setHint("Prenom");
        register.add(txtPrenom, "w 60%");
        MyTextField txtTelephone = new MyTextField();
        txtTelephone.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/icon/user_1.png")));
        txtTelephone.setHint("Numero téléphone");
        register.add(txtTelephone, "w 60%");
         MyTextField txtAdresse = new MyTextField();
        txtAdresse.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/icon/user_1.png")));
        txtAdresse.setHint("Adresse");
        register.add(txtAdresse, "w 60%");
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/icon/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/icon/pass.png")));
        txtPass.setHint("Mot de passe");
        register.add(txtPass, "w 60%");
        MyPasswordField txtConfirme = new MyPasswordField();
        txtConfirme.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/icon/pass.png")));
        txtConfirme.setHint("Confirmer mot de passe");
        register.add(txtConfirme, "w 60%");

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventRegister);
        cmd.setText("S'INSCRIRE");
        register.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String userName = txtNom.getText().trim();
                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                user = new ModelUtilisateur(0, userName, email, password);
            }
        });
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("AUTHENTIFICATION");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/sn/ferme/icon/pass.png")));
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
        cmd.setText("SE CONNECTER");
        login.add(cmd, "w 40%, h 40");
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
            .addGap(0, 400, Short.MAX_VALUE)
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
