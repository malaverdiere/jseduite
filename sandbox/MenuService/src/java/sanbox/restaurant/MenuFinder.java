package sanbox.restaurant;

import java.util.Date;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

@WebService()
public class MenuFinder {

    @WebMethod(operationName = "findMenuByDate")
    public Menu findMenuByDate(@WebParam(name = "date") Date date) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String k = MenuCRUD.toSql(date);
            String sql = "SELECT * FROM `menu`, `course` WHERE";
            sql += "`date` = '" + k + "' AND `menu`.`course` = `course`.`name`;";
            DalResultSet rSet = dal.extractDataSet(sql);
            if (rSet.size() == 0)
                return null;
            Menu result = new Menu();
            result.setDate(date);
            ArrayList<Course> courses = new ArrayList<Course>(rSet.size());
            for(int i = 0; i < rSet.size(); i++){
                courses.add(new Course(rSet));
                rSet.next();
            }
            result.setCourses(courses.toArray(new Course[courses.size()]));
            return result;
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
