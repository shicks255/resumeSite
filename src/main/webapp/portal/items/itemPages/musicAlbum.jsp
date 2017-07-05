<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="album" type="com.steven.hicks.entities.store.items.MusicAlbum" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<%--<link href="${pageContext.request.contextPath}/CSS/materialize.min.css" rel="stylesheet" type="text/css">--%>
<%--<link href="${pageContext.request.contextPath}/CSS/jquery-ui.min.css" rel="stylesheet" type="text/css"/>--%>
<%--<link href="${pageContext.request.contextPath}/CSS/mainStyle.css" rel="stylesheet" type="text/css">--%>
<%----%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/JS/materialize.min.js"></script>--%>
<%--<script type="text/javascript" src=https://code.jquery.com/jquery-2.1.1.min.js></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-ui.min.js"></script>--%>

<script>
//    function addToCart(itemObjectId)
//    {
        <%--$.post( '${pageContext.request.contextPath}/portalItemHandler?action=addItemToCart&itemObjectId=' + itemObjectId,--%>
//            function(data)
//            {
//                location.reload();
//                alert("Item added to your cart");
//            });
//    }

    function addToCart(itemObjectId)
    {
        $( '#addToCart' ).blur();
        $.post( '${pageContext.request.contextPath}/portalItemHandler?action=addItemToCart&itemObjectId=' + itemObjectId,
            function(data)
            {
                location.reload();
                $( '#addToCartDiv' ).fadeIn().delay(1250).fadeOut( 500 );
            });
    }

</script>

<div class="container">

    <div id="itemContainer" style="border : 1px solid black;
                                    margin : 10%;">

        <div id="imageDiv" style="display : inline-block;
                                    float : left;
                                    border : 1px solid red;
                                    ">
            <img alt="no good" height="250" width="250" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${album.smallPictureId}"/>
        </div>

        <div id="infoDiv" style="float: right; margin-left : 5px; border : 1px solid blue;">

            <table>
                <tr>
                    <td><c:out value="${album.albumTitle}"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><c:out value="${album.artist}"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><c:out value="${album.itemCode}"/> </td>
                    <td></td>
                </tr>
                <tr>
                    <td><c:out value="${album.itemDescription}"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>$<c:out value="${album.itemPrice}"/> pieces </td>
                    <td></td>
                </tr>
                <tr>
                    <td>released: <c:out value="${album.releaseYear}"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <button class="btn waves-effect waves-light" onclick="addToCart('${album.itemNumber}');" type="submit" name="action">Add To Cart
                            <i class="material-icons right">add_shopping_cart</i>
                        </button>
                    </td>
                    <td></td>
                </tr>
            </table>

        </div>
    </div>

    <div id="addToCartDiv" class="popupAlert" style="display:none" >
        <div class="popupContentAlert">
            Item added to cart
        </div>/
    </div>

</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />
