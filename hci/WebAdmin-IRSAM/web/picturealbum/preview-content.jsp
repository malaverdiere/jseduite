<%--
    Document   : preview-content
    Created on : 24 août 2009
    Author     : Steve Colombié
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.PICTUREALBUM_PREVIEW}" /></h1>
    <h:outputLink value="./listing.jsf" title="#{bundle.PICTUREALBUM_BACK}" styleClass="backLink">
        <h:outputText value="#{bundle.PICTUREALBUM_BACK}"/>
    </h:outputLink>

    <h:dataTable value="#{PictureAlbumManagedBean.preview}" var="iterator">
        <h:column>
            <h:panelGroup styleClass="alignCenter">
                <h:graphicImage value="#{iterator.image[0]}" alt="#{iterator.image[0]}"/>
            </h:panelGroup>
        </h:column>
        <h:column>
            <h:panelGroup styleClass="alignCenter">
                <h:graphicImage value="#{iterator.image[1]}" alt="#{iterator.image[1]}"/>
            </h:panelGroup>
        </h:column>
        <h:column>
            <h:panelGroup styleClass="alignCenter">
                <h:graphicImage value="#{iterator.image[2]}" alt="#{iterator.image[2]}"/>
            </h:panelGroup>
        </h:column>
        <h:column>
            <h:panelGroup styleClass="alignCenter">
                <h:graphicImage value="#{iterator.image[3]}" alt="#{iterator.image[3]}"/>
            </h:panelGroup>
        </h:column>
        <h:column>
            <h:panelGroup styleClass="alignCenter">
                <h:graphicImage value="#{iterator.image[4]}" alt="#{iterator.image[4]}"/>
            </h:panelGroup>
        </h:column>
    </h:dataTable>

    <h:outputLink value="./listing.jsf" title="#{bundle.PICTUREALBUM_BACK}" styleClass="backLink">
        <h:outputText value="#{bundle.PICTUREALBUM_BACK}"/>
    </h:outputLink>
</f:subview>