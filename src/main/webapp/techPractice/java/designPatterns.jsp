<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<script>

    $( document ).ready(function()
    {
//        $( '#artistSearchField' ).keyup(function(event)
//        {
//            if (event.keyCode == 13)
//            {
//                $( '#artistSearchButton' ).click();
//            }
//        });
//
//        $( '#albumSearchName' ).keyup(function(event)
//        {
//            if (event.keyCode == 13)
//            {
//                $( '#albumSearchButton' ).click();
//            }
//        });

    });


</script>

<div class="container">

    <br/>
    <h1>Object Oriented Design Patterns</h1>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=restfulServicesPage';">
        Strategy Pattern
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=restfulServicesPage';">
        Decorator Pattern
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=restfulServicesPage';">
        Factory Pattern
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=restfulServicesPage';">
        Command Pattern
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=restfulServicesPage';">
        Adapter Pattern
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=restfulServicesPage';">
        Builder Pattern
    </button>
    <br/>


</div>

<jsp:include page="/_pageSections/footer.jsp" />
