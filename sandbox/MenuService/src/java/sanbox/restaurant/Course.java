package sanbox.restaurant;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
public class Course {
    
    private Integer id;
    private String kind;
    private String name;

    public Course(){}
    
    public Course(String k, String n) {
        this.setKind(k);
        this.setName(n);
    }

    public Course(Integer i, String k, String n) {
        this(k,n);
        this.setId(id);
    }

    public Course(DalResultSet tuple) {
        this(new Integer(tuple.getValue("id")), tuple.getValue("kind"),
                tuple.getValue("name"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
