<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="timeOptions"   type="java.util.List<java.lang.String>" scope="request"/>
<jsp:useBean id="selectedTimePeriod" type="java.lang.String" scope="request"/>
<jsp:useBean id="resultPages" type="java.util.List<com.steven.hicks.ResultsPage>"  scope="request"/>
<jsp:useBean id="selectedPage" type="com.steven.hicks.ResultsPage"  scope="request"/>

<script>
    var selectedTimePeriod = $( '#timePeriod' ).val();
    function getTopAlbumsForTime()
    {
        showWaitingPopup("updating data...");
        selectedTimePeriod = $( '#timePeriod' ).val();
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/techPractice?action=topAlbums&selectedTimeOption=" + selectedTimePeriod,
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

    function changePage(pageNumber)
    {
        if (pageNumber > 0 && pageNumber <= ${resultPages.size()})
        {
            showWaitingPopup("updating data...");
            selectedTimePeriod = $( '#timePeriod' ).val();
            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/techPractice?action=topAlbums&selectedTimeOption=" + selectedTimePeriod + "&pageNumber=" + pageNumber,
                success: function(data)
                {
                    $( '#restResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#restResultsBoxPopup' ).html(data);
                    hideWaitingPopup();
                },
                error: function(jqXHR, textStatus, errorThrown)
                {
                    alert("error, status is " + text + ", error is " + errorThrown);
                }
            });
        }
    }

</script>

<jsp:include page="lastFMTopRecordsNavBar.jsp"/>

<table border="1" class="striped">
    <tr>
        <td><b>Rank</b></td>
        <td></td>
        <td><b>Album</b></td>
        <td><b>Artist</b></td>
        <td><b>Play Count</b></td>
    </tr>

    <c:set var="rowIndex" value="${0}"/>
    <c:forEach var="result" items="${selectedPage.results}">
        <tr>
            <td><b><c:out value="${result.rank}"/></b></td>
            <td><img alt="albumImg" src="${result.album.medImageUrl}"/></td>
            <td><c:out value="${result.album.album}"/></td>
            <td><c:out value="${result.album.artist}"/></td>
            <td><fmt:formatNumber value="${result.playCount}" pattern="#,###"/></td>
        </tr>
    </c:forEach>

</table>

<ul class="pagination">
    <%--Back arrow--%>
    <c:set var="backArrowClass" value="waves-effect"/>
    <c:if test="${resultPages.indexOf(selectedPage) == 0}">
        <c:set var="backArrowClass" value="disabled"/>
    </c:if>
    <li class="${backArrowClass}"><a onclick="changePage('${selectedPage.pageNumber -1}')"><i class="material-icons">chevron_left</i></a></li>

    <c:forEach var="page" items="${resultPages}">
        <c:set var="pageClass" value="waves-effect"/>
        <c:if test="${resultPages.indexOf(page) == resultPages.indexOf(selectedPage)}">
            <c:set var="pageClass" value="active"/>
        </c:if>
        <li class="${pageClass}"><a onclick="changePage('${resultPages.indexOf(page)+1}');"><c:out value="${resultPages.indexOf(page) + 1}"/> </a></li>
    </c:forEach>

    <%--Forward arrow--%>
    <c:set var="forwardArrowClass" value="waves-effect"/>
    <c:if test="${resultPages.indexOf(selectedPage) == resultPages.size() -1}">
        <c:set var="forwardArrowClass" value="disabled"/>
    </c:if>
    <li class="${forwardArrowClass}"><a onclick="changePage('${selectedPage.pageNumber + 1}');"><i class="material-icons">chevron_right</i></a></li>

</ul>
