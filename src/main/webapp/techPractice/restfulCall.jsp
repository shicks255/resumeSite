<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 7/26/2016
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="steamGameList" type="java.util.List<java.lang.String>" scope="request"/>

<html>
<head>
    <title>Restful Call</title>
</head>
<body>
<jsp:include page="/_pageSections/header.jsp"/>
<jsp:include page="/_pageSections/navBar.jsp"/>
<jsp:include page="techPracticeNavBar.jsp"/>

<br/>
<h1>Calling A Restful Service</h1>

<hr/>

<h4>Not Parsed...</h4>
<c:forEach var="game" items="${steamGameList}">
    <c:out value="${game}"/>
    <br/>
</c:forEach>

<h4>XML Parsing</h4>



<jsp:include page="/_pageSections/footer.jsp" />
</body>
</html>
