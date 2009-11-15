/**
 * This file is part of jSeduite::HelloWorld
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::HelloWorld is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::HelloWorld is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::HelloWorld; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Sebastien Mosser          [mosser@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jseduite.technicals.sandbox.hello;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/** A simple web service to be used as a Seduite sandbox
 */
@WebService()
public class HelloWorld {

    /** Say hello to the world ^_^
     * @return a string saying "Hello, World !".
     */
    @WebMethod(operationName = "sayHello")
    public String sayHello() throws HelloWorldException {
        return sayPersonalizedHello("World");
    }

    /** Say hello to a given user
     * @param namr the given user
     * @return a string saying "Hello, USER !"
     */
    @WebMethod(operationName = "sayPersonalizedHello")
    public String sayPersonalizedHello(@WebParam(name = "name") String name)
        throws HelloWorldException {
        if (name.length() == 0)
            throw new HelloWorldException("You MUST specify a name !");
        return "Hello, " + name + " !";
    }

}
