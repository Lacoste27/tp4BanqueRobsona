/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.mbds.banque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import org.mbds.banque.entities.CompteBancaire;
import org.mbds.banque.service.GestionnaireCompte;

/**
 *
 * @author robsona
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

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
            Util.messageErreur("L'id ne correspond à aucune compte", String.format("L'id numero %s n'existe pas !",sourceId));
        } else {
            if (source.getSolde() < montant) {
                erreur = true;
                Util.messageErreur("Le transfert ne peut être effectuer, solde insufisant", "Solde insufisante");
            }

        }

        if (destination == null) {
            erreur = true;
            Util.messageErreur("L'id ne correspond à aucune compte", String.format("L'id numero %s n'existe pas !",sourceId));
        }

        if (montant == 0) {
            erreur = true;
            Util.messageErreur("Le montant doit être supérieur à zero !", "Erreur montant");
        }

        if (erreur) {
            return null;
        }

        gestionnaire.transferer(source, destination, montant);

        Util.addFlashInfoMessage("Le tranfert a été effectué !");

        return "listeComptes?faces-redirect=true";
    }

}
