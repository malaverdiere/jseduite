package sanbox.restaurant;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;


@WebService()
public class CourseCRUD {

    private CourseFinder finder = new CourseFinder();

    @WebMethod(operationName = "createCourse")
    public String createCourse(@WebParam(name = "c") Course c) {
        if (null == c)
            throw new RuntimeException("Null creation !");
        if (null != finder.findCourseByName(c.getName()))
            throw new RuntimeException("Re-Creation !");
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "INSERT INTO `course` (`kind`, `name`) VALUES (";
            sql += "'"+c.getKind()+"','"+c.getName()+"');";
            dal.executeVoid(sql);
            return c.getName();
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "readCourse")
    public Course readCourse(@WebParam(name = "ref") String ref) {
       if(null == ref)
           throw new RuntimeException("Null read !");
       Course found = finder.findCourseByName(ref);
       if (null == found)
           throw new RuntimeException("UnexistingRefRead: " + ref);
       return found;
    }

    @WebMethod(operationName = "updateCourse")
    public void updateCourse(@WebParam(name = "c") Course c) {
        if (null == c)
            throw new RuntimeException("Null update !");
        if (null == c.getName())
            throw new RuntimeException("Unreferenced update !");
        String sql = "UPDATE `course` SET `kind` = '"+c.getKind()+"' ";
        sql += "WHERE `name` = '" + c.getName()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RuntimeException("SQLException: " + e.getMessage());
       }
    }

    @WebMethod(operationName = "deleteCourse")
    public void deleteCourse(@WebParam(name = "c") Course c) {
        if (null == c)
            throw new RuntimeException("Null delete !");
        if (null == c.getName())
            throw new RuntimeException("Unreferenced delete !");
        String sql = "DELETE FROM `course` WHERE `name` = '" + c.getName()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RuntimeException("SQLException: " + e.getMessage());
       }
    }
}
