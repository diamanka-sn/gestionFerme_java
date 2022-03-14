/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.model;

/**
 *
 * @author hp
 */
public class ModelTraite {
    private int idTraite;
    private int idUtilisateur;
    private int idBovin;
    private int traiteMatin;
    private int traiteSoir;
    private String dateTraite;
    private String nom;

    public ModelTraite(int idTraite, int idUtilisateur, int idBovin, int traiteMatin, int traiteSoir, String dateTraite) {
        this.idTraite = idTraite;
        this.idUtilisateur = idUtilisateur;
        this.idBovin = idBovin;
        this.traiteMatin = traiteMatin;
        this.traiteSoir = traiteSoir;
        this.dateTraite = dateTraite;
    }

    public ModelTraite(int idTraite, int idUtilisateur, int idBovin, int traiteMatin, int traiteSoir, String dateTraite, String nom) {
        this.idTraite = idTraite;
        this.idUtilisateur = idUtilisateur;
        this.idBovin = idBovin;
        this.traiteMatin = traiteMatin;
        this.traiteSoir = traiteSoir;
        this.dateTraite = dateTraite;
        this.nom = nom;
    }

    public ModelTraite(int traiteMatin, int traiteSoir, String nom) {
        this.traiteMatin = traiteMatin;
        this.traiteSoir = traiteSoir;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ModelTraite() {
    }

    public int getIdTraite() {
        return idTraite;
    }

    public void setIdTraite(int idTraite) {
        this.idTraite = idTraite;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdBovin() {
        return idBovin;
    }

    public void setIdBovin(int idBovin) {
        this.idBovin = idBovin;
    }

    public int getTraiteMatin() {
        return traiteMatin;
    }

    public void setTraiteMatin(int traiteMatin) {
        this.traiteMatin = traiteMatin;
    }

    public int getTraiteSoir() {
        return traiteSoir;
    }

    public void setTraiteSoir(int traiteSoir) {
        this.traiteSoir = traiteSoir;
    }

    public String getDateTraite() {
        return dateTraite;
    }

    public void setDateTraite(String dateTraite) {
        this.dateTraite = dateTraite;
    }
    
}
