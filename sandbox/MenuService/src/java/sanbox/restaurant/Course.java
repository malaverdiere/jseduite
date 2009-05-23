package sanbox.restaurant;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
public class Course {
    
    private String kind;
    private String name;

    public Course(){}
    
    public Course(String k, String n) {
        this.setKind(k);
        this.setName(n);
    }

    public Course(DalResultSet tuple) {
        this(tuple.getValue("kind"), tuple.getValue("name"));
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
