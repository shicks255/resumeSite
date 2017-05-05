<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cart" type="com.steven.hicks.entities.store.Cart"  scope="session"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<div class="container">

    <h3>Your Cart:</h3>

    <c:set var="itemsInCart" value="${cart.itemsInCart}"/>
    <c:forEach var="item" items="${itemsInCart}">
        <br/>
        <c:out value="${item.storeItem.itemName}"/>
        <c:out value="${item.storeItem.itemNumber}"/>
        <c:out value="${item.storeItem.itemPrice}"/>
    </c:forEach>

</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />