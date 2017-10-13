<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="albums" type="java.util.List<com.steven.hicks.entities.Album>" scope="request"/>

<button class="waves-effect waves-light btn" onclick="closePopupLastfm();">Close</button>

<table border="1">
    <tr>
        <td>#</td>
        <td>Album</td>
        <td>Artist</td>
        <td>Year</td>
    </tr>

    <c:set var="rowIndex" value="${0}"/>
    <c:forEach var="album" items="${albums}">
        <tr>
            <td><c:out value="${rowIndex +1}"/></td>
            <td><c:out value="${album.album}"/></td>
            <td><c:out value="${album.artist}"/></td>
            <td><c:out value="${album.year}"/></td>
        </tr>
        <c:set var="rowIndex" value="${rowIndex +1}"/>
    </c:forEach>
</table>
