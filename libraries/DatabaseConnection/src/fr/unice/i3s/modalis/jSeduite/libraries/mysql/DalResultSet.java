/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.libraries.mysql;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author mosser
 */
public class DalResultSet {


    public Hashtable<String,Integer> labels;
    private int index;
    private String[][] content;
    public int nbColomns;

    public DalResultSet(ResultSet r) throws DALException {
        try {
            this.index = 0;
            this.labels = new Hashtable<String, Integer>();
            ResultSetMetaData metadata = r.getMetaData();
            for(int i = 1; i < metadata.getColumnCount()+1; i++)
                this.labels.put(metadata.getColumnName(i), new Integer(i));

            ArrayList<String[]> tmp = new ArrayList<String[]>();
            int colCount = metadata.getColumnCount();
            nbColomns = metadata.getColumnCount();
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

    public void next() { index++; }
    public void pred() { index--; }

    public int size() { return content.length; }

    public String getValue(String name) {
        if (labels.get(name)!=null)
            return content[index][labels.get(name).intValue()];
        else
            return "";
    }


}
