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

import org.junit.Test;
import static org.junit.Assert.*;


public class HelloWorldProxyTest {

    @Test
    public void testSayHello() throws Exception {
        System.out.println("sayHello");
        HelloWorldProxy instance = new HelloWorldProxy();
        String expResult = "Hello, World !";
        String result = instance.sayHello();
        assertEquals(expResult, result);
    }

    @Test
    public void testSayPersonalizedHello_Normal() throws Exception {
        System.out.println("sayPersonalizedHello: normal");
        String name = "Sebastien";
        HelloWorldProxy instance = new HelloWorldProxy();
        String expResult = "Hello, Sebastien !";
        String result = instance.sayPersonalizedHello(name);
        assertEquals(expResult, result);
    }

   @Test
    public void testSayPersonalizedHello_Exception() throws Exception {
        System.out.println("sayPersonalizedHello: Exception");
        String name = "";
        HelloWorldProxy instance = new HelloWorldProxy();
        try {
            instance.sayPersonalizedHello(name);
            fail("an exception should have been thrown");
        }
        catch(Exception e) {
            assertEquals(e.getClass(),fr.unice.i3s.modalis.jseduite.technicals.sandbox.hello.HelloWorldException_Exception.class);
        }
    }

}