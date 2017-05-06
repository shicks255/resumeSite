<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cart" type="com.steven.hicks.entities.store.Cart"  scope="session"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<div class="container">

    <h3>Your Cart:</h3>

    <c:set var="itemsInCart" value="${cart.itemsInCart}"/>
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

        <c:forEach var="item" items="${itemsInCart}">
        <tr>
            <td>
                <img alt="no good" height="250" width="250" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${item.storeItem.pictureObjectId}"/>
            </td>
            <td>
                <c:out value="${item.storeItem.itemName}"/>
            </td>
            <td>
                <c:out value="${item.storeItem.itemNumber}"/>
            </td>
            <td>
                <c:out value="${item.storeItem.itemPrice}"/>
            </td>
            <td>
                0
            </td>

        </tr>
        </c:forEach>
    </table>

</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />