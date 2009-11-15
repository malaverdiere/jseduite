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

import javax.naming.*;
import java.sql.*;
import java.util.ArrayList;
import javax.sql.DataSource;

/** A MySQL DataAccessLayer (aka DAL) implementation
 * @author mosser
 */
public class DataAccessLayer {

    private static final String DEFAULT_JNDI = "jdbc/jseduite";
    private String jndiName;
    private boolean shouldLog = false;

    /** Build a DAL using the default jSeduite JDBC resource
     */
    public DataAccessLayer() {
        this.jndiName = DEFAULT_JNDI;
    }

    /** Build a DAL using a user-given JDBC resource JNDI name
     * @param url the JNDI name of the wanted JDBC resource
     */
    public DataAccessLayer(String url) {
        this.jndiName = url;
    }

    /** (De)Activate the query log mechanism
     * @param b true to activate, false to deactivate
     * @remarks this mechanism is deactivated by default
     */
    public void setQueryLog(boolean b) {
        this.shouldLog = b;
    }

    /** Log a string, if explicitely asked for (basically print it to stdout)
     * @param l the log to print
     */
    private void log(String l) {
        if (this.shouldLog) {
            System.out.println(l);
        }
    }

    /** Build a connection to the database
     * @return A Connection to the database through the MySQL JDBC driver
     * @throws DALException
     */
    private Connection buildConnection() throws DALException {
        log("Opening connection");
        try {
            InitialContext ictx = new InitialContext();
            DataSource ds = (DataSource) ictx.lookup(this.jndiName);
            return ds.getConnection();
        } catch (Exception e) {
            throw new DALException(e.getMessage());
        }
    }

    /** Close a connection previously opened to the database
     * @param c the connection toc lode
     * @throws DALException
     */
    private void closeConnection(Connection c) throws DALException {
        log("Closing connection");
        try {
            c.close();
        } catch (Exception e) {
            throw new DALException(e.getMessage());
        }
    }

    /** Execute a query which does not produce any output result (eg insert)
     * @param query the SQL (MySQL dialect) query to execute
     * @throws DALException
     */
    public void executeVoid(String query) throws DALException {
        Connection c = buildConnection();
        try {
            Statement s = c.createStatement();
            log("executeVoid [" + s + "]");
            s.executeUpdate(query);
            return;
        } catch (Exception e) {
            throw new DALException(e.getMessage());
        } finally {
            closeConnection(c);
        }
    }

    /** Extract a scalar value from the database 
     * @param query the query to execute
     * @param column the column's name of the scalar value to retrieve
     * @return a single string containing the scalar
     * @throws DALException (no result for query ...)
     */
    public String extractScalar(String query, String column)
            throws DALException {
        Connection c = buildConnection();
        try {
            Statement s = c.createStatement();
            log("extractScalar [" + query + "]");
            ResultSet r = s.executeQuery(query);
            if (!r.first()) {
                throw new DALException("No result for [" + query + "]");
            }
            return r.getString(column);
        } catch (Exception e) {
            throw new DALException(e.getMessage());
        } finally {
            closeConnection(c);
        }
    }

    /** Extract a set of scalar values from the database
     * @param query the query to execute
     * @param column the column's name of the scalar value to retrieve
     * @return an array of String containing all the expected values
     * @throws DALException
     */
    public String[] extractScalarSet(String query, String column)
            throws DALException {
        Connection c = buildConnection();
        try {
            Statement s = c.createStatement();
            log("extractScalarSet [" + query + "]");
            ResultSet r = s.executeQuery(query);
            ArrayList<String> result = new ArrayList<String>();
            while (r.next()) {
                result.add(r.getString(column));
            }
            return result.toArray(new String[0]);
        } catch (Exception e) {
            throw new DALException(e.getMessage());
        } finally {
            closeConnection(c);
        }
    }

    /** Extract a complex dataset from the database
     * @param query the query to execute
     * @return a DalResultSet containing all the result extracted from the DB
     * @throws fr.unice.i3s.modalis.jSeduite.libraries.mysql.DALException
     */
    public DalResultSet extractDataSet(String query) throws DALException {
        Connection c = buildConnection();
        try {
            Statement s = c.createStatement();
            log("extractDataSet [" + query + "]");
            ResultSet r = s.executeQuery(query);
            return new DalResultSet(r);
        } catch (Exception e) {
            throw new DALException(e.getMessage());
        } finally {
            closeConnection(c);
        }
    }
}
