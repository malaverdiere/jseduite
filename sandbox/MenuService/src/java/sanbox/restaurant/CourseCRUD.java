package sanbox.restaurant;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;


@WebService()
public class CourseCRUD {


    @WebMethod(operationName = "create")
    public Integer create(@WebParam(name = "c") Course c) {
        if (null == c)
            throw new RuntimeException("Null creation !");
        if (c.getId() != null)
            throw new RuntimeException("Re-Creation !");
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "INSERT INTO `course` (`kind`, `name`) VALUES (";
            sql += "'"+c.getKind()+"','"+c.getName()+"');";
            dal.executeVoid(sql);
            sql = "SELECT MAX(`id`) AS `id` FROM `course`;";
            String result = dal.extractScalar(sql, "id");
            return new Integer(result);
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "read")
    public Course read(@WebParam(name = "ref") Integer ref) {
       if(null == ref)
           throw new RuntimeException("Null read !");
       String sql = "SELECT * FROM `course` WHERE `id` = " + ref+";";
       DataAccessLayer dal = new DataAccessLayer();
       try {
           DalResultSet result = dal.extractDataSet(sql);
           Course c = new Course();
           c.setId(new Integer(result.getValue("id")));
           c.setKind(result.getValue("kind"));
           c.setName(result.getValue("name"));
           return c;
       } catch(Exception e) {
           throw new RuntimeException("UnexistingRefRead: " + e.getMessage());
       }
    }

    @WebMethod(operationName = "update")
    public void update(@WebParam(name = "c") Course c) {
        if (null == c)
            throw new RuntimeException("Null update !");
        if (null == c.getId())
            throw new RuntimeException("Transient update !");
        String sql = "UPDATE `course` SET `kind` = '"+c.getKind()+"', ";
        sql += " `name` = '"+c.getName()+"' WHERE `id` = " + c.getId()+";";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RuntimeException("SQLException: " + e.getMessage());
       }
    }

    @WebMethod(operationName = "delete")
    public void delete(@WebParam(name = "c") Course c) {
        if (null == c)
            throw new RuntimeException("Null delete !");
        if (null == c.getId())
            throw new RuntimeException("Transient delete !");
        String sql = "DELETE FROM `course` WHERE `id` = " + c.getId()+";";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RuntimeException("SQLException: " + e.getMessage());
       }
    }
}
