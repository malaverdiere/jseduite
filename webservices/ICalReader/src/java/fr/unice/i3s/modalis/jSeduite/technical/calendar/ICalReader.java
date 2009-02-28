/**
 * This file is part of jSeduite::ICalReader
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ICalReader is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ICalReader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ICalReader; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.calendar;

import java.io.InputStream;
import java.net.URL;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.filter.*;



/** A service able to read an ICal file from a remote URL
 * @author mosser
 */
@WebService()
public class ICalReader {


    private ComponentList buildComponents(URL url) throws Exception {
        InputStream is = url.openStream();
        CalendarBuilder builder = new CalendarBuilder();
        Calendar c = builder.build(is);
        return c.getComponents(Component.VEVENT);
    }


    /** Dump the whole content of an iCal calendar
     * @param calendar an URL to reach the iCal file.
     * @return A set of CalendarEvent, built from the 'calendar' file
     * @throws ICalReaderException
     */
    @WebMethod(operationName = "getContent")
    public CalendarEvent[] getContent(@WebParam(name = "calendar") URL calendar)
            throws ICalReaderException {
        try {
            ComponentList cList = buildComponents(calendar);
            return CalendarEvent.transform(cList);
        }
        catch(Exception e) { throw new ICalReaderException(e.getMessage()); }
    }

    /** Extract the content of the current day inside the calendar
     * @param calendar an URL to reach the iCal file.
     * @return A set of filtered CalendarEvent, built from the 'calendar' file
     * @throws ICalReaderException
     */
    @WebMethod(operationName = "getToday")
    public CalendarEvent[] getToday(@WebParam(name = "calendar") URL calendar)
            throws ICalReaderException {
        try {
            ComponentList cList = buildComponents(calendar);
            java.util.Calendar today = java.util.Calendar.getInstance();
            today.set(java.util.Calendar.HOUR_OF_DAY, 0);
            today.clear(java.util.Calendar.MINUTE);
            today.clear(java.util.Calendar.SECOND);
            Period period = new Period(new DateTime(today.getTime()),
                                       new Dur(1, 0, 0, 0));
            Filter filter = new Filter(new PeriodRule(period));
            cList = (ComponentList) filter.filter(cList);
            return CalendarEvent.transform(cList);
        }
        catch(Exception e) { throw new ICalReaderException(e.getMessage()); }
    }

    /** Extract current events inside the calendar
     * @param calendar an URL to reach the iCal file.
     * @return A set of filtered CalendarEvent, built from the 'calendar' file
     * @throws ICalReaderException
     */
    @WebMethod(operationName = "getNow")
    public CalendarEvent[] getNow(@WebParam(name = "calendar") URL calendar)
            throws ICalReaderException {
        try {
            ComponentList cList = buildComponents(calendar);
            java.util.Calendar today = java.util.Calendar.getInstance();
            Period period = new Period(new DateTime(today.getTime()),
                                       new Dur(0, 0, 0, 0));
            Filter filter = new Filter(new PeriodRule(period));
            cList = (ComponentList) filter.filter(cList);
            return CalendarEvent.transform(cList);
        }
        catch(Exception e) { throw new ICalReaderException(e.getMessage()); }
    }

}
