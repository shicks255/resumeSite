<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="musicArtist" type="java.util.List<com.steven.hicks.entities.MusicArtist>" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

Music Guy

<table border="1">
    <tr>
        <td>#</td>
        <td>Game ID</td>
        <td>Name</td>
    </tr>

    <c:set var="rowIndex" value="${0}"/>
    <c:forEach var="mu" items="${musicArtist}">
        <tr>
            <td><c:out value="${mu.artistName}"/></td>
        </tr>
    </c:forEach>

    <%--<c:forEach var="artist" items="${musicArtist}">--%>
        <%--<c:set var="rowIndex" value="${rowIndex + 1}"/>--%>
        <%--<tr>--%>
            <%--<td><c:out value="${rowIndex}"/></td>--%>
            <%--<td><c:out value="${musicArtist.size()}"/></td>--%>
            <%--<td class="rightAlign"><c:out value="${artist.lastName}"/></td>--%>
            <%--<td><c:out value="${artist.artistName}"/></td>--%>
        <%--</tr>--%>
</table>


</body>
</html>
