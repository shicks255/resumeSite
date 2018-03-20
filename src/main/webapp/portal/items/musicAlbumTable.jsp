<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:useBean id="items" type="java.util.List<com.steven.hicks.entities.store.items.MusicAlbum>" scope="request"/>

<script>
    function deleteItem(itemNumber)
    {
        $.post("${pageContext.request.contextPath}/portalItemHandler?action=deleteItem&itemNumber=" + itemNumber);
        location.reload();
    }

    function editItem(itemNumber)
    {
        $( '#editAlbumPopup' ).removeClass('hiddenDiv');
        $( '#editAlbumPopup' ).addClass('popup');

        $.getJSON('${pageContext.request.contextPath}/portalItemHandler?action=ajaxGetJSONItem&itemNumber=' + itemNumber,
            function(data)
            {

            });

    }
</script>

<%--<form name="frmEditMusicAlbum" method="post" action="${pageContext.request.contextPath}/portalItemHandler?action=editMusicAlbums">--%>
    <table class="highlight centered">
        <thead>
        <tr>
            <th></th>
            <th>Item <br/> Number</th>
            <th>Name</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>
                    <img class="" alt="no good" height="175" width="175" id="itemPic_${item.itemNumber}" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${item.firstPictureId}">
                </td>
                <td><c:out value="${item.itemName}"/></td>
                <td></td>
                <td>
                    <button class="small btn waves-effect waves-light" onclick="editItem('${item.itemNumber}');" type="button" name="editButton">
                        Edit
                        <i class="material-icons right">mode_edit</i>
                    </button>
                    <button class="small btn waves-effect waves-light" onclick="deleteItem('${item.itemNumber}');" type="submit" name="action">Delete
                        <i class="small material-icons right">send</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
<%--</form>--%>

<div id="editAlbumPopup" class="hiddenDiv">
    <div class="popupContent">
        <div class="popupHeader">
            <span id="editAlbumHeader"></span>
        </div>
        <div class="popupContainer">
            <form method="post" action="">
                <label for="artistName">Name</label>
                <input id="artistName" name="artistName" type="text" value=""/>

                <label for="albumName">Name</label>
                <input id="albumName" name="albumName" type="text" value=""/>

                <label for="price">Price</label>
                <input type="text" name="price" id="price" value=""/>

                <label for="releaseYear">Released:</label>
                <input type="text" name="releaseYear" id="releaseYear" value=""/>

            </form>
        </div>
    </div>
</div>