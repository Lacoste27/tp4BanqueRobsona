/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.mbds.banque.exception;

/**
 *
 * @author robsona
 */
public class SoldeInsuffisant extends Exception {
    public SoldeInsuffisant(int solde){
        super(String.format("Le transfert ne peut pas être effectué, solde disponible %s ", solde));
    }
}
