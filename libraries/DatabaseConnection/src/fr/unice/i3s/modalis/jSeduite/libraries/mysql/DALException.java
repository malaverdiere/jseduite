/**
 * This file is part of jSeduite::DatabaseConnection
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::DatabaseConnection is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::DatabaseConnection is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::DatabaseConnection; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author Sébastien Mosser [mosser@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.libraries.mysql;

/** A Very Simple Exception dedictaed to the Data Access Layer (aka DAL)
 * @author mosser
 */
public class DALException extends Exception {
    public DALException (String msg) { super(msg); }
}
