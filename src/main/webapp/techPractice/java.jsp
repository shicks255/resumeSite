<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 7/17/2016
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java Practice</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-3.1.0.js"></script>

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

        function closePopup()
        {
            $( '#steamApiResultsBox' ).removeClass('popup').addClass('hiddenDiv');
        }


    </script>

</head>
<body>
<jsp:include page="/_pageSections/header.jsp"/>
<jsp:include page="/_pageSections/navBar.jsp"/>
<jsp:include page="techPracticeNavBar.jsp"/>

<br/>
<h1>Java Programs/Practice</h1>

<button onclick='getSteamApi();' >Calling a Restful Service</button>

<div id="steamApiResultsBox" class="hiddenDiv">
    <div id="steamApiResultsPopup" class="popupContent">
    </div>
</div>

<jsp:include page="/_pageSections/footer.jsp" />
</body>
</html>
