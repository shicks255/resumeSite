<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<jsp:useBean id="fileList" type="java.util.List<java.lang.String>" scope="request"/>
<jsp:useBean id="imagePath" type="java.lang.String" scope="request"/>

    <script>
        $(document).ready(function()
        {

        });

        function openPicture(fileName)
        {
            var cutHere = fileName.indexOf("_small");
            var bigPicNameStart = fileName.slice(0,cutHere);
            var bigPicNameEnd = fileName.slice(cutHere+6);

            var newFileName = bigPicNameStart + bigPicNameEnd;
            var servletContext = "${pageContext.request.contextPath}";
            window.open(servletContext + '/images/' + newFileName, '');
        }
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
                <a href="#"><img onclick="openPicture('${file}');" class="liquidPic hoverable" data-caption="A pic" src="${pageContext.request.contextPath}/images/${file}"/></a>
            </div>
        </c:forEach>
    </div>
    </div>


</div>
<jsp:include page="_pageSections/footer.jsp" />
