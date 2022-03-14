/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.event;

import sn.ferme.model.Utilisateur;

/**
 *
 * @author hp
 */
public interface ActionEvenenement {
    public void delete(Utilisateur user);

    public void update(Utilisateur user);
}
