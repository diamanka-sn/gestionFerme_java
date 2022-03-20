/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sn.ferme.connexionDb.DatabaseConnection;
import sn.ferme.model.ModelDiagnostic;

/**
 *
 * @author hp
 */
public class ServiceDiagnostic {

    private final Connection con;

    ArrayList<String> listeMaladie = new ArrayList<>();

    public ServiceDiagnostic() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public int insererMaladie(ModelDiagnostic maladie) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into maladie values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        p.setInt(1, 0);
        p.setString(2, maladie.getNomMaladie());

        int status = p.executeUpdate();
        return status;
    }

    public ArrayList<String> listeMaladie() throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from maladie", PreparedStatement.RETURN_GENERATED_KEYS);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            listeMaladie.add(rs.getString("nomMaladie"));
        }
        return listeMaladie;
    }

    public int recupererIdMaladie(String nom) throws SQLException {
        int code = 0;
        PreparedStatement p = con.prepareStatement("select idMaladie from maladie where nomMaladie = ? limit 1");
        p.setString(1, nom);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            code = r.getInt("idMaladie");
        }
        return code;
    }

    public int insererDiagnostic(ModelDiagnostic maladie) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into diagnostic values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        p.setInt(1, maladie.getIdMaladie());
        p.setInt(2, maladie.getIdBovin());
        p.setInt(3, 0);
        p.setString(4, maladie.getDateMaladie());
        p.setString(5, maladie.getDateGuerison());
        int status = p.executeUpdate();
        return status;
    }

    public void updateDateGuerison(int id, String date,String dateG) throws SQLException {
        PreparedStatement p = con.prepareStatement("UPDATE `diagnostic` SET dateGuerison=? WHERE idBovin=? and dateMaladie=? limit 1");
        p.setString(1, dateG);
        p.setInt(2, id);
        p.setString(3, date);

        p.execute();

    }
}
