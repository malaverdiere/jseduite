/**
 * This file is part of jSeduite::ProfileManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ProfileManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ProfileManager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ProfileManager; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.profiles.sources;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;

public class Source {
    private String nickname;
    private String name;

    public Source() {

    }

    public Source(DalResultSet rset) throws Exception {
        this.nickname = rset.getValue("nickname");
        this.name = rset.getValue("name");
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
