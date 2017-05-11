<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="legoSet" type="com.steven.hicks.entities.store.items.LegoSet" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    function addToCart(itemObjectId)
    {
        $( '#addToCart' ).blur();
        $.post( '${pageContext.request.contextPath}/portalItemHandler?action=addItemToCart&itemObjectId=' + itemObjectId,
            function(data)
            {
                $( '#addToCartDiv' ).fadeIn().delay(1250).fadeOut( 500 );
            });
    }

</script>

<div class="container" style="border : 1pt solid green;"/>

    <div id="itemContainer" style="border : 1px solid black;
                                    margin : 10%;">

        <div id="imageDiv" style="display : inline-block;
                                    float : left;
                                    border : 1px solid red;
                                    ">
            <img alt="no good" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${legoSet.pictureObjectId}"/>
        </div>

        <div id="infoDiv" style="float: right; margin-left : 5px; border : 1px solid blue;">

            <table>
                <tr>
                    <td><c:out value="${legoSet.itemName}"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><c:out value="${legoSet.legoCode}"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><c:out value="${legoSet.legoTheme}"/> </td>
                    <td></td>
                </tr>
                <tr>
                    <td><c:out value="${legoSet.itemCode}"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><c:out value="${legoSet.numberOfPieces}"/> pieces </td>
                    <td></td>
                </tr>
                <tr>
                    <td>released: <c:out value="${legoSet.releaseYear}"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <button id="addToCart" class="btn waves-effect waves-light" onclick="addToCart('${legoSet.itemNumber}');" type="submit" name="action">Add To Cart
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
