<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

    <script>

        $( document ).ready(function()
        {
            $( '#artistSearchField' ).keyup(function(event)
            {
                if (event.keyCode == 13)
                {
                    $( '#artistSearchButton' ).click();
                }
            });

            $( '#albumSearchName' ).keyup(function(event)
            {
                if (event.keyCode == 13)
                {
                    $( '#albumSearchButton' ).click();
                }
            });

        });



        function goToSessionPage()
        {
            window.open('${pageContext.request.contextPath}/techPractice?action=sessionPractice&invalidate=false', "_self");
        }

        function closePopup()
        {
            $( '#steamApiResultsBox' ).removeClass('popup').addClass('hiddenDiv');
            location.reload();
        }

        function closePopupLastfm()
        {
            $( '#steamApiResultsBox' ).removeClass('popup').addClass('hiddenDiv');
            location.reload();
        }


    </script>

<div class="bread nav-wrapper hide-on-small-and-down">
    <div class="col s12">
        <a href="${pageContext.request.contextPath}/techPractice?action=form" class="breadcrumb">Tech Practice</a>
        <a href="${pageContext.request.contextPath}/techPractice?action=java" class="breadcrumb">Java</a>
    </div>
</div>
<div class="container">

    <br/>
    <h1>Java Programs/Practice</h1>


    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=sessionPracticePage';">
        Session Practice
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=restfulServicesPage';">
        Restful Services Practice
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=multithreadingPage';">
        Multithreading Practice
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=designPatternsPage';">
        Design Patterns
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=treeGame';">
        Binary Tree Game
    </button>


</div>

<jsp:include page="/_pageSections/footer.jsp" />
