<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="user" type="com.steven.hicks.entities.User" scope="session"/>
<%--<jsp:useBean id="allItems" type="java.util.List<com.steven.hicks.entities.StoreItemGeneric>" scope="session"/>--%>
<%--<jsp:useBean id="cart"  type="com.steven.hicks.entities.store.Cart" scope="session"/>--%>

<!DOCTYPE html>
<html>
<head>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/icons/ShicksLogo.ico" />
    <title>Steven M Hicks | Java Developer</title>

    <link href="${pageContext.request.contextPath}/CSS/materialize.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/mainStyle.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src=https://code.jquery.com/jquery-2.1.1.min.js></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/materialize.min.js"></script>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Steven M. Hicks - New Jersey based Website Developer and Computer Programmer, currelty using Java and Tomcat with various front end libraries...JQuery/Jquery UI, Materialize CSS, ajax & json,."/>
    <meta name="keywords" content="Steven Hicks, Steven M Hicks, Steven M. Hicks, shicks, shicks255, New Jersey, NJ, Hunterdon County, java, forge-tech, forge tech, web developer, software developer, computer programmer, programming"/>

    <script>
        var searchTerms = '';
        $(document).ready(function()
        {
            $(function()
            {
                $.ajax({
                    type: 'GET',
                    url: '${pageContext.request.contextPath}/portalItemHandler?action=getAllItemsJSON',
                    success: function(response)
                    {
                        response = JSON.parse(response);
                        var dataArray = response;
                        var data = {};
                        for (var i = 0; i < dataArray.length; i++)
                        {

                            var key = Object.keys(dataArray[i]);
                            var value = dataArray[i][key];

                            console.log(value);
                            var cleanKey = key.toString().replace(/'/g, '&apos;');
//                            var cleanKey = key.toString().replace(/([^a-z0-9 ._-]+)/gi, '');
                            $( '#items' ).append("<option value='" + cleanKey + "'>");

                            data[key] = '${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=' + value;
                        }
                    }
                });
            });


            $(".button-collapse").sideNav();

            $(document.body).keyup(function(event)
            {
                if (event.keyCode == 27)
                {
                    $( '.popup' ).each(function(i, obj){
                        $( this ).removeClass('popup').addClass('hiddenDiv');
                    });
                }
            });

            $( '#search' ).keyup(function(e)
            {
                if (e.keyCode == 13)
                {
                    doSearch();
                }
            })
        });

        function doSearch()
        {
            searchTerms = $( '#search' ).val();
            window.open("${pageContext.request.contextPath}/portalItemHandler?action=searchForItems&searchTerms=" + searchTerms, "_self");
        }

        function dialogEditProfile()
        {
            $( '#editProfileDialog' ).removeClass('hiddenDiv').addClass('popup');
        }

        function closeEditProfile()
        {
            $( '#editProfileDialog' ).removeClass('popup').addClass('hiddenDiv');
        }

    </script>

</head>
<body>

<datalist id="items">
</datalist>

<style>
    input::-webkit-calendar-picker-indicator {
        display: none;
}

    nav ul {
        /*display :absolute;*/
        width: 100%;
        position : absolute;
        /*flex-direction:row;*/
        /*justify-content : space-between;*/
        padding : 0;
        margin : 0;
    }

    nav ul li {
        display : block;
        position: relative;
        list-style : none;
        text-align: center;
    }
    #menuButton{
    }
    #menuButton i {
    }
    #portalNavTitle {
    }
    #searchBar {
        width : 35%;
        left : 5%;
    }
    #searchIcon {
        left : 2.5%;
    }
    #cartIcon {
        position : absolute;
        right : 4.5%;
        width : 7.5%;
    }
    #test {
        /*display:inline-block;*/
        position : absolute;
        top : 0;
        right : 1%;
    }

</style>

<nav>
    <div class="nav-wrapper cyan">
        <ul>
            <li id="menuButton">
                <a href=""  data-activates="slide-out" class="button-collapse show-on-large"><i class="material-icons">menu</i></a>
            </li>
            <li id="portalNavTitle">
                <a href="${pageContext.request.contextPath}/portal?action=form"> Portal Search</a>
            </li>
            <li id="searchBar">
                <input type="text" id="search" name="search" list="items" placeholder="Search for an item">
            </li>
            <li id="searchIcon">
                <a href="#" onclick="doSearch();" ><i class="material-icons">search</i></a>
            </li>
            <li id="cartIcon">
                <a href="${pageContext.request.contextPath}/portal?action=portalCart"> <i class="material-icons">shopping_cart</i></a>
                <div class="hide-on-small-and-down" id="test">(<c:out value="${sessionScope.user.userCart.itemsInCart.size()}"/>)</div>
            </li>
        </ul>
    </div>

    <div class="nav-wrapper-secondary cyan">

    </div>
</nav>



<ul id="slide-out" class="side-nav">
    <li>
        <div class="userView">
            <div class="background">
                <img src="${pageContext.request.contextPath}/backgrounds/cartoon-background-with-stars_1110-892.jpg">
            </div>
            <span class="white-text name">Your Profile<a style="display: inline-block;color : white;" onclick="dialogEditProfile();"><i class="material-icons">edit</i></a></span>
            <span class="white-text"><c:out value="${user.userName}"/> </span>
            <c:if test="${!empty user.avatar}">
                <img class="circle" alt="no good" id="avatarPic" src="${pageContext.request.contextPath}/imageScreenRenderServlet?action=form&avatarId=${user.avatar.objectId}">
            </c:if>
            <c:if test="${empty user.avatar}">
                <i class="material-icons circle medium">account_circle</i>
            </c:if>
            <span class="white-text name"><c:out value="${user.firstAndLastName}"/> </span>
            <span class="white-text email"><c:out value="${user.emailAddress}"/></span>
        </div>
    </li>
    <li><a href="#!"><i class="material-icons">cloud</i>First Link With Icon</a></li>
    <li><a href="${pageContext.request.contextPath}/portal?action=orderHistory">Order History</a></li>
    <li><a href="${pageContext.request.contextPath}/portal?action=reviews">Your Product Reviews</a></li>
    <li><div class="divider"></div></li>
    <li><a class="waves-effect" href="${pageContext.request.contextPath}/">StevenMHicks.com</a></li>
    <c:if test="${user.role == 'admin'}">
        <li><a class="waves-effect" href="${pageContext.request.contextPath}/portalItemHandler?action=form">Item Maintenance</a></li>
    </c:if>
    <li><a class="waves-effect" href="${pageContext.request.contextPath}/portal?action=signOut">Sign Out</a></li>
</ul>

<div id="editProfileDialog" class="hiddenDiv">
    <div id="frmDiv" class="popupContent">
        <a class="waves-effect waves-light btn" id="closeAddCourse" name="closeAddCourse" onclick="closeEditProfile();">Close</a>
        <form name="frmEditProfile" id="frmEditProfile" action="${pageContext.request.contextPath}/portal?action=editProfile" method="post">
            <table>
                <tr>
                    <td>
                        <label for="editFirstName">First Name:</label>
                        <input value="${user.firstName}" type="text" id="editFirstName" name="editFirstName">
                    </td>
                    <td>
                        <label for="editLastName">Last Name:</label>
                        <input value="${user.lastName}" type="text" id="editLastName" name="editLastName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="editEmail">Email:</label>
                        <input value="${user.emailAddress}" type="email" id="editEmail" name="editEmail"/>
                    </td>
                </tr>
            </table>
            <button class="btn waves-effect waves-light" type="submit" name="action">Update
                <i class="material-icons right">send</i>
            </button>
        </form>
        <br/><br/>
        <form enctype="multipart/form-data" name="frmEditAvatar" id="frmEditAvatar" action="${pageContext.request.contextPath}/portal?action=editAvatar" method="post">
            <label for="editAvatar">Select a new profile picture:</label>
            <input type="file" name="editAvatar" id="editAvatar" enctype="multipart/form-data">
            <button class="btn waves-effect waves-light" type="submit" name="action">Select
                <i class="material-icons right">send</i>
            </button>
        </form>
    </div>
</div>