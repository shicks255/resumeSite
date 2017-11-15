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
        window.location.href = '${pageContext.request.contextPath}/techPractice?action=multithreadingFunction&function=largeNumMultiThread';
    }

    function largeNumbers()
    {
        window.location.href = '${pageContext.request.contextPath}/techPractice?action=multithreadingFunction&function=largeNum';
    }

</script>

<div class="container">

    <br/>
    <h1>Java Multithreading</h1>


    <button class="btn waves-effect waves-light" onclick="doRaceCondition();">Race Condition</button>
    <button class="btn waves-effect waves-light" onclick="largeNumbers();">Large Number Count</button>
    <button class="btn waves-effect waves-light" onclick="largeNumbersMultiThread();">Large Number Count Multi-Threaded</button>



</div>

<jsp:include page="/_pageSections/footer.jsp" />
