<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cart" type="com.steven.hicks.entities.store.Cart"  scope="request"/>
<jsp:useBean id="cartItems" type="java.util.List<com.steven.hicks.entities.store.CartItem>" scope="request"/>
<jsp:useBean id="storeItems" type="java.util.List<com.steven.hicks.entities.StoreItemGeneric>" scope="request"/>
<jsp:useBean id="itemPictures" type="java.util.List<com.steven.hicks.entities.store.StoreItemPicture>" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

//    :todo what is this
    $(document).ready(function()
    {
        updateCartTotal();
        updateCartSubTotal();
        $('.modal').modal();
    });

    //:todo make a better popup thing to notify
    function updateQty(itemObjectId, currentQty)
    {
        $( '#updateQtyBtn' ).blur();
        var itemGetter = '#qty_' + itemObjectId;
        var qty = $( itemGetter ).val();

        if (qty !== currentQty)
        {
            $.post('${pageContext.request.contextPath}/portalItemHandler?action=updateCartQty&itemObjectId=' + itemObjectId +'&newQuantity=' + qty, '');
            $( '#updateModal' ).modal('open');
            updateCartTotal();
            updateCartSubTotal();
        }
    }

    //:todo make a better popup to notify item removed
    function removeItem(itemObjectId)
    {
        $.post('${pageContext.request.contextPath}/portalItemHandler?action=removeItemFromCart&itemObjectId=' + itemObjectId,
            function(data)
            {
                location.reload();
                $( '#removeModal' ).modal('open');
            });
    }

    function updateCartTotal()
    {
        $.get( '${pageContext.request.contextPath}/portalItemHandler?action=updateCartTotal',
        function(data)
        {
            $( '#cartTotal' ).html(data);
        });
    }

    function updateCartSubTotal()
    {
        $.get( '${pageContext.request.contextPath}/portalItemHandler?action=updateCartSubTotal',
        function(data)
        {
            $( '#cartSubtotal' ).html(data);
        });
    }

</script>

<div class="container">

    <h3>Your Cart:</h3>

    <%--<table>--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th></th>--%>
            <%--<th>Item</th>--%>
            <%--<th>Number</th>--%>
            <%--<th>Price</th>--%>
            <%--<th>Quantity</th>--%>
            <%--<th></th>--%>
        <%--</tr>--%>
        <%--</thead>--%>

        <%--<c:forEach var="storeItem" items="${storeItems}">--%>
            <%--<c:set var="item" value="${map[storeItem]}"/>--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<img class="responsive-img" alt="no good" height="150" width="150" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${itemsToPicture[storeItem].objectId}"/>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<c:out value="${storeItem.itemName}"/>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<c:out value="${storeItem.itemNumber}"/>--%>
            <%--</td>--%>
            <%--<td style="width: 100px;">--%>
                <%--$ <c:out value="${storeItem.itemPrice}"/>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<input style="width : 25px;" id="qty_${item.objectId}" value="${item.quantity}">--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<button style="display:inline-block" class="btn waves-effect waves-light" id="updateQtyBtn" onclick="updateQty('${item.objectId}', '${item.quantity}');">Update</button>--%>
                <%--<button style="display:inline-block" id="removeItemBtn" class="btn waves-effect waves-light" onclick="removeItem('${item.objectId}');this.blur();">Remove</button>--%>
            <%--</td>--%>

        <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>

    <div style="display:grid;grid-gap:5px;grid-template-columns: 25% 35% 10% 10% 15%;">
        <div style="grid-column: 3">
            <b>Price</b>
        </div>
        <div style="grid-column: 4;">
            <b>Quantity</b>
        </div>
    </div>
<c:forEach var="storeItem" items="${storeItems}">
    <c:set var="item" value="${map[storeItem]}"/>
<hr/>
    <div style="display:grid;grid-gap:5px;grid-template-columns: 25% 35% 10% 10% 15%;
    grid-template-rows: 50px 20px 20px 20px">
        <div style="grid-column: 1;grid-row:1/10">
            <img class="responsive-img" alt="no good" height="150" width="150" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${itemsToPicture[storeItem].objectId}"/>
        </div>
        <div style="grid-column: 2;grid-row:1;color: dodgerblue">
            <b><c:out value="${storeItem.itemName}"/></b>
        </div>
        <div style="grid-column: 2;grid-row: 2">
            Item# <c:out value="${storeItem.itemNumber}"/>
        </div>
        <div style="grid-column: 2;grid-row:3">
            <c:out value="${storeItem.itemTypeObject.itemType}"/>
        </div>
        <div style="grid-column: 2;grid-row:4">
            <c:out value="${storeItem.itemDescription}"/>
        </div>
        <div style="grid-column: 3;grid-row:1">
            <c:out value="$${storeItem.itemPrice}"/>
        </div>
        <div style="grid-column: 4;grid-row:1">
            <input style="width : 25px;line-height: 3; height: 1.5em;" id="qty_${item.objectId}" value="${item.quantity}">
        </div>
        <div style="grid-column: 5;grid-row:1">
            <button style="display:inline-block" class="btn waves-effect waves-light" id="updateQtyBtn" onclick="updateQty('${item.objectId}', '${item.quantity}');">Update</button>
            <button style="display:inline-block" id="removeItemBtn" class="btn waves-effect waves-light" onclick="removeItem('${item.objectId}');this.blur();">Remove</button>
        </div>
    </div>

</c:forEach>

    <table>

        <thead>
        <tr>
            <th colspan="7"></th>
        </tr>
        </thead>
        <tr>
            <td class="right-align"><b>Sub-total:</b> $ <span id="cartSubtotal"></span></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="right-align"><b>Total:</b> $ <span id="cartTotal"></span></td>
        </tr>
    </table>


    <div id="updateModal" class="modal">
        <div class="modal-content">
            <h4>Quantity updated</h4>
            <p>A bunch of text</p>
        </div>
    </div>

    <div id="removeModal" class="modal">
        <div class="modal-content">
            <h4>Item removed</h4>
            <p>A bunch of text</p>
        </div>
    </div>

</div>

<jsp:include page="/_pageSections/portalFooter.jsp" />