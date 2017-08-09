<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="courseWorkList" type="java.util.List<com.steven.hicks.entities.Coursework>" scope="request"/>

<c:if test="${!empty adminComputer}">
    <jsp:useBean id="adminComputer" type="java.lang.String" scope="request"/>
</c:if>

<html>
<head>
    <title>Title</title>

    <script>

        $(document).ready( function()
        {
        });

        function deleteThisCoursework(fileName)
        {
            $.post("academic?action=deleteCoursework&fileName=" + fileName,
            function(data){
               location.reload();
            });
        }

    </script>

</head>
<body>

<div class="popupContent">
        <a class="waves-effect waves-light btn closeButton" value="Cancel" onclick="closePopups();">
            Close
            <i class="material-icons right">close</i>
        </a>
        <c:set var="index" value="${0}"/>
        <table>
            <thead>
            <tr>
                <th>File</th>
                <th>Notes</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach var="coursework" items="${courseWorkList}">
                <c:set var="index" value="${index +1}"/>
                <tr>
                    <td>${index}. <a href="academic?action=printCoursework&courseworkName=${coursework.fileName}"><c:out value="${coursework.fileName}"/></a></td>
                    <td>${coursework.additionalNotes}</td>
                    <td>
                        <c:if test="${!empty adminComputer}">
                            <button class="waves-effect waves-light btn" value="Delete" onclick="deleteThisCoursework('${coursework.fileName}');">Delete
                                <i class="material-icons right">delete</i>
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
</div>

</body>
</html>
