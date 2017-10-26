<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<script>

    $( document ).ready(function()
    {

        //These two functions set an event listener, so if enter is pressed while in that text field, it will submit
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

    function getSteamApi()
    {
        $.post( '${pageContext.request.contextPath}/techPractice?action=steamApi&sort=id',
            function(data)
            {
                $( '#restResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                $( '#restResultsBoxPopup' ).html(data);
                $(window).resize();
            }
        );
    }

    function getLastFmAPI()
    {
        $.post( '${pageContext.request.contextPath}/techPractice?action=getMusicArtistsFromLast_FM',
            function(data)
            {
                $( '#restResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                $( '#restResultsBoxPopup' ).html(data);
                $(window).resize();
            }
        );
    }

    function searchArtist()
    {
        var searchTerms = $( '#artistSearchField' ).val();
        if (searchTerms.length === 0)
            showInfoMessage('No search terms were entered');
        else
        {
            $.post( '${pageContext.request.contextPath}/techPractice?&action=artistSearch&artistSearchField=' + searchTerms,
                function(data)
                {
                    $( '#restResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#restResultsBoxPopup' ).html(data);
                    $(window).resize();
                }
            );
        }
    }

    function searchAlbum()
    {
        var searchTerms = $( '#albumSearchName' ).val();
        if (searchTerms.length === 0)
            showInfoMessage('No search terms were entered');
        else
        {
            $.post( '${pageContext.request.contextPath}/techPractice?&action=albumSearch&albumSearchName=' + searchTerms,
                function(data)
                {
                    $( '#restResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#restResultsBoxPopup' ).html(data);
                    $(window).resize();
                }
            );
        }
    }

    function getLastFmTopArtists()
    {
        showWaitingPopup("receiving data...");
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/techPractice?action=topArtists",
            success: function(data)
            {
                hideWaitingPopup();
                $( '#restResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                $( '#restResultsBoxPopup' ).html(data);
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                alert("error, status is " + textStatus + ", error is " + errorThrown);
            }
        });
    }

    function getLastFmTopSongs()
    {
        showWaitingPopup("receiving data...");
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/techPractice?action=topSongs",
            success:function(data)
            {
                hideWaitingPopup();
                $( '#restResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                $( '#restResultsBoxPopup' ).html(data);
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                alert("error, status is " + textStatus + ", error is " + errorThrown);
            }
        });
    }

    function getLastFmTopAlbums()
    {
        showWaitingPopup("receiving data...");
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/techPractice?action=topAlbums",
            success:function(data)
            {
                hideWaitingPopup();
                $( '#restResultsBox' ).removeClass('hiddenDiv').addClass('popup');
                $( '#restResultsBoxPopup' ).html(data);
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                alert("error, status is " + textStatus + ", error is " + errorThrown);
            }
        });
    }

    function closeResultsPopup()
    {
        $( '.popup' ).each(function(i, obj){
            $( this ).removeClass('popup').addClass('hiddenDiv');
        });

        location.reload();
    }

</script>

<div class="container">

    <br/>
    <h1>Restful Services</h1>


    <button class="btn waves-effect waves-light" onclick='getSteamApi();' >List of All Steam Games</button>
    <br/><br/>

    <button class="btn waves-effect waves-light" onclick="getLastFmAPI();">Last FM Api</button>
    <br/><br/>

    <button class="btn waves-effect waves-light" onclick='getLastFmTopArtists();'>My Last FM Profile</button>
    <br/><br/>

    <h3>Last FM API</h3>
    <label for="artistSearchField">Search for an artist:</label>
    <input type="text" name="artistSearchField" id="artistSearchField"/>
    <button class="btn waves-effect waves-light" onclick="searchArtist();" type="submit" name="artistSearchButton" id="artistSearchButton">
        Submit
    </button>

    <br/><br/>

    <label for="albumSearchName">Search for an album:</label>
    <input type="text" name="albumSearchName" id="albumSearchName"/>
    <button class="btn waves-effect waves-light" onclick="searchAlbum();" type="submit" name="albumSearchButton" id="albumSearchButton">
        Submit
    </button>

    <div id="restResultsBox" class="hiddenDiv">
        <div id="restResultsBoxPopup" class="popupContent">
        </div>
    </div>

</div>

<jsp:include page="/_pageSections/footer.jsp" />
