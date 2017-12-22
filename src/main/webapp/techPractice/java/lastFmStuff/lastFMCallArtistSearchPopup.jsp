<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="musicArtist" type="java.util.List<com.steven.hicks.entities.MusicArtist>" scope="request"/>

<table border="1">
    <tr>
        <td><b>#</b></td>
        <td><b>Artist</b></td>
    </tr>

    <c:set var="rowIndex" value="${0}"/>
    <c:forEach var="mu" items="${musicArtist}">
        <tr>
            <td><b><c:out value="${rowIndex +1}"/></b></td>
            <td><c:out value="${mu.artistName}"/></td>
        </tr>
        <c:set var="rowIndex" value="${rowIndex +1}"/>
    </c:forEach>
</table>
