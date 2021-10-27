/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.exception;

/**
 *
 * @author tatia
 */
public class ConflictException  extends Exception {

    /**
     * Constructor de la clase ConflictException
     *
     * @param conflict
     */
    public ConflictException(String conflict) {
        super(conflict);
    }
}
