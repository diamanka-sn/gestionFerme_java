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
    private String nomType;
    private int idType;

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
}
