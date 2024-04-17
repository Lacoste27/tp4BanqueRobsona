/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.mbds.banque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.mbds.banque.entities.CompteBancaire;
import org.mbds.banque.entities.OperationBancaire;
import org.mbds.banque.service.GestionnaireCompte;

/**
 *
 * @author robsona
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaire;

    private int idCompte;
    private CompteBancaire compte;
    private List<OperationBancaire> historiques = new ArrayList<>();

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
     * @return the historiques
     */
    public List<OperationBancaire> getHistoriques() {
        return historiques;
    }

    /**
     * @param historiques the historiques to set
     */
    public void setHistoriques(List<OperationBancaire> historiques) {
        this.historiques = historiques;
    }

    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }

    public void load() {
        this.compte = this.gestionnaire.getCompteById(idCompte);
        this.historiques = this.compte.getOperations();
    }

}
