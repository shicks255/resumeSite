<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="items" type="java.util.List<com.steven.hicks.entities.StoreItemGeneric>" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    function addToCart(itemObjectId)
    {
        $.post( '${pageContext.request.contextPath}/portalItemHandler?action=addItemToCart&itemObjectId=' + itemObjectId,
            function(data)
            {
                alert("Item added to your cart");
            });
    }

</script>

<div class="container">

        <table>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemName=${item.itemName}"><img height="250"  alt="no good" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${item.firstPictureId}"/> </a></td>
                    <td><a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemName=${item.itemName}"><c:out value="${item.itemName}"/>   </a></td>
                    <td><a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemName=${item.itemName}"><c:out value="${item.itemNumber}"/> </a></td>
                    <td><a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemName=${item.itemName}"><c:out value="${item.itemPrice}"/>  </a></td>
                    <td>description will go here </td>
                </tr>
            </c:forEach>
        </table>
</div>

<jsp:include page="/_pageSections/portalFooter.jsp" />
