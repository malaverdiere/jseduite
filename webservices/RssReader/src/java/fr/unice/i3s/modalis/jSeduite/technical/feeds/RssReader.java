/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.feeds;

import javax.jws.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


/**
 *
 * @author mosser
 */
@WebService()
public class RssReader {
    
    @WebMethod()
    public FeedContent read (@WebParam(name="url") URL url) {
        try {
            // retrieving data stream from third party publisher
            //URL feedUrl = new URL(url);
            URL feedUrl = url;
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            // creating result data
            FeedContent result = new FeedContent(feed.getTitle(),
                    feed.getLink(), feed.getLanguage());

            ArrayList<FeedNode> tmpList = new ArrayList<FeedNode>();
            List<SyndEntry> entries = (List<SyndEntry>) feed.getEntries();
            for (SyndEntry syndEntry : entries) {
                String author = syndEntry.getAuthor();
                if (author.equals(""))
                    author = feed.getTitle();
                //String author = (syndEntry.getAuthor().equals("") ? feed.getTitle() : syndEntry.getAuthor());
                FeedNode tmp = new FeedNode(author,
                        syndEntry.getTitle(),
                        syndEntry.getDescription().getType(),
                        syndEntry.getDescription().getValue());
                tmpList.add(tmp);
            }
            result.setContent(tmpList.toArray(new FeedNode[tmpList.size()]));
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
