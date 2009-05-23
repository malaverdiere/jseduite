package sanbox.restaurant;
import javax.jws.WebMethod;
import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;


@WebService()
public class CourseBusiness {

    @WebMethod(operationName="getAvailableCourseKinds")
    public String[] getAvailableCourseKinds() {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT DISTINCT `kind` FROM `course`;";
            return dal.extractScalarSet(sql, "kind");
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }
}
