

package sanbox.restaurant;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@WebService()
public class MenuCRUD {

     private MenuFinder finder = new MenuFinder();

     public static String toSql(Date date) {
         GregorianCalendar cal = new GregorianCalendar();
         cal.setTime(date);
         String d = "" + cal.get(Calendar.YEAR);
         d += "-" + cal.get(Calendar.MONTH);
         d += "-" + cal.get(Calendar.DAY_OF_MONTH);
         return d;
     }

    @WebMethod(operationName = "createMenu")
    public Date createMenu(@WebParam(name = "m") Menu m) {
        if (null == m)
            throw new RuntimeException("Null creation !");
        if (null != finder.findMenuByDate(m.getDate()))
            throw new RuntimeException("Re-Creation !");
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String d = toSql(m.getDate());
            for(Course c: m.getCourses()) {
                String sql = "INSERT INTO `menu` (`date`, `course`) VALUES (";
                sql += "'"+d+"','"+c.getName()+"');";
                dal.executeVoid(sql);
            }
            return m.getDate();
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }


    @WebMethod(operationName = "readMenu")
    public Menu readMenu(@WebParam(name = "ref") Date ref) {
        if(null == ref)
           throw new RuntimeException("Null read !");
       Menu found = finder.findMenuByDate(ref);
       if (null == found)
           throw new RuntimeException("UnexistingRefRead: " + ref);
       return found;
    }

    @WebMethod(operationName = "updateMenu")
    public void updateMenu(@WebParam(name = "m") Menu m) {
        if (null == m)
            throw new RuntimeException("Null update !");
        if (null == m.getDate())
            throw new RuntimeException("Unreferenced update !");
        this.deleteMenu(m); // Ouch ... An ugly shortcut (but simple)
        this.createMenu(m); // => should be improved in next releases
    }

    @WebMethod(operationName = "deleteMenu")
    public void deleteMenu(@WebParam(name = "m") Menu m) {
        if (null == m)
            throw new RuntimeException("Null delete !");
        if (null == m.getDate())
            throw new RuntimeException("Unreferenced delete !");
        String k = toSql(m.getDate());
        String sql = "DELETE FROM `menu` WHERE `date` = '" + k+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RuntimeException("SQLException: " + e.getMessage());
       }
    }
}
