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
        window.location.href = '${pageContext.request.contextPath}/techPractice?action=multithreadingFunction&function=largeNumMultiThread';
    }

    function largeNumbersMultiThread()
    {
        showWaitingPopup("Calculating multithreaded answer.");
        $.get('${pageContext.request.contextPath}/techPractice?action=multithreadingFunction&function=largeNumMultiThread',
            function(data)
            {
                $( '#answerPopup' ).removeClass('hiddenDiv').addClass('popup');
                console.log(data);
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

<div class="container">

    <br/>
    <h1>Java Multithreading</h1>


    <button class="btn waves-effect waves-light" onclick="doRaceCondition();">Race Condition</button>
    <button class="btn waves-effect waves-light" onclick="largeNumbers();">Large Number Count</button>
    <button class="btn waves-effect waves-light" onclick="largeNumbersMultiThread();">Large Number Count Multi-Threaded</button>

    <div id="answerPopup" class="hiddenDiv">
        <div class="popupContent">
            <button class="btn waves-effect waves-light" onclick="closePopups();">Close</button>
            <div id="answer"></div>
        </div>
    </div>


</div>

<jsp:include page="/_pageSections/footer.jsp" />
