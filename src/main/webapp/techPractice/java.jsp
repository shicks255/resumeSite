<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<html>
<head>
    <title>Java Practice</title>
    <script>

        function getSteamApi()
        {
            $.post( '${pageContext.request.contextPath}/techPractice?action=steamApi&sort=id',
                function(data)
                {
                    $( '#steamApiResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#steamApiResultsPopup' ).html(data);
                    $(window).resize();
                }
            );
        }

        function getLastFmAPI()
        {
            $.post( '${pageContext.request.contextPath}/techPractice?action=getMusicArtistsFromLast_FM',
                function(data)
                {
                    $( '#steamApiResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#steamApiResultsPopup' ).html(data);
                    $(window).resize();
                }
            );
        }

        function searchArtist()
        {
            var searchTerms = $( '#artistSearchField' ).val();
            console.log(searchTerms);

            $.post( '${pageContext.request.contextPath}/techPractice?&action=artistSearch&artistSearchField=' + searchTerms,
                function(data)
                {
                    $( '#steamApiResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#steamApiResultsPopup' ).html(data);
                    $(window).resize();
                }
            );
        }

        function searchAlbum()
        {
            var searchTerms = $( '#albumSearchName' ).val();
            console.log(searchTerms);

            $.post( '${pageContext.request.contextPath}/techPractice?&action=albumSearch&albumSearchName=' + searchTerms,
                function(data)
                {
                    $( '#steamApiResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#steamApiResultsPopup' ).html(data);
                    $(window).resize();
                }
            );
        }

        function goToSessionPage()
        {
            window.open('${pageContext.request.contextPath}/techPractice?action=sessionPractice', "_self");
        }

        function closePopup()
        {
            $( '#steamApiResultsBox' ).removeClass('popup').addClass('hiddenDiv');
        }

        function closePopupLastfm()
        {
            $( '#steamApiResultsBox' ).removeClass('popup').addClass('hiddenDiv');
        }


    </script>

</head>
<body>
<div class="container">

    <br/>
    <h1>Java Programs/Practice</h1>

    <button onclick='getSteamApi();' >Calling a Restful Service</button>
    <br/><br/>

    <button onclick="goToSessionPage();">Session Practice</button>
    <br/><br/>

    <button onclick="getLastFmAPI();">Last FM Api</button>
    <br/><br/>

    <label for="artistSearchField">Search for an artist:</label>
    <input type="text" name="artistSearchField" id="artistSearchField"/>
    <input type="button" onclick="searchArtist();" value="Submit" />
    <br/><br/>

    <label for="albumSearchName">Search for an album:</label>
    <input type="text" name="albumSearchName" id="albumSearchName"/>
    <input type="button" onclick="searchAlbum();" value="Submit" />

    <div id="steamApiResultsBox" class="hiddenDiv">
        <div id="steamApiResultsPopup" class="popupContent">
        </div>
    </div>

</div>

<jsp:include page="/_pageSections/footer.jsp" />
</body>
</html>
