/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.mbds.banque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import org.mbds.banque.entities.CompteBancaire;
import org.mbds.banque.service.GestionnaireCompte;

/**
 *
 * @author robsona
 */
@Named(value = "modificationCompte")
@ViewScoped
public class ModificationCompte implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaire;

    private int idCompte;

    private CompteBancaire compte;

    /**
     * @return the compte
     */
    public CompteBancaire getCompte() {
        return compte;
    }

    /**
     * @param compte the compte to set
     */
    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    /**
     * @return the idCompte
     */
    public int getIdCompte() {
        return idCompte;
    }

    /**
     * @param idCompte the idCompte to set
     */
    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    /**
     * Creates a new instance of ModificationCompte
     */
    public ModificationCompte() {
    }
    
    public void load(){
        this.compte = this.gestionnaire.getCompteById(idCompte);
    }
    
    public String modifier(){
        this.gestionnaire.update(compte);
        return "listeComptes?faces-redirect=true";
    }

}
