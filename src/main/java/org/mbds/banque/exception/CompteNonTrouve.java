/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.mbds.banque.exception;

/**
 *
 * @author robsona
 */
public class CompteNonTrouve extends Exception {
    
    public CompteNonTrouve(int id) {
        super(String.format("L'id %s ne correspon à aucun compte !", id));
    } 
}
