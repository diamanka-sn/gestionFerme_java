/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ferme.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author hp
 */
public class ValiderChamp {

    private Pattern modele;
    private Matcher matcher;

    private static final String EMAIL_MODELE = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String NOM_MODELE = "#^([a-z]+(( |')[a-z]+)*)+([-]([a-z]+(( |')[a-z]+)*)+)*$#iu";
    private static final String PASSWORD_MODELE = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}";

    public boolean validerMail(final String mail) {
        modele = Pattern.compile(EMAIL_MODELE);
        matcher = modele.matcher(mail);

        return matcher.matches();
    }

    public boolean verifNom(final String prenom) {
        modele = Pattern.compile(NOM_MODELE);
        matcher = modele.matcher(prenom);

        return matcher.matches();
    }

    public boolean validerMotPasse(final String passe) {
        modele = Pattern.compile(PASSWORD_MODELE);
        matcher = modele.matcher(passe);

        return matcher.matches();
    }

}
