<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<script>

    $( document ).ready(function()
    {
    });

    function runGame()
    {
        $( '#gamePopup' ).removeClass('hiddenDiv').addClass('popup');
    }

</script>

<div class="container">

    <br/>
    <h1>Binary Tree Animal Guessing Game</h1>

    <p>
        This is a game that is constructed using binary search trees.
    </p>

    <button class="btn waves-effect waves-light" onclick="runGame();">
        Play
    </button>

    <div id="gamePopup" class="hiddenDiv">
        <div class="popupContent">
            <button class="btn waves-light waves-effect" onclick="closePopups();">
                Close
            </button>
            <br/>
            test
        </div>
    </div>

</div>

<jsp:include page="/_pageSections/footer.jsp" />
