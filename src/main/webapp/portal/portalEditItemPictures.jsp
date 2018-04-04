<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="item" type="com.steven.hicks.entities.StoreItemGeneric" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    $(document).ready(function()
    {
        $( '#productType' ).val('');
    });

    function promptToConfirmDelete(pictureNumber)
    {
        $( '#deletePictureNumber' ).val(pictureNumber);
        $( '#confirmDialog' ).removeClass('hiddenDiv');
        $( '#confirmDialog' ).addClass('popup');
    }

    function deletePicture()
    {
        var confirmText = $( '#confirmPromptText' ).val();
        if (confirmText === 'DELETE')
        {
            var pictureNumber = $( '#deletePictureNumber' ).val();
            $.post( '${pageContext.request.contextPath}/portalItemHandler?action=deleteItemPicture&pictureNumber=' + pictureNumber );
            location.reload();
        }
    }

    function addPicture()
    {

    }

    function changeCaption(buttonId, pictureNumber)
    {
        var buttonPressed = document.getElementById(buttonId);
        buttonPressed.blur();

        $.get( '${pageContext.request.contextPath}/portalItemHandler?action=ajaxGetPictureCaption&pictureNumber=' + pictureNumber,
            function(data)
            {
                $( '#changeCaptionPictureNumber' ).val(pictureNumber);
                $( '#changeCaptionText' ).val(data);
                $( '#changeCaptionDialog' ).removeClass('hiddenDiv').addClass('popup');
            });
    }

    function makePrimary(buttonId, pictureNumber)
    {
        var buttonPressed = document.getElementById(buttonId);
        console.log(buttonPressed);
        buttonPressed.blur();
    }

</script>

<div class="container">

    <button class="btn waves-light waves-effect" id="deleteButton'" name="deleteButton" onclick="addPicture(this.id);">
        Add picture
        <i class="material-icons right"></i>
    </button>

    <table>
        <c:forEach var="picture" items="${item.itemPictures}">
            <tr>
                <td>
                    <img src="${pageContext.request.contextPath}/portalItemHandler?action=getItemPicture&itemPictureObjectId=${picture.objectId}"/>
                </td>
                <td>
                    <c:out value="${picture.pictureCaption}"/>
                </td>
                <td>
                    <button class="btn waves-light waves-effect" id="deleteButton" name="deleteButton" onclick="promptToConfirmDelete('${picture.objectId}');">
                        Delete
                        <i class="material-icons right"></i>
                    </button>
                    <button class="btn waves-light waves-effect" id="changeCaptionButton" name="changeCaptionButton" onclick="changeCaption(this.id, '${picture.objectId}');">
                        Change caption
                        <i class="material-icons right"></i>
                    </button>
                    <button class="btn waves-light waves-effect" id="makePrimaryButton" name="makePrimaryButton" onclick="makePrimary(this.id, '${picture.objectId}');">
                        Make Primary
                        <i class="material-icons right"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div id="confirmDialog" class="hiddenDiv">
        <div class="popupContent">
            <div class="popupHeader">
                <span style="margin: auto;">Delete Picture</span>
                <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
            </div>
            <div class="popupContainer">
                <label for="confirmPromptText">Type DELETE to confirm removing this item.</label>
                <input type="text" id="confirmPromptText" name="confirmPromptText" value=""/>
                <input type="hidden" id="deletePictureNumber" name="deletePictureNumber" value=""/>
                <button class="waves-effect waves-light btn submitButton" value="Confirm" onclick="deletePicture();">
                    Confirm
                    <i class="material-icons right">delete</i>
                </button>
            </div>
        </div>
    </div>

    <div id="changeCaptionDialog" class="hiddenDiv">
        <div class="popupContent">
            <div class="popupHeader">
                <span style="margin: auto;">Change Caption</span>
                <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
            </div>
            <div class="popupContainer">
                <form method="post" name="frmChangeCaption" id="frmChangeCaption" action="${pageContext.request.contextPath}/portalItemHandler?action=changePictureCaption">
                    <input type="hidden" name="changeCaptionPictureNumber" id="changeCaptionPictureNumber" value=""/>
                    <label for="changeCaptionText">Caption:</label>
                    <textarea name="changeCaptionText" id="changeCaptionText"></textarea>
                    <button class="waves-effect waves-light btn submitButton" value="Change" onclick="$('#frmChangeCaption').submit();">
                        Change
                        <i class="material-tooltip right">send</i>
                    </button>
                </form>
            </div>
        </div>
    </div>

</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />
