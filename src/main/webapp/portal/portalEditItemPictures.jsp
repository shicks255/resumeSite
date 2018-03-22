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

    function deletePicture(buttonId, pictureNumber)
    {
        var buttonPressed = document.getElementById(buttonId);
        buttonPressed.blur();

        window.location.href = '${pageContext.request.contextPath}/portalItemHandler?action=deleteItemPicture&itemNumber=' + pictureNumber;
    }

    //:todo going to have to ajax this
    function changeCaption(buttonId, pictureNumber, currentCaption)
    {
        var buttonPressed = document.getElementById(buttonId);
        buttonPressed.blur();

        $( '#editPictureCaption' ).removeClass('hiddenDiv').addClass('popup');

        $( '#caption' ).val(currentCaption);
        $( '#pictureNumber' ).val(pictureNumber);
    }

    function makePrimary(buttonId, pictureNumber)
    {
        var buttonPressed = document.getElementById(buttonId);
        buttonPressed.blur();
    }

</script>

<div class="container">

        <button class="btn waves-light waves-effect" id="deleteButton'" name="deleteButton" onclick="deletePicture(this.id, '${picture.objectId}');">
            Add pictre
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
                    <button class="btn waves-light waves-effect" id="deleteButton" name="deleteButton" onclick="deletePicture(this.id, '${picture.objectId}');">
                        Delete
                        <i class="material-icons right"></i>
                    </button>
                    <button class="btn waves-light waves-effect" id="changeCaptionButton" name="changeCaptionButton" onclick="changeCaption(this.id, '${picture.objectId}', '${picture.pictureCaption}');">
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


    <div id="editPictureCaption" class="hiddenDiv">
        <div class="popupContent">
            <div class="popupHeader">
                <span style="margin: auto;">Edit Album</span>
                <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
            </div>
            <div class="popupContainer">
                <form method="post" action="${pageContext.request.contextPath}/portalItemHandler?action=editPictureCaption">
                    <input type="hidden" name="pictureNumber" id="pictureNumber" value=""/>

                    <label for="caption">Name</label>
                    <input id="caption" name="caption" type="text" value=""/>
                </form>
            </div>
        </div>
    </div>

</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />
