/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.libraries.mysql;

/**
 *
 * @author mosser
 */
public class DALException extends Exception {
    public String message;

    public DALException (String msg) { this.message = msg; }
}
