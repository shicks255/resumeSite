<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 7/26/2016
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="allGameList" type="java.util.List<com.steven.hicks.entities.SteamGame>" scope="request"/>
<html>
<head>


</head>
<body>
<button onclick="closePopup();">Close</button>
<br/>
    <table border="1">
        <tr>
            <td>#</td>
            <td>Game ID</td>
            <td>Name</td>
        </tr>

        <c:set var="rowIndex" value="${0}"/>

        <c:forEach var="game" items="${allGameList}">
            <c:set var="rowIndex" value="${rowIndex + 1}"/>
            <tr>
                <td><c:out value="${rowIndex}"/></td>
                <td class="rightAlign"><c:out value="${game.appId}"/></td>
                <td><c:out value="${game.name}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
