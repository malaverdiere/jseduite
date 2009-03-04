/**
 * This file is part of jSeduite::FeedRegistry
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::FeedRegistry is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::FeedRegistry is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::FeedRegistry; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor 2008 Clémentine Delerce-Mauris [delercm@polytech.unice.fr]
 * @contributor 2008 Lionel Palacin            [lionel.palacin@gmail.com]
 * @contributor 2008 Stéphane Martarello       [stephane.martarello@gmail.com]
 *
 **/
package fr.unice.i3s.modalis.jSeduite.technical.registry;

/**
 * A very simple dedicated exception to specialize the WSDL fault
 * @author mosser
 */
public class FeedRegistryException extends Exception {
    public FeedRegistryException(String m) { super(m); }
}
