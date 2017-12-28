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
        $.get( '${pageContext.request.contextPath}/techPractice?action=runTreeGame',
            function(data)
            {
                $( '#question' ).html(data);
                $( '#gamePopup' ).removeClass('hiddenDiv').addClass('popup');
            });
    }

    function next()
    {
        var answer = $( "input[name='treeAnswer']:checked").val();
        $.get( '${pageContext.request.contextPath}/techPractice?action=queryTreeGame&treeAnswer=' + answer,
            function(data)
            {
               $( "input[name='treeAnswer']" ).prop('checked', false);
               $( '#question' ).html(data);
            });
    }

</script>

<div class="bread nav-wrapper hide-on-small-and-down">
    <div class="col s12">
        <a href="${pageContext.request.contextPath}/techPractice?action=form" class="breadcrumb">Tech Practice</a>
        <a href="${pageContext.request.contextPath}/techPractice?action=java" class="breadcrumb">Java</a>
        <a href="${pageContext.request.contextPath}/techPractice?action=treeGame" class="breadcrumb">Binary Tree Game</a>
    </div>
</div>
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
            <div class="popupHeader">
                <span style="margin: auto;" id="popupTitle">Binary Tree Animal Guessing Game</span>
                <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
            </div>
            <div class="popupContainer">
                <div id="question">
                </div>
                <br/>
                <div id="answer">
                    <input type="radio" id="answerYes" name="treeAnswer" value="Yes">
                    <label for="answerYes">Yes</label>
                    <br/>
                    <input type="radio" id="answerNo" name="treeAnswer" value="No">
                    <label for="answerNo">No</label>
                </div>
                <button class="btn waves-effect waves-light" onclick="next();">
                    Next
                </button>
            </div>
        </div>
    </div>

</div>

<jsp:include page="/_pageSections/footer.jsp" />
