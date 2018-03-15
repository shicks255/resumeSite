<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="album" type="com.steven.hicks.entities.store.items.MusicAlbum" scope="request"/>
<jsp:useBean id="picture" type="com.steven.hicks.entities.store.StoreItemPicture" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<style>

    .itemGridContainerLarge {display: grid;}
    .itemGridContainerSmall {display: inline-block;}

    @media(min-width: 320px) and (max-width: 980px)
    {
        .itemGridContainerLarge{
            display:none;
        }

        .itemGridContainerSmall{
            display:inline-block;
        }
    }
    @media(min-width: 980px)
    {
        .itemGridContainerLarge{
            display: grid;
        }

        .itemGridContainerSmall{
            display: none;
        }
    }


</style>

<script>

    $(document).ready(function()
    {
        // $('.modal').modal();
    });

    function addToCart(itemObjectId)
    {
        showWaitingPopupPortal('Adding item to cart');
        $.post( '${pageContext.request.contextPath}/portalItemHandler?action=addItemToCart&itemObjectId=' + itemObjectId,
            function(data)
            {
                hideWaitingPopupPortal();
                getNumberOfItemsInCart();
            });
    }
</script>

<div class="container">
    <br/>
    <div class="itemGridContainerLarge" style="grid-gap:5px;grid-template-columns: 45% 55%;
        grid-template-rows: 40px 20px 20px 20px 20px;">

        <div style="grid-column: 1; grid-row: 1/4">
            <img class="responsive-img" alt="no good" height="200" width="200" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${album.firstPictureId}"/>
        </div>
        <div style="grid-column: 2; grid-row:1;">
            <b><c:out value="${album.albumTitle}"/> </b>
        </div>
        <div style="grid-column: 2; grid-row: 2;">
            <c:out value="${album.artist}"/>
        </div>
        <div style="grid-column: 2; grid-row: 3;">
            Item # <c:out value="${album.itemNumber}"/>
        </div>
        <div style="grid-column: 2; grid-row: 4;">
            $<c:out value="${album.itemPrice}"/>
        </div>
        <div style="grid-column: 2; grid-row: 5;">
            released: <c:out value="${album.releaseYear}"/>
        </div>
    </div>

    <div class="itemGridContainerSmall center" style="margin: auto;width: 100%;text-align: center">
        <img class="responsive-img" alt="no good" height="250" width="250" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${album.firstPictureId}"/>

        <br/><br/>
        <b><c:out value="${album.albumTitle}"/></b>
        <br/>
        <c:out value="${album.artist}"/>
        <br/>
        Item # <c:out value="${album.itemNumber}"/>
        <br/>
        $<c:out value="${album.itemPrice}"/>
        <br/>
        Released: <c:out value="${album.releaseYear}"/>

    </div>

    <br/>

    <div class="center">
        <button class="btn waves-effect waves-light" onclick="addToCart('${album.itemNumber}');" type="submit" name="action">Add To Cart
            <i class="material-icons right">add_shopping_cart</i>
        </button>
    </div>

    <div  class="itemDescription">
        <h5>Item Description:</h5>
        <c:out value="${album.itemDescription}"/>
    </div>

</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />
