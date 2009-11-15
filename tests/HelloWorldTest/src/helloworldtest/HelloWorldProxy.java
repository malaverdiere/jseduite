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

package helloworldtest;

/**
 *
 * @author mosser
 */
public class HelloWorldProxy {

   public String sayHello() throws Exception {
       try { 
           fr.unice.i3s.modalis.jseduite.technicals.sandbox.hello.HelloWorldService service = new fr.unice.i3s.modalis.jseduite.technicals.sandbox.hello.HelloWorldService();
           fr.unice.i3s.modalis.jseduite.technicals.sandbox.hello.HelloWorld port = service.getHelloWorldPort();
           java.lang.String result = port.sayHello();
           return result;
       } catch (Exception ex) {
           throw ex;
       }
   }

   public String sayPersonalizedHello(String name) throws Exception {
       try { 
           fr.unice.i3s.modalis.jseduite.technicals.sandbox.hello.HelloWorldService service = new fr.unice.i3s.modalis.jseduite.technicals.sandbox.hello.HelloWorldService();
           fr.unice.i3s.modalis.jseduite.technicals.sandbox.hello.HelloWorld port = service.getHelloWorldPort();
           String result = port.sayPersonalizedHello(name);
           return result;
       } catch (Exception ex) {
           throw ex;
       }

   }


}
