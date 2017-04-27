<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="itemTypes" type="java.util.List<com.steven.hicks.entities.StoreItems.StoreItemType>"   scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    function filterItemAdds()
    {
        var itemType = $( '#productType' ).val();

        $.get('${pageContext.request.contextPath}/portalItemHandler?action=ajaxGetItems&itemType=' + itemType,
            function(data)
            {

            });
    }


</script>

<div class="container">

    <a class="waves-effect waves-light btn" name="btnAddAnItem" id="btnAddAnItem" href="${pageContext.request.contextPath}/portalItemHandler?action=form">Add Items</a>
    <a class="waves-effect waves-light btn" name="btnEditItems" id="btnEditItems" href="${pageContext.request.contextPath}/portalItemHandler?action=editItems">Edit Items</a>

    <h3>Edit Items</h3>

    <label for="productType">Choose a Product Type</label>
    <select onchange="filterItemAdds();" class="browser-default" name="productType" id="productType" >
        <option value=""></option>
        <c:forEach var="itemType" items="${itemTypes}">
            <option value="${itemType.itemType}">${itemType.itemType}</option>
        </c:forEach>
    </select>

    <div id="editItemsList">

    </div>


</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />