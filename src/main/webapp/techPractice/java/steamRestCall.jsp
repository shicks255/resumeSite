<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="allGameList" type="java.util.List<com.steven.hicks.entities.SteamGame>" scope="request"/>

    <script>

        function sortReverseIdOrder()
        {
            $.post( '${pageContext.request.contextPath}/techPractice?action=steamApi&sort=reverseId',
                    function(data)
                    {
//                        $( '#steamApiResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                        $( '#steamApiResultsPopup' ).html(data);
                        $(window).resize();
                    }
            );
        }

        function sortAlpha()
        {
            $.post( '${pageContext.request.contextPath}/techPractice?action=steamApi&sort=alpha',
                    function(data)
                    {
//                        $( '#steamApiResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                        $( '#steamApiResultsPopup' ).html(data);
                        $(window).resize();
                    }
            );
        }

        function sortReverseAlpha()
        {
            $.post( '${pageContext.request.contextPath}/techPractice?action=steamApi&sort=reverseAlpha',
                    function(data)
                    {
//                        $( '#steamApiResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                        $( '#steamApiResultsPopup' ).html(data);
                        $(window).resize();
                    }
            );
        }

    </script>

<button class="waves-effect waves-light btn" onclick="sortReverseIdOrder();">Reverse Order</button>
<button class="waves-effect waves-light btn" onclick="sortAlpha();">Alphabetical</button>
<button class="waves-effect waves-light btn" onclick="sortReverseAlpha();">Reverse Alphabetical</button>
<br/>
    <table border="1">
        <tr>
            <td>#</td>
            <td>Game ID</td>
            <td>Name</td>
        </tr>

        <c:set var="rowIndex" value="${0}"/>

        <c:forEach var="game" items="${allGameList}">
            <c:set var="rowIndex" value="${rowIndex + 1}"/>
            <tr>
                <td><c:out value="${rowIndex}"/></td>
                <td class="rightAlign"><c:out value="${game.appId}"/></td>
                <td><c:out value="${game.name}"/></td>
            </tr>
        </c:forEach>
    </table>
