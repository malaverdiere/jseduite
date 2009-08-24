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
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.registry;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;

public class Feed {
    private String providerDNS;
    private String nickname;
    private String feedURL;
    private FeedClass feedClass;

    public Feed() {
    }

    public Feed(DalResultSet rSet) {
        providerDNS = rSet.getValue("provider_dns");
        nickname = rSet.getValue("nickname");
        feedURL = rSet.getValue("feed_url");
        feedClass = new FeedClass(rSet);
    }

    public FeedClass getFeedClass() {
        return feedClass;
    }

    public void setFeedClass(FeedClass feedClass) {
        this.feedClass = feedClass;
    }

    public String getFeedURL() {
        return feedURL;
    }

    public void setFeedURL(String feedURL) {
        this.feedURL = feedURL;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProviderDNS() {
        return providerDNS;
    }

    public void setProviderDNS(String providerDNS) {
        this.providerDNS = providerDNS;
    }


}
