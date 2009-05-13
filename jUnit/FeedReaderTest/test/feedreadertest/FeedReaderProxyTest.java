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
import org.junit.Test;
import static org.junit.Assert.*;

public class FeedReaderProxyTest {

    public static final String FEED_NAME = "TV5_Une";

    private FeedReaderProxy instance;
    public FeedReaderProxyTest() {
        this.instance = new FeedReaderProxy();
    }

    @Test
    public void testReadAFeed_Lang() throws Exception {
        System.out.println("readAFeed: Lang");
        FeedContent c = this.instance.readAFeed(FEED_NAME);
        assertEquals("Bad language", c.getLanguage(), "fr");
    }

    @Test
    public void testReadAFeed_Length() throws Exception {
        System.out.println("readAFeed: Length");
        FeedContent c = this.instance.readAFeed(FEED_NAME);
        assertTrue("Empty feed", c.getContent().size() > 0);
    }

    @Test(expected=Exception.class)
    public void testReadAFeed_Unknown() throws Exception {
        System.out.println("readAFeed: Unknown");
        FeedContent c = this.instance.readAFeed("");
        assertFalse("Should never be executed !",true);
    }

}