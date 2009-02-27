/**
 * This file is part of jSeduite::DatabaseConnection
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::DatabaseConnection is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::DatabaseConnection is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::DatabaseConnection; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author Sébastien Mosser [mosser@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.libraries.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

/** Data Structure to handle data extracted from a database throught the DAL
 * @author mosser
 */
public class DalResultSet {

    /** Bind a string label to a column integer position
     */
    public Hashtable<String,Integer> labels;

    /** Number of columns inside this result set
     */
    public int nbColumns;

    private int index;
    private String[][] content;

    /** Build a DalResultSet using a database ResultSet as data container
     * @param r the ResultSet usd to fill the instance
     * @throws DALException
     */
    public DalResultSet(ResultSet r) throws DALException {
        try {
            this.index = 0;
            this.labels = new Hashtable<String, Integer>();
            ResultSetMetaData metadata = r.getMetaData();
            for(int i = 1; i < metadata.getColumnCount()+1; i++)
                this.labels.put(metadata.getColumnName(i), new Integer(i));

            ArrayList<String[]> tmp = new ArrayList<String[]>();
            int colCount = metadata.getColumnCount();
            nbColumns = metadata.getColumnCount();
            while(r.next()) {
                String[] row = new String[colCount+1];
                for(int i = 1; i < colCount+1; i++) {
                    row[i] = r.getString(i);
                    tmp.add(row);
                }
            }
            this.content = tmp.toArray(new String[0][0]);
        } catch(Exception e) {
            throw new DALException(e.getMessage());
        }
    }

    /** forward to the next result
     */
    public void next() { index++; }
    
    /** rewind to the previous result
     */
    public void pred() { index--; }

    /** Get the size (ie the number of tuples) of this DalResultSet
     * @return the size of this data set
     */
    public int size() { return content.length; }

    /** Retrieve the value of an attribute on the current tuple
     * @param name the column name
     * @return the attribute value
     */
    public String getValue(String name) {
        if (labels.get(name)!=null)
            return content[index][labels.get(name).intValue()];
        else
            return "";
    }


}
