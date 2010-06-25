<%-- 
    Document   : listing-content
    Created on : 3 août 2009
    Author     : Steve Colombié
--%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.DEVICES_LISTING}" /></h1>
    <h:outputLink value="create.jsf" styleClass="create">
        <h:outputText value="#{bundle.DEVICES_CREATE}"/>
    </h:outputLink>
    <h:dataTable value="#{DeviceManagedBean.devices}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.DEVICES_NAME}" />
            </f:facet>
            <h:outputText value="#{iterator.name}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="#{bundle.DEVICES_LOCATION}" />
            </f:facet>
            <h:outputText value="#{iterator.location}"/>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{DeviceManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{DeviceManagedBean.name}" value="#{iterator.name}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{DeviceManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{DeviceManagedBean.name}" value="#{iterator.name}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <f:facet name="footer">
            <h:form>
                <h:commandLink title="#{bundle.PAGE_FIRST}" action="#{DataTablePager.firstPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/first.png" alt="#{bundle.PAGE_FIRST}"/>
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_PREV}" action="#{DataTablePager.prevPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/previous.png" alt="#{bundle.PAGE_PREV}"/>
                </h:commandLink>
                <h:outputText value=" #{DataTablePager.currentPage} " />
                <h:commandLink title="#{bundle.PAGE_NEXT}" action="#{DataTablePager.nextPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/next.png" alt="#{bundle.PAGE_NEXT}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{DeviceManagedBean.devicesCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{DeviceManagedBean.devicesCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>
    </h:dataTable>
</f:subview>

