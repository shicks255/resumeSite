<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="timeOptions"   type="java.util.List<java.lang.String>" scope="request"/>
<jsp:useBean id="selectedTimePeriod" type="java.lang.String" scope="request"/>
<jsp:useBean id="lookupCriteria" type="java.lang.String" scope="request"/>

<button class="waves-effect waves-light btn" onclick="closeResultsPopup();">Close</button>
<br/>

<br/>
<br/>
<br/>
<button class="waves-effect waves-light btn <c:if test="${lookupCriteria.equals('artist')}">pressed</c:if>" onclick="getLastFmTopArtists();">Artists</button>
<button class="waves-effect waves-light btn <c:if test="${lookupCriteria.equals('album')}">pressed</c:if>" onclick="getLastFmTopAlbums();">Albums</button>
<button class="waves-effect waves-light btn <c:if test="${lookupCriteria.equals('song')}">pressed</c:if>" onclick="getLastFmTopSongs();">Songs</button>

<label for="timePeriod">Time period:</label>

<c:set var="onChangeEvent" value=""/>
<c:if test="${lookupCriteria.equals('artist')}">
    <c:set var="onChangeEvent" value="getTopArtistsForTime();"/>
</c:if>
<c:if test="${lookupCriteria.equals('album')}">
    <c:set var="onChangeEvent" value="getTopAlbumsForTime();"/>
</c:if>
<c:if test="${lookupCriteria.equals('song')}">
    <c:set var="onChangeEvent" value="getTopSongsForTime();"/>
</c:if>

<select class="browser-default" onchange="${onChangeEvent}" required id="timePeriod" name="timePeriod">

    <c:forEach var="option" items="${timeOptions}">
        <option value="${option}" <c:if test="${option.equals(selectedTimePeriod)}">selected</c:if>>
            <c:out value="${option}"/>
        </option>
    </c:forEach>
</select>
