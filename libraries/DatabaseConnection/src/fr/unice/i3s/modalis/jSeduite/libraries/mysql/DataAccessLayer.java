/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.libraries.mysql;

import javax.naming.*;
import java.sql.*;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author mosser
 */
public class DataAccessLayer {

    private static final String DEFAULT_JNDI = "jdbc/jSeduite";
    
    private String jndiName;

    public DataAccessLayer() { this.jndiName = DEFAULT_JNDI; }
    public DataAccessLayer(String url) { this.jndiName = url; }

    private Connection buildConnection() throws DALException {
        try {
            InitialContext ictx = new InitialContext();
            DataSource ds = (DataSource) ictx.lookup(this.jndiName);
            return ds.getConnection();
       }
       catch(Exception e) {
           e.printStackTrace();
           throw new DALException(e.getMessage());
       }
    }

    private void closeConnection(Connection c) throws DALException {
        try { c.close(); }
        catch(Exception e) { throw new DALException(e.getMessage()); }
    }

    public void executeVoid(String query) throws DALException {
        Connection c = buildConnection();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(query);
            return ;
        }catch (Exception e) {
            closeConnection(c);
            throw new DALException(e.getMessage());
        }  finally { closeConnection(c);}
    }


    public String extractScalar(String query, String column)
            throws DALException {
        Connection c = buildConnection();
        try {
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(query);
            if ( ! r.first())
                throw new DALException("No result for [" + query + "]");
            return r.getString(column);
        } catch(Exception e) {
            closeConnection(c);
            throw new DALException(e.getMessage());
        } finally { closeConnection(c);}
    }

    public String[] extractScalarSet(String query, String column)
            throws DALException {
        Connection c = buildConnection();
        try {
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(query);
            ArrayList<String> result = new ArrayList<String>();
            while (r.next())
                result.add(r.getString(column));
            return result.toArray(new String[0]);
        }catch (Exception e) {
            closeConnection(c);
            throw new DALException(e.getMessage());
        }  finally { closeConnection(c);}
    }

    public DalResultSet extractDataSet(String query) throws DALException {
        Connection c = buildConnection();
        try {
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(query);
            return new DalResultSet(r);
        } catch (Exception e) {
            closeConnection(c);
            throw new DALException(e.getMessage());
        }  finally { closeConnection(c);}
    }
}
