<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

    <script>

        $( document ).ready(function()
        {
            $( '#artistSearchField' ).keyup(function(event)
            {
                if (event.keyCode == 13)
                {
                    $( '#artistSearchButton' ).click();
                }
            });

            $( '#albumSearchName' ).keyup(function(event)
            {
                if (event.keyCode == 13)
                {
                    $( '#albumSearchButton' ).click();
                }
            });

        });



        function goToSessionPage()
        {
            window.open('${pageContext.request.contextPath}/techPractice?action=sessionPractice&invalidate=false', "_self");
        }

        function closePopup()
        {
            $( '#steamApiResultsBox' ).removeClass('popup').addClass('hiddenDiv');
            location.reload();
        }

        function closePopupLastfm()
        {
            $( '#steamApiResultsBox' ).removeClass('popup').addClass('hiddenDiv');
            location.reload();
        }


    </script>

<div class="container">

    <br/>
    <h1>Java Programs/Practice</h1>


    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=sessionPracticePage';">
        Session Practice
    </button>
    <br/>

    <button class="btn waves-effect waves-light"
            onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=restfulServicesPage';">
        Restful Services Practice
    </button>
    <br/>

    <button class="btn waves-effect waves-light" onclick="">
        Multithreading Practice
    </button>
    <br/>

    <button class="btn waves-effect waves-light" onclick="">
        Design Patterns
    </button>
    <br/>





    <button class="btn waves-effect waves-light" onclick='getSteamApi();' >List of All Steam Games</button>
    <br/><br/>

    <button class="btn waves-effect waves-light" onclick="goToSessionPage();">Session Practice</button>
    <br/><br/>

    <button class="btn waves-effect waves-light" onclick="getLastFmAPI();">Last FM Api</button>
    <br/><br/>

    <h3>Last FM API</h3>
    <label for="artistSearchField">Search for an artist:</label>
    <input type="text" name="artistSearchField" id="artistSearchField"/>
    <%--<input type="button" id="artistSearchButton" onclick="searchArtist();" value="Submit" />--%>
    <button class="btn waves-effect waves-light" onclick="searchArtist();" type="submit" name="artistSearchButton" id="artistSearchButton">
        Submit
        <%--<i class="material-icons right">send</i>--%>
    </button>

    <br/><br/>

    <label for="albumSearchName">Search for an album:</label>
    <input type="text" name="albumSearchName" id="albumSearchName"/>
    <%--<input type="button" id="albumSearchButton" onclick="searchAlbum();" value="Submit" />--%>
    <button class="btn waves-effect waves-light" onclick="searchAlbum();" type="submit" name="albumSearchButton" id="albumSearchButton">
        Submit
        <%--<i class="material-icons right">send</i>--%>
    </button>

    <div id="steamApiResultsBox" class="hiddenDiv">
        <div id="steamApiResultsPopup" class="popupContent">
        </div>
    </div>

</div>

<jsp:include page="/_pageSections/footer.jsp" />
