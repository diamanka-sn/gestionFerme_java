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
public class ModelBovin {

    private int idBovin;
    private int idRace;
    private String nomRace;
    private String nom;
    private String codeBovin;
    private String photo;
    private String dateNaissance;
    private String etatSante;
    private String geniteur;
    private String genetrice;
    private String etat;
    private String situation;
    private int prix;
    private String description;

    private int idAchatBovin;
    private int idUtilisateur;
    private int montantAchat;
    private String dateAchatBovin;

    private String phase;
    private String periode;

    private int idPesage;
    private String datePese;
    private double poids;

    private String maladie;
    private String dateMaladie;
    
    private int nombre;
    private int mois;
    private int idCom;
    private String dateCom;

    public ModelBovin(int mois,double poids) {
        this.poids = poids;
        this.mois = mois;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    private int capacite;

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public ModelBovin(int idCom, int capacite) {
        this.idCom = idCom;
        this.capacite = capacite;
    }
    
    public int getIdCom() {
        return idCom;
    }

    public void setIdCom(int idCom) {
        this.idCom = idCom;
    }

    public String getDateCom() {
        return dateCom;
    }

    public ModelBovin( int idCom,int idUtilisateur, String dateCom) {
        this.idUtilisateur = idUtilisateur;
        this.idCom = idCom;
        this.dateCom = dateCom;
    }

    public ModelBovin( int idCom,int idBovin, int prix) {
        this.idBovin = idBovin;
        this.prix = prix;
        this.idCom = idCom;
    }

    
    public void setDateCom(String dateCom) {
        this.dateCom = dateCom;
    }
    
    
    
    public ModelBovin(String description, int nombre) {
        this.description = description;
        this.nombre = nombre;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    
    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public String getDateMaladie() {
        return dateMaladie;
    }

    public void setDateMaladie(String dateMaladie) {
        this.dateMaladie = dateMaladie;
    }

    public ModelBovin(String codeBovin, String nom, String nomRace, String maladie, String dateMaladie) {
        this.nomRace = nomRace;
        this.nom = nom;
        this.codeBovin = codeBovin;
        this.maladie = maladie;
        this.dateMaladie = dateMaladie;
    }

    public ModelBovin(int idBovin, int idRace, String codeBovin, String nom, String photo, String dateNaissance, String etatSante, String geniteur, String genetrice, String etat, String situation, int prix, String description) {
        this.idBovin = idBovin;
        this.idRace = idRace;
        this.nom = nom;
        this.codeBovin = codeBovin;
        this.photo = photo;
        this.dateNaissance = dateNaissance;
        this.etatSante = etatSante;
        this.geniteur = geniteur;
        this.genetrice = genetrice;
        this.etat = etat;
        this.situation = situation;
        this.prix = prix;
        this.description = description;
    }

    public ModelBovin(int idBovin, int idRace, String nomRace, String nom, String codeBovin, String photo, String dateNaissance, String etatSante, String geniteur, String genetrice, String etat, String situation, int prix, String description, int idAchatBovin, int idUtilisateur, int montantAchat, String dateAchatBovin) {
        this.idBovin = idBovin;
        this.idRace = idRace;
        this.nomRace = nomRace;
        this.nom = nom;
        this.codeBovin = codeBovin;
        this.photo = photo;
        this.dateNaissance = dateNaissance;
        this.etatSante = etatSante;
        this.geniteur = geniteur;
        this.genetrice = genetrice;
        this.etat = etat;
        this.situation = situation;
        this.prix = prix;
        this.description = description;
        this.idAchatBovin = idAchatBovin;
        this.idUtilisateur = idUtilisateur;
        this.montantAchat = montantAchat;
        this.dateAchatBovin = dateAchatBovin;
    }

    public ModelBovin(int idBovin, int idUtilisateur, int montantAchat, String dateAchatBovin) {
        // this.idAchatBovin = idAchatBovin;
        this.idBovin = idBovin;
        this.idUtilisateur = idUtilisateur;
        this.montantAchat = montantAchat;
        this.dateAchatBovin = dateAchatBovin;
    }

    public ModelBovin(int idBovin, int idRace, String nomRace, String nom, String codeBovin, String photo, String dateNaissance, String etatSante, String geniteur, String genetrice, String etat, String situation, int prix, String description) {
        this.idBovin = idBovin;
        this.idRace = idRace;
        this.nomRace = nomRace;
        this.nom = nom;
        this.codeBovin = codeBovin;
        this.photo = photo;
        this.dateNaissance = dateNaissance;
        this.etatSante = etatSante;
        this.geniteur = geniteur;
        this.genetrice = genetrice;
        this.etat = etat;
        this.situation = situation;
        this.prix = prix;
        this.description = description;
    }

    public ModelBovin() {
    }

    public ModelBovin(int idBovin, int idRace, String nomRace, String nom, String codeBovin, String photo, String dateNaissance, String etatSante, String geniteur, String genetrice, String etat, String situation, int prix, String description, int idAchatBovin, int idUtilisateur, int montantAchat, String dateAchatBovin, String phase, String periode) {
        this.idBovin = idBovin;
        this.idRace = idRace;
        this.nomRace = nomRace;
        this.nom = nom;
        this.codeBovin = codeBovin;
        this.photo = photo;
        this.dateNaissance = dateNaissance;
        this.etatSante = etatSante;
        this.geniteur = geniteur;
        this.genetrice = genetrice;
        this.etat = etat;
        this.situation = situation;
        this.prix = prix;
        this.description = description;
        this.idAchatBovin = idAchatBovin;
        this.idUtilisateur = idUtilisateur;
        this.montantAchat = montantAchat;
        this.dateAchatBovin = dateAchatBovin;
        this.phase = phase;
        this.periode = periode;
    }

    public ModelBovin(int idBovin, int idRace, String nomRace, String nom, String codeBovin, String photo, String dateNaissance, String etatSante, String geniteur, String genetrice, String etat, String situation, int prix, String description, int idAchatBovin, int idUtilisateur, int montantAchat, String dateAchatBovin, String phase, String periode, int idPesage, String datePese, double poids) {
        this.idBovin = idBovin;
        this.idRace = idRace;
        this.nomRace = nomRace;
        this.nom = nom;
        this.codeBovin = codeBovin;
        this.photo = photo;
        this.dateNaissance = dateNaissance;
        this.etatSante = etatSante;
        this.geniteur = geniteur;
        this.genetrice = genetrice;
        this.etat = etat;
        this.situation = situation;
        this.prix = prix;
        this.description = description;
        this.idAchatBovin = idAchatBovin;
        this.idUtilisateur = idUtilisateur;
        this.montantAchat = montantAchat;
        this.dateAchatBovin = dateAchatBovin;
        this.phase = phase;
        this.periode = periode;
        this.idPesage = idPesage;
        this.datePese = datePese;
        this.poids = poids;
    }

    public ModelBovin(int idPesage, int idBovin, String datePese, double poids) {
        this.idBovin = idBovin;
        this.idPesage = idPesage;
        this.datePese = datePese;
        this.poids = poids;
    }

    public int getIdPesage() {
        return idPesage;
    }

    public void setIdPesage(int idPesage) {
        this.idPesage = idPesage;
    }

    public String getDatePese() {
        return datePese;
    }

    public void setDatePese(String datePese) {
        this.datePese = datePese;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public ModelBovin(int idBovin, String periode, String phase) {
        this.idBovin = idBovin;
        this.phase = phase;
        this.periode = periode;
    }

    public int getIdAchatBovin() {
        return idAchatBovin;
    }

    public void setIdAchatBovin(int idAchatBovin) {
        this.idAchatBovin = idAchatBovin;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getMontantAchat() {
        return montantAchat;
    }

    public void setMontantAchat(int montantAchat) {
        this.montantAchat = montantAchat;
    }

    public String getDateAchatBovin() {
        return dateAchatBovin;
    }

    public void setDateAchatBovin(String dateAchatBovin) {
        this.dateAchatBovin = dateAchatBovin;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getNomRace() {
        return nomRace;
    }

    public void setNomRace(String nomRace) {
        this.nomRace = nomRace;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public int getIdBovin() {
        return idBovin;
    }

    public void setIdBovin(int idBovin) {
        this.idBovin = idBovin;
    }

    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeBovin() {
        return codeBovin;
    }

    public void setCodeBovin(String codeBovin) {
        this.codeBovin = codeBovin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEtatSante() {
        return etatSante;
    }

    public void setEtatSante(String etatSante) {
        this.etatSante = etatSante;
    }

    public String getGeniteur() {
        return geniteur;
    }

    public void setGeniteur(String geniteur) {
        this.geniteur = geniteur;
    }

    public String getGenetrice() {
        return genetrice;
    }

    public void setGenetrice(String genetrice) {
        this.genetrice = genetrice;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
