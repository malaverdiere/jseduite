<%-- 
    Document   : listing-content
    Created on : 10 août 2009
    Author     : Steve Colombié
--%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="content">
    <h1><h:outputText value="#{bundle.SUMMONINGS_LISTING}" /></h1>
    <h:outputLink value="create.jsf" styleClass="create">
        <h:outputText value="#{bundle.SUMMONINGS_CREATE}"/>
    </h:outputLink>
    <h:dataTable value="#{SummoningManagedBean.summonings}" var="iterator" rowClasses="list-row-even,list-row-odd"
    headerClass="list-header" rows="#{DataTablePager.itemByPage}" first="#{DataTablePager.firstItem}">
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.SUMMONINGS_STUDENT}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByStudent}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByStudentDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.student}" styleClass="#{iterator.valid}Valid"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.SUMMONINGS_PROMO}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByPromo}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByPromoDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.promo.code}" styleClass="#{iterator.valid}Valid"/>
        </h:column>
         <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.SUMMONINGS_OWNER}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByOwner}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByOwnerDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.owner}" styleClass="#{iterator.valid}Valid"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.SUMMONINGS_DATE}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByDate}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByDateDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.date}"  styleClass="#{iterator.valid}Valid">
                <f:converter converterId="XMLGregorianCalendarConverter" />
            </h:outputText>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:form>
                    <h:panelGroup id="theader">
                        <h:outputText value="#{bundle.SUMMONINGS_LEVEL}"/>
                        <h:commandLink title="#{bundle.SORT_ASC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/up.png" alt="#{bundle.SORT_ASC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByLevel}" />
                        </h:commandLink>
                        <h:commandLink title="#{bundle.SORT_DESC}" action="#{SummoningManagedBean.sortBy}" styleClass="sort">
                            <h:graphicImage value="../resources/images/down.png" alt="#{bundle.SORT_DESC}"/>
                            <f:setPropertyActionListener target="#{SummoningManagedBean.sort}" value="#{SummoningSorter.sortByLevelDesc}" />
                        </h:commandLink>
                    </h:panelGroup>
                </h:form>
            </f:facet>
            <h:outputText value="#{iterator.level}"  styleClass="#{iterator.valid}Valid">
                <f:converter converterId="LevelConverter"/>
            </h:outputText>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{SummoningManagedBean.goUpdate}" title="#{bundle.UPDATE}" styleClass="update">
                    <f:setPropertyActionListener target="#{SummoningManagedBean.id}" value="#{iterator.id}" />
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <h:form>
                <h:commandLink action="#{SummoningManagedBean.delete}" title="#{bundle.DELETE}" styleClass="delete">
                    <f:setPropertyActionListener target="#{SummoningManagedBean.id}" value="#{iterator.id}" />
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
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{SummoningManagedBean.summoningsCard}" />
                </h:commandLink>
                <h:commandLink title="#{bundle.PAGE_LAST}" action="#{DataTablePager.lastPage}" styleClass="pager">
                    <h:graphicImage value="../resources/images/last.png" alt="#{bundle.PAGE_LAST}"/>
                    <f:setPropertyActionListener target="#{DataTablePager.listCard}" value="#{SummoningManagedBean.summoningsCard}" />
                </h:commandLink>
                <h:outputText value="#{bundle.FORM_ITEM_BY_PAGE} " />
                <h:inputText value="#{DataTablePager.itemByPage}" size="2"/>
            </h:form>
        </f:facet>
    </h:dataTable>
</f:subview>

