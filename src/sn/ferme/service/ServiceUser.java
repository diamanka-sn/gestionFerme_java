package sn.ferme.service;

import sn.ferme.connexionDb.DatabaseConnection;
import sn.ferme.model.ModelLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import sn.ferme.model.Utilisateur;

public class ServiceUser {

    private final Connection con;
    ArrayList<Utilisateur> listeEmploye = new ArrayList<Utilisateur>();

    public ServiceUser() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public Utilisateur login(ModelLogin login) throws SQLException {
        Utilisateur data = null;
        PreparedStatement p = con.prepareStatement("select * from utilisateur where BINARY(Email)=? and BINARY(`Password`)=? limit 1");
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.first()) {
            int id = r.getInt(1);
            String nom = r.getString(2);
            String prenom = r.getString(3);
            String telephone = r.getString(4);
            String adresse = r.getString(5);
            String email = r.getString(6);
            String profile = r.getString(8);
            boolean isAdmin = r.getBoolean(9);
            data = new Utilisateur(id, nom, prenom, telephone, adresse, email, profile, isAdmin);
        }
        r.close();
        p.close();
        return data;
    }

    public void insertUser(Utilisateur user) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into utilisateur(idUtilisateur, nom, prenom, telephone, adresse, email, password, profile,isAdmin) values (?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        int id = genererIdentifiant();
        p.setInt(1, id);
        p.setString(2, user.getNom());
        p.setString(3, user.getPrenom());
        p.setString(4, user.getTelephone());
        p.setString(5, user.getAdresse());
        p.setString(6, user.getEmail());
        p.setString(7, user.getPassword());
        p.setString(8, user.getProfile());
        p.setBoolean(9, user.isIsAdmin());

        p.execute();
        ResultSet r = p.getGeneratedKeys();
        r.first();
        //  int userID = r.getInt(1);
        r.close();
        p.close();
    }

    private int genererIdentifiant() throws SQLException {
        //    DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        int code = ran.nextInt(1000000);
        while (verifierDoublonCode(code)) {
            code = ran.nextInt(1000000);
        }
        return code;
    }

    private boolean verifierDoublonCode(int code) throws SQLException {
        boolean doublon = false;
        PreparedStatement p = con.prepareStatement("select idUtilisateur from utilisateur where idUtilisateur=? limit 1");
        p.setInt(1, code);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            doublon = true;
        }
        r.close();
        p.close();
        return doublon;
    }

    public boolean checkDuplicateUser(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where UserName=? and `Status`='Verified' limit 1");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean VerifierDuplicationEmail(String email) throws SQLException {
        boolean doublon = false;
        PreparedStatement p = con.prepareStatement("select idUtilisateur from utilisateur where email=? limit 1");
        p.setString(1, email);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            doublon = true;
        }
        r.close();
        p.close();
        return doublon;
    }

    public void doneVerify(int userID) throws SQLException {
        PreparedStatement p = con.prepareStatement("update `user` set VerifyCode='', `Status`='Verified' where UserID=? limit 1");
        p.setInt(1, userID);
        p.execute();
        p.close();
    }

    public boolean verifyCodeWithUser(int userID, String code) throws SQLException {
        boolean verify = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where UserID=? and VerifyCode=? limit 1");
        p.setInt(1, userID);
        p.setString(2, code);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            verify = true;
        }
        r.close();
        p.close();
        return verify;
    }

    public int insererEmploye(String nom, String prenom, String telephone, String adresse, String email, String password, String profile, boolean isAdmin) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into utilisateur(idUtilisateur, nom, prenom, telephone, adresse, email, password, profile,isAdmin) values (?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        int id = genererIdentifiant();
        p.setInt(1, id);
        p.setString(2, nom);
        p.setString(3, prenom);
        p.setString(4, telephone);
        p.setString(5, adresse);
        p.setString(6, email);
        p.setString(7, password);
        p.setString(8, profile);
        p.setBoolean(9, isAdmin);
        int status = p.executeUpdate();

        return status;
    }

    public int ligne() throws SQLException {
        int nb = 0;
        PreparedStatement p = con.prepareStatement("Select * from Utilisateur");
        ResultSet r = p.executeQuery();
        if (r.last()) {
            nb = r.getRow();
        }

        return nb;
    }

    public ArrayList<Utilisateur> afficher() {
        String select = "SELECT * FROM utilisateur";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur employe = new Utilisateur();
                employe.setIdUtilisateur(rs.getInt("idUtilisateur"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setEmail(rs.getString("email"));
                employe.setTelephone(rs.getString("telephone"));
                employe.setAdresse(rs.getString("adresse"));
                employe.setProfile(rs.getString("profile"));
                employe.setIsAdmin(rs.getBoolean("isAdmin"));
                listeEmploye.add(employe);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeEmploye;
    }
}
