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
import java.util.Random;
import sn.ferme.connexionDb.DatabaseConnection;
import sn.ferme.model.ModelBovin;

/**
 *
 * @author hp
 */
public class ServiceBovin {

    SimpleDateFormat dchoix = new SimpleDateFormat("dd/MM/yyyy");
    Date dactuelle = new Date();
    private final Connection con;
    ArrayList<ModelBovin> listeBovin = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> listeBovinPasEnVente = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> listeBovinEnVente = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> listeBovinVivant = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> detailsBovin = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> listeBovinAcheter = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> listeBovinVendu = new ArrayList<ModelBovin>();

    ArrayList<String> listeRace = new ArrayList<String>();
    ArrayList<String> listeGeniteur = new ArrayList<String>();
    ArrayList<String> listeGenitrice = new ArrayList<String>();
    ArrayList<String> listeBovinNonPese = new ArrayList<String>();

    public ServiceBovin() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public int insererBovin(ModelBovin bovin) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into bovin values (?,?,?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        int id = genererIdentifiantBovin();
        p.setInt(1, id);
        p.setInt(2, bovin.getIdRace());
        p.setString(3, bovin.getCodeBovin());
        p.setString(4, bovin.getNom());
        p.setString(5, bovin.getPhoto());
        p.setString(6, bovin.getDateNaissance());
        p.setString(7, bovin.getEtatSante());
        p.setString(8, bovin.getGeniteur());
        p.setString(9, bovin.getGenetrice());
        p.setString(10, bovin.getEtat());
        p.setString(11, bovin.getSituation());
        p.setInt(12, bovin.getPrix());
        p.setString(13, bovin.getDescription());
        int status = p.executeUpdate();

        return status;
    }

    public int insererRace(ModelRace race) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into race values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        int id = genererIdentifiant();
        p.setInt(1, id);
        p.setString(2, race.getNomRace());

        int status = p.executeUpdate();

        return status;
    }

    public ArrayList<ModelBovin> afficherBovin() {
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and bovin.etat=?";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, "Vivant");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ModelBovin bovin = new ModelBovin();
                bovin.setCodeBovin(rs.getString("codeBovin"));
                bovin.setNom(rs.getString("nom"));
                bovin.setDateNaissance(rs.getString("dateNaissance"));
                bovin.setEtatSante(rs.getString("etatSante"));
                bovin.setGeniteur(rs.getString("geniteur"));
                bovin.setGenetrice(rs.getString("genitrice"));
                bovin.setEtat(rs.getString("etat"));
                bovin.setSituation(rs.getString("situation"));
                bovin.setPrix(rs.getInt("prix"));
                bovin.setDescription(rs.getString("description"));
                bovin.setNomRace(rs.getString("nomRace"));
                listeBovin.add(bovin);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeBovin;
    }

    private int genererIdentifiant() throws SQLException {
        //    DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        int code = ran.nextInt(1000000);
        while (verifierDoublonIdRace(code)) {
            code = ran.nextInt(1000000);
        }
        return code;
    }

    private int genererIdentifiantBovin() throws SQLException {
        //    DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        int code = ran.nextInt(1000000);
        while (verifierDoublonIdBovin(code)) {
            code = ran.nextInt(1000000);
        }
        return code;
    }

    private boolean verifierDoublonIdRace(int code) throws SQLException {
        boolean doublon = false;
        PreparedStatement p = con.prepareStatement("select idRace from race where idRace=? limit 1");
        p.setInt(1, code);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            doublon = true;
        }
        r.close();
        p.close();
        return doublon;
    }

    private boolean verifierDoublonIdBovin(int code) throws SQLException {
        boolean doublon = false;
        PreparedStatement p = con.prepareStatement("select idBovin from bovin where idBovin=? limit 1");
        p.setInt(1, code);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            doublon = true;
        }
        r.close();
        p.close();
        return doublon;
    }

    public ArrayList<ModelBovin> afficherBovinNonEnLigne() {
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and etatSante!='Malade' and etat='Vivant' and situation='pas en vente'";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ModelBovin bovin = new ModelBovin();
                bovin.setCodeBovin(rs.getString("codeBovin"));
                bovin.setNom(rs.getString("nom"));
                bovin.setDateNaissance(rs.getString("dateNaissance"));
                bovin.setEtatSante(rs.getString("etatSante"));
                bovin.setGeniteur(rs.getString("geniteur"));
                bovin.setGenetrice(rs.getString("genitrice"));
                bovin.setEtat(rs.getString("etat"));
                bovin.setSituation(rs.getString("situation"));
                bovin.setPrix(rs.getInt("prix"));
                bovin.setDescription(rs.getString("description"));
                bovin.setNomRace(rs.getString("nomRace"));
                listeBovinPasEnVente.add(bovin);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeBovinPasEnVente;
    }

    public ArrayList<ModelBovin> afficherBovinEnLigne() {
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and etatSante!='Malade' and etat='Vivant' and situation='en vente'";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ModelBovin bovin = new ModelBovin();
                bovin.setCodeBovin(rs.getString("codeBovin"));
                bovin.setNom(rs.getString("nom"));
                bovin.setDateNaissance(rs.getString("dateNaissance"));
                bovin.setEtatSante(rs.getString("etatSante"));
                bovin.setGeniteur(rs.getString("geniteur"));
                bovin.setGenetrice(rs.getString("genitrice"));
                bovin.setEtat(rs.getString("etat"));
                bovin.setSituation(rs.getString("situation"));
                bovin.setPrix(rs.getInt("prix"));
                bovin.setDescription(rs.getString("description"));
                bovin.setNomRace(rs.getString("nomRace"));
                listeBovinEnVente.add(bovin);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeBovinEnVente;
    }

    public void enleverOuMettre(String id, String situation) throws SQLException {

        String sql = "UPDATE bovin set situation=? where codeBovin=? limit 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, situation);
        ps.setString(2, id);

        ps.execute();
        ps.close();
    }

    public ArrayList<String> listeRaceExistant() throws SQLException {
        PreparedStatement p = con.prepareStatement("select Distinct nomRace from race");
        ResultSet r = p.executeQuery();

        while (r.next()) {
            listeRace.add(r.getString("nomRace"));
        }
        return listeRace;
    }

    public int recupererCodeRace(String race) throws SQLException {
        int code = 0;
        PreparedStatement p = con.prepareStatement("select idRace from race where nomRace = ? limit 1");
        p.setString(1, race);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            code = r.getInt("idRace");
        }
        return code;
    }

    public int recupererCodeBovin(String nom) throws SQLException {
        int code = 0;
        PreparedStatement p = con.prepareStatement("select idBovin from bovin where nom = ? limit 1");
        p.setString(1, nom);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            code = r.getInt("idBovin");
        }
        return code;
    }

    public void updatecodeBovin(int id, String code) throws SQLException {
        PreparedStatement p = con.prepareStatement("UPDATE `bovin` SET codeBovin=? WHERE idBovin=? limit 1");
        p.setString(1, code);
        p.setInt(2, id);

        p.execute();

    }

    public void inserVache(ModelBovin vache) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into vache values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

        p.setInt(1, vache.getIdBovin());
        p.setString(2, vache.getPeriode());
        p.setString(3, vache.getPhase());
        p.executeUpdate();

    }

    public void inserAchatsBovin(ModelBovin achats) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into achatbovin values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

        p.setInt(1, achats.getIdBovin());
        p.setInt(2, achats.getIdUtilisateur());
        p.setInt(3, achats.getMontantAchat());
        p.setString(4, achats.getDateAchatBovin());

        p.executeUpdate();

    }

    public ArrayList<String> listeGeniteur(String des) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT nom FROM `bovin` WHERE etat=? and situation!=? and description=?");
        p.setString(1, "Vivant");
        p.setString(2, "vendu");
        p.setString(3, des);
        ResultSet r = p.executeQuery();
        if ("Taureau".equals(des)) {
            while (r.next()) {
                listeGeniteur.add(r.getString("nom"));
            }
            return listeGeniteur;
        } else {
            while (r.next()) {
                listeGenitrice.add(r.getString("nom"));
            }
            return listeGenitrice;
        }
    }

    public ArrayList<ModelBovin> ListeBovinVivant() {
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and etat='Vivant' and situation!='vendu'";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ModelBovin bovin = new ModelBovin();
                bovin.setCodeBovin(rs.getString("codeBovin"));
                bovin.setNom(rs.getString("nom"));
                bovin.setDateNaissance(rs.getString("dateNaissance"));
                bovin.setEtatSante(rs.getString("etatSante"));
                bovin.setGeniteur(rs.getString("geniteur"));
                bovin.setGenetrice(rs.getString("genitrice"));
                bovin.setEtat(rs.getString("etat"));
                bovin.setSituation(rs.getString("situation"));
                bovin.setPrix(rs.getInt("prix"));
                bovin.setDescription(rs.getString("description"));
                bovin.setNomRace(rs.getString("nomRace"));
                listeBovinVivant.add(bovin);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeBovinVivant;
    }

    public ArrayList<ModelBovin> DetailsBovin(String id) {
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and bovin.codeBovin=? limit 1";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ModelBovin bovin = new ModelBovin();
                bovin.setIdBovin(rs.getInt("idBovin"));
                bovin.setCodeBovin(rs.getString("codeBovin"));
                bovin.setNom(rs.getString("nom"));
                bovin.setDateNaissance(rs.getString("dateNaissance"));
                bovin.setEtatSante(rs.getString("etatSante"));
                bovin.setGeniteur(rs.getString("geniteur"));
                bovin.setGenetrice(rs.getString("genitrice"));
                bovin.setEtat(rs.getString("etat"));
                bovin.setSituation(rs.getString("situation"));
                bovin.setPrix(rs.getInt("prix"));
                bovin.setDescription(rs.getString("description"));
                bovin.setNomRace(rs.getString("nomRace"));
                detailsBovin.add(bovin);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return detailsBovin;
    }

    public ArrayList<String> listeBovinNonPese() throws SQLException {
        String dA = dchoix.format(dactuelle);
        PreparedStatement p = con.prepareStatement("SELECT nom from bovin where bovin.etat=? AND bovin.situation!=? AND bovin.nom not in(SELECT nom from bovin, pesage WHERE pesage.datePese=?  and bovin.etat=? and bovin.idBovin=pesage.idBovin and bovin.situation!=?)");
        p.setString(1, "Vivant");
        p.setString(2, "vendu");
        p.setString(3, dA);
        p.setString(4, "Vivant");
        p.setString(5, "Vendu");

        ResultSet r = p.executeQuery();

        while (r.next()) {
            listeBovinNonPese.add(r.getString("nom"));
        }
        return listeBovinNonPese;
    }

    public ArrayList<ModelBovin> bovinAcheter(int id) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT * from achatbovin where idBovin=? limit 1");
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();

        if (rs.first()) {
            ModelBovin bovin = new ModelBovin();
            bovin.setIdBovin(rs.getInt("idBovin"));
            bovin.setIdUtilisateur(rs.getInt("idUtilisateur"));
            bovin.setMontantAchat(rs.getInt("MontantBovin"));
            bovin.setDateAchatBovin(rs.getString("dateAchatBovin"));
            listeBovinAcheter.add(bovin);
        }
        return listeBovinAcheter;
    }
    
    public ArrayList<ModelBovin> listeBovinVendu() {
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and bovin.situation=?";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, "Vendu");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ModelBovin bovin = new ModelBovin();
                bovin.setIdBovin(rs.getInt("idBovin"));
                bovin.setCodeBovin(rs.getString("codeBovin"));
                bovin.setNom(rs.getString("nom"));
                bovin.setDateNaissance(rs.getString("dateNaissance"));
                bovin.setEtatSante(rs.getString("etatSante"));
                bovin.setGeniteur(rs.getString("geniteur"));
                bovin.setGenetrice(rs.getString("genitrice"));
                bovin.setEtat(rs.getString("etat"));
                bovin.setSituation(rs.getString("situation"));
                bovin.setPrix(rs.getInt("prix"));
                bovin.setDescription(rs.getString("description"));
                bovin.setNomRace(rs.getString("nomRace"));
                listeBovinVendu.add(bovin);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeBovinVendu;
    }
}
