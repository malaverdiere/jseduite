/**
 * This file is part of jSeduite::Twitter
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::Twitter is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::Twitter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::Twitter; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.messaging.twitter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import winterwell.jtwitter.*;

/** Wrap the jTwitter library as a WebService jseduite-ready
 * @remarks be aware of the API Limitation rate (150 request/hour)
 * @author mosser
 */
@WebService()
public class TwitterWrapper {

    /** Retrieve all '@username' Tweets from twitter
     * @param username the Twitter account
     * @param password the associated password
     * @param treshold amount of maximum expected tweets (20 max)
     * @return an array of "tweets" (author, date and text)
     */
    @WebMethod(operationName = "getTweets")
    public Tweet[] getIntendedTweets(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "treshold") int treshold) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        Twitter twitter = new Twitter(username, password);
        for(Twitter.Status s: twitter.getReplies()) {
            String user = "@"+ s.getUser().getScreenName();
            String date =  this.getDateAsString(s.getCreatedAt());
            String message = s.getText().replaceAll("@"+username, "").trim();
            tweets.add(new Tweet(user, date, message));
        }
        if (tweets.size() <= treshold)
            return tweets.toArray(new Tweet[tweets.size()]);
        Tweet[] result = new Tweet[treshold];
        for(int i = 0; i < treshold; i++)
            result[i] = tweets.get(i);
        return result;
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @WebMethod(operationName="getFreeTokens")
    public int getFreeTokens(@WebParam(name = "username") String username,
            @WebParam(name = "password") String password) {
        Twitter twitter = new Twitter(username, password);
        return twitter.getRateLimitStatus();
    }

    /** Transform a java Date (which sucks!) into a simple string (which rocks!)
     * @param date the date to transform
     * @return a date following the "YYYY-MM-DD HH:MM:SS" pattern
     */
    private String getDateAsString(Date date){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        String d = "" + cal.get(Calendar.YEAR);
        d += "-" + this.prettyfy(cal.get(Calendar.MONTH));
        d += "-" + this.prettyfy(cal.get(Calendar.DAY_OF_MONTH));
        d += " " + this.prettyfy(cal.get(Calendar.HOUR_OF_DAY));
        d += ":" + this.prettyfy(cal.get(Calendar.MINUTE));
        d += ":" + this.prettyfy(cal.get(Calendar.SECOND));
        return d;
    }

    /** Makes an integer pretty ('4' -> '04')
     * @param n the integer to make pretty
     * @return a preety string representing n with 0 at the begining, if needed.
     */
    private String prettyfy(int n) {
        return (n < 10 ? "0"+n : ""+n);
    }


}
