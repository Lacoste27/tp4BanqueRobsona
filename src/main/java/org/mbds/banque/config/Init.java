/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.mbds.banque.config;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.mbds.banque.entities.CompteBancaire;
import org.mbds.banque.service.GestionnaireCompte;

/**
 *
 * @author robsona
 */
@ApplicationScoped
public class Init {

    @Inject
    private GestionnaireCompte gestionnaire;

    @Transactional
    public void init(@Observes @Initialized(ApplicationScoped.class) ServletContext context) {
        List<CompteBancaire> comptes = new ArrayList<>();

        comptes.add(new CompteBancaire("John Lennon", 150000));
        comptes.add(new CompteBancaire("Paul McCartney", 950000));
        comptes.add(new CompteBancaire("Ringo Starr", 20000));
        comptes.add(new CompteBancaire("Georges Harrisson", 100000));

        if (gestionnaire.nbComptes() == 0) {
            for (CompteBancaire compte : comptes) {
                gestionnaire.creerCompte(compte);
            }
        }

    }
}
