<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="itemTypes" type="java.util.List<com.steven.hicks.entities.store.StoreItemType>" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    $(document).ready(function()
    {
       $( '#productType' ).val('');

       $( '#productPriceMusicAlbum' ).focusout(function(event)
       {
           var enteredPrice = $( '#productPriceMusicAlbum' ).val();
           if (isNaN(enteredPrice))
               $( '#submitAlbum' ).addClass('disabled');
           else
               $( '#submitAlbum' ).removeClass('disabled');
       });

       $( '#releaseYear' ).focusout(function(event)
       {
          var releaseYear = $( '#releaseYear' ).val();
          if (isNaN(releaseYear))
              df
       });

       $( '#productPriceLego' ).focusout(function(event)
       {
          var enteredPrice = $( '#productPriceLego' ).val();
          if (isNaN(enteredPrice))
              df
       });

       $( '#numberOfPieces' ).focusout(function(event)
       {
          var numberOfPrices = $( '#numberOfPieces' ).val();
          if (isNaN(numberOfPrices))
              df
       });

       $( '#releaseYearLego' ).focusout(function(event)
       {
          var releaseYear = $( '#releaseYearLego' ).val();
          if (isNaN(releaseYear))
              df
       });

    });

    function filterItemAdds()
    {
        $( '.addingItemDiv' ).css('display', 'none');
        var itemType = $( '#productType' ).val();
        if (itemType == 'MusicAlbum')
            $( '#addMusicAlbum' ).css('display', 'inline');
        if (itemType == 'LegoSet')
            $( '#addLegoSet' ).css('display', 'inline');
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

<h3>Add And Item:</h3>

    <label for="productType">Choose a Product Type</label>
    <select onchange="filterItemAdds();" class="browser-default" name="productType" id="productType" >
        <option value=""></option>
        <c:forEach var="itemType" items="${itemTypes}">
            <option value="${itemType.itemType}">${itemType.itemType}</option>
        </c:forEach>
    </select>

    <div id="addMusicAlbum" class="addingItemDiv" style="display:none">
        <form enctype="multipart/form-data" name="frmAddItem" method="post" action="${pageContext.request.contextPath}/portalItemHandler?action=addMusicAlbum">
            <table>
                <tr>
                    <td>
                        <label for="productDescriptionMusicAlbum">Product Description:</label>
                        <input type="text" name="productDescriptionMusicAlbum" id="productDescriptionMusicAlbum"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="productPriceMusicAlbum">Product Price:</label>
                        <input type="text" name="productPriceMusicAlbum" id="productPriceMusicAlbum"/>
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
                <tr>
                    <td>
                        Item Picture: <input type="file" name="file" id="file" enctype="multipart/form-data">
                    </td>
                </tr>
            </table>
            <button id="submitAlbum" class="waves-effect waves-light btn" type="submit">
                Add
            </button>
        </form>
    </div>

    <div id="addLegoSet" class="addingItemDiv" style="display:none">
        <form enctype="multipart/form-data" name="frmAddItem" method="post" action="${pageContext.request.contextPath}/portalItemHandler?action=addLegoSet">
            <table>
                <tr>
                    <td>
                        <label for="productDescriptionLego">Product Description:</label>
                        <input type="text" name="productDescriptionLego" id="productDescriptionLego"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="productNameLego">Product Name:</label>
                        <input type="text" name="productNameLego" id="productNameLego"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="productPriceLego">Product Price:</label>
                        <input type="text" name="productPriceLego" id="productPriceLego"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label for="legoCode">Lego Code:</label>
                        <input type="text" name="legoCode" id="legoCode">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="legoTheme">Lego Theme:</label>
                        <input type="text" name="legoTheme" id="legoTheme">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="numberOfPieces">Pieces:</label>
                        <input type="text" name="numberOfPieces" id="numberOfPieces">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="releaseYearLego">Release Year:</label>
                        <input type="text" name="releaseYearLego" id="releaseYearLego">
                    </td>
                </tr>
                <tr>
                    <td>
                        Item Picture: <input type="file" name="file" id="fileLego" enctype="multipart/form-data">
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
