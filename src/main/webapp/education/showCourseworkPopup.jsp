<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="courseWorkList" type="java.util.List<com.steven.hicks.entities.Coursework>" scope="request"/>
<jsp:useBean id="course"         type="com.steven.hicks.entities.AcademicCourse" scope="request"/>

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
    <div class="popupHeader">
        <span style="margin: auto;">Coursework for <b>${course.courseName}</b></span>
        <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
    </div>
    <div class="popupContainer">
        <br/><br/>
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

            <c:if test="${empty courseWorkList}">
                <tr>
                    <td colspan="3">No coursework uploaded for this class</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>

</body>
</html>
