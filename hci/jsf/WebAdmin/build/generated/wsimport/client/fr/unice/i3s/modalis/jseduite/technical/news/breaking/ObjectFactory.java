
package fr.unice.i3s.modalis.jseduite.technical.news.breaking;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.i3s.modalis.jseduite.technical.news.breaking package. 
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

    private final static QName _UpdateBreakingNews_QNAME = new QName("http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", "updateBreakingNews");
    private final static QName _DeleteBreakingNews_QNAME = new QName("http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", "deleteBreakingNews");
    private final static QName _ReadBreakingNews_QNAME = new QName("http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", "readBreakingNews");
    private final static QName _UpdateBreakingNewsResponse_QNAME = new QName("http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", "updateBreakingNewsResponse");
    private final static QName _CreateBreakingNewsResponse_QNAME = new QName("http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", "createBreakingNewsResponse");
    private final static QName _CreateBreakingNews_QNAME = new QName("http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", "createBreakingNews");
    private final static QName _BreakingNewsException_QNAME = new QName("http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", "BreakingNewsException");
    private final static QName _DeleteBreakingNewsResponse_QNAME = new QName("http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", "deleteBreakingNewsResponse");
    private final static QName _ReadBreakingNewsResponse_QNAME = new QName("http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", "readBreakingNewsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.i3s.modalis.jseduite.technical.news.breaking
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateBreakingNewsResponse }
     * 
     */
    public UpdateBreakingNewsResponse createUpdateBreakingNewsResponse() {
        return new UpdateBreakingNewsResponse();
    }

    /**
     * Create an instance of {@link ReadBreakingNews }
     * 
     */
    public ReadBreakingNews createReadBreakingNews() {
        return new ReadBreakingNews();
    }

    /**
     * Create an instance of {@link DeleteBreakingNewsResponse }
     * 
     */
    public DeleteBreakingNewsResponse createDeleteBreakingNewsResponse() {
        return new DeleteBreakingNewsResponse();
    }

    /**
     * Create an instance of {@link DeleteBreakingNews }
     * 
     */
    public DeleteBreakingNews createDeleteBreakingNews() {
        return new DeleteBreakingNews();
    }

    /**
     * Create an instance of {@link ReadBreakingNewsResponse }
     * 
     */
    public ReadBreakingNewsResponse createReadBreakingNewsResponse() {
        return new ReadBreakingNewsResponse();
    }

    /**
     * Create an instance of {@link BreakNew }
     * 
     */
    public BreakNew createBreakNew() {
        return new BreakNew();
    }

    /**
     * Create an instance of {@link UpdateBreakingNews }
     * 
     */
    public UpdateBreakingNews createUpdateBreakingNews() {
        return new UpdateBreakingNews();
    }

    /**
     * Create an instance of {@link BreakingNewsException }
     * 
     */
    public BreakingNewsException createBreakingNewsException() {
        return new BreakingNewsException();
    }

    /**
     * Create an instance of {@link CreateBreakingNews }
     * 
     */
    public CreateBreakingNews createCreateBreakingNews() {
        return new CreateBreakingNews();
    }

    /**
     * Create an instance of {@link CreateBreakingNewsResponse }
     * 
     */
    public CreateBreakingNewsResponse createCreateBreakingNewsResponse() {
        return new CreateBreakingNewsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBreakingNews }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", name = "updateBreakingNews")
    public JAXBElement<UpdateBreakingNews> createUpdateBreakingNews(UpdateBreakingNews value) {
        return new JAXBElement<UpdateBreakingNews>(_UpdateBreakingNews_QNAME, UpdateBreakingNews.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteBreakingNews }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", name = "deleteBreakingNews")
    public JAXBElement<DeleteBreakingNews> createDeleteBreakingNews(DeleteBreakingNews value) {
        return new JAXBElement<DeleteBreakingNews>(_DeleteBreakingNews_QNAME, DeleteBreakingNews.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadBreakingNews }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", name = "readBreakingNews")
    public JAXBElement<ReadBreakingNews> createReadBreakingNews(ReadBreakingNews value) {
        return new JAXBElement<ReadBreakingNews>(_ReadBreakingNews_QNAME, ReadBreakingNews.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBreakingNewsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", name = "updateBreakingNewsResponse")
    public JAXBElement<UpdateBreakingNewsResponse> createUpdateBreakingNewsResponse(UpdateBreakingNewsResponse value) {
        return new JAXBElement<UpdateBreakingNewsResponse>(_UpdateBreakingNewsResponse_QNAME, UpdateBreakingNewsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBreakingNewsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", name = "createBreakingNewsResponse")
    public JAXBElement<CreateBreakingNewsResponse> createCreateBreakingNewsResponse(CreateBreakingNewsResponse value) {
        return new JAXBElement<CreateBreakingNewsResponse>(_CreateBreakingNewsResponse_QNAME, CreateBreakingNewsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBreakingNews }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", name = "createBreakingNews")
    public JAXBElement<CreateBreakingNews> createCreateBreakingNews(CreateBreakingNews value) {
        return new JAXBElement<CreateBreakingNews>(_CreateBreakingNews_QNAME, CreateBreakingNews.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BreakingNewsException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", name = "BreakingNewsException")
    public JAXBElement<BreakingNewsException> createBreakingNewsException(BreakingNewsException value) {
        return new JAXBElement<BreakingNewsException>(_BreakingNewsException_QNAME, BreakingNewsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteBreakingNewsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", name = "deleteBreakingNewsResponse")
    public JAXBElement<DeleteBreakingNewsResponse> createDeleteBreakingNewsResponse(DeleteBreakingNewsResponse value) {
        return new JAXBElement<DeleteBreakingNewsResponse>(_DeleteBreakingNewsResponse_QNAME, DeleteBreakingNewsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadBreakingNewsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/", name = "readBreakingNewsResponse")
    public JAXBElement<ReadBreakingNewsResponse> createReadBreakingNewsResponse(ReadBreakingNewsResponse value) {
        return new JAXBElement<ReadBreakingNewsResponse>(_ReadBreakingNewsResponse_QNAME, ReadBreakingNewsResponse.class, null, value);
    }

}
