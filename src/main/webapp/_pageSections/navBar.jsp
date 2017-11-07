<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/icons/ShicksLogo.ico" />
    <title>Steven M Hicks | Java Developer</title>

    <link href="${pageContext.request.contextPath}/CSS/mainStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/materialize.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/materializeOverrides.css" rel="stylesheet" type="text/css">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Steven M. Hicks - New Jersey based Website Developer and Computer Programmer, currelty using Java and Tomcat with various front end libraries...JQuery/Jquery UI, Materialize CSS, ajax & json,."/>
    <meta name="keywords" content="Steven Hicks, Steven M Hicks, Steven M. Hicks, shicks, shicks255, New Jersey, NJ, Hunterdon County, java, forge-tech, forge tech, web developer, software developer, computer programmer, programming"/>

    <script type="text/javascript" src=https://code.jquery.com/jquery-2.1.1.min.js></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/materialize.js"></script>

    <script>
        window.setInterval(function()
        {
            ajaxToGetTracks();
        }, 20000);

        $(document).ready(function()
        {

            doDropdown();
            doDropdownMobile();

            $(document.body).keyup(function(event)
            {
                if (event.keyCode == 27)
                {
                    $( '.popup' ).each(function(i, obj){
                        $( this ).removeClass('popup').addClass('hiddenDiv');
                    });
                }
            });
        });

        function closePopups()
        {
            $( '.popup' ).each(function(i, obj){
                $( this ).removeClass('popup').addClass('hiddenDiv');
            });
        }

        function doDropdown()
        {
            $('.dropdown-button').dropdown({
                inDuration: 300,
                outDuration: 225,
                constrainWidth: false, // Does not change width of dropdown to that of the activator
                hover: true, // Activate on hover
                gutter: 0, // Spacing from edge
                belowOrigin: true, // Displays dropdown below the button
                alignment: 'left', // Displays dropdown with edge aligned to the left of button
                stopPropagation: false // Stops event propagation
            })
        }

        function doDropdownMobile()
        {
            $('.dropdown-button-mobile').dropdown({
                inDuration: 300,
                outDuration: 225,
                constrainWidth: false, // Does not change width of dropdown to that of the activator
                hover: false, // Activate on hover
                gutter: 0, // Spacing from edge
                belowOrigin: true, // Displays dropdown below the button
                alignment: 'left', // Displays dropdown with edge aligned to the left of button
                stopPropagation: false // Stops event propagation
            });
        }

        function showWaitingPopup(text)
        {
            $( '#popup-processingAction' ).removeClass('hiddenDiv').addClass('popup').addClass('important');
            $( '#_processing_text' ).text(text);
        }

        function hideWaitingPopup()
        {
            $( '#popup-processingAction' ).removeClass('popup').addClass('hiddenDiv');
        }

        function showInfoMessage(infoText)
        {
            $( '#popup-infoMessage' ).removeClass('hiddenDiv').addClass('popup').addClass('important');
            $( '#_info_message_text_' ).text(infoText);
        }

        function hideInfoMessage()
        {
            $( '#popup-infoMessage' ).removeClass('popup').addClass('hiddenDiv');
        }

        function ajaxToGetTracks()
        {
            console.log("fuck me");
            $( '#recentTracksTable tbody' ).empty();
            $.getJSON('https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=shicks255&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=10&format=json',
                function(json)
                {
                    $.each(json.recenttracks.track, function(i, item)
                    {
                        if (item['@attr'])
                        {
                            $( '#recentTracksTable' ).find('tbody').append(
                                '<tr align="center" style="width:100%;">' +
                                '<td style="padding:0;"><img src="' + item.image[1]['#text'] + '" /></td>' +
                                '<td>' + item.artist['#text'] + ' - ' + item.name + '</td>' +
                                '<td style="text-align:right;"> <i class="material-icons right"><img src="${pageContext.request.contextPath}/icons/musicPlaying.gif"/></i>now playing </td>' +
                                '</tr>'
                            );
                        }
                        else
                        {
                            var date = new Date(item.date['#text']);
                            date.setHours(date.getHours() - 5);
                            $( '#recentTracksTable' ).find('tbody').append(
                                '<tr align="center" style="width:100%;">' +
                                '<td style="padding:0;"><img src="' + item.image[1]['#text'] + '" /></td>' +
                                '<td>' + item.artist['#text'] + ' - ' + item.name + '</td>' +
                                '<td style="text-align:right;">' + date.toLocaleString() + '</td>' +
                                '</tr>'
                            );
                        }
                    });
                });
        }

        function showRecentTracks()
        {
            showWaitingPopup("gathering data...");
            ajaxToGetTracks();
            $( '#recentTracksContainer' ).removeClass('hiddenDiv').addClass('popup');
            hideWaitingPopup();
        }

    </script>

</head>
<body>
    <nav>
        <div class="nav-wrapper cyan">
            <%--Icons--%>
            <div class="brand-logo right cyan" style="min-width: 115px;">
                <a target="_blank" style="cursor:pointer" onclick="showRecentTracks();" class="hide-on-med-and-down cyan"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/last-fm-3-24_smaller.png"/></i></a>
                <a target="_blank" href="https://www.linkedin.com/in/steven-hicks-03390093/" class="hide-on-med-and-down cyan"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/linkedin-box.png"/></i></a>
                <a target="_blank" href="https://www.facebook.com/steven.hix.9" class="hide-on-med-and-down cyan"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/facebook-box_white.png"/></i></a>
                <a target="_blank" href="https://stackoverflow.com/users/8138169/shicks255" class="hide-on-med-and-down cyan"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/stackoverflow_white.png"/></i></a>
                <a target="_blank" href="https://github.com/shicks255" class="hide-on-med-and-down cyan"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/github-circle_white.png"/></i></a>
                <a href="${pageContext.request.contextPath}/" class="hide-on-med-and-down cyan">STEVEN HICKS</a>
                <ul id="nav-mobile2" class="right hide-on-large-only cyan">
                    <li><a href="${pageContext.request.contextPath}/" class="dropdown-button-mobile" data-activates="dropdown5">S. HICKS</a></li>
                </ul>
            </div>

                <%--NavBar big screen--%>
            <ul id="nav" class="left hide-on-small-and-down cyan" style="min-width: 115px">
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a class="dropdown-button" href="${pageContext.request.contextPath}/academic?action=form"     data-activates="dropdown3">Education</a></li>
                <li><a class="dropdown-button" href="${pageContext.request.contextPath}/techPractice?action=form" data-activates="dropdown4">Tech Practice</a></li>
                <li><a href="${pageContext.request.contextPath}/pictures?action=form">Galleries</a></li>
            </ul>

                <%--NavBar small screen--%>
            <ul id="nav-mobile1" class="left hide-on-med-and-up cyan" style="min-width: 115px;">
                <li><a href="${pageContext.request.contextPath}/"><i class="material-icons">home</i></a></li>
                <li><a class="dropdown-button-mobile" href="${pageContext.request.contextPath}/academic?action=form" data-activates="dropdown1"><i class="material-icons">school</i></a></li>
                <li><a class="dropdown-button-mobile" href="${pageContext.request.contextPath}/techPractice?action=form" data-activates="dropdown2"><i class="material-icons">memory</i></a></li>
                <li><a href="${pageContext.request.contextPath}/pictures?action=form"><i class="material-icons">collections</i></a></li>
            </ul>

                <%--dropdown for small education--%>
                <ul id="dropdown1" class="dropdown-content cyan">
                    <li class="divider"></li>
                    <li><a class="white-text" href="${pageContext.request.contextPath}/academic?action=thesis">Thesis</a></li>
                    <li class="divider"></li>
                    <li><a class="white-text " href="${pageContext.request.contextPath}/academic?action=bibliography">Bibliography</a></li>
                </ul>
                <%--dropdown for normal size education--%>
                <ul id="dropdown3" class="dropdown-content cyan">
                    <li class="divider"></li>
                    <li>
                        <a class="white-text" href="${pageContext.request.contextPath}/academic?action=thesis">Thesis</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="white-text" href="${pageContext.request.contextPath}/academic?action=bibliography">Bibliography</a>
                    </li>
                </ul>

                <%--dropdown for small tech--%>
                <ul id="dropdown2" class="dropdown-content cyan">
                    <li class="divider"></li>
                    <li>
                        <a class="white-text" href="${pageContext.request.contextPath}/techPractice?action=java">Java</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="white-text" href="${pageContext.request.contextPath}/techPractice?action=javaScript">JavaScript</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="white-text" href="${pageContext.request.contextPath}/techPractice?action=css">CSS</a>
                    </li>
                </ul>

                <%--dropdown for normal size tech--%>
                <ul id="dropdown4" class="dropdown-content cyan">
                    <li class="divider"></li>
                    <li><a class="white-text" href="${pageContext.request.contextPath}/techPractice?action=java">Java</a></li>
                    <li class="divider"></li>
                    <li><a class="white-text" href="${pageContext.request.contextPath}/techPractice?action=javaScript">JavaScript</a></li>
                    <li class="divider"></li>
                    <li><a class="white-text" href="${pageContext.request.contextPath}/techPractice?action=css">CSS</a></li>
                </ul>

                <%--dropdown for small icon colum--%>
                <ul id="dropdown5" class="dropdown-content cyan right">
                    <li><a class="dropdown-button-mobile" onclick="showRecentTracks();"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/last-fm-3-24_smaller.png"/></i></a></li>
                    <li><a class="dropdown-button-mobile" target="_blank" href="https://www.linkedin.com/in/steven-hicks-03390093/"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/linkedin-box.png"/></i></a></li>
                    <li><a class="dropdown-button-mobile" target="_blank" href="https://www.facebook.com/steven.hix.9"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/facebook-box_white.png"/></i></a></li>
                    <li><a class="dropdown-button-mobile" target="_blank" href="https://stackoverflow.com/users/8138169/shicks255"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/stackoverflow_white.png"/></i></a></li>
                    <li><a class="dropdown-button-mobile" target="_blank" href="https://github.com/shicks255"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/github-circle_white.png"/></i></a></li>
                </ul>
        </div>
    </nav>


    <div id="popup-processingAction" class="hiddenDiv important">
        <div id="processingAction" class="popupContent">
            <p style="margin: 15pt;text-align: center">
                <img src="${pageContext.request.contextPath}/icons/waiting.gif" alt="One moment" /> <span id="_processing_text">Processing request...</span>
            </p>
        </div>
    </div>

    <div id="popup-infoMessage" class="hiddenDiv">
        <div id="infoMessage" class="popupContent important">
            <p style="margin :15pt;text-align: center">
                <span id="_info_message_text_">Info message</span>
            </p>
            <button class="btn waves-effect waves-light" onclick="hideInfoMessage();">Close</button>
        </div>
    </div>

    <div class="hiddenDiv" id="recentTracksContainer">
        <div id="recentTracks" class="popupContent">
            <button class="waves-effect waves-light btn" onclick="closePopups();">Close</button>
            <h4>Recently Listened To</h4>
            <table class="striped" id="recentTracksTable" align="center">
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
