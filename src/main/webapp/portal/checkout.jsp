<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cart" type="com.steven.hicks.entities.store.Cart"  scope="request"/>
<%--<jsp:useBean id="cartItems" type="java.util.List<com.steven.hicks.entities.store.CartItem>" scope="request"/>--%>
<%--<jsp:useBean id="storeItems" type="java.util.List<com.steven.hicks.entities.StoreItemGeneric>" scope="request"/>--%>
<%--<jsp:useBean id="itemPictures" type="java.util.List<com.steven.hicks.entities.store.StoreItemPicture>" scope="request"/>--%>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    $(document).ready(function()
    {
    });

</script>

<div class="container">


    <h3>CHECK OUT</h3>


</div>

<jsp:include page="/_pageSections/portalFooter.jsp" />