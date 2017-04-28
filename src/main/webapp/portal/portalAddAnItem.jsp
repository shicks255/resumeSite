<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="itemTypes" type="java.util.List<com.steven.hicks.entities.StoreItems.StoreItemType>"   scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    function filterItemAdds()
    {
        var itemType = $( '#productType' ).val();
        if (itemType == 'MusicAlbum')
            $( '#addMusicAlbum' ).css('display', 'inline');
    }


</script>

<div class="container">

    <a class="waves-effect waves-light btn" name="btnAddAnItem" id="btnAddAnItem" href="${pageContext.request.contextPath}/portalItemHandler?action=form">Add Items</a>
    <a class="waves-effect waves-light btn" name="btnEditItems" id="btnEditItems" href="${pageContext.request.contextPath}/portalItemHandler?action=editItems">Edit Items</a>

<h3>Add And Item:</h3>

    <label for="productType">Choose a Product Type</label>
    <select onchange="filterItemAdds();" class="browser-default" name="productType" id="productType" >
        <option value=""></option>
        <c:forEach var="itemType" items="${itemTypes}">
            <option value="${itemType.itemType}">${itemType.itemType}</option>
        </c:forEach>
    </select>

    <div id="addMusicAlbum" style="display:none">
        <form name="frmAddItem" method="post" action="${pageContext.request.contextPath}/portalItemHandler?action=addMusicAlbum">
            <table>
                <tr>
                    <td>
                        <label for="productDescription">Product Description:</label>
                        <input type="text" name="productDescription" id="productDescription"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="productPrice">Product Price:</label>
                        <input type="text" name="productPrice" id="productPrice"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label for="artistName">Artist Name:</label>
                        <input type="text" name="artistName" id="artistName">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="albumTitle">Album Title:</label>
                        <input type="text" name="albumTitle" id="albumTitle">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="releaseYear">Release Year:</label>
                        <input type="text" name="releaseYear" id="releaseYear">
                    </td>
                </tr>
            </table>
            <button class="waves-effect waves-light btn" type="submit">
                Add
            </button>
        </form>
    </div>


</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />
