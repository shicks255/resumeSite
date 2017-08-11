<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cart" type="com.steven.hicks.entities.store.Cart"  scope="session"/>
<jsp:useBean id="cartItems" type="java.util.List<com.steven.hicks.entities.store.CartItem>" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

//    :todo what is this
    $(document).ready(function()
    {
        $('.modal').modal();
        $( '#test' ).on( "click", function(e)
        {
            e.preventDefault();
            $(this).blur();
        });
    });

    //:todo make a better popup thing to notify
    function updateQty(itemObjectId, currentQty)
    {
        $( '#updateQtyBtn' ).blur();
        var itemGetter = '#qty_' + itemObjectId;
        var qty = $( itemGetter ).val();

        if (qty !== currentQty)
        {
            <%--$.post('${pageContext.request.contextPath}/portalItemHandler?action=updateCartQty&itemObjectId=' + itemObjectId +'&newQuantity=' + qty,--%>
            $.post('${pageContext.request.contextPath}/portalItemHandler?action=updateCartQty&itemObjectId=' + itemObjectId +'&newQuantity=' + qty, '');
            $( '#updateModal' ).modal('open');
        }
    }

    //:todo make a better popup to notify item removed
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

    <table>
        <thead>
        <tr>
            <th></th>
            <th>Item</th>
            <th>Number</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        </thead>

        <c:forEach var="item" items="${cartItems}">
            <c:set var="storeItem" value="${item.storeItem}"/>
        <tr>
            <td>
                <img alt="no good" height="250" width="250" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${storeItem.smallPictureId}"/>
            </td>
            <td>
                <c:out value="${storeItem.itemName}"/>
            </td>
            <td>
                <c:out value="${storeItem.itemNumber}"/>
            </td>
            <td>
                $ <c:out value="${storeItem.itemPrice}"/>
            </td>
            <td>
                <input style="width : 15px; margin-right : 15px;" size="4px" width="4px" type="text" id="qty_${item.objectId}" value="${item.quantity}"><button style="display:inline-block" class="btn waves-effect waves-light" id="updateQtyBtn" onclick="updateQty('${item.objectId}', '${item.quantity}');">Update</button>
                <button style="display:inline-block" id="removeItemBtn" class="btn waves-effect waves-light" onclick="removeItem('${item.objectId}');this.blur();">Remove</button>
            </td>

        </tr>
        </c:forEach>

        <tr>
            <td colspan="3" style="text-align: right;">Sub-total:</td>
            <td> $ <c:out value="${cart.subTotal}"/></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: right;">Total:</td>
            <td> $ <fmt:formatNumber value="${cart.total}" pattern="#.00"/></td>
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

    <div id="updateModal" class="modal">
        <div class="modal-content">
            <h4>Quantity updated</h4>
            <p>A bunch of text</p>
        </div>
    </div>

</div>

<jsp:include page="/_pageSections/portalFooter.jsp" />