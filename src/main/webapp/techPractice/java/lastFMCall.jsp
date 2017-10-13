<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="musicArtist" type="java.util.List<com.steven.hicks.entities.MusicArtist>" scope="request"/>

<button class="waves-effect waves-light btn" onclick="closeResultsPopup();">Close</button>

<table border="1">
    <tr>
        <td>#</td>
        <td>Artist</td>
    </tr>

    <c:set var="rowIndex" value="${0}"/>
    <c:forEach var="mu" items="${musicArtist}">
        <tr>
            <td><c:out value="${rowIndex +1}"/></td>
            <td><c:out value="${mu.artistName}"/></td>
        </tr>
        <c:set var="rowIndex" value="${rowIndex +1}"/>
    </c:forEach>
</table>
