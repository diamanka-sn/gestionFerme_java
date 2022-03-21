/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sn.ferme.connexionDb.DatabaseConnection;
import sn.ferme.model.ModelTraite;

/**
 *
 * @author hp
 */
public class ServiceTraite {

    private final Connection con;
    SimpleDateFormat dchoix = new SimpleDateFormat("yyyy-MM-dd");
    Date dactuelle = new Date();

    ArrayList<String> listeVache = new ArrayList<String>();
    HashMap<String, ModelTraite> listeProdVache = new HashMap<>();

    public ServiceTraite() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void insererTraite(ModelTraite traite) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into traitedujour values (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        int id = genererIdentifiant();
        p.setInt(1, id);
        p.setInt(2, traite.getIdUtilisateur());
        p.setInt(3, traite.getIdBovin());
        p.setInt(4, traite.getTraiteMatin());
        p.setInt(5, traite.getTraiteSoir());
        p.setString(6, traite.getDateTraite());
        p.execute();
    }

    public ArrayList<String> listeVacheNonTraite() throws SQLException {
        String dA = dchoix.format(dactuelle);
        PreparedStatement p = con.prepareStatement("SELECT nom FROM bovin,vache where bovin.idBovin=vache.idBovin and vache.periode=? and bovin.situation!=? and bovin.etat=? and bovin.nom not in (SELECT nom FROM `traitedujour`,bovin,vache WHERE bovin.idBovin=vache.idBovin and dateTraite=? and situation!=? and vache.idBovin=traitedujour.idBovin)");
        p.setString(1, "lactation");
        p.setString(2, "Vendu");
        p.setString(3, "Vivant");

        p.setString(4, dA);
        p.setString(5, "Vendu");

        ResultSet r = p.executeQuery();

        while (r.next()) {
            listeVache.add(r.getString("nom"));
        }
        return listeVache;
    }

    public int recupererIdBovin(String nom) throws SQLException {
        int code = 0;
        String sql = "Select idBovin from bovin where nom=? limit 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nom);

        ResultSet r = ps.executeQuery();
        if (r.first()) {
            code = r.getInt("idBovin");
        }
        return code;
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
        PreparedStatement p = con.prepareStatement("select idtraite from traitedujour where idtraite=? limit 1");
        p.setInt(1, code);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            doublon = true;
        }
        r.close();
        p.close();
        return doublon;
    }

    public HashMap<String, ModelTraite> afficherPodParVache() {
        try {
            String sql = "SELECT SUM(traitedujour.traiteMatin) AS jour,SUM(traitedujour.traiteSoir) as soir, bovin.nom AS nom FROM `vache`,bovin,traitedujour WHERE bovin.idBovin=vache.idBovin AND vache.idBovin=traitedujour.idBovin GROUP BY nom";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                ModelTraite traite = new ModelTraite();
                traite.setNom(rs.getString("nom"));
                traite.setTraiteMatin(rs.getInt("jour"));
                traite.setTraiteSoir(rs.getInt("soir"));
                listeProdVache.put(traite.getNom(), traite);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTraite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeProdVache;
    }

    public int recupererProdictionTotal() throws SQLException {
        int code = 0;
        String sql = "SELECT SUM(traiteMatin) + SUM(traiteSoir) as production FROM `traitedujour`";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet r = ps.executeQuery();
        if (r.first()) {
            code = r.getInt("production");
        }
        return code;
    }

    public int recupererVenduLait() throws SQLException {
        int code = 0;
        String sql = "SELECT Sum(capacite) as vendu FROM `ventelait`";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet r = ps.executeQuery();
        if (r.first()) {
            code = r.getInt("vendu");
        }
        return code;
    }
    
}
