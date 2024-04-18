/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.mbds.banque.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import java.io.Serializable;
import org.mbds.banque.entities.CompteBancaire;
import org.mbds.banque.service.GestionnaireCompte;

/**
 *
 * @author robsona
 */
@Named(value = "transaction")
@ViewScoped
public class Transaction implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaire;
    private int idCompte;
    private int montant;
    private String type;
    private CompteBancaire compte;

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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * Creates a new instance of Transaction
     */
    public Transaction() {
    }

    public void soldeValidator(FacesContext context, UIComponent composant, Object valeur) {
        UIInput composantTypeTransaction = (UIInput) composant.findComponent("typeTransaction");

        String typeTransaction = (String) composantTypeTransaction.getLocalValue();

        if (typeTransaction == null) {
            return;
        }

        if (typeTransaction.equals("retrait")) {
            int retrait = (int) valeur;
            if (compte.getSolde() < retrait) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le retrait doit être inférieur au solde du compte", "Le retrait doit être inférieur au solde du compte");
                throw new ValidatorException(message);
            }
        }
    }

    public void load() {
        this.setCompte(this.gestionnaire.getCompteById(idCompte));
    }

    public String transaction() {
        try {
            switch (type) {
                case "retrait":
                    this.compte.retirer(montant);
                    break;
                case "depot":
                    this.compte.deposer(montant);
                    break;
            }

            this.gestionnaire.update(compte);

            String successMessage = String.format("Le transaction de %s a été effectué avec success !", compte.getNom());
            Util.addFlashInfoMessage(successMessage);

            return "listeComptes?faces-redirect=true";
        } catch (OptimisticLockException lockException) {
            String message = String.format("Le compte de %s a été modifié ou supprimé !", this.getCompte().getNom());
            Util.messageErreur(message, message);
            return null;
        }
    }

}
