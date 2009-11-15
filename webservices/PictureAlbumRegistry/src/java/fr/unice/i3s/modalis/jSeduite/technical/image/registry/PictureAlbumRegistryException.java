/**
 * This file is part of jSeduite::PictureAlbumRegistry
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::PictureAlbumRegistry is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::PictureAlbumRegistry is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::PictureAlbumRegistry; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.image.registry;

/** A simple exception to specialize errors
 * @author mosser
 */
public class PictureAlbumRegistryException extends Exception {
    public PictureAlbumRegistryException(String m) { super(m); }
}
