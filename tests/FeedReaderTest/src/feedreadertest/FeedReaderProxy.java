/**
 * This file is part of jSeduite::FeedReader
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::FeedReader is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::FeedReader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::FeedReader; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Sebastien Mosser [mosser@polytech.unice.fr]
 **/

package feedreadertest;
import fr.unice.i3s.modalis.jseduite.technical.feeds.FeedContent;
import fr.unice.i3s.modalis.jseduite.orchestrations.schema.feedreader.*;
public class FeedReaderProxy {

    public FeedContent readAFeed(String feedName) throws Exception {
        fr.unice.i3s.modalis.jseduite.orchestrations.wsdl.feedreader.FeedReaderService service = new fr.unice.i3s.modalis.jseduite.orchestrations.wsdl.feedreader.FeedReaderService();
        fr.unice.i3s.modalis.jseduite.orchestrations.wsdl.feedreader.FeedReaderPortType port = service.getFeedReaderPort();
        ReadAFeedIn in = new ReadAFeedIn();
        in.setFeedName(feedName);
        ReadAFeedOut result = port.readAFeed(in);
        return result.getFeedData();
    }

}
