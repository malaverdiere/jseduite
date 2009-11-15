/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.cache;

/**
 *
 * @author mosser
 */
public class DataCacheException extends Exception {
    public String message;
    public DataCacheException(String msg) { this.message = msg; }
}
