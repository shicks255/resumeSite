<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="accessCount" type="java.lang.String" scope="request"/>



    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Session Practice</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-3.1.0.js"></script>

<script>
    function resetURL()
    {
        location.reload(true);
        window.open("${pageContext.request.contextPath}/techPractice?action=sessionInvalidate", "_self");
    }
</script>

<jsp:include page="/_pageSections/navBar.jsp"/>
<br/>

<div class="container">
    <h1>Session Tracking Practice</h1>

${cookie}
    <br/>

    Session:
${sessionScope.user}
    <br/>

    Request:
${pageContext.request}
    <br/>

    Response:
${pageContext.response}
    <br/>


<br/>
    <br/>
    You have accessed this page <c:out value="${accessCount}"/> time this session.
    Refresh the page to see this number increase.


<br/><br/>
    <%--Click <a onclick="resetURL();" href="${pageContext.request.contextPath}/techPractice?action=sessionPractice&invalidate=true">here</a> to clear out your session--%>
    Click
    <button class="waves-effect waves-light btn" onclick="resetURL()" >here</button>
    to clear out your session

</div>