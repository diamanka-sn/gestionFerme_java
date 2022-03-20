/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.model;

import java.awt.Color;

/**
 *
 * @author Mouhamadou DIAMANKA
 */
public class ModelDiagramme {
    
    private String nom;
    private double valeur;
    private Color couleur;

    public ModelDiagramme(String nom, double valeur, Color couleur) {
        this.nom = nom;
        this.valeur = valeur;
        this.couleur = couleur;
    }

    public ModelDiagramme() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    
}
