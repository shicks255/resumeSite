<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="topArtists" type="java.util.List<com.steven.hicks.entities.TopArtistRecord>" scope="request"/>
<jsp:useBean id="timeOptions"   type="java.util.List<java.lang.String>" scope="request"/>
<jsp:useBean id="selectedTimePeriod" type="java.lang.String" scope="request"/>

<script>

    function getTopArtistsForTime()
    {
        showWaitingPopup("updating data...");
        var selectedTimePeriod = $( '#timePeriod' ).val();

        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/techPractice?action=topArtists&selectedTimeOption=" + selectedTimePeriod,
            success: function(data)
            {
                $( '#restResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                $( '#restResultsBoxPopup' ).html(data);
                hideWaitingPopup();
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                alert("error, status is " + textStatus + ", error is " + errorThrown);
            }
        });
    }

</script>


<button class="waves-effect waves-light btn" onclick="closeResultsPopup();">Close</button>

<select class="browser-default" onchange="getTopArtistsForTime();" required id="timePeriod" name="timePeriod">

    <c:forEach var="option" items="${timeOptions}">

        <option value="${option}" <c:if test="${option.equals(selectedTimePeriod)}">selected</c:if>>
            <c:out value="${option}"/>
        </option>

    </c:forEach>


</select>


<table border="1" class="striped">
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
            <td><fmt:formatNumber value="${record.playCount}" pattern="#,###"/></td>
        </tr>
        <c:set var="rowIndex" value="${rowIndex +1}"/>
    </c:forEach>
</table>
