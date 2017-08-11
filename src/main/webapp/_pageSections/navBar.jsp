<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/materialize.min.js"></script>

    <script>
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

    </script>

</head>
<body>
    <ul id="dropdown1" class="dropdown-content cyan">
        <li class="divider"></li>
        <li><a class="white-text" href="${pageContext.request.contextPath}/academic?action=thesis">Thesis</a></li>
        <li class="divider"></li>
        <li><a class="white-text " href="${pageContext.request.contextPath}/academic?action=bibliography">Bibliography</a></li>
    </ul>

    <ul id="dropdown2" class="dropdown-content cyan">
        <li class="divider"></li>
        <li><a class="white-text" href="${pageContext.request.contextPath}/techPractice?action=java">Java</a></li>
        <li class="divider"></li>
        <li><a class="white-text " href="${pageContext.request.contextPath}/techPractice?action=javaScript">JavaScript</a></li>
        <li class="divider"></li>
        <li><a class="white-text " href="${pageContext.request.contextPath}/techPractice?action=css">CSS</a></li>
    </ul>

    <ul id="dropdown4" class="dropdown-content cyan">
        <li class="divider"></li>
        <li><a class="white-text" href="${pageContext.request.contextPath}/techPractice?action=java">Java</a></li>
        <li class="divider"></li>
        <li><a class="white-text " href="${pageContext.request.contextPath}/techPractice?action=javaScript">JavaScript</a></li>
        <li class="divider"></li>
        <li><a class="white-text " href="${pageContext.request.contextPath}/techPractice?action=css">CSS</a></li>
    </ul>

    <ul id="dropdown3" class="dropdown-content cyan">
        <li class="divider"></li>
        <li><a class="white-text" href="${pageContext.request.contextPath}/academic?action=thesis">Thesis</a></li>
        <li class="divider"></li>
        <li><a class="white-text " href="${pageContext.request.contextPath}/academic?action=bibliography">Bibliography</a></li>
    </ul>

    <ul id="dropdown5" class="dropdown-content cyan right">
        <li><a target="_blank" href="https://www.linkedin.com/in/steven-hicks-03390093/"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/linkedin-box.png"/></i></a></li>
        <li><a target="_blank" href="https://www.facebook.com/steven.hix.9"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/facebook-box_white.png"/></i></a></li>
        <li><a target="_blank" href="https://stackoverflow.com/users/8138169/shicks255"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/stackoverflow_white.png"/></i></a></li>
        <li><a target="_blank" href="https://github.com/shicks255"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/github-circle_white.png"/></i></a></li>
    </ul>

    <nav>
        <div class="nav-wrapper cyan">
            <%--Icons--%>
            <div class="brand-logo right cyan" style="min-width: 115px;">
                <a target="_blank" href="https://www.linkedin.com/in/steven-hicks-03390093/" class="hide-on-med-and-down cyan"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/linkedin-box.png"/></i></a>
                <a target="_blank" href="https://www.facebook.com/steven.hix.9" class="hide-on-med-and-down cyan"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/facebook-box_white.png"/></i></a>
                <a target="_blank" href="https://stackoverflow.com/users/8138169/shicks255" class="hide-on-med-and-down cyan"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/stackoverflow_white.png"/></i></a>
                <a target="_blank" href="https://github.com/shicks255" class="hide-on-med-and-down cyan"><i class="material-icons"><img src="${pageContext.request.contextPath}/icons/github-circle_white.png"/></i></a>
                <a href="${pageContext.request.contextPath}/" class="hide-on-med-and-down cyan">STEVEN HICKS</a>
                <ul id="nav-mobile2" class="right hide-on-large-only cyan">
                    <li><a href="${pageContext.request.contextPath}/" class="dropdown-button cyan" data-activates="dropdown5">S. HICKS</a></li>
                </ul>
            </div>

                <%--NavBar big screen--%>
            <ul id="nav-mobile" class="left hide-on-small-and-down cyan" style="min-width: 115px">
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a class="dropdown-button" href="${pageContext.request.contextPath}/academic?action=form"     data-activates="dropdown3">Education<i class="material-icons right"></i></a></li>
                <li><a class="dropdown-button" href="${pageContext.request.contextPath}/techPractice?action=form" data-activates="dropdown4">Tech Practice<i class="material-icons right"></i></a></li>
                <li><a href="${pageContext.request.contextPath}/pictures?action=form">Galleries</a></li>
            </ul>

                <%--NavBar small screen--%>
            <ul id="nav-mobile1" class="left hide-on-med-and-up cyan" style="min-width: 115px;">
                <li><a href="${pageContext.request.contextPath}/"><i class="material-icons">home</i></a></li>
                <li><a class="dropdown-button-mobile" href="${pageContext.request.contextPath}/academic?action=form" data-activates="dropdown1"><i class="material-icons">school</i></a></li>
                <li><a class="dropdown-button-mobile" href="${pageContext.request.contextPath}/techPractice?action=form" data-activates="dropdown2"><i class="material-icons">memory</i></a></li>
                <li><a href="${pageContext.request.contextPath}/pictures?action=form"><i class="material-icons">collections</i></a></li>
            </ul>
        </div>
    </nav>


