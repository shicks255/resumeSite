<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="items" type="java.util.List<com.steven.hicks.entities.StoreItemGeneric>" scope="request"/>
<jsp:useBean id="searchTerms" type="java.lang.String" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<style>

    @media(max-width: 640px)
    {
        .showOnMedAndUp{
            display: none;
        }
        .showOnSmall {
            display:table;
        }
    }

    @media(min-width: 640px) and (max-width: 980px)
    {
        .hideOnSmall{
            display: none;
        }
        .showOnMedAndUp{
            display: table;
        }
        .showOnSmall{
            display:none;
        }
    }

    @media(min-width: 980px)
    {
        .hideOnSmall{
            display: table-cell;
        }
        .showOnMedAndUp{
            display:table;
        }
        .showOnSmall{
            display:none;
        }

    }

</style>

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

    <br/>

    <table class="showOnMedAndUp">
        <thead>
        <tr>
            <td></td>
            <td>Item</td>
            <td class="hideOnSmall">Item #</td>
            <td>Price</td>
            <td class="hideOnSmall"></td>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${items}">
            <tr style="border-bottom: 1px solid #d0d0d0;">
                <td><a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemNumber=${item.itemNumber}"><img height="250"  alt="no good" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${item.firstPictureId}"/> </a></td>
                <td><a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemNumber=${item.itemNumber}"><c:out value="${item.itemName}"/>   </a></td>
                <td class="hideOnSmall"><a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemNumber=${item.itemNumber}"><c:out value="${item.itemNumber}"/> </a></td>
                <td><a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemNumber=${item.itemNumber}">$<c:out value="${item.itemPrice}"/>  </a></td>
                <td class="hideOnSmall"><c:out value="${item.itemDescription}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="showOnSmall">
        <thead>
        <tr>
            <td></td>
            <td>Item</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}">
            <tr style="border-bottom: 1px solid #d0d0d0;">
                <td><a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemNumber=${item.itemNumber}"><img height="250"  alt="no good" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${item.firstPictureId}"/> </a></td>
                <td>
                    <a href="${pageContext.request.contextPath}/portalItemHandler?action=showItemPage&itemNumber=${item.itemNumber}">
                        <c:out value="${item.itemName}"/>
                    </a>
                    <br/><br/>
                    $<c:out value="${item.itemPrice}"/>
                    <br/><br/>
                    <c:out value="${item.itemDescription}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/_pageSections/portalFooter.jsp" />
