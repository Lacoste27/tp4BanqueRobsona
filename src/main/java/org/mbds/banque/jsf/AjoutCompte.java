/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.mbds.banque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import org.mbds.banque.entities.CompteBancaire;
import org.mbds.banque.service.GestionnaireCompte;

/**
 *
 * @author robsona
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaire;

    private String nom;
    private int solde;

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the solde
     */
    public int getSolde() {
        return solde;
    }

    /**
     * @param solde the solde to set
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String ajout() {
        boolean erreur = false;
        
        if(solde <= 0) {
            erreur = true;
            Util.messageErreur("Le solde doit être supérieur à zero !", "Solde erroné");
        }
        
        if(erreur){
            return null;
        }
        
        
        
        
        CompteBancaire compte = new CompteBancaire(nom, solde);
        
        this.gestionnaire.creerCompte(compte);
        
        String successMessage = String.format("Le compte de %s a été crée avec success !", compte.getNom());
        Util.addFlashInfoMessage(successMessage);

        return "listeComptes?faces-redirect=true";
    }

}
