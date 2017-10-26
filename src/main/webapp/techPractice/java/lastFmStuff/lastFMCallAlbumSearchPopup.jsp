<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="albums" type="java.util.List<com.steven.hicks.entities.Album>" scope="request"/>

<button class="waves-effect waves-light btn" onclick="closeResultsPopup();">Close</button>

<table border="1">
    <tr>
        <td>#</td>
        <td>Album</td>
        <td>Artist</td>
    </tr>

    <c:set var="rowIndex" value="${0}"/>
    <c:forEach var="album" items="${albums}">
        <tr>
            <td><b><c:out value="${rowIndex +1}"/></b></td>
            <td><c:out value="${album.album}"/></td>
            <td><c:out value="${album.artist}"/></td>
        </tr>
        <c:set var="rowIndex" value="${rowIndex +1}"/>
    </c:forEach>
</table>
