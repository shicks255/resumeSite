<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<html>
<head>
    <title>Java Practice</title>
    <script>

        function getSteamApi()
        {
            $.post( '${pageContext.request.contextPath}/techPractice?action=steamApi&sort=id',
                function(data)
                {
                    $( '#steamApiResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#steamApiResultsPopup' ).html(data);
                    $(window).resize();
                }
            );
        }

        function getLastFmAPI()
        {
            $.post( '${pageContext.request.contextPath}/techPractice?action=getLastFmAPI&sort=id',
                function(data)
                {
                    $( '#steamApiResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#steamApiResultsPopup' ).html(data);
                    $(window).resize();
                }
            );
        }

        function goToSessionPage()
        {
            window.open('${pageContext.request.contextPath}/techPractice?action=sessionPractice', "_self");
        }

        function closePopup()
        {
            $( '#steamApiResultsBox' ).removeClass('popup').addClass('hiddenDiv');
        }


    </script>

</head>
<body>
<div class="container">

    <br/>
    <h1>Java Programs/Practice</h1>

    <button onclick='getSteamApi();' >Calling a Restful Service</button>
    <br/><br/>

    <button onclick="goToSessionPage();">Session Practice</button>

    <div id="steamApiResultsBox" class="hiddenDiv">
        <div id="steamApiResultsPopup" class="popupContent">
        </div>
    </div>
</div>

<jsp:include page="/_pageSections/footer.jsp" />
</body>
</html>
