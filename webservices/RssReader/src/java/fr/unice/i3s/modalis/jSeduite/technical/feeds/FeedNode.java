/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.feeds;

/**
 *
 * @author mosser
 */
public class FeedNode {
	private String author;
	private String title;
	private String type;
	private String content;

	public FeedNode(String author, String title, String type, String content) {
		super();
		this.author = author; this.title = title;
		this.type = type;     this.content = content;
	}

	public FeedNode() {	super(); }

	public String getAuthor()  { return author;  }
	public String getTitle()   { return title;   }
	public String getType()    { return type;    }
	public String getContent() { return content; }

	public void setAuthor(String author)   { this.author = author;  }
	public void setTitle(String title)     { this.title = title;    }
	public void setType(String type)       { this.type = type;      }
	public void setContent(String content) { this.content = content;}
}
