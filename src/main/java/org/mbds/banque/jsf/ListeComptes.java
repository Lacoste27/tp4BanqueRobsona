/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.mbds.banque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import org.mbds.banque.entities.CompteBancaire;
import org.mbds.banque.service.GestionnaireCompte;

/**
 *
 * @author robsona
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    
    @Inject
    private GestionnaireCompte gestionnaire;

    private List<CompteBancaire> allComptes;

    /**
     * @return the allComptes
     */
    public List<CompteBancaire> getAllComptes() {
        if (allComptes == null) {
            allComptes = gestionnaire.getAllComptes();
        }

        return allComptes;
    }

    /**
     * @param allComptes the allComptes to set
     */
    public void setAllComptes(List<CompteBancaire> allComptes) {
        this.allComptes = allComptes;
    }

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

}
