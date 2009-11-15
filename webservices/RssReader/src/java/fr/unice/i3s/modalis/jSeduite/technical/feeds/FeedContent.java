/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.feeds;

/**
 *
 * @author mosser
 */
public class FeedContent {
	private String title;
	private String link;
	private String language;
	private FeedNode[] content;

	public FeedContent() { super(); }

	public FeedContent(String title, String link, String language) {
		super();
		this.title = title;
		this.link = link;
		this.language = language;
	}

	public String getTitle()       { return title;    }
	public String getLink()        { return link;     }
	public String getLanguage()    { return language; }
	public FeedNode[] getContent() { return content;  }


	public void setTitle(String title)         { this.title = title;       }
	public void setLink(String link)           { this.link = link;         }
	public void setLanguage(String language)   { this.language = language; }
	public void setContent(FeedNode[] content) { this.content = content;   }
}
