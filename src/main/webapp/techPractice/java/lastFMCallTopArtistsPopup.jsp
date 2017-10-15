<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="topArtists" type="java.util.List<com.steven.hicks.entities.TopArtistRecord>" scope="request"/>

<button class="waves-effect waves-light btn" onclick="closeResultsPopup();">Close</button>

<table border="1">
    <tr>
        <td><b>Rank</b></td>
        <td><b>Artist</b></td>
        <td><b>Play Count</b></td>

    </tr>

    <c:set var="rowIndex" value="${0}"/>
    <c:forEach var="record" items="${topArtists}">
        <tr>
            <td><b><c:out value="${record.rank}"/></b></td>
            <td><c:out value="${record.musicArtist.artistName}"/></td>
            <td><c:out value="${record.playCount}"/></td>
        </tr>
        <c:set var="rowIndex" value="${rowIndex +1}"/>
    </c:forEach>
</table>
