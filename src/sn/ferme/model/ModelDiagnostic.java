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
public class ModelDiagnostic {
    private int idDiagnostic;
    private int idBovin;
    private int idMaladie;
    private String dateMaladie;
    private String dateGuerison;
    
    private String nomMaladie;

    public ModelDiagnostic(int idDiagnostic, int idBovin, int idMaladie, String dateMaladie, String dateGuerison, String nomMaladie) {
        this.idDiagnostic = idDiagnostic;
        this.idBovin = idBovin;
        this.idMaladie = idMaladie;
        this.dateMaladie = dateMaladie;
        this.dateGuerison = dateGuerison;
        this.nomMaladie = nomMaladie;
    }

    public ModelDiagnostic(int idMaladie, String nomMaladie) {
        this.idMaladie = idMaladie;
        this.nomMaladie = nomMaladie;
    }

    public ModelDiagnostic(int idDiagnostic, int idBovin, int idMaladie, String dateMaladie, String dateGuerison) {
        this.idDiagnostic = idDiagnostic;
        this.idBovin = idBovin;
        this.idMaladie = idMaladie;
        this.dateMaladie = dateMaladie;
        this.dateGuerison = dateGuerison;
    }

    public ModelDiagnostic() {
    }

    public int getIdDiagnostic() {
        return idDiagnostic;
    }

    public void setIdDiagnostic(int idDiagnostic) {
        this.idDiagnostic = idDiagnostic;
    }

    public int getIdBovin() {
        return idBovin;
    }

    public void setIdBovin(int idBovin) {
        this.idBovin = idBovin;
    }

    public int getIdMaladie() {
        return idMaladie;
    }

    public void setIdMaladie(int idMaladie) {
        this.idMaladie = idMaladie;
    }

    public String getDateMaladie() {
        return dateMaladie;
    }

    public void setDateMaladie(String dateMaladie) {
        this.dateMaladie = dateMaladie;
    }

    public String getDateGuerison() {
        return dateGuerison;
    }

    public void setDateGuerison(String dateGuerison) {
        this.dateGuerison = dateGuerison;
    }

    public String getNomMaladie() {
        return nomMaladie;
    }

    public void setNomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
    }
    
    
    
    
}
