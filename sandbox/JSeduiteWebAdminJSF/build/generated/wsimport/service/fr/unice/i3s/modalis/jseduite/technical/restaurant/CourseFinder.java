
package fr.unice.i3s.modalis.jseduite.technical.restaurant;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3.1-hudson-749-SNAPSHOT
 * Generated source version: 2.1
 * 
 */
@WebService(name = "CourseFinder", targetNamespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CourseFinder {


    /**
     * 
     * @param kind
     * @return
     *     returns java.util.List<fr.unice.i3s.modalis.jseduite.technical.restaurant.Course>
     * @throws RestaurantException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findCoursesByKind", targetNamespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.restaurant.FindCoursesByKind")
    @ResponseWrapper(localName = "findCoursesByKindResponse", targetNamespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.restaurant.FindCoursesByKindResponse")
    public List<Course> findCoursesByKind(
        @WebParam(name = "kind", targetNamespace = "")
        String kind)
        throws RestaurantException_Exception
    ;

    /**
     * 
     * @param name
     * @return
     *     returns fr.unice.i3s.modalis.jseduite.technical.restaurant.Course
     * @throws RestaurantException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findCourseByName", targetNamespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.restaurant.FindCourseByName")
    @ResponseWrapper(localName = "findCourseByNameResponse", targetNamespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.restaurant.FindCourseByNameResponse")
    public Course findCourseByName(
        @WebParam(name = "name", targetNamespace = "")
        String name)
        throws RestaurantException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     * @throws RestaurantException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllCoursesReferences", targetNamespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.restaurant.GetAllCoursesReferences")
    @ResponseWrapper(localName = "getAllCoursesReferencesResponse", targetNamespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.restaurant.GetAllCoursesReferencesResponse")
    public List<String> getAllCoursesReferences()
        throws RestaurantException_Exception
    ;

}