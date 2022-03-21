/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.model;

import javax.swing.Icon;

/**
 *
 * @author hp
 */
public class ModelCard {

    private String titre;
    private double valeur;
    private int pourcentage;
    private Icon icone;

    public ModelCard(String titre, double valeur, int pourcentage) {
        this.titre = titre;
        this.valeur = valeur;
        this.pourcentage = pourcentage;
        //   this.icone = icone;
    }

    public ModelCard(String titre, double valeur) {
        this.titre = titre;
        this.valeur = valeur;
        //   this.icone = icone;
    }

    public ModelCard() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Icon getIcone() {
        return icone;
    }

    public void setIcone(Icon icone) {
        this.icone = icone;
    }

}
