<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<jsp:useBean id="raceConditionNumbers1" type="java.util.List<java.lang.Integer>" scope="request"/>--%>
<%--<jsp:useBean id="raceConditionNumbers2" type="java.util.List<java.lang.Integer>" scope="request"/>--%>

<jsp:include page="/_pageSections/navBar.jsp"/>

<script>

    $( document ).ready(function()
    {
    });

    function doRaceCondition()
    {
        showWaitingPopup('Calculating...');
        $.get('${pageContext.request.contextPath}/techPractice?action=multithreadingFunction&function=raceCondition',
            function(data)
            {
               $( '#answerPopup' ).removeClass('hiddenDiv').addClass('popup');
               $( '#answer' ).html(data);
               hideWaitingPopup();
            });
    }

    function largeNumbersMultiThread()
    {
        showWaitingPopup("Calculating multithreaded answer.");
        $.get('${pageContext.request.contextPath}/techPractice?action=multithreadingFunction&function=largeNumMultiThread',
            function(data)
            {
                $( '#answerPopup' ).removeClass('hiddenDiv').addClass('popup');
                $( '#answer' ).html(data);
                hideWaitingPopup();
            });
    }

    function largeNumbers()
    {
        showWaitingPopup("Calculating single threaded answer.");
        $.get('${pageContext.request.contextPath}/techPractice?action=multithreadingFunction&function=largeNum',
                function(data)
                {
                    $( '#answerPopup' ).removeClass('hiddenDiv').addClass('popup');
                    $( '#answer' ).html(data);
                    hideWaitingPopup();
                });
    }

</script>

<div class="bread nav-wrapper hide-on-small-and-down">
    <div class="col s12">
        <a href="${pageContext.request.contextPath}/techPractice?action=form" class="breadcrumb">Tech Practice</a>
        <a href="${pageContext.request.contextPath}/techPractice?action=java" class="breadcrumb">Java</a>
        <a href="${pageContext.request.contextPath}/techPractice?action=multithreadingPage" class="breadcrumb">Multithreading</a>
    </div>
</div>
<div class="container">

    <br/>
    <h1>Java Multithreading</h1>


    Both of these buttons will do the same 3 mathematical calculations.
    <br/>
    Click to see the difference between running all through in sequence, vs running each problem in parallel through multithreading.
    <br/>
    <button class="btn waves-effect waves-light" onclick="largeNumbers();">Large Number Count</button>
    <button class="btn waves-effect waves-light" onclick="largeNumbersMultiThread();">Large Number Count Multi-Threaded</button>

    <br/><br/>

    This button will cause a race condition.
    <br/>
    Two different threads will attempt to increment the same number a million times.
    <br/>

    <button class="btn waves-effect waves-light" onclick="doRaceCondition();">Race Condition</button>

    <div id="answerPopup" class="hiddenDiv">
        <div class="popupContent">
            <div class="popupHeader">
                <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
            </div>
            <div class="popupContainer">
                <div id="answer"></div>
            </div>
        </div>
    </div>


</div>

<jsp:include page="/_pageSections/footer.jsp" />
