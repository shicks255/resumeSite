<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 7/17/2016
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tech Practice</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">

</head>
<body>
<jsp:include page="_pageSections/header.jsp"/>
<jsp:include page="_pageSections/navBar.jsp"/>
<jsp:include page="/techPractice/techPracticeNavBar.jsp"/>

<h1>Tech Practice:</h1>

<jsp:include page="_pageSections/footer.jsp" />
</body>
</html>
