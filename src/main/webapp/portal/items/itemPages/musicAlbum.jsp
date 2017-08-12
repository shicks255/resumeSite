<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="album" type="com.steven.hicks.entities.store.items.MusicAlbum" scope="request"/>
<jsp:useBean id="picture" type="com.steven.hicks.entities.store.StoreItemPicture" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    $(document).ready(function()
    {
        $('.modal').modal();
    });

    function addToCart(itemObjectId)
    {
        $.post( '${pageContext.request.contextPath}/portalItemHandler?action=addItemToCart&itemObjectId=' + itemObjectId,
            function(data)
            {
                $('#modal').modal('open');
            });
    }

</script>

<div class="container">

    <div id="itemContainer" style="border : 1px solid black;
                                    margin : 10%;">

        <a class="btn modal-trigger" href="#modal" style="visibility: collapse"></a>

        <div id="imageDiv" style="display : inline-block;
                                    float : left;
                                    border : 1px solid red;
                                    ">
            <img alt="no good" height="250" width="250" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${picture.objectId}"/>
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

    <div id="modal" class="modal">
        <div class="modal-content">
            <h4>Added to cart</h4>
            <p>A bunch of text</p>
        </div>
    </div>

</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />
