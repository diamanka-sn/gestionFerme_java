/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.model;

import java.util.Date;

/**
 *
 * @author hp
 */
public class ModelDepense {

    private int idDepense;
    private String dateDepense;
    private String libelle;
    private int montant;
    private int quantite;
    private String nomType;
    private int idType;

    private String aliment;
    private int stock;
    private int consomes;

    private int idAlimentation;
    private int idUtilisateur;
    private String nomAliment;
    private String date;

    public ModelDepense(String aliment, int stock, int consomes) {
        this.aliment = aliment;
        this.stock = stock;
        this.consomes = consomes;
    }

    public ModelDepense( int idAlimentation, int idUtilisateur, String nomAliment,int quantite, String date) {
        this.quantite = quantite;
        this.idAlimentation = idAlimentation;
        this.idUtilisateur = idUtilisateur;
        this.nomAliment = nomAliment;
        this.date = date;
    }

    public int getIdAlimentation() {
        return idAlimentation;
    }

    public void setIdAlimentation(int idAlimentation) {
        this.idAlimentation = idAlimentation;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomAliment() {
        return nomAliment;
    }

    public void setNomAliment(String nomAliment) {
        this.nomAliment = nomAliment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAliment() {
        return aliment;
    }

    public void setAliment(String aliment) {
        this.aliment = aliment;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getConsomes() {
        return consomes;
    }

    public void setConsomes(int consomes) {
        this.consomes = consomes;
    }

    public ModelDepense(int idDepense, String dateDepense, String libelle, int montant, String nomType) {
        this.idDepense = idDepense;
        this.dateDepense = dateDepense;
        this.libelle = libelle;
        this.montant = montant;
        this.nomType = nomType;
    }

    public ModelDepense(int idDepense, String dateDepense, String libelle, int montant) {
        this.idDepense = idDepense;
        this.dateDepense = dateDepense;
        this.libelle = libelle;
        this.montant = montant;
    }

    public ModelDepense(int idType, String nomType) {
        this.idType = idType;
        this.nomType = nomType;
    }

    public ModelDepense(int idDepense, int idType, String dateDepense, String libelle, int montant) {
        this.idDepense = idDepense;
        this.dateDepense = dateDepense;
        this.libelle = libelle;
        this.montant = montant;
        this.idType = idType;
        //   this.quantite = quantite;
    }

    public ModelDepense(int idDepense, int idType, String dateDepense, String libelle, int montant, int quantite) {
        this.idDepense = idDepense;
        this.dateDepense = dateDepense;
        this.libelle = libelle;
        this.montant = montant;
        this.quantite = quantite;
        this.idType = idType;
    }

    public ModelDepense(String nomType, int idType) {
        this.nomType = nomType;
        this.idType = idType;
    }

    public ModelDepense() {
    }

    public int getIdDepense() {
        return idDepense;
    }

    public void setIdDepense(int idDepense) {
        this.idDepense = idDepense;
    }

    public String getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(String dateDepense) {
        this.dateDepense = dateDepense;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
