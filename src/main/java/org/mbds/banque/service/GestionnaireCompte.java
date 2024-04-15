/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.mbds.banque.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.mbds.banque.entities.CompteBancaire;

/**
 *
 * @author robsona
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root",
        password = "Admin123+",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire c) {
        this.em.persist(c);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
    
    @Transactional
    public void supprimerCompte(CompteBancaire compte){
        em.remove(em.merge(compte));
    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination, int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    public List<CompteBancaire> getAllComptes() {
        String query = "SELECT c FROM CompteBancaire c";
        TypedQuery<CompteBancaire> typeQuery = em.createQuery(query, CompteBancaire.class);
        return typeQuery.getResultList();
    }

    public Long nbComptes() {
        String query = "SELECT count(c) FROM CompteBancaire c";
        TypedQuery<Long> typeQuery = em.createQuery(query, Long.class);
        return typeQuery.getSingleResult();
    }

    public CompteBancaire getCompteById(int id) {
        return em.find(CompteBancaire.class, id);
    }
}
