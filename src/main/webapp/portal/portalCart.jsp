<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cart" type="com.steven.hicks.entities.store.Cart"  scope="session"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    $(document).ready(function()
    {
        $( '#test' ).on( "click", function(e)
        {
            e.preventDefault();
            $(this).blur();
        });
    });

    function updateQty(itemObjectId)
    {
        $( '#updateQtyBtn' ).blur();
        var itemGetter = '#qty_' + itemObjectId;
        var qty = $( itemGetter ).val();
        $.post('${pageContext.request.contextPath}/portalItemHandler?action=updateCartQty&itemObjectId=' + itemObjectId +'&newQuantity=' + qty,
            function(data)
            {
                $( '#updateDiv' ).fadeIn().delay(1250).fadeOut( 500 );
            });
    }

    function removeItem(itemObjectId)
    {
        $.post('${pageContext.request.contextPath}/portalItemHandler?action=removeItemFromCart&itemObjectId=' + itemObjectId,
            function(data)
            {
                location.reload();
//                $( '#deleteDiv' ).fadeIn().delay(1000).fadeOut(500);
//                $( '#removeItemBtn' ).removeClass('btn:focus');
            });
    }

</script>

<div class="container">

    <h3>Your Cart:</h3>

    <c:set var="itemsInCart" value="${sessionScope.cart.itemsInCart}"/>
    <table border="1">
        <thead>
        <tr>
            <th></th>
            <th>Item</th>
            <th>Number</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        </thead>

        <c:forEach var="item" items="${itemsInCart}">
        <tr>
            <td>
                <img alt="no good" height="250" width="250" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${item.storeItem.firstPictureId}"/>
            </td>
            <td>
                <c:out value="${item.storeItem.itemName}"/>
            </td>
            <td>
                <c:out value="${item.storeItem.itemNumber}"/>
            </td>
            <td>
                $ <c:out value="${item.storeItem.itemPrice}"/>
            </td>
            <td>
                <input style="width : 15px; margin-right : 15px;" size="4px" width="4px" type="text" id="qty_${item.objectId}" value="${item.quantity}"><button style="display:inline-block" class="btn waves-effect waves-light" id="updateQtyBtn" onclick="updateQty('${item.objectId}');">Update</button>
                <button style="display:inline-block" id="removeItemBtn" class="btn waves-effect waves-light" onclick="removeItem('${item.objectId}');this.blur();">Remove</button>
            </td>

        </tr>
        </c:forEach>

        <tr>
            <td colspan="3" style="text-align: right;">Sub-total:</td>
            <td> $ <c:out value="${sessionScope.cart.subTotal}"/></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: right;">Total:</td>
            <td> $ <fmt:formatNumber value="${sessionScope.cart.total}" pattern="#.00"/></td>
            <td></td>
        </tr>
    </table>


<div id="updateDiv" class="popupAlert" style="display:none" >
    <div class="popupContentAlert">
        Quantity updated
    </div>/
</div>

<div id="deleteDiv" class="popupAlert" style="display: none">
    <div class="popupContentAlert">
        Item removed from cart
    </div>/
</div>
</div>

<jsp:include page="/_pageSections/portalFooter.jsp" />