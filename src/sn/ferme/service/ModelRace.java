/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.service;

/**
 *
 * @author hp
 */
public class ModelRace {
    private int idRace;
    private String nomRace;

    public ModelRace() {
    }

    public ModelRace(int idRace, String nomRace) {
        this.idRace = idRace;
        this.nomRace = nomRace;
    }

    public ModelRace(String nomRace) {
        this.nomRace = nomRace;
    }
    

    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    public String getNomRace() {
        return nomRace;
    }

    public void setNomRace(String nomRace) {
        this.nomRace = nomRace;
    }
    
}
