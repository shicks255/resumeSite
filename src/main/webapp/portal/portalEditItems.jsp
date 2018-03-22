<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="itemTypes" type="java.util.List<com.steven.hicks.entities.store.StoreItemType>" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    $(document).ready(function()
    {
        $( '#productType' ).val('');
    });

    function filterItemAdds()
    {
        var itemType = $( '#productType' ).val();

        if (itemType.length == 0)
            $( '#editItemsList' ).html('');
        else
        {
            $.get('${pageContext.request.contextPath}/portalItemHandler?action=ajaxGetItemsByType&itemType=' + itemType,
                function(data)
                {
                    $( '#editItemsList' ).html(data);
                });
        }
    }

    function goToAddButtonPage(toCloseId)
    {
        var button = document.getElementById(toCloseId);
        button.blur();
        window.location.href = "${pageContext.request.contextPath}/portalItemHandler?action=form";
    }

    function goToEditButtonPage(toCloseId)
    {
        var button = document.getElementById(toCloseId);
        button.blur();
        window.location.href = "${pageContext.request.contextPath}/portalItemHandler?action=editItems";
    }


</script>

<div class="container">

    <button class="btn waves-effect waves-light" id="addItemButton" name="addItemButton" onclick="goToAddButtonPage(this.id);">
        Add Items
        <i class="material-icons right">add</i>
    </button>
    <button class="btn waves-light waves-effect" id="editItemButton" name="editItemButton" onclick="goToEditButtonPage(this.id);">
        Edit Items
        <i class="material-icons right">mode_edit</i>
    </button>

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
