<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:useBean id="items" type="java.util.List<com.steven.hicks.entities.store.items.MusicAlbum>" scope="request"/>

<form name="frmEditMusicAlbum" method="post" action="${pageContext.request.contextPath}/portalItemHandler?action=editMusicAlbums">
    <table class="highlight striped centered">
        <thead>
        <tr>
            <th></th>
            <th>Item <br/> Number</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Item Code</th>
            <th>Artist</th>
            <th>Album</th>
            <th>Release Year</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td><img class="" alt="no good" height="175" width="175" id="itemPic_${item.itemNumber}" src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${item.pictureObjectId}"></td>
                <td><input readonly="${true}" type="text" value="${item.itemNumber}"/></td>
                <td><input name="name_${item.itemNumber}" id="name_${item.itemNumber}" type="text" value="${item.itemName}"/></td>
                <td><input name="description_${item.itemNumber}" id="description_${item.itemNumber}" type="text" value="${item.itemDescription}"/></td>
                <td><input name="price_${item.itemNumber}" id="price_${item.itemNumber}" type="text" value="${item.itemPrice}"/></td>
                <td><input readonly=${true} type="text" value="${item.itemCode}"/></td>
                <td><input name="artist_${item.itemNumber}" id="artist_${item.itemNumber}" type="text" value="${item.artist}"/></td>
                <td><input name="albumTitle_${item.itemNumber}" id="albumTitle_${item.itemNumber}" type="text" value="${item.albumTitle}"/></td>
                <td><input name="releaseYear_${item.itemNumber}" id="releaseYear_${item.itemNumber}" type="text" value="${item.releaseYear}"/></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <button class="btn waves-effect waves-light" type="submit" name="action">Edit
        <i class="material-icons right">send</i>
    </button>

</form>