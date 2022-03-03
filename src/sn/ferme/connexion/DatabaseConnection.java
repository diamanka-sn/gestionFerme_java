/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.connexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hp
 */
public class DatabaseConnection {
    static Connection connection;
    
    public static Connection conDb(){
        String server = "localhost";
        String db = "ferme";
        String username = "root";
        String password = "";
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql//"+server+"/"+db, username,password);
            return connection;
        } catch(Exception e){
            System.out.println("erreur de connexion à la base de données"+e.getMessage());
            return null;
        }
    }
    
}
