<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 7/17/2016
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java Practice</title>
</head>
<body>
<jsp:include page="/_pageSections/header.jsp"/>
<jsp:include page="/_pageSections/navBar.jsp"/>
<jsp:include page="techPracticeNavBar.jsp"/>

<br/>
<h1>Java Programs/Practice</h1>

<a href="${pageContext.request.contextPath}/techPractice?action=steamApi">Calling A Restful Service</a>

<jsp:include page="/_pageSections/footer.jsp" />
</body>
</html>
