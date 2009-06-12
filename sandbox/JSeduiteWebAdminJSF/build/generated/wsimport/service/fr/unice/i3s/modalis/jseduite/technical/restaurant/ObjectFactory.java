
package fr.unice.i3s.modalis.jseduite.technical.restaurant;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.i3s.modalis.jseduite.technical.restaurant package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RestaurantException_QNAME = new QName("http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", "RestaurantException");
    private final static QName _FindCourseByName_QNAME = new QName("http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", "findCourseByName");
    private final static QName _FindCourseByNameResponse_QNAME = new QName("http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", "findCourseByNameResponse");
    private final static QName _GetAllCoursesReferencesResponse_QNAME = new QName("http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", "getAllCoursesReferencesResponse");
    private final static QName _GetAllCoursesReferences_QNAME = new QName("http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", "getAllCoursesReferences");
    private final static QName _FindCoursesByKind_QNAME = new QName("http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", "findCoursesByKind");
    private final static QName _FindCoursesByKindResponse_QNAME = new QName("http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", "findCoursesByKindResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.i3s.modalis.jseduite.technical.restaurant
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindCourseByNameResponse }
     * 
     */
    public FindCourseByNameResponse createFindCourseByNameResponse() {
        return new FindCourseByNameResponse();
    }

    /**
     * Create an instance of {@link GetAllCoursesReferencesResponse }
     * 
     */
    public GetAllCoursesReferencesResponse createGetAllCoursesReferencesResponse() {
        return new GetAllCoursesReferencesResponse();
    }

    /**
     * Create an instance of {@link RestaurantException }
     * 
     */
    public RestaurantException createRestaurantException() {
        return new RestaurantException();
    }

    /**
     * Create an instance of {@link Course }
     * 
     */
    public Course createCourse() {
        return new Course();
    }

    /**
     * Create an instance of {@link FindCoursesByKindResponse }
     * 
     */
    public FindCoursesByKindResponse createFindCoursesByKindResponse() {
        return new FindCoursesByKindResponse();
    }

    /**
     * Create an instance of {@link FindCourseByName }
     * 
     */
    public FindCourseByName createFindCourseByName() {
        return new FindCourseByName();
    }

    /**
     * Create an instance of {@link FindCoursesByKind }
     * 
     */
    public FindCoursesByKind createFindCoursesByKind() {
        return new FindCoursesByKind();
    }

    /**
     * Create an instance of {@link GetAllCoursesReferences }
     * 
     */
    public GetAllCoursesReferences createGetAllCoursesReferences() {
        return new GetAllCoursesReferences();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestaurantException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", name = "RestaurantException")
    public JAXBElement<RestaurantException> createRestaurantException(RestaurantException value) {
        return new JAXBElement<RestaurantException>(_RestaurantException_QNAME, RestaurantException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCourseByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", name = "findCourseByName")
    public JAXBElement<FindCourseByName> createFindCourseByName(FindCourseByName value) {
        return new JAXBElement<FindCourseByName>(_FindCourseByName_QNAME, FindCourseByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCourseByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", name = "findCourseByNameResponse")
    public JAXBElement<FindCourseByNameResponse> createFindCourseByNameResponse(FindCourseByNameResponse value) {
        return new JAXBElement<FindCourseByNameResponse>(_FindCourseByNameResponse_QNAME, FindCourseByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCoursesReferencesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", name = "getAllCoursesReferencesResponse")
    public JAXBElement<GetAllCoursesReferencesResponse> createGetAllCoursesReferencesResponse(GetAllCoursesReferencesResponse value) {
        return new JAXBElement<GetAllCoursesReferencesResponse>(_GetAllCoursesReferencesResponse_QNAME, GetAllCoursesReferencesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCoursesReferences }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", name = "getAllCoursesReferences")
    public JAXBElement<GetAllCoursesReferences> createGetAllCoursesReferences(GetAllCoursesReferences value) {
        return new JAXBElement<GetAllCoursesReferences>(_GetAllCoursesReferences_QNAME, GetAllCoursesReferences.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCoursesByKind }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", name = "findCoursesByKind")
    public JAXBElement<FindCoursesByKind> createFindCoursesByKind(FindCoursesByKind value) {
        return new JAXBElement<FindCoursesByKind>(_FindCoursesByKind_QNAME, FindCoursesByKind.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCoursesByKindResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/", name = "findCoursesByKindResponse")
    public JAXBElement<FindCoursesByKindResponse> createFindCoursesByKindResponse(FindCoursesByKindResponse value) {
        return new JAXBElement<FindCoursesByKindResponse>(_FindCoursesByKindResponse_QNAME, FindCoursesByKindResponse.class, null, value);
    }

}
