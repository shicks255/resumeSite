<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="sh" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="accessCount" type="java.lang.String" scope="request"/>

<jsp:include page="/_pageSections/navBar.jsp"/>

<script>
    function resetURL()
    {
//        location.reload(true);
        window.open("${pageContext.request.contextPath}/techPractice?action=sessionInvalidate", "_self");
    }
</script>

<div class="bread nav-wrapper hide-on-small-and-down">
    <div class="col s12">
        <a href="${pageContext.request.contextPath}/techPractice?action=form" class="breadcrumb">Tech Practice</a>
        <a href="${pageContext.request.contextPath}/techPractice?action=java" class="breadcrumb">Java</a>
        <a href="${pageContext.request.contextPath}/techPractice?action=sessionPracticePage" class="breadcrumb">Session Practice</a>
    </div>
</div>
<div class="container">

    <h1>Session Tracking Practice</h1>

${cookie}
    <br/>

    Session:
${sessionScope.user}
    <br/>

    Request:
${pageContext.request}
    <br/>

    Response:
${pageContext.response}
    <br/>


<br/>
    <br/>
    You have accessed this page <c:out value="${accessCount}"/> time this session.
    Refresh the page to see this number increase.


<br/><br/>
    <%--Click <a onclick="resetURL();" href="${pageContext.request.contextPath}/techPractice?action=sessionPractice&invalidate=true">here</a> to clear out your session--%>
    Click
    <button class="waves-effect waves-light btn" onclick="resetURL()" >here</button>
    to clear out your session

</div>