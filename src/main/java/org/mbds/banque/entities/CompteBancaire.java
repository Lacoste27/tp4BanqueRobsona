/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.mbds.banque.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author robsona
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c")
})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private int solde;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OperationBancaire> operations = new ArrayList<>();

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        this.operations.add(new OperationBancaire("Création du compte", solde));
    }

    public CompteBancaire() {
    }

    /**
     * Get the value of solde
     *
     * @return the value of solde
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Set the value of solde
     *
     * @param solde new value of solde
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the operations
     */
    public List<OperationBancaire> getOperations() {
        return operations;
    }

    @Override
    public String toString() {
        return "org.mbds.banque.entities.CompteBancaire[ id=" + id + " ]";
    }

    public void deposer(int montant) {
        this.solde += montant;
        
        this.operations.add(new OperationBancaire("Crédit", montant));
    }

    public void retirer(int montant) {
        if (montant < this.solde) {
            this.solde -= montant;
        } else {
            this.solde = 0;
        }

        this.operations.add(new OperationBancaire("Débit", montant * -1));
    }
}
