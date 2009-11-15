/** This file is part of jSeduite::MenuService
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::MenuService is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::MenuService is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::MenuService; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Mireille Blay-Fornarino [blay@polytech.unice.fr]
 * @contributor 2009 Mosser Sebastien   [mosser@polytech.unice.fr]
**/
package fr.unice.i3s.modalis.jSeduite.technical.restaurant;

import javax.jws.WebMethod;
import javax.jws.WebService;

/** jSeduite Business service for Menu business object
 */
@WebService()
public class MenuBusiness {

    /** Return today's menu
     * @return the menu expected for today, null if not exists
     * @throws RestaurantException (see findMenuByDate @MenuFinder
     */
    @WebMethod(operationName = "getTodayMenu")
    public Menu getTodayMenu() throws RestaurantException {
        MenuFinder finder = new MenuFinder();
        return finder.findMenuByDate(new java.util.Date());
    }

}
