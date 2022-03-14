/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.model;

import sn.ferme.event.ActionEvenenement;

/**
 *
 * @author hp
 */
public class ModelAction {

    private Utilisateur utilisateur;
    private ActionEvenenement actionEvent;

    public ModelAction(Utilisateur utilisateur, ActionEvenenement actionEvent) {
        this.utilisateur = utilisateur;
        this.actionEvent = actionEvent;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ActionEvenenement getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(ActionEvenenement actionEvent) {
        this.actionEvent = actionEvent;
    }
   

}
