<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="timeOptions"   type="java.util.List<java.lang.String>" scope="request"/>
<jsp:useBean id="selectedTimePeriod" type="java.lang.String" scope="request"/>
<jsp:useBean id="lookupCriteria" type="java.lang.String" scope="request"/>

<button class="waves-effect waves-light btn" onclick="closeResultsPopup();">Close</button>
<br/>

<h4>My Last.fm Profile Data</h4>

<br/>
<br/>
<br/>
<button class="waves-effect waves-light btn <c:if test="${lookupCriteria.equals('artist')}">pressed</c:if>" onclick="getLastFmTopArtists();">Artists</button>
<button class="waves-effect waves-light btn <c:if test="${lookupCriteria.equals('album')}">pressed</c:if>" onclick="getLastFmTopAlbums();">Albums</button>
<button class="waves-effect waves-light btn <c:if test="${lookupCriteria.equals('song')}">pressed</c:if>" onclick="getLastFmTopSongs();">Songs</button>
<br/>
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

<h5>
    <label for="timePeriod"><h5>Time period:</h5></label>
</h5>

<style>
    @media (min-width : 720px)
    {
        .changeSize
        {
            width : 20% ;
        }
    }
</style>

<select class="browser-default changeSize" onchange="${onChangeEvent}" required id="timePeriod" name="timePeriod">
    <c:forEach var="option" items="${timeOptions}">
        <option value="${option}" <c:if test="${option.equals(selectedTimePeriod)}">selected</c:if>>
            <c:out value="${option}"/>
        </option>
    </c:forEach>
</select>
