<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:useBean id="items" type="java.util.List<com.steven.hicks.entities.store.items.MusicAlbum>" scope="request"/>

<script>

    function promptToDelete(itemNumber)
    {
        $( '#deleteItemNumber' ).val(itemNumber);
        $( '#confirmDialog' ).removeClass('hiddenDiv');
        $( '#confirmDialog' ).addClass('popup');
    }

    function deleteItem()
    {
        var confirmText = $( '#confirmPromptText' ).val();
        if (confirmText === 'DELETE')
        {
            var itemNumber = $( '#deleteItemNumber' ).val();
            $.post("${pageContext.request.contextPath}/portalItemHandler?action=deleteItem&itemNumber=" + itemNumber);
            location.reload();
        }
    }

    function editItem(itemNumber)
    {
        $( '#editAlbumPopup' ).removeClass('hiddenDiv');
        $( '#editAlbumPopup' ).addClass('popup');

        $.getJSON('${pageContext.request.contextPath}/portalItemHandler?action=ajaxGetJSONItem&itemNumber=' + itemNumber,
            function(data)
            {
                $( '#artistName' ).val(data[0].getArtist);
                $( '#albumName' ).val(data[0].getAlbumTitle);
                $( '#price' ).val(data[0].getItemPrice);
                $( '#releaseYear' ).val(data[0].getReleaseYear);
                $( '#itemNumber' ).val(data[0].getItemNumber);
                $( '#itemDescription' ).val(data[0].getItemDescription);

            });
    }

</script>

    <table class="highlight centered">
        <thead>
        <tr>
            <th></th>
            <th>Item</th>
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
                <td>
                    <button class="small btn waves-effect waves-light" onclick="editItem('${item.itemNumber}');" type="button" name="editButton">
                        Edit
                        <i class="material-icons right">mode_edit</i>
                    </button>
                    <button class="small btn waves-effect waves-light" onclick="promptToDelete('${item.itemNumber}');" type="submit" name="action">
                        Delete
                        <i class="small material-icons right">send</i>
                    </button>
                    <button class="small btn waves-effect waves-light" onclick="window.location.href = '${pageContext.request.contextPath}/portalItemHandler?action=showEditItemPicture&itemNumber=${item.itemNumber}';">
                        Pictures
                        <i class="material-icons small right">photo</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

<div id="editAlbumPopup" class="hiddenDiv">
    <div class="popupContent">
        <div class="popupHeader">
            <span style="margin: auto;">Edit Album</span>
            <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
        </div>
        <div class="popupContainer">
            <form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/portalItemHandler?action=editMusicAlbums">
                <input type="hidden" name="itemNumber" id="itemNumber" value=""/>

                <label for="artistName">Name</label>
                <input id="artistName" name="artistName" type="text" value=""/>

                <label for="albumName">Name</label>
                <input id="albumName" name="albumName" type="text" value=""/>

                <label for="price">Price</label>
                <input type="text" name="price" id="price" value=""/>

                <label for="releaseYear">Released:</label>
                <input type="text" name="releaseYear" id="releaseYear" value=""/>

                <label for="itemDescription">Description</label>
                <textarea rows="5" cols="5" name="itemDescription" id="itemDescription">

                </textarea>

                <button class="waves-effect waves-light btn submitButton" type="submit" value="Update">
                    Update
                    <i class="material-icons right">send</i>
                </button>

            </form>
        </div>
    </div>
</div>

<div id="confirmDialog" class="hiddenDiv">
    <div class="popupContent">
        <div class="popupHeader">
            <span style="margin: auto;">Edit Album</span>
            <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
        </div>
        <div class="popupContainer">
            <label for="confirmPromptText">Type DELETE to confirm removing this item.</label>
            <input type="text" id="confirmPromptText" name="confirmPromptText" value=""/>
            <input type="hidden" id="deleteItemNumber" name="deleteItemNumber" value=""/>
            <button class="waves-effect waves-light btn" value="Confirm" onclick="deleteItem();">
                Confirm
                <i class="material-icons right">delete</i>
            </button>
        </div>
    </div>
</div>