<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 9/24/2016
  Time: 12:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="session" type="javax.servlet.http.HttpSession" scope="session"/>--%>
<html>
<head>
    <title>Java Practice</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-3.1.0.js"></script>

</head>
<body>
<jsp:include page="/_pageSections/header.jsp"/>
<jsp:include page="/_pageSections/navBar.jsp"/>
<br/>

${cookie}

Session:
${pageContext.session}
<br/>

Request:
${pageContext.request}
<br/>

Response:
${pageContext.response}
<br/>

</body>
</html>
