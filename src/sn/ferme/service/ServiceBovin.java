/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.service;

import java.sql.Connection;
import static java.sql.JDBCType.NULL;
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
    ArrayList<ModelBovin> listepesage = new ArrayList<ModelBovin>();

    // ArrayList<ModelBovin> listeB = new ArrayList<>();
    HashMap<String, ModelBovin> etatFerme = new HashMap<>();
    HashMap<Integer, ModelBovin> listePoidsParbovin = new HashMap<>();

    HashMap<String, ModelBovin> etatSanteFerme = new HashMap<>();

    ArrayList<ModelBovin> listeBovinLactation = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> listeBovinTarissement = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> listeBovinMalade = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> listeBovinPese = new ArrayList<ModelBovin>();
    ArrayList<ModelBovin> listeBovinCommander = new ArrayList<ModelBovin>();

    ArrayList<String> listeRace = new ArrayList<String>();
    ArrayList<String> listeGeniteur = new ArrayList<String>();
    ArrayList<String> listeGenitrice = new ArrayList<String>();
    ArrayList<String> listeBovinNonPese = new ArrayList<String>();
    ArrayList<String> listeBovinSain = new ArrayList<String>();

    // HashMap<String, ModelBovin> listeBovinLactation = new HashMap<>();
    //HashMap<String, ModelBovin> listeBovinTarissement = new HashMap<>();
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
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and etat='Vivant' and situation='pas en vente'";

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
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and etatSante!='Malade' and etat='Vivant' and situation='En vente'";

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

    public void updateEtatBovin(int id, String code) throws SQLException {
        PreparedStatement p = con.prepareStatement("UPDATE `bovin` SET etatSante =? WHERE idBovin=? limit 1");
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
        int mois = dactuelle.getMonth() + 1;

        PreparedStatement p = con.prepareStatement("SELECT nom from bovin where bovin.etat=? AND bovin.situation!=? AND bovin.nom not in(SELECT nom from bovin, pesage WHERE Month(pesage.datePese)=?  and bovin.etat=? and bovin.idBovin=pesage.idBovin and bovin.situation!=?)");
        p.setString(1, "Vivant");
        p.setString(2, "vendu");
        p.setInt(3, mois);
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

    public ArrayList<ModelBovin> listeBovinLactation() {
        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM `bovin`,race,vache WHERE bovin.idRace=race.idRace and vache.idBovin=bovin.idBovin and etat=? and situation!=? and periode=?");
            p.setString(1, "Vivant");
            p.setString(2, "vendu");
            p.setString(3, "lactation");
            ResultSet r = p.executeQuery();

            while (r.next()) {
                ModelBovin bovin = new ModelBovin();
                bovin.setCodeBovin(r.getString("codeBovin"));
                bovin.setNom(r.getString("nom"));
                bovin.setNomRace(r.getString("nomRace"));
                bovin.setPhase(r.getString("phase"));
                bovin.setPeriode(r.getString("periode"));
                listeBovinLactation.add(bovin);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBovin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeBovinLactation;
    }

    public ArrayList<ModelBovin> listeBovinTarissement() {
        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM `bovin`,race,vache WHERE bovin.idRace=race.idRace and vache.idBovin=bovin.idBovin and etat=? and situation!=? and periode=?");
            p.setString(1, "Vivant");
            p.setString(2, "vendu");
            p.setString(3, "tarissement");
            ResultSet r = p.executeQuery();

            while (r.next()) {
                ModelBovin bovin = new ModelBovin();
                bovin.setCodeBovin(r.getString("codeBovin"));
                bovin.setNom(r.getString("nom"));
                bovin.setNomRace(r.getString("nomRace"));
                bovin.setPhase(r.getString("phase"));
                bovin.setPeriode(r.getString("periode"));
                listeBovinTarissement.add(bovin);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBovin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeBovinTarissement;

    }

    public ArrayList<String> listeBovinSain() throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT DISTINCT nom FROM `bovin` WHERE etat=? and situation!=? and etatSante!=?");
        p.setString(1, "Vivant");
        p.setString(2, "vendu");
        p.setString(3, "Malade");
        ResultSet r = p.executeQuery();

        while (r.next()) {
            listeBovinSain.add(r.getString("nom"));
        }
        return listeBovinSain;
    }

    public ArrayList<ModelBovin> listeBovinMalade() {
        String select = "select DISTINCT codeBovin,nom,nomMaladie,dateMaladie,nomRace from bovin, race, diagnostic,maladie where race.idRace=bovin.idRace and bovin.idBovin=diagnostic.idBovin and maladie.idMaladie=diagnostic.idMaladie and bovin.situation!=? and bovin.etat=? and etatSante=? and dateGuerison=?";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, "Vendu");
            ps.setString(2, "Vivant");
            ps.setString(3, "Malade");
            ps.setString(4, "non");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ModelBovin bovin = new ModelBovin();

                //  bovin.setIdBovin(rs.getInt("idBovin"));
                bovin.setCodeBovin(rs.getString("codeBovin"));
                bovin.setNom(rs.getString("nom"));
                bovin.setMaladie(rs.getString("nomMaladie"));
                bovin.setDateMaladie(rs.getString("dateMaladie"));
                bovin.setNomRace(rs.getString("nomRace"));
                listeBovinMalade.add(bovin);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeBovinMalade;
    }

    public HashMap<String, ModelBovin> etatFerme() throws SQLException {
        String select = "Select description, count(idBovin) as nombre FROM bovin WHERE etat=? GROUP BY description";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, "Vivant");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ModelBovin depense = new ModelBovin();

                depense.setDescription(rs.getString("description"));
                depense.setNombre(rs.getInt("nombre"));

                etatFerme.put(depense.getDescription(), depense);

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return etatFerme;
    }

    public HashMap<String, ModelBovin> etatSanteFerme() throws SQLException {
        String select = "Select etatSante, count(idBovin) as nombre FROM bovin WHERE etat=? and situation!=? GROUP BY etatSante";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, "Vivant");
            ps.setString(2, "vendu");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ModelBovin depense = new ModelBovin();

                depense.setEtat(rs.getString("etatSante"));
                depense.setNombre(rs.getInt("nombre"));

                etatSanteFerme.put(depense.getEtat(), depense);

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return etatSanteFerme;
    }

    public int insererPesage(ModelBovin bovin) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into pesage values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

        p.setInt(1, 0);
        p.setInt(2, bovin.getIdBovin());
        p.setString(3, bovin.getDatePese());
        p.setDouble(4, bovin.getPoids());

        int status = p.executeUpdate();

        return status;
    }

    public ArrayList<ModelBovin> listeBovinEnVente() {
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and bovin.situation=? and bovin.etat=?";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, "En vente");
            ps.setString(2, "Vivant");

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

    public int insererCommande(ModelBovin bovin) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into commande values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ;
        p.setInt(1, 0);
        p.setInt(2, bovin.getIdUtilisateur());
        p.setString(3, bovin.getDateCom());
        p.executeUpdate();
        ResultSet rs = p.getGeneratedKeys();
        rs.next();
        int status = rs.getInt(1);
        return status;
    }

    public int insererVenteBovin(ModelBovin bovin) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into ventebovin values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

        p.setInt(1, bovin.getIdCom());
        p.setInt(2, bovin.getIdBovin());
        p.setInt(3, bovin.getPrix());
        int status = p.executeUpdate();

        return status;
    }

    public int insererVenteLait(ModelBovin bovin) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into ventelait values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

        p.setInt(1, bovin.getIdCom());
        p.setInt(2, bovin.getCapacite());
        p.setBoolean(3, false);
        int status = p.executeUpdate();

        return status;
    }

    public void updateSituationtBovin(int id, String code) throws SQLException {
        PreparedStatement p = con.prepareStatement("UPDATE `bovin` SET situation =? WHERE idBovin=? limit 1");
        p.setString(1, code);
        p.setInt(2, id);

        p.execute();
    }

    public ArrayList<ModelBovin> afficherBovinCommander() {
        String select = "SELECT * FROM `bovin`, `race` WHERE `race`.`idRace`=bovin.idRace and etat='Vivant' and situation='commander'";

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
                listeBovinCommander.add(bovin);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listeBovinCommander;
    }

    public void updatePeriode(int code, String phase) throws SQLException {
        PreparedStatement p = con.prepareStatement("UPDATE `vache` SET periode =? WHERE idBovin=? limit 1");
        p.setString(1, phase);
        p.setInt(2, code);

        p.execute();
    }

    public HashMap<Integer, ModelBovin> pesageParBovin(int id) {
        String select = "SELECT Month(datePese) as mois, poids FROM pesage, bovin WHERE bovin.idBovin = pesage.idBovin and bovin.idBovin=?";

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ModelBovin bovin = new ModelBovin();
                bovin.setMois(rs.getInt("mois"));
                bovin.setPoids(rs.getDouble("poids"));

                listePoidsParbovin.put(bovin.getMois(), bovin);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return listePoidsParbovin;
    }
}
