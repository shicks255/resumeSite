<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 6/18/2016
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Education</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">

</head>
<body>
<jsp:include page="_pageSections/header.jsp"/>
<jsp:include page="_pageSections/navBar.jsp"/>

    <a href="${pageContext.request.contextPath}/academic?">Thesis</a>
    <a href="${pageContext.request.contextPath}/academic?">Bibliography</a>

<h1>Course History:</h1>

<jsp:include page="_pageSections/footer.jsp" />
</body>
</html>
