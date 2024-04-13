/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.mbds.banque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.mbds.banque.entities.CompteBancaire;
import org.mbds.banque.service.GestionnaireCompte;

/**
 *
 * @author robsona
 */
@Named(value = "transfert")
@Dependent
public class Transfert {

    @Inject
    private GestionnaireCompte gestionnaire;

    private int sourceId;
    private int destinataireId;
    private int montant;

    /**
     * @return the sourceId
     */
    public int getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId the sourceId to set
     */
    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return the destinataireId
     */
    public int getDestinataireId() {
        return destinataireId;
    }

    /**
     * @param destinataireId the destinataireId to set
     */
    public void setDestinataireId(int destinataireId) {
        this.destinataireId = destinataireId;
    }

    /**
     * @return the montant
     */
    public int getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of Transfert
     */
    public Transfert() {
    }

    public String transferer() {
        boolean erreur = false;
        CompteBancaire source = gestionnaire.getCompteById(sourceId);
        CompteBancaire destination = gestionnaire.getCompteById(destinataireId);

        if (source == null) {
            erreur = true;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Compte inexistante", "L'id ne correspond à aucune compte");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 

        if (source.getSolde() < montant) {
            erreur = true;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solde insufisante", "Le transfert ne peut être effectuer, solde insufisant");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        if (destination == null) {
            erreur = true;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Compte inexistante", "L'id ne correspond à aucune compte");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        if (erreur) {
            return "transfert";
        }

        gestionnaire.transferer(source, destination, montant);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transfert effectué", "Le transfert a été effectué");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "listeComptes?faces-redirect=true";
    }

}
