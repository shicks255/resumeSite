<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="items" type="java.util.List<com.steven.hicks.entities.StoreItemGeneric>" scope="request"/>

<script>
    $( document ).ready(function (event)
    {
        $( '#itemSearchResultsTable' ).removeAttribute('style');
    })
</script>

<style>
    table tr td {
        padding : 0;
    }
    tr {
        max-height : 5px;
    }
</style>

<table id="itemSearchResultsTable" border="1">
    <c:forEach var="item" items="${items}">
        <tr style="background-color : green;">
            <td>
                <a href="#"><c:out value="${item.itemName}"/></a>
            </td>
        </tr>
    </c:forEach>

</table>