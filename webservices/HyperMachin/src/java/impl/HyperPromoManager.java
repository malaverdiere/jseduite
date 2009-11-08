/**
 * This file is part of jSeduite::HyperMachin
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::HyperMachin is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::HyperMachin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::HyperMachin; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main   Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor [2009] Claudine Peyrat           [claudine@polytech.unice.fr]
 *
 **/
package impl;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import data.*;
import util.*;


@WebService(targetNamespace="http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin")
public class HyperPromoManager {

    /** Build an hyperPromo for a given promo ID
     * @param promoId the promo to readFromDataBase
     * @return the associated HyperPromo
     * @throws data.HyperException
     */
    @WebMethod(operationName = "get")
    public HyperPromo get(@WebParam(name = "promoId") String promoId)
            throws HyperException {
        try {
           HyperCache c = new HyperCache(promoId);
           return c.localize();
        } catch (Exception e) { throw new HyperException(e.getMessage()); }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "synchronize")
    @Oneway
    public void synchronize(@WebParam(name = "promoId") String promoId) {
        try {
            new HyperCache(promoId).repatriate();
        }catch(Exception e) { System.err.println(e.getMessage()); }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "waitStillSynchronize")
    public boolean waitStillSynchronize(@WebParam(name = "promoId") String promoId) {
        try {
            new HyperCache(promoId).repatriate();
            return true;
        }catch(Exception e) { System.err.println(e.getMessage()); }
        return false;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "isValid")
    public HyperCacheStatus getValidity(
            @WebParam(name = "promoId") String promoId)
            throws HyperException {
        try {
            return (new HyperCache(promoId)).isValid();
        } catch (Exception e) { throw new HyperException(e.getMessage()); }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAll")
    public String[] getAllIdentifiers() throws HyperException  {
        try {
            return HyperCache.getCacheContent();
        } catch(Exception e) { throw new HyperException(e.getMessage()); }
    }

}
