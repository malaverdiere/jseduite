package sanbox.restaurant;

import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.ArrayList;

@WebService()
public class CourseFinder {

    public Course[] findByKind(String kind) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `course` WHERE `kind`= '"+kind+"';";
            DalResultSet rSet = dal.extractDataSet(sql);
            ArrayList<Course> result = new ArrayList<Course>(rSet.size());
            for(int i = 0; i < rSet.size(); i++){
                result.add(new Course(rSet));
                rSet.next();
            }
            return result.toArray(new Course[result.size()]);
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    public Course[] findByName(String name) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `course` WHERE `name`= '"+name+"';";
            DalResultSet rSet = dal.extractDataSet(sql);
            ArrayList<Course> result = new ArrayList<Course>(rSet.size());
            for(int i = 0; i < rSet.size(); i++){
                result.add(new Course(rSet));
                rSet.next();
            }
            return result.toArray(new Course[result.size()]);
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }


}
