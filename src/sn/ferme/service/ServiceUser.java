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
import java.util.logging.Level;
import java.util.logging.Logger;
import sn.ferme.model.Utilisateur;

public class ServiceUser {

    private final Connection con;
    ArrayList<Utilisateur> listeEmploye = new ArrayList<Utilisateur>();
    ArrayList<Utilisateur> listeClient = new ArrayList<Utilisateur>();
    ArrayList<Utilisateur> listeCommandeNONValider = new ArrayList<Utilisateur>();
    ArrayList<Utilisateur> listeCommandeNONValiderU = new ArrayList<Utilisateur>();
    ArrayList<Utilisateur> listeCommandeValiderU = new ArrayList<Utilisateur>();

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

    public int insertUser(Utilisateur user) throws SQLException {
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

        int r = p.executeUpdate();
        return r;
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

    public int insererFermier(Utilisateur user) {
        int status = 0;
        try {
            PreparedStatement p = con.prepareStatement("insert into fermier(idUtilisateur, salaire) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            p.setInt(1, user.getIdUtilisateur());
            p.setInt(2, user.getSalire());
            status = p.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public int recupereridUser(String email) throws SQLException {
        int nb = 0;
        PreparedStatement p = con.prepareStatement("Select idUtilisateur from utilisateur where email=? limit 1");
        p.setString(1, email);
        ResultSet r = p.executeQuery();
        if (r.last()) {
            nb = r.getInt("idUtilisateur");
        }

        return nb;
    }

    public ArrayList<Utilisateur> afficherEmploye() {
        String select = "SELECT * FROM utilisateur where profile='fermier'";

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

    public ArrayList<Utilisateur> afficherClient() {
        String select = "SELECT * FROM utilisateur where profile='client'";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur client = new Utilisateur();
                client.setIdUtilisateur(rs.getInt("idUtilisateur"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEmail(rs.getString("email"));
                client.setTelephone(rs.getString("telephone"));
                client.setAdresse(rs.getString("adresse"));
                listeClient.add(client);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeClient;
    }

    public void updatePassword(int id, String newPassword, String Ancien) throws SQLException {
        String sql = "UPDATE `utilisateur` SET password=? WHERE idUtilisateur=? and password=? limit 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, newPassword);
        ps.setInt(2, id);
        ps.setString(3, Ancien);

        ps.execute();
    }

    public void updateUser(int id, String nom, String prenom, String email, String telephone, String adresse) throws SQLException {
        String sql = "UPDATE `utilisateur` SET nom=?,prenom=?,email=?,telephone=?,adresse=? WHERE idUtilisateur=? limit 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, email);
        ps.setString(4, telephone);
        ps.setString(5, adresse);
        ps.setInt(6, id);

        ps.execute();
    }

    public ArrayList<Utilisateur> afficherCommandeNONValiderLAit() {
        String select = "SELECT * FROM `ventelait`,commande,utilisateur WHERE utilisateur.idUtilisateur=commande.idUtilisateur and commande.idCom=ventelait.idCom AND utilisateur.profile='client' and ventelait.etat=false";

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
                employe.setCapacite(rs.getInt("capacite"));
                employe.setDateCom(rs.getString("dateCom"));
                employe.setIdCom(rs.getInt("idCom"));
                listeCommandeNONValider.add(employe);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeCommandeNONValider;
    }

    public ArrayList<Utilisateur> afficherCommandeNONValiderLAitU(int id) {
        String select = "SELECT * FROM `ventelait`,commande,utilisateur WHERE utilisateur.idUtilisateur=commande.idUtilisateur and commande.idCom=ventelait.idCom AND utilisateur.profile='client' and ventelait.etat=false and utilisateur.idUtilisateur=?";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
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
                employe.setCapacite(rs.getInt("capacite"));
                employe.setDateCom(rs.getString("dateCom"));
                employe.setIdCom(rs.getInt("idCom"));
                listeCommandeNONValiderU.add(employe);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeCommandeNONValiderU;
    }

    public ArrayList<Utilisateur> afficherCommandeValiderLAitU(int id) {
        String select = "SELECT * FROM `ventelait`,commande,utilisateur WHERE utilisateur.idUtilisateur=commande.idUtilisateur and commande.idCom=ventelait.idCom AND utilisateur.profile='client' and ventelait.etat=true and utilisateur.idUtilisateur=?";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
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
                employe.setCapacite(rs.getInt("capacite"));
                employe.setDateCom(rs.getString("dateCom"));
                employe.setIdCom(rs.getInt("idCom"));
                listeCommandeValiderU.add(employe);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeCommandeValiderU;
    }

    public void UpdateCommandeLAit(int idCom) throws SQLException {
        String sql = "UPDATE `ventelait` SET etat=? WHERE idCom=?  limit 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setInt(2, idCom);

        ps.execute();
    }

    public int masseSalariale() throws SQLException {
        int nb = 0;
        PreparedStatement p = con.prepareStatement("SELECT sum(salaire) as montant FROM `fermier`");
        ResultSet r = p.executeQuery();
        if (r.first()) {
            nb = r.getInt("montant");
        }

        return nb;
    }
}
