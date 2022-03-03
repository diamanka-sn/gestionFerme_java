/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sn.ferme.connexion.DatabaseConnection;
import sn.ferme.model.Utilisateur;

/**
 *
 * @author hp
 */
public class ServiceUtilisateur {

    private final Connection con;
    PreparedStatement ps;

    public ServiceUtilisateur() {
        con = DatabaseConnection.conDb();
    }

    public int insererUtilisateur(Utilisateur utilisateur) throws SQLException {
        String insertion = "insert into utilisateur values (?,?,?,?,?,?,?,?,?,?)";
        ps = con.prepareStatement(insertion);

        ps.setInt(1, 0);
        ps.setString(2, utilisateur.getNom());
        ps.setString(3, utilisateur.getPrenom());
        ps.setString(4, utilisateur.getTelephone());
        ps.setString(5, utilisateur.getAdresse());
        ps.setString(6, utilisateur.getPhoto());
        ps.setString(7, utilisateur.getEmail());
        ps.setString(8, utilisateur.getPassword());
        ps.setString(9, utilisateur.getProfile());
        ps.setBoolean(10, utilisateur.isIsAdmin());

        int status = ps.executeUpdate();

        ps.close();
        return status;

    }

    public Utilisateur login(Utilisateur login) throws SQLException {
        Utilisateur data = null;
        PreparedStatement p = con.prepareStatement("select * from utilisateur where email=? and password=? limit 1");
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.first()) {
            int id = r.getInt(1);
            String nom = r.getString(2);
            String prenom = r.getString(3);
            String telephone = r.getString(4);
            String adresse = r.getString(5);
            String photo = r.getString(6);
            String email = r.getString(7);
         
        //    String data = new Utilisateur(id, nom, prenom, "");
        }
        r.close();
        p.close();
        return data;
    }
}
