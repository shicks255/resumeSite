<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<jsp:useBean id="fileList" type="java.util.List<java.lang.String>" scope="request"/>

    <script>
        $(document).ready(function()
        {

        });
    </script>

    <style>
        .liquidPic {width : 100%;}
    </style>

<div class="container">

    <h1>Various Photos:</h1>

    <div class="row">
        <c:set var="classType" value="col s12 m6 l3"/>
        <c:forEach var="file" items="${fileList}">
            <div class="${classType}" style="margin-bottom : 1.8%;">
                <img class="materialboxed liquidPic hoverable" data-caption="A pic" src="images/${file}"/>
            </div>
        </c:forEach>
    </div>


</div>
<jsp:include page="_pageSections/footer.jsp" />
