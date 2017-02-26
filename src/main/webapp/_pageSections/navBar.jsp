<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">

    <link href="${pageContext.request.contextPath}/CSS/mainStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/materialize.min.css" rel="stylesheet" type="text/css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <script type="text/javascript" src=https://code.jquery.com/jquery-2.1.1.min.js></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-3.1.0.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/materialize.min.js"></script>

    <script>

        $(document).ready(function() {
            $('.dropdown-button').dropdown({
                inDuration: 300,
                outDuration: 225,
                constrainWidth: true, // Does not change width of dropdown to that of the activator
                hover: true, // Activate on hover
                gutter: 0, // Spacing from edge
                belowOrigin: true, // Displays dropdown below the button
                alignment: 'left', // Displays dropdown with edge aligned to the left of button
                stopPropagation: false // Stops event propagation
            });
        });
    </script>

</head>
<body>

    <ul id="dropdown1" class="dropdown-content cyan">
        <li class="divider"></li>
        <li><a class="white-text" href="${pageContext.request.contextPath}/academic?&action=thesis">Thesis</a></li>
        <li class="divider"></li>
        <li><a class="white-text " href="${pageContext.request.contextPath}/academic?&action=bibliography">Bibliography</a></li>
    </ul>

    <ul id="dropdown2" class="dropdown-content cyan">
        <li class="divider"></li>
        <li><a class="white-text" href="${pageContext.request.contextPath}/techPractice?&action=java">Java</a></li>
        <li class="divider"></li>
        <li><a class="white-text " href="${pageContext.request.contextPath}/techPractice?&action=javaScript">JavaScript</a></li>
        <li class="divider"></li>
        <li><a class="white-text " href="${pageContext.request.contextPath}/techPractice?&action=css">CSS</a></li>
    </ul>
    <nav>
        <div class="nav-wrapper cyan">
            <a href="#!" class="brand-logo right cyan">STEVEN HICKS</a>
            <ul id="nav-mobile" class="left hide-on-small-and-down cyan">
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a class="dropdown-button" href="${pageContext.request.contextPath}/academic?action=form"     data-activates="dropdown1">Education<i class="material-icons right"></i></a></li>
                <li><a class="dropdown-button" href="${pageContext.request.contextPath}/techPractice?action=form" data-activates="dropdown2">Tech Practice<i class="material-icons right"></i></a></li>
                <li><a href="${pageContext.request.contextPath}/pictures?action=form">Galleries</a></li>
            </ul>
        </div>
    </nav>
