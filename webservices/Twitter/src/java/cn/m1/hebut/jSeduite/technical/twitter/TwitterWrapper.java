
/**
 * This file is part of jSeduite::BreakTimeManager
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
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 **/
package cn.m1.hebut.jSeduite.technical.twitter;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import winterwell.jtwitter.Twitter;

/**
 *@author      Zhao Yichen         [yichenzhao18@gmail.com]
 *@author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 *
 */
@WebService()
public class TwitterWrapper {
    /**
     * get a list messages from Twitter website
     * @param user,password,threshold
     * @exception TwitterWrapperException
     * @return a list of messages
     */
    @WebMethod(operationName = "getLastMessages")
    public List<Message> getLastMessages(@WebParam(name = "user")
    String user, @WebParam(name = "password")
    String password, @WebParam(name = "threshold")
    int threshold) throws TwitterWrapperException{
        Twitter t=new Twitter(user,password);
        List<Message> list=new ArrayList<Message>();
        if(t.getRecentDirectMessages().size()==0) return null;
        int number=(threshold<t.getRecentDirectMessages().size()?threshold:t.getRecentDirectMessages().size());
        for(int i=0;i<number;i++)
        {
            list.add(new Message(t.getRecentDirectMessages().get(i)));
        }
        return list;
    }
}
