<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="courseWorkList" type="java.util.List<com.steven.hicks.entities.Coursework>" scope="request"/>

<html>
<head>
    <title>Title</title>

    <script>

        $(document).ready( function()
        {
        });

        function closePopup()
        {
            $( '#editCourseDiv' ).removeClass('popup').addClass('hiddenDiv');
            location.reload();
        }

    </script>

</head>
<body>

<div class="popupContent">
        <table>
            <c:forEach var="coursework" items="${courseWorkList}">
                <a href="academic?action=printCoursework&courseworkName=${coursework.fileName}"><c:out value="${coursework.fileName}"/></a>
            </c:forEach>
        </table>
</div>

</body>
</html>
